package com.br.uepb.constants;


/**
 * Usada para ser a excess�o da {@link SessaoDomain}.
 * @author Lucas Miranda e Bruno Clementino
 *
 */
public class SessaoException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public SessaoException(String mensagem) {
		super(mensagem);
	}
}
