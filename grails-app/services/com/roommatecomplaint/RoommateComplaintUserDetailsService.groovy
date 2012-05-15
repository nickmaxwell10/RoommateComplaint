package com.roommatecomplaint

import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUserDetailsService
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException

class RoommateComplaintUserDetailsService implements GrailsUserDetailsService {
	
	static transactional = 'mongo'
	
   UserDetails loadUserByUsername(String username, boolean loadRoles)
			throws UsernameNotFoundException {
	  return loadUserByUsername(username)
   }

   UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	  User.withTransaction { status ->

		 User user = User.findByUid(username)
		 if (!user) throw new UsernameNotFoundException(
					  'User not found', username)

		 def authorities = user.authorities.collect {
			 new GrantedAuthorityImpl(it.authority)
		 }
		 
		 RoommateComplaintUserDetails gu = new RoommateComplaintUserDetails(user.id, user.uid, user.accessToken, user.name,
			user.dateCreated, authorities)
		 return gu
		 
	  }
   }
}
