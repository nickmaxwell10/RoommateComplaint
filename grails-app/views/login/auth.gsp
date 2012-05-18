<html>
<head>
	<meta name="layout" content="main"/>
    <r:require modules="custom-bootstrap, bootstrap-responsive-css"/>
</head>

<body>
	<g:render template="/templates/navbar" />
	<div class="container topContainer">
		<div class="hero-unit">
	        <h1>Roommate Complaint</h1>
	        <p>Welcome to Roommate Complaint!</p>
	        <p>A social-friendly way to poke fun at your messy roommates.</p>
			<br/><br/>
			<g:if test='${flash.message}'>
				<div class='login_message'>${flash.message}</div>
			</g:if>
			<g:link action="fbLogin">
				<r:img dir="images" file="facebook_login.png" width="240" height="37"/>
			</g:link>
		</div>
	</div>
</body>
</html>
