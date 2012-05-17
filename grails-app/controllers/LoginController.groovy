import grails.converters.JSON

import javax.servlet.http.HttpServletResponse

import org.bson.types.ObjectId
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

import org.springframework.security.authentication.AccountExpiredException
import org.springframework.security.authentication.CredentialsExpiredException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.LockedException
import org.springframework.security.core.context.SecurityContextHolder as SCH
import org.springframework.security.web.WebAttributes
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.multipart.MultipartFile

import com.roommatecomplaint.Role
import com.roommatecomplaint.User

class LoginController {
	
	/**
	 * Dependency injection for the authenticationTrustResolver.
	 */
	def authenticationTrustResolver

	/**
	 * Dependency injection for the springSecurityService.
	 */
	def springSecurityService
	
	/**
	* Injected UserService
	*/
	def userService
	
	/**
	 * Injected property from conf/spring/resources.groovy
	 */
	def facebookKeys

	/**
	 * Default action; redirects to 'defaultTargetUrl' if logged in, /login/auth otherwise.
	 */
	def index = {
		if (springSecurityService.isLoggedIn()) {
			def model = [:]
			model.user = springSecurityService.currentUser
			render(view:'/index', model: model, params: null)
		} else {
			//redirect action: 'auth', params: params
			auth(params:params)
		}
	}
	
	/**
	* Show the login page.
	*/
   def auth = {

	   def config = SpringSecurityUtils.securityConfig

	   if (springSecurityService.isLoggedIn()) {
		   redirect uri: config.successHandler.defaultTargetUrl
		   return
	   }
	   render(view:'auth')
    }
	
	def fbLogin = {
		def redirectURI = createLink(absolute:true, action: 'fbCallback')
		redirect(url: "https://www.facebook.com/dialog/oauth?client_id=${facebookKeys.clientCode}&redirect_uri=${redirectURI}")
	}
	
	def fbCallback = {
		// Request an access token by fetching url with given code.
		def redirectURI = createLink(absolute:true, action: 'fbCallback')
		def rootUrl = "https://graph.facebook.com/oauth/access_token?client_id=${facebookKeys.clientCode}&client_secret=${facebookKeys.clientSecret}"
		String accessTokenURL = rootUrl + "&redirect_uri=${redirectURI}&code=${params.code.encodeAsURL()}"
		String result = new URL(accessTokenURL).getText()
		// Access token is first key=value pairs value.
		String accessToken = result.tokenize("&")[0].tokenize("=")[1]
		def meURL = "https://graph.facebook.com/me?access_token=" + accessToken
		def meJSON = new URL(meURL).getText()
		def me = JSON.parse(meJSON)
		def userId = me["id"]
		User user = User.findByUid(userId)
		if(!user) {
			user = new User()
			user.uid = userId
			user.accessToken = accessToken
			user.name = me["name"]
			user.dateCreated = new Date()
			user.authorities = new HashSet<Role>()
			user.authorities.add(Role.findByAuthority("ROLE_USER"))
			if(!userService.save(user)) {
				flash.message = "Unable to save new user"
				redirect(action:"auth")
				return
			}
			
		} 
		springSecurityService.reauthenticate user.uid
		if (springSecurityService.isLoggedIn()) {
			redirect(action:"index")
			return
		}
	}

	/**
	 * The redirect action for Ajax requests.
	 */
	def authAjax = {
		response.setHeader 'Location', SpringSecurityUtils.securityConfig.auth.ajaxLoginFormUrl
		response.sendError HttpServletResponse.SC_UNAUTHORIZED
	}

	/**
	 * Show denied page.
	 */
	def denied = {
		if (springSecurityService.isLoggedIn() &&
				authenticationTrustResolver.isRememberMe(SCH.context?.authentication)) {
			// have cookie but the page is guarded with IS_AUTHENTICATED_FULLY
			redirect action: 'full', params: params
		}
	}

	/**
	 * Login page for users with a remember-me cookie but accessing a IS_AUTHENTICATED_FULLY page.
	 */
	def full = {
		def config = SpringSecurityUtils.securityConfig
		render view: 'auth', params: params,
			model: [hasCookie: authenticationTrustResolver.isRememberMe(SCH.context?.authentication),
			        postUrl: "${request.contextPath}${config.apf.filterProcessesUrl}"]
	}

	/**
	 * Callback after a failed login. Redirects to the auth page with a warning message.
	 */
	def authfail = {

		def username = session[UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_USERNAME_KEY]
		String msg = ''
		def exception = session[WebAttributes.AUTHENTICATION_EXCEPTION]
		if (exception) {
			if (exception instanceof AccountExpiredException) {
				msg = g.message(code: "springSecurity.errors.login.expired")
			}
			else if (exception instanceof CredentialsExpiredException) {
				msg = g.message(code: "springSecurity.errors.login.passwordExpired")
			}
			else if (exception instanceof DisabledException) {
				msg = g.message(code: "springSecurity.errors.login.disabled")
			}
			else if (exception instanceof LockedException) {
				msg = g.message(code: "springSecurity.errors.login.locked")
			}
			else {
				msg = g.message(code: "springSecurity.errors.login.fail")
			}
		}

		if (springSecurityService.isAjax(request)) {
			render([error: msg] as JSON)
		}
		else {
			flash.message = msg
			redirect action: 'auth', params: params
		}
	}

	/**
	 * The Ajax success redirect url.
	 */
	def ajaxSuccess = {
		render([success: true, username: springSecurityService.authentication.name] as JSON)
	}

	/**
	 * The Ajax denied redirect url.
	 */
	def ajaxDenied = {
		render([error: 'access denied'] as JSON)
	}
}
