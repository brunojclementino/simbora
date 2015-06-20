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
				<div class="panel panel-primary">
					<div class="panel-heading">Informações básicas</div>
					<div class="panel-body">

						<form:form modelAttribute="usuarioDomain" method="post">

							<div class="form-group font15">
								<label class="font15" for="login">Login: </label> 
								<label for="login">${usuarioDomain.login }</label>
							</div>

							<div class="form-group font15">
								<label>Nome: </label> <label for="nome">${usuarioDomain.nome }</label>
							</div>

							<div class="form-group font15">
								<label for="email">E-mail: </label>
								<form:label path="email">${usuarioDomain.email }</form:label>
							</div>

							<div class="form-group font15">
								<label>Endereço: </label> <label for="endereco">${usuarioDomain.endereco }</label>
							</div>
						</form:form>
					</div>
				</div>
			</div>
			
			<div class="col-lg-6">
				<div class="panel panel-primary">
					<div class="panel-heading">Caronas criadas</div>
					<div class="panel-body">
						<table class="table panel-primary">
							<thead>
								<tr>
									<th class="col-md-1 tab font13">Origem</th>
									<th class="col-md-1 tab font13">Destino</th>
									<th class="col-md-1 tab font13">Vagas</th>
									<th class="col-md-1 tab font13">Data</th>
								</tr>
							</thead>
							<c:forEach items="${carHist }" var="c">
								<tbody>
									<tr>
										<td class="col-md-1 font13">${c.origem }</td>
										<td class="col-md-1 font13">${c.destino }</td>
										<td class="col-md-1 font13">${c.vagas }</td>
										<td class="col-md-1 font13">${c.data }</td>
									</tr>
								</tbody>
							</c:forEach>
						</table>
					</div>

				</div>
			</div>
			
		</div>

		<!-- /.row -->
		<div class="row">
						<!-- /.col-lg-4 -->
			<div class="col-lg-6">
				<div class="panel panel-primary">
					<div class="panel-heading">Caronas solicitadas</div>
					<div class="panel-body">
						<table class="table table-hover">
							<thead>
								<tr>
									<th class="col-md-1 tab font13">Id carona</th>
									<th class="col-md-1 tab font13">Status</th>
									<th class="col-md-1 tab font13">Ponto encontro</th>
								</tr>
							</thead>
							<c:forEach items="${solicitacoes }" var="c">
								<tbody>
									<tr>
										<td class="col-md-1 font13">${c.idCarona }</td>
										<td class="col-md-1 font13">${c.status }</td>
										<td class="col-md-1 font13">${c.idPonto }</td>
									</tr>
								</tbody>
							</c:forEach>
						</table>

					</div>
				</div>
			</div>
			<!-- /.col-lg-6 -->
			<div class="col-lg-6">
				<div class="panel panel-primary">
					<div class="panel-heading">Caronas que não funcionaram</div>
					<div class="panel-body">
						<table class="table table-hover">
							<thead>
								<tr>
									<th class="col-md-1 tab font13">Origem</th>
									<th class="col-md-1 tab font13">Destino</th>
									<th class="col-md-1 tab font13">Vagas</th>
								</tr>
							</thead>
							<c:forEach items="${carNaoFunc }" var="c">
								<tbody>
									<tr>
										<td class="col-md-1 font13">${c.origem }</td>
										<td class="col-md-1 font13">${c.destino }</td>
										<td class="col-md-1 font13">${c.vagas }</td>
									</tr>
								</tbody>
							</c:forEach>
						</table>
					</div>
				</div>
				<!-- /.col-lg-4 -->
			</div>
			<!-- /.col-lg-4 -->
		</div>

	</div>