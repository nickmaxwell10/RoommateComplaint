<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
    	<r:require modules="custom-bootstrap, bootstrap-responsive-css"/>
    	<r:script>
          $(document).ready(function() {
			  if (window.location.hash == '#_=_') {
			  	window.location = window.location.href.substr(0, window.location.href.indexOf('#'));
			  }
			});
      	</r:script>
	</head>
	<body>
		<g:render template="/templates/navbar" />
		
		<div class="container topContainer">

	      <h1>Bootstrap starter template</h1>
	      <p>Use this document as a way to quick start any new project.<br> All you get is this message and a barebones HTML document.</p>
	
	    </div>
	</body>
</html>
