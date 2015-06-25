<div class="container">
	<form:form modelAttribute="usuarioDomain" method="post">
		<div class="row">
			<div class="col-md-12">
				<a href="verUsuario.html?login=${usuarioDomain.login }"> <h1 class="page-head-line">${usuarioDomain.nome }</h1> </a>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading panel-title">Sobre ele</div>
					<div class="panel-body">

						<div class="panel-body">
						<p><h5>${usuarioDomain.nome } reside em ${usuarioDomain.endereco },
						<p>para entrar em contato com ele basta enviar e-mail atrav�s do endere�o
						<p>eletr�nico "${usuarioDomain.email }"</h5>
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
					<div class="panel-heading"><h4>Aqui s�o exibidas todas as caronas dispon�veis oferecidas por ele</h4></div>
					<div class="panel-body">
						<table class="table table-hover">
							<thead>
								
							</thead>
							<c:forEach items="${solicitacoes }" var="c">
								<tbody>
									<tr>
										<td class="col-md-1 font13">O usu�rio ${c.idSessao } solicitou uma vaga na carona ${c.idCarona }</td>
										<td class="col-md-1 font13"><a href="#">Saiba mais</a></td>
									</tr>
								</tbody>
							</c:forEach>
						</table>

					</div>
				</div>
			</div>
		</div>
 </form:form>
	</div>