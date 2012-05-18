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
			<div class="hero-unit">
				</br>
				<div class="row">
					<div class="span1">
					 	<p>Plaintiff</p>
			        	<img src="https://graph.facebook.com/${_case?.plaintiff?.uid}/picture"/>
			        	</br>
			          	<small>${_case?.plaintiff?.name}</small>
			          	</br>
			          	<small><g:formatDate format="EEE, MMM d yyyy HH:mm:ss" date="${_case?.dateCreated}"/></small>
					</div>
			        <div class="offset1 span8">
			          <h2>${_case.plaintiffCaption}</h2>
			           <img src="${_case.plaintiffImgURL}" style="max-width:600px;max-height:1000px;">
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
