
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
				<tr class="linhas_login">
					<td class="colunas_loginNome">Nome:</td>
					<td class="colunas_login"><form:input path="nome"
							placeholder="Nome" /></td>
				</tr>

				<tr class="linhas_login">
					<td class="colunas_loginNome">Senha:</td>
					<td class="colunas_login"><form:password path="nome"
							placeholder="senha" /></td>
				</tr>

				<tr class="#colunas_botao_login">
					<th></th>
					<td id="colunas_botao_login"><form:button>Logar</form:button></td>
				</tr>
			</table>
		</div>
	</form:form>
</body>
</html>
