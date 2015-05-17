<%@ include file="/WEB-INF/views/imports.jsp"%>
<%@ include file="/WEB-INF/views/includeTags.jsp"%>

<div class="container">
	<div class="row_principal">
		<div id="span_name_logo">

			<div class="title-site">
				<a href="http://localhost:8080/simbora-presentation/home/home.html" >Simbora</a>
			</div>

			<div id="logomarca">
				<a href="http://localhost:8080/simbora-presentation/home/home.html">
					<img src="../images/caronaLogo2.png" width="100" height="100">
				</a>
			</div>
		</div>


		<div id=dropdown>
			<div class="btn-group">
				<button type="button" class="btnUsuario_login"
					data-toggle="dropdown">
					<p class="manager_user">USUÁRIO
						<span class="caret"></span>
					</p>					
				</button>

				<ul class="dropdown-menu" role="menu">
					<li><a href="#" >Perfil</a></li>
					<li><a href="#">Sair</a></li>
				</ul>
			</div>
		</div>
		
	</div>
	
<!--  	<div class="dropup">
  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-expanded="true">
    Dropdown
    <span class="caret"></span>
  </button>
  <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu2">
    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Action</a></li>
    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Another action</a></li>
    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Something else here</a></li>
    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Separated link</a></li>
  </ul>
</div>-->