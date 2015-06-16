<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Login</title>
<%@ include file="/WEB-INF/views/imports.jsp"%>
<%@ include file="/WEB-INF/views/includeTags.jsp"%>

</head>
<body>

	<!-- Content -->
	<div class="jumbotron">
	<div class="content-wrapper">
		<div class="container">
			<div class="row"></div>
			<div class="row">
				<div class="col-md-6 col-sm-3 col-xs-6">
					<h1>Cadastrar carona</h1>
				</div>
			</div>


			<form:form modelAttribute="caronaDomain" method="POST">
								
				<div class="form-group">
					<label>Cidade de origem: </label>
					<form:input path="origem" type="text" class="form-control"
						placeholder="Insira a origem" />
				</div>

				<div class="form-group">
					<label>Cidade de destino: </label>
					<form:input path="destino" type="text" class="form-control"
						placeholder="Insira a destino" />
				</div>

				<div class="form-group">
					<label>Data: </label>
					<form:input path="data" type="text" class="form-control"
						placeholder="Insira a data da viagem" />
				</div>

				<div class="form-group">
					<label>Hora: </label>
					<form:input path="hora" type="text" class="form-control"
						placeholder="Insira a horário de saída" />
				</div>

				<div class="form-group">
					<label>Vagas: </label>
					<form:input path="vagas" type="text" class="form-control"
						placeholder="Insira a quantidade de vagas" />
				</div>

				<div class="form-group">
					<label>É preferencial?: </label>
					<form:input path="ehPreferencial" type="text" class="form-control"
						placeholder="Insira a origem" />
				</div>
				
				<div class="form-group">
					<form:button type="submit" class="btn btn-default">Salvar</form:button>
				</div>
			</form:form>
		</div>
	</div>
</div>