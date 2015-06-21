<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Login</title>
<%@ include file="/WEB-INF/views/imports.jsp"%>
<%@ include file="/WEB-INF/views/includeTags.jsp"%>
</head>
<body>

	<!-- Content -->
	
	<div class="content-wrapper">
		<div class="container">
			<div class="row"></div>
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-6">
					<a href='<spring:url value="cadastrocarona.html"></spring:url>'>
						<div class="btn btn-default btn-size azul">
							<h4>Cadastrar carona</h4>
						</div>
					</a>
				</div>

				<div class="col-md-3 col-sm-3 col-xs-6">
					<a href='<spring:url value="buscarCarona.html"></spring:url>'>
						<div class="btn btn-default btn-size verde">
							<h4>Buscar carona</h4>
						</div>
					</a>
				</div>

				<div class="col-md-3 col-sm-3 col-xs-6">
					<a href="#">
						<div class="btn btn-default btn-size amarelo">
							<h4>Solicitações</h4>
						</div>
					</a>
				</div>

				<div class="col-md-3 col-sm-3 col-xs-6">
					<a href="perfil.html">
						<div class="btn btn-default btn-size vermelho">
							<h4>Perfil</h4>
						</div>
					</a>
				</div>
			</div>
		</div>

		<!-- Caronas cadastradas -->
		<!--main-->
		<div class="container" id="main">
			<div class="row">
				<div class="col-md-4 col-sm-6">
					<div class="panel panel-default">
						<div class="panel-heading">
							<a href="#" class="pull-right">Veja mais!</a>
							<h4>Histórico das suas caronas</h4>
						</div>
						<div class="panel-body">
							<div class="list-group">
								<a href="#"
									class="list-group-item">Caronas cadastradas</a> <a
									href="http://bootply.com/tagged/datetime"
									class="list-group-item">Cancelamentos</a> <a
									href="#"
									class="list-group-item">Caronas que funcionaram</a>
							</div>
						</div>
					</div>
					<div class="well"></div>

				</div>


				<div class="col-md-7 col-sm-6">

					<c:forEach items="${lstCaronas }" var="carona">
					
						<div class="panel panel-default">
						<div class="panel-heading">
							<a href='<spring:url value="solicitarVagaCarona.html?idCarona=${carona.id }"></spring:url>'
							 class="pull-right">Veja mais</a>
							<h4>Carona ${carona.id }</h4>
						</div>

						<div class="panel-body">
							
							<div class="clearfix"></div>
							<h5>Saindo de ${carona.origem } no
							dia ${carona.data } as ${carona.hora }hr com destino à ${carona.destino }.</h5>
						</div>
					</div>
					
				</c:forEach>

				</div>