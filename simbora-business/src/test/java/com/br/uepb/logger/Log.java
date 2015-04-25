package com.br.uepb.logger; 

import org.apache.log4j.Logger;

import com.br.uepb.business.PerfilBusiness;

public class Log {
	
	public static void main(String[] args) {		
		Logger log = Logger.getLogger(PerfilBusiness.class);
		
		log.error("ERRO");
		PerfilBusiness.logger.debug("DEBUG");
		PerfilBusiness.logger.info("INFO");
		PerfilBusiness.logger.error("ERRO");
		 
	}

}
