<div class="navbar navbar-fixed-top">
  <div class="navbar-inner">
    <div class="container">
      <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </a>
      <div class="btn-group pull-right">
      	<g:if test="${user != null}">
        <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
          <i class="icon-user"></i> ${user.name}
          
          <span class="caret"></span>
        </a>
        <ul class="dropdown-menu">
          <li><a href="#">Profile</a></li>
          <li class="divider"></li>
          <li><g:link controller="logout">Sign Out</g:link></li>
        </ul>
        </g:if>
      </div>
      <g:link class="brand" controller="login">Roommate Complaint</g:link>
      <div class="nav-collapse">
        <ul class="nav">
          <li class="active"><g:link controller="case"><i class="icon-book"></i> Cases</g:link></li>
          <li><g:link controller="case" action="newCase"><i class="icon-plus-sign"></i> New Case</g:link></li>
        </ul>
      </div><!--/.nav-collapse -->
    </div>
  </div>
</div>