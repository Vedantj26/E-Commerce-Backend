package net.javaguide.springboot.entity;

public class EmailDTO {
	private String recipient;
	private String emailSub;
	private String emailBody;
	private String emailAttach;

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getEmailSub() {
		return emailSub;
	}

	public void setEmailSub(String emailSub) {
		this.emailSub = emailSub;
	}

	public String getEmailBody() {
		return emailBody;
	}

	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}

	public String getEmailAttach() {
		return emailAttach;
	}

	public void setEmailAttach(String emailAttach) {
		this.emailAttach = emailAttach;
	}

	public EmailDTO(String recipient, String emailSub, String emailBody, String emailAttach) {
		super();
		this.recipient = recipient;
		this.emailSub = emailSub;
		this.emailBody = emailBody;
		this.emailAttach = emailAttach;
	}

	@Override
	public String toString() {
		return "EmailDTO [recipient=" + recipient + ", emailSub=" + emailSub + ", emailBody=" + emailBody
				+ ", emailAttach=" + emailAttach + "]";
	}

	public EmailDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
