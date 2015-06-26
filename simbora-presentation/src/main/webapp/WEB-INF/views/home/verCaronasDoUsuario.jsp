<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Login</title>
<%@ include file="/WEB-INF/views/imports.jsp"%>
<%@ include file="/WEB-INF/views/includeTags.jsp"%>

</head>
<body>
	
	<div class="row">
	<div class="col-md-1"></div>
			<div class="col-md-11">
				<a href="perfil.html"> <h1 class="page-head-line">Perfil</h1> </a>
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
								class="glyphicon glyphicon-plus"></a></td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
		</div>
	</div>