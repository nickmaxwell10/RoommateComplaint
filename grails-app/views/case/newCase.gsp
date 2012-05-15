<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>New Case</title>
		<style type="text/css" media="screen">
			
		</style>
		<r:script>
          $(document).ready(function() {
			  if (window.location.hash == '#_=_') {
			  	window.location = window.location.href.substr(0, window.location.href.indexOf('#'));
			  }
			});
      	</r:script>
	</head>
	<body>
		<div id="page-body" role="main">
			<h1>New Case</h1>
			<p>${user.name} ${user.uid}</p>
			
		</div>
	</body>
</html>
