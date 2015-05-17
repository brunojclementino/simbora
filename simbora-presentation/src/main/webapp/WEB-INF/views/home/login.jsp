
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Login</title>
<%@ include file="/WEB-INF/views/imports.jsp"%>
<%@ include file="/WEB-INF/views/includeTags.jsp"%>
</head>
<body>
	<form:form modelAttribute="usuarioDomain" method="post">
		<div class="login_simbora">
			
			<h1 id="login_title">Login</h1>
			
			<table class="tabela_login">
				
				<tr class="linhas_login_nome">
					<th class="colunas_loginNome">Nome:</th>
					<th class="colunas_login"><form:input class="formLogin" path="nome"
							placeholder="Nome" /></th>
				</tr>
	
				<tr class="linhas_login_senha">
					<th class="colunas_loginNome">Senha:</th>
					<th class="colunas_login"><form:password class="formLogin" path="nome"
							placeholder="senha" /></th>
				</tr>

				<tr class="#colunas_botao_login">
					<th></th>
					<th id="colunas_botao_login">
						<form:button id="btnlogin" value="#">Entrar</form:button>
					</th>
				</tr>
				<tr>
					<th></th>
					<th id="esqueciSenha"><a href="#">Esqueceu a senha?</a></th>
					
				</tr>
			</table>
		
		</div>
	</form:form>
</body>
</html>
