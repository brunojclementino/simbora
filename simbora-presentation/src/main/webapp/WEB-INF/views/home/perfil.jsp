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
				<a href="perfil.html"> <h1 class="page-head-line">Perfil</h1> </a>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading panel-title">Sobre voc�</div>
					<div class="panel-body">

						<div class="panel-body">
						<a href="#">	<h4>Visualize ou modifique suas informa��es</h4></a>
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading panel-title">Suas caronas</div>
						<div class="panel-body">
						<a href="#">	<h4>Aqui voc� pode consultar todas as caronas que voc� ofereceu</h4></a>
						</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading panel-title">Suas solicita��es</div>
						<div class="panel-body">
						<a href="#">	<h4>Esta op��o permite visualizar todas as solicita��es de carona feitas por voc�</h4></a>
						</div>
				</div>
			</div>
			
		</div>
		<!-- /.row -->
		<div class="row">
						<!-- /.col-lg-4 -->
			<div class="col-lg-6">
				<div class="panel panel-primary">
					<div class="panel-heading"><h4>Aqui s�o exibidas as solicita��es de caronas que outros usu�rios lhe fizeram
													e ainda n�o foram respondidas</h4></div>
					<div class="panel-body">
						<table class="table table-hover">
							<thead>
								
							</thead>
							<c:forEach items="${solicitacoes }" var="c">
								<tbody>
									<tr>
										<td class="col-md-1 font13">O usu�rio ${c.idSessao } solicitou uma vaga na carona ${c.idCarona }</td>
										<td class="col-md-1 font13">Status: ${c.status }</td>
										<td class="col-md-1 font13"><a href="requisicaoDeVagaNaCarona.html?idSolicitacao=${c.idSolicitacao }">Saiba mais</a></td>
									</tr>
								</tbody>
							</c:forEach>
						</table>

					</div>
				</div>
			</div>
		</div>

	</div>