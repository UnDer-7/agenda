package com.matus.agenda.services;

import com.matus.agenda.domain.Anotacao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class AbstractEmailService implements EmailService{

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendOrderConfirmationEmail(Anotacao anotacao) {
        SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(anotacao);
    }

    private SimpleMailMessage prepareSimpleMailMessageFromPedido(Anotacao anotacao) {
        SimpleMailMessage sm = new SimpleMailMessage();

        sm.setTo();

        return null;
    }

    @Override
    public void sendEmail(SimpleMailMessage smg) {

    }
}
