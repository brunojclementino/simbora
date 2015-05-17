package com.br.uepb.constants;


/**
 * Essa classe � usada para a excess�o do {@link PerfilDomain} do usu�rio.
 * @author Lucas Miranda
 *
 */
public class PerfilException extends Exception {	

	private static final long serialVersionUID = 1L;

	public PerfilException(String msg) {
		super(msg);
	}
}