package com.br.uepb.logger; 

import com.br.uepb.business.PerfilBusiness;

public class Log {
	
	public static void main(String[] args) {		
		PerfilBusiness.logger.debug("DEBUG");
		PerfilBusiness.logger.info("INFO");
		PerfilBusiness.logger.error("ERRO");
		 
	}

}
