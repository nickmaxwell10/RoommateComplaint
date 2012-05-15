<html>
<head>
	<meta name='layout' content='main'/>
	<style type='text/css' media='screen'>
	
	</style>
</head>

<body>
	<div id='login'>
		<div class='inner' style="text-align:center">
			<h1 style="font-size:30px;color:brown;">Roommate Complaint</h1>
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
