<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Login</title>
<%@ include file="/WEB-INF/views/imports.jsp"%>
<%@ include file="/WEB-INF/views/includeTags.jsp"%>
</head>
<body>

	<!-- Content -->
	
	<div class="jumbotron content-wrapper">
		<div class="container">
			<div class="row"></div>
			<div class="row">
				<div class="col-md-6 col-sm-3 col-xs-6">
					<h1>Cadastrar usuário</h1>
				</div>
			</div>
			
			<form:form modelAttribute="usuarioDomain" method="POST">
				
					<div class="form-group">
	    			<label>Nome: </label>
	    			<form:input path="nome" type="text" class="form-control" placeholder="Insira o Nome" />
					</div>
					
					<div class="form-group">
	    			<label>Login: </label>
	    			<form:input path="login" type="text" class="form-control" placeholder="Insira o Login" />
					</div>
					
					<div class="form-group">
	    			<label>Endereço: </label>
	    			<form:input path="endereco" type="text" class="form-control" placeholder="Insira o Endereço" />
					</div>
					
					<div class="form-group">
	    			<label>E-mail: </label>
	    			<form:input path="email" type="text" class="form-control" placeholder="Insira o E-mail" />
					</div>
					
					<div class="form-group">
	    			<label>Senha: </label>
	    			<form:input path="senha" type="password" class="form-control" placeholder="Insira a Senha" />
					</div>
					
					
					<form:button type="submit" class="btn btn-default">Salvar</form:button>
				</form:form>
		</div>
	</div>