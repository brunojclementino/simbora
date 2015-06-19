<%@ include file="/WEB-INF/views/imports.jsp"%>
<%@ include file="/WEB-INF/views/includeTags.jsp"%>

<nav class="container margem">
	<div class="col-md-8">

		<div class="col-md-8">
			<div id="logomarca">
				<a href='<spring:url value="paginaprincipal.html"></spring:url>'>
					<img src="../images/caronaLogo.png" width="100" height="100">
				</a>
			</div>
		</div>
		<div class="col-md-4">
			<div class="title-site">
				<a href='<spring:url value="paginaprincipal.html"></spring:url>'>Simbora</a>
			</div>
		</div>
	</div>
	<div class="col-md-4 left margem-top">
		<form:form methodParam="sessaoDomain" method="post">
			<div class="dropdown right">
				<button class="btn btn-default dropdown-toggle" type="button"
					id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="true">
					Olá ${sessao } <span class="caret"></span>
				</button>
				<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
					<li><a href="perfil.html"><span class="glyphicon glyphicon-user space"></span> Perfil</a></li>
					
					 <li role="separator" class="divider"></li>
					<li class="left all">
					<button class="btn all left">
						<span class="glyphicon glyphicon-log-out space left" aria-hidden="true"></span>
						 Sair
					</button>
					</li>
				</ul>
			</div>
		</form:form>
	</div>
</nav>

