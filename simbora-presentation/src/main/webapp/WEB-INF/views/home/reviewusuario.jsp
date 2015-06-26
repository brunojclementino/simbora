<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Login</title>
<%@ include file="/WEB-INF/views/imports.jsp"%>
<%@ include file="/WEB-INF/views/includeTags.jsp"%>

</head>
<body>
	<div class="content-wrapper">
		<div class="container">

			<table class="table table-hover text-left">
				<thead>
					<tr>
						<th class="col-md-3 tab">Login</th>
						<th class="col-md-3 tab">Seguras e tranquilas</th>
						<th class="col-md-1 tab">Faltou</th>
						<th class="col-md-3 tab">Não funcionou</th>
						<th class="col-md-1 tab">Funcionou</th>
						<th class="col-md-1 tab">Mais detalhes</th>

					</tr>
				</thead>
				<tbody>
				<c:forEach items="${listPerfil }" var="per" >
					<tr>
						<th class="col-md-3 left">${per.nomeUsuario }</th>
						<th class="col-md-3">${per.caronaSeguraTranquila }</th>
						<th class="col-md-3">${per.usuarioFaltou }</th>
						<th class="col-md-3">${per.caronaNaoFuncionaram }</th>
						<th class="col-md-3">${per.caronaFuncionou }</th>
						<th class="col-md-3">
							<a href="perfil.html" class="glyphicon glyphicon-plus"/></a>
						<th>						
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div class="content-wrapper">
		<div class="container"></div>
	</div>

</body>