package com.br.uepb.constants;


/**
 * Esta classe � usada para identificar a excess�o do {@link UsuarioDomain}
 * @author Lucas Miranda
 *
 */
public class UsuarioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioException(String mensagem) {
		super(mensagem);
	}
}
