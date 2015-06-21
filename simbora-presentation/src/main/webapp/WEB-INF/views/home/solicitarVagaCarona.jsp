<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Login</title>
<%@ include file="/WEB-INF/views/imports.jsp"%>
<%@ include file="/WEB-INF/views/includeTags.jsp"%>

</head>
<body>
	<div class="content-wrapper">
		<div class="container">
			<div class="row">
				<div class="col-md-4 col-sm-3 col-xs-6">
					<div class="panel panel-default">
						<div class="panel-heading">Informa��es b�sicas - Dono da
							carona</div>
						<div class="panel-body">

							<form:form modelAttribute="usuarioDomain" method="post">

								<div class="form-group">
									<label for="login">Login: </label> <label for="login">${usuarioDomain.login }</label>
								</div>

								<div class="form-group">
									<label>Nome: </label> <label for="nome">${usuarioDomain.nome }</label>
								</div>

								<div class="form-group">
									<label for="email">E-mail: </label>
									<form:label path="email">${usuarioDomain.email }</form:label>
								</div>

								<div class="form-group">
									<label>Endere�o: </label> <label for="endereco">${usuarioDomain.endereco }</label>
								</div>
							</form:form>
						</div>
					</div>
				</div>

				<div class="col-md-4 col-sm-3 col-xs-6">
					<div class="panel panel-default">
						<div class="panel-heading">Informa��es da Carona</div>
						<div class="panel-body">

							<form:form modelAttribute="caronaDomain" method="post" >

								<div class="form-group">
									<label for="login">Origem: </label> 
									<label for="login">${caronaDomain.origem }</label>
								</div>

								<div class="form-group">
									<label>Destino: </label> 
									<label for="nome">${caronaDomain.destino }</label>
								</div>

								<div class="form-group">
									<label for="email">Hora: </label> 
									<label>${caronaDomain.hora }</label>
								</div>

								<div class="form-group">
									<label>Vagas: </label> 
									<label>${caronaDomain.vagas }</label>
								</div>
							</form:form>
						</div>
					</div>
				</div>

				<div class="col-md-4 col-sm-3 col-xs-6">
					<div class="panel panel-default">
						<div class="panel-heading">Solicita��o</div>
						<div class="panel-body">
						<p>Fa�a uma solicita��o da carona. Ap�s a solicita��o espere o dono da carona 
						aprovar a sua solicita��o.</p>
						<form:form methodParam="solicitacaoVagasDomain">
							<button class="btn btn-success" value="solicitarVagaCarona.html?idCarona=${idCarona }">Solicita��o carona</button>
						</form:form>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>