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
					<div class="panel-heading panel-title">Sobre você</div>
					<div class="panel-body">

						<div class="panel-body">
						<a href="#">	<h4>Visualize ou modifique suas informações</h4></a>
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading panel-title">Suas caronas</div>
						<div class="panel-body">
						<a href="#">	<h4>Aqui você pode consultar todas as caronas que você ofereceu</h4></a>
						</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading panel-title">Suas solicitações</div>
						<div class="panel-body">
						<a href="#">	<h4>Esta opção permite visualizar todas as solicitações de carona feitas por você</h4></a>
						</div>
				</div>
			</div>
			
		</div>
		<!-- /.row -->
		<div class="row">
						<!-- /.col-lg-4 -->
			<div class="col-lg-6">
				<div class="panel panel-primary">
					<div class="panel-heading"><h4>Aqui são exibidas as solicitações de caronas que outros usuários lhe fizeram
													e ainda não foram respondidas</h4></div>
					<div class="panel-body">
						<table class="table table-hover">
							<thead>
								
							</thead>
							<c:forEach items="${solicitacoes }" var="c">
								<tbody>
									<tr>
										<td class="col-md-1 font13">O usuário ${c.idSessao } solicitou uma vaga na carona ${c.idCarona }</td>
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