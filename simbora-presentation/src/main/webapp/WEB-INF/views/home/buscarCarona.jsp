<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Login</title>
<%@ include file="/WEB-INF/views/imports.jsp"%>
<%@ include file="/WEB-INF/views/includeTags.jsp"%>

</head>
<body>
	<div class="content-wrapper">
		<div class="container">
			<!-- Nav -->
			<nav class="navbar navbar-default">
				<div class="container-fluid">
					<div class="navbar-header nav-buscar">Buscar caronas</div>

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
		</div>
	</div>
	<div class="content-wrapper">
		<div class="container">
			<table class="table table-hover">
				<thead>
					<tr>
						<th class="col-md-3 tab">Origem</th>
						<th class="col-md-3 tab">Destino</th>
						<th class="col-md-1 tab">Vagas</th>
						<th class="col-md-1 tab">Hora</th>
						<th class="col-md-1 tab">Data</th>
						<th class="col-md-1 tab">Preferencia</th>
						<th class="col-md-1 tab">Ver mais</th>
					</tr>
				</thead>
				<c:forEach items="${lstCaronas }" var="car">
					<tbody>
						<tr>
							<td class="col-md-3">${car.origem }</td>
							<td class="col-md-3">${car.destino }</td>
							<td class="col-md-1">${car.vagas }</td>
							<td class="col-md-1">${car.hora }</td>
							<td class="col-md-1">${car.data }</td>
							<td class="col-md-1">${car.ehPreferencial }</td>
							<td class="col-md-1"><a
								href='<spring:url value="solicitarVagaCarona.html?idCarona=${car.id }"></spring:url>'
								class="glyphicon glyphicon-plus" /></td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
		</div>
	</div>