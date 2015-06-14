<%@ include file="/WEB-INF/views/imports.jsp"%>
<%@ include file="/WEB-INF/views/includeTags.jsp"%>

<nav class="container">
	<div class="row_principal">
		
		<div class="span_logo">
			<div id="logomarca">
				<a href="http://localhost:8080/simbora-presentation/home/home.html">
					<img src="../images/caronaLogo2.png" width="100" height="100">
				</a>
			</div>
		</div>
		<div class="span_name_logo">
			<div class="title-site">
				<a href="http://localhost:8080/simbora-presentation/home/home.html">Simbora</a>
			</div>
		
		</div>

		<!-- Usuario -->
		<div class="btn-group">
			<button type="button" class="btn btn-danger">Usuário</button>
			<button type="button" class="btn btn-danger dropdown-toggle"
				data-toggle="dropdown" aria-expanded="false">
				<span class="caret"></span> <span class="sr-only">Informações</span>
			</button>
			<ul class="dropdown-menu" role="menu">
				<li><a href="#">Perfil</a></li>
				<li><a href="#">Caronas</a></li>
				<li><a href="#">Mensagens</a></li>
				<li><a href="#">Sair</a></li>
			</ul>
		</div>
	</div>
</nav>
