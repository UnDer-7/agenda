package com.matus.agenda.services;

import com.matus.agenda.domain.Anotacao;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    void sendOrderConfirmationEmail(Anotacao anotacao);

    void sendEmail(SimpleMailMessage smg);
}
