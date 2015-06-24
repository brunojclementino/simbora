
<%@page import="javax.swing.Spring"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Login</title>
<%@ include file="/WEB-INF/views/imports.jsp"%>
<%@ include file="/WEB-INF/views/includeTags.jsp"%>

</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Simbora</h3>
					</div>
					
					<div class="panel-body">
						<form:form modelAttribute="usuarioDomain" method="post">
							<form:errors path="*" cssClass="errorblock" element="div" />
							${mensagem}
							<div class="form-group">
								<form:input class="form-control" path="login" name="login"
									placeholder="Login" />
							</div>

							<div class="form-group">
								<form:input class="form-control" path="senha" name="password"
									type="password" value="" placeholder="senha" />
							</div>

							<button class="btn btn-lg btn-success btn-block ">Login</button>
						</form:form>
					</div>

					<div class="form-group center  font18">
						<a class="label label-default" href='<spring:url value="cadastrousuario.html"></spring:url>'>
							<spring:message code="home.login.cadastrousuario"/>
						</a>
					</div>
				</div>
			</div>

		</div>
	</div>

</body>
</html>
