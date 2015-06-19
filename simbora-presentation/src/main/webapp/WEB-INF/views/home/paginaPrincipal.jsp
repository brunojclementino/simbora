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
					<a href="#">
						<div class="btn btn-default btn-size verde">
							<h4>Buscar carona</h4>
						</div>
					</a>
				</div>

				<div class="col-md-3 col-sm-3 col-xs-6">
					<a href="#">
						<div class="btn btn-default btn-size amarelo">
							<h4>Caronas solicitadas</h4>
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

				<div class="col-md-4 col-sm-6">

					<div class="panel panel-default">
						<div class="panel-heading">
							<a href="#" class="pull-right">Veja mais</a>
							<h4>Carona 01</h4>
						</div>

						<div class="panel-body">
							<p>
								<img src="//placehold.it/150x150" class="img-circle pull-right">
								<a href="#">Carona 02</a>
							</p>
							<div class="clearfix"></div>
							<hr>
							Origem: Campina Grande, destino: João Pessoa. Saída as 14hrs no
							dia 20/10/2015.
						</div>
					</div>

					<div class="panel panel-default">
						<div class="panel-heading">
							<a href="#" class="pull-right">Veja mais</a>
							<h4>Carona 03</h4>
						</div>
						<div class="panel-body">
							<img src="//placehold.it/150x150" class="img-circle pull-right">
							<a href="#">Steve Jobs</a>
							<div class="clearfix"></div>
							<hr>
							<p>Saida de João Pessoa para Campina Grande as 20 hrs.</p>
							<hr>
						</div>
					</div>


				</div>
				<div class="col-md-4 col-sm-6">
					<div class="panel panel-default">
						<div class="panel-heading">
							<a href="#" class="pull-right">Veja mais</a>
							<h4>Carona 02</h4>
						</div>
						<div class="panel-body">
							<img src="//placehold.it/150x150" class="img-circle pull-right">
							<a href="#">Zuckerberg</a>
							<div class="clearfix"></div>
							<hr>
							<p>Saída de California para New York as 20 hrs.</p>
							<hr>
						</div>
					</div>


					<div class="panel panel-default">
						<div class="panel-heading">
							<a href="#" class="pull-right">Veja mais</a>
							<h4>Carona 04</h4>
						</div>
						<div class="panel-body">
							<img src="//placehold.it/150x150" class="img-circle pull-right">
							<a href="#">Bill</a>
							<div class="clearfix"></div>
							<hr>
							<p>Saida de João Pessoa para Campina Grande as 20 hrs.</p>
							<hr>
						</div>
					</div>

				</div>