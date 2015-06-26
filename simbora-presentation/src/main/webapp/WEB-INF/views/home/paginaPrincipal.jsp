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
					<a href="perfil.html">
						<div class="btn btn-default btn-size azul">
							<h4>Perfil</h4>
						</div>
					</a>
				</div>

				<div class="col-md-3 col-sm-3 col-xs-6">
					<a href='<spring:url value="cadastrocarona.html"></spring:url>'>
						<div class="btn btn-default btn-size verde">
							<h4>Oferecer carona</h4>
						</div>
					</a>
				</div>

				<div class="col-md-3 col-sm-3 col-xs-6">
					<a href='<spring:url value="buscarCarona.html"></spring:url>'>
						<div class="btn btn-default btn-size amarelo">
							<h4>Buscar carona</h4>
						</div>
					</a>
				</div>

				<div class="col-md-3 col-sm-3 col-xs-6">
					<a href='<spring:url value="reviewusuario.html"></spring:url>'>
						<div class="btn btn-default btn-size vermelho">
							<h4>Outras pessoas</h4>
						</div>
					</a>
				</div>


			</div>
		</div>

		<!-- Caronas cadastradas -->
		<!--main-->
		<div class="container" id="main">
			<div class="row">

				<h5 class="col-md-12 textoazul panel-title panel-heading">A
					seguir você encontra algumas caronas recentemente adicionadas por
					você ou outras pessoas</h5>
				<br>
				<div class="col-md-4 col-sm-12">

					<h3>Caronas intermunicipais</h3>
					<c:forEach items="${lstCaronas }" var="carona">

						<div class="panel panel-default">
							<div class="panel-heading">
								<a class="panel-title"
									href='verUsuario.html?login=${carona.idUsuario }'> <span
									class="glyphicon glyphicon-user" aria-hidden="true"></span>
									${carona.idUsuario }
								</a>
							</div>

							<div class="panel-body">
								<h5>
									<p>Código: ${carona.id }</p>
									<p>${carona.vagas }vagas para o dia ${carona.data } às
										${carona.hora } horas saindo de ${carona.origem } com destino
										a ${carona.destino }</p>
								</h5>
							</div>
							<div class='panel-footer'>
								<a href='solicitarVagaCarona.html?idCarona=${carona.id }'>Saiba
									mais sobre a carona</a>
							</div>

						</div>

					</c:forEach>
				</div>

				<div class="col-md-4 col-sm-12">

					<h3>Caronas municipais</h3>
					<c:forEach items="${lstCaronasMunicipais }" var="carona">

						<div class="panel panel-default">
							<div class="panel-heading">
								<a class="panel-title"
									href='verUsuario.html?login=${carona.idUsuario }'> <span
									class="glyphicon glyphicon-user" aria-hidden="true"></span>
									${carona.idUsuario }
								</a>
							</div>

							<div class="panel-body">
								<h5>
									<p>Código: ${carona.id }</p>
									<p>Esta carona ocorre em ${carona.caronaMunicipal.cidade }.</p>
									<p>${carona.vagas }vagas para o dia ${carona.data } às
										${carona.hora } horas saindo de ${carona.origem } com destino
										a ${carona.destino }</p>
								</h5>
							</div>
							<div class='panel-footer'>
								<a href='solicitarVagaCarona.html?idCarona=${carona.id }'>Saiba
									mais sobre a carona</a>
							</div>

						</div>

					</c:forEach>
				</div>

				<div class="col-md-4 col-sm-12">
					<h3>Caronas relampagos</h3>

					<c:forEach items="${lstCaronasRelampagos }" var="carona">

						<div class="panel panel-default">
							<div class="panel-heading">
								<a class="panel-title"
									href='verUsuario.html?login=${carona.idUsuario }'> <span
									class="glyphicon glyphicon-user" aria-hidden="true"></span>
									${carona.idUsuario }
								</a>
							</div>

							<div class="panel-body">
								<h5>
									<p>Código: ${carona.id }</p>
									<p>${carona.vagas }vagas para o dia ${carona.data } às
										${carona.hora } horas saindo de ${carona.origem } com destino
										a ${carona.destino } e volta no dia
										${carona.caronaRelampago.dataVolta }</p>
								</h5>
							</div>
							<div class='panel-footer'>
								<a href='solicitarVagaCarona.html?idCarona=${carona.id }'>Saiba
									mais sobre a carona</a>
							</div>

						</div>

					</c:forEach>
				</div>