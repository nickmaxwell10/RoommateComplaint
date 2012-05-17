<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<r:require modules="jquery-ui"/>
		<title>New Case</title>
		<style type="text/css" media="screen">
			
		</style>
		<r:script>
          	$(document).ready(function() {
				if (window.location.hash == '#_=_') {
			  		window.location = window.location.href.substr(0, window.location.href.indexOf('#'));
			  	}
			  	
			  	$( "#defendantName" ).autocomplete({
					source: '${grailsApplication.config.grails.serverURL}/ajax/filterFriends',
					search: function(event, ui) { 
				    	$('#defendantSpinner').show();
				   	},
				   	open: function(event, ui) {
				    	$('#defendantSpinner').hide();
				   	},
				   	select: function(event, ui) {
				   		$('#defendantId').val(ui.item.id);
				   	}
				});
			  
			});
      	</r:script>
	</head>
	<body>
		<div id="page-body" role="main">
			<h1>New Case</h1>
			<p>${user.name} ${user.uid}</p>
			<g:hasErrors bean="${newCase}">
			  <ul>
			   <g:eachError var="err" bean="${newCase}">
			       <li>${err}</li>
			   </g:eachError>
			  </ul>
			</g:hasErrors>
			<g:form action="newCase" enctype="multipart/form-data">
				<g:render template="/templates/case/form" />
				<g:hiddenField id="defendantId" name="defendantId"/>
			</g:form>
		</div>
	</body>
</html>
