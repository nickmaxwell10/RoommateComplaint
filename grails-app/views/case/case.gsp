<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Case</title>
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
			<h1>Case</h1>
			<p>${user.name} ${user.uid}</p>
			<table style="margin-left: auto;margin-right: auto;">
				<tr>
					<td>Case Number</td>
					<td>${_case.caseNumber}</td>
				</tr>
				<tr>
					<td>Plaintiff Caption</td>
					<td>${_case.plaintiffCaption}</td>
				</tr>
				<tr>
					<td colspan="2"><img src="${_case.plaintiffImgURL}"></td>
				</tr>
			</table>
		</div>
	</body>
</html>
