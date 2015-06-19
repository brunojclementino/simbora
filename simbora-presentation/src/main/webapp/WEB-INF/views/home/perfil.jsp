<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Login</title>
<%@ include file="/WEB-INF/views/imports.jsp"%>
<%@ include file="/WEB-INF/views/includeTags.jsp"%>
</head>
<body>


	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h1 class="page-head-line">Perfil</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">Informações básicas</div>
					<div class="panel-body">
						
						<form:form modelAttribute="usuarioDomain" method="post">
						
							<div class="form-group">
								<label for="login">Login: </label> 
								<label for="login">${sessao }</label>
							</div>

							<div class="form-group">
								<label>Nome: </label> 
								<label for="nome">${usuario.nome }</label>
							</div>

							<div class="form-group">
								<label for="email">E-mail: </label> 
								<form:label path="email">${usuario.email }</form:label>
							</div>

							<div class="form-group">
								<label>Endereço: </label> 
								<label for="endereco">${usuario.endereco }</label>
							</div>
						</form:form>
					</div>
				</div>
			</div>

			<div class="panel left">
				<form class="navbar-form navbar-left" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search">
					</div>
					<button type="submit" class="btn btn-default">Pesquisar</button>
				</form>
			</div>

			<div class="col-lg-4">
					<div class="panel-heading">Resultado da Busca</div>
					<div class="panel-body">
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
							Vestibulum tincidunt est vitae ultrices accumsan. Aliquam ornare
							lacus adipiscing, posuere lectus et, fringilla augue.</p>
					</div>
			</div>

		</div>

		<!-- /.row -->
		<div class="row">
			<div class="col-lg-4">
				<div class="panel panel-green">
					<div class="panel-heading">Caronas de sucesso</div>
					<div class="panel-body">
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
							Vestibulum tincidunt est vitae ultrices accumsan. Aliquam ornare
							lacus adipiscing, posuere lectus et, fringilla augue.</p>
					</div>
					<div class="panel-footer">Panel Footer</div>
				</div>
			</div>
			<!-- /.col-lg-4 -->
			<div class="col-lg-4">
				<div class="panel panel-primary">
					<div class="panel-heading">Caronas solicitadas</div>
					<div class="panel-body">
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
							Vestibulum tincidunt est vitae ultrices accumsan. Aliquam ornare
							lacus adipiscing, posuere lectus et, fringilla augue.</p>
					</div>
					<div class="panel-footer">Panel Footer</div>
				</div>
			</div>
			<!-- /.col-lg-4 -->
			<div class="col-lg-4">
				<div class="panel panel-red">
					<div class="panel-heading">Caronas que não funcionaram</div>
					<div class="panel-body">
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
							Vestibulum tincidunt est vitae ultrices accumsan. Aliquam ornare
							lacus adipiscing, posuere lectus et, fringilla augue.</p>
					</div>
					<div class="panel-footer">Panel Footer</div>
				</div>
				<!-- /.col-lg-4 -->
			</div>
			<!-- /.col-lg-4 -->
		</div>

	</div>