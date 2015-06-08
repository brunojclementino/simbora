//package com.br.uepb.junit;
//
//import org.junit.Test;
//
//import com.br.uepb.domain.Email;
//
//public class EnviarEmailTest {
//
//	Email email;
//	@Test
//	public void enviarEmail() {
//		email = new Email();
//		
//		email.setEmail("simboracarona@gmail.com");
//		
//		email.setEmailRementente("lucasmirandadourado@gmail.com");
//		email.setEmailRementente("lucas_jd16@hotmail.com");
//		email.setEmailRementente("uepb.bruno@gmail.com");
//		
//		email.setMensagem("Vai funcionar!");
//		email.setTitulo("Teste com o JMail.");
//		email.setPassword("simboranegada");
//		
//		email.enviarEmail(email.getEmail(), email.getPassword(), email.getTitulo(), email.getMensagem(), email.getListaEmail());
//	}
//
//}
