
<%@page import="javax.swing.Spring"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Login</title>
<%@ include file="/WEB-INF/views/imports.jsp"%>
<%@ include file="/WEB-INF/views/includeTags.jsp"%>

</head>
<body>
	<form:form modelAttribute="usuarioDomain" method="post" class="container">

		<table>
			<h1 id="login_title">Login</h1>
			<tr>
				<th class="colunas_loginNome">Nome:</th>
				<th class="colunas_login"><form:input class="formLogin"
						path="nome" placeholder="Nome" /></th>
			</tr>
			<tr>
				<th class="colunas_loginNome">Senha:</th>
				<th class="colunas_login"><form:password class="formLogin"
						path="nome" placeholder="senha" /></th>
			</tr>
			<tr>
				<th><a href="#">Esqueceu a senha?</a></th>
				<th id="colunas_botao_login">
					<form:button class="btn-success">Entrar</form:button>
				</th>
			
			</tr>
		</table>
	</form:form>
</body>
</html>
