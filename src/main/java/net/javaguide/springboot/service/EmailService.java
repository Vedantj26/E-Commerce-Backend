package net.javaguide.springboot.service;

import net.javaguide.springboot.entity.EmailDTO;

public interface EmailService {

	String sendEmail(EmailDTO emailDto);

	String sendEmailWithAttach(EmailDTO emailDto);

}
