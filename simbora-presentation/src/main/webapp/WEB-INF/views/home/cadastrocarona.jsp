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

				<div class="tabbable">
					<!-- Only required for left/right tabs -->
					<ul class="nav nav-tabs">
						<li class="active"><a href="#tab1" data-toggle="tab">Carona</a></li>
						<li><a href="#tab2" data-toggle="tab">Municipal</a></li>
						<li><a href="#tab3" data-toggle="tab">Relampago</a></li>
						
					</ul>
					<div class="tab-content">
						<!-- Carona -->
						<div class="tab-pane active" id="tab1">

							<div class="row">
								<h2
									title="Crie uma carona. Neste cadastro deverá ser feito para viagens de uma cidade para outra. Caso queira criar para carona dentro da cidade clique no Municipal.">Carona</h2>
							</div>

							<form:form modelAttribute="caronaDomain" method="POST"
								class="widthForm" action="cadastrocarona.html">

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
									<div class="hero-unit">
										<form:input path="data" class="form-control" type="text"
											placeholder="Selecione a data" id="data"></form:input>
									</div>
								</div>

								<div class="form-group">
									<label>Hora: </label>
									<div id="datetimepicker3" class="input-append">
										<form:input path="hora" type="text"
											placeholder="Insira a horário de saída" class="form-control" />

									</div>
								</div>

								<div class="form-group">
									<label>Vagas: </label>
									<form:input path="vagas" type="text" class="form-control"
										placeholder="Insira a quantidade de vagas" />
								</div>

								<div class="form-group">
									<label>É preferencial?: </label>
									<form:select path="ehPreferencial" class="form-control"
										placeholder="Insira a origem">
										<form:option value="false">False</form:option>
										<form:option value="true">True</form:option>
									</form:select>

								</div>

								<div class="form-group">
									<form:button type="submit" class="btn btn-default">Salvar</form:button>
								</div>
							</form:form>

						</div>
						<div class="tab-pane" id="tab2">
							<!-- Cadastro de carona Municipal -->
							<div class="row">
								<div class="col-md-6 col-sm-3 col-xs-6">
									<h3>Municipal</h3>
								</div>
							</div>


							<form:form modelAttribute="caronaDomain" method="POST"
								class="widthForm" action="cadastrocaronamunicipal.html">
								<!-- Mudar o path da cidade! -->
								<div class="form-group">
									<label>Cidade: </label>
									<form:input path="caronaMunicipal.cidade" type="text" class="form-control"
										placeholder="Qual a cidade da carona?" />
								</div>

								<div class="form-group">
									<label>Local de origem: </label>
									<form:input path="origem" type="text" class="form-control"
										title="Local de encontro" placeholder="Insira a origem" />
								</div>

								<div class="form-group">
									<label>Local de destino: </label>
									<form:input path="destino" type="text" class="form-control"
										placeholder="Insira a destino" />
								</div>

								<div class="form-group">
									<label>Data: </label>
									<div class="hero-unit">
										<form:input path="data" class="form-control" type="text"
											placeholder="Selecione a data" id="data"></form:input>
									</div>
								</div>

								<div class="form-group">
									<label>Hora: </label>
									<div id="datetimepicker3" class="input-append">
										<form:input path="hora" type="text"
											placeholder="Insira a horário de saída" class="form-control" />

									</div>
								</div>

								<div class="form-group">
									<label>Vagas: </label>
									<form:input path="vagas" type="text" class="form-control"
										placeholder="Insira a quantidade de vagas" />
								</div>

								<div class="form-group">
									<label>É preferencial?: </label>
									<form:select path="ehPreferencial" class="form-control"
										placeholder="Insira a origem">
										<form:option value="false">False</form:option>
										<form:option value="true">True</form:option>
									</form:select>

								</div>

								<div class="form-group">
									<form:button type="submit" class="btn btn-default">Salvar</form:button>
								</div>
							</form:form>

						</div>

						<div class="tab-pane" id="tab3">

							<div class="row">
								<div class="col-md-6 col-sm-3 col-xs-6">
									<h3>Relampago</h3>
								</div>
							</div>


							<form:form modelAttribute="caronaDomain" method="POST"
								class="widthForm" action="cadastrocaronamunicipal.html">

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
									<label>Data de ida: </label>
									<div class="hero-unit">
										<form:input path="data" class="form-control" type="text"
											placeholder="Selecione a data de ida" id="data"></form:input>
									</div>
								</div>

								<div class="form-group">
									<label>Data da volta: </label>
									<div class="hero-unit">
										<form:input path="caronaRelampago.dataVolta" class="form-control" type="text"
											placeholder="Selecione a data da volta." id="data"></form:input>
									</div>
								</div>

								<div class="form-group">
									<label>Hora: </label>
									<div id="datetimepicker3" class="input-append">
										<form:input path="hora" type="text"
											placeholder="Insira a horário de saída" class="form-control" />

									</div>
								</div>

								<div class="form-group">
									<label>Vagas: </label>
									<form:input path="vagas" type="text" class="form-control"
										placeholder="Insira a quantidade de vagas" />
								</div>

								<div class="form-group">
									<label>É preferencial?: </label>
									<form:select path="ehPreferencial" class="form-control"
										placeholder="Insira a origem">
										<form:option value="false">False</form:option>
										<form:option value="true">True</form:option>
									</form:select>

								</div>

								<div class="form-group">
									<form:button type="submit" class="btn btn-default">Salvar</form:button>
								</div>
							</form:form>

						</div>

					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Load jQuery and bootstrap datepicker scripts -->
	<script src="../js/jquery-1.11.1.min.js"></script>
	<script src="../js/bootstrap-datepicker.js"></script>


	<script type="text/javascript">
		$(document).ready(function() {
			$('#data').datepicker({
				format : "dd/mm/yyyy"
			});
		});
	</script>