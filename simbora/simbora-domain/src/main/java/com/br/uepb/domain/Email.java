package com.br.uepb.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

	private String email;
	private String password;
	private String titulo;
	private String mensagem;

	private String emailRementente;
	private ArrayList<String> listaEmail = new ArrayList<String>();

	String destinatarios = "";
	
	public Email(){
		
	}
	
	/**
	 * Email, password, titulo, mensagem e lista
	 * 
	 * @param email
	 * @param password
	 * @param titulo
	 * @param mensagem
	 * @param listaEmail
	 */
	public void enviarEmail(final String email, final String password, String titulo,
			String mensagem, ArrayList<String> listaEmail) {
		Properties props = new Properties();
		/** Parâmetros de conexão com servidor Gmail */
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(email, password);
					}
				});

		/** Ativa Debug para sessão */
		session.setDebug(true);

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email)); // Remetente

			// Destinatário(s)
			
			for (int i = 0; i < listaEmail.size(); i++) {
				if (i == listaEmail.size() - 1) {
					destinatarios += listaEmail.get(i);
				} else
					destinatarios += listaEmail.get(i) + ", ";
			}
System.out.println(destinatarios);
			Address[] toUser = InternetAddress.parse(destinatarios);
			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject(titulo);
			message.setText(mensagem);
			/** Método para enviar a mensagem criada */
			Transport.send(message);

			System.out.println("Feito!!!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailRementente() {
		return emailRementente;
	}

	public void setEmailRementente(String emailRementente) {
		this.listaEmail.add(emailRementente);
	}

	public ArrayList<String> getListaEmail() {
		return this.listaEmail;
	}

	public void setListaEmail(ArrayList<String> listaEmail) {
		this.listaEmail = listaEmail;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
