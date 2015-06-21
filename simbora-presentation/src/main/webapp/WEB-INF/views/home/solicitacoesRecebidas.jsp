<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Login</title>
<%@ include file="/WEB-INF/views/imports.jsp"%>
<%@ include file="/WEB-INF/views/includeTags.jsp"%>

</head>
<body>
	<!-- Nav -->
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header nav-buscar">Solicitações recebidas</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">


				</ul>
				<form class="navbar-form navbar-left" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>

			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

	<div class="col-lg-6">
				<div class="panel panel-primary">
					<div class="panel-heading">Caronas criadas</div>
					<div class="panel-body">
						<table class="table panel-primary">
							<thead>
								<tr>
									<th class="col-md-1 tab font13">Origem</th>
									<th class="col-md-1 tab font13">Destino</th>
									<th class="col-md-1 tab font13">Vagas</th>
									<th class="col-md-1 tab font13">Data</th>
								</tr>
							</thead>
							<c:forEach items="${carHist }" var="c">
								<tbody>
									<tr>
										<td class="col-md-1 font13">${c.origem }</td>
										<td class="col-md-1 font13">${c.destino }</td>
										<td class="col-md-1 font13">${c.vagas }</td>
										<td class="col-md-1 font13">${c.data }</td>
									</tr>
								</tbody>
							</c:forEach>
						</table>
					</div>

				</div>
			</div>
			
		</div>