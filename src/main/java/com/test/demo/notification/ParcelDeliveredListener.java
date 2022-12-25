package com.test.demo.notification;

import com.test.demo.dto.ParcelDto;
import com.test.demo.entity.ParcelEntity;
import com.test.demo.mail.Mail;
import com.test.demo.mail.MailConfig;
import com.test.demo.mail.MailService;
import com.test.demo.repository.ParcelRepository;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
@RequiredArgsConstructor
class ParcelDeliveredListener implements ApplicationListener<ParcelDeliveredEvent> {

    private final MailService mailService;

    private final ParcelRepository parcelRepository;

    private final MailConfig mailConfig;

    private Mail constructDeliveredMail(Long parcelId) {
        ParcelEntity parcel = parcelRepository.findById(parcelId).get();
        Mail mail = new Mail();
        mail.setMessage(String.format(mailConfig.getMessage(), parcel.getParcelLockerWanted().getCode()));
        mail.setSubject(mailConfig.getSubject());
        mail.setTo(parcel.getReceiver().getEmailAddress());
        mail.setFrom(mailConfig.getFrom());
        return mail;
    }

    @Override
    public void onApplicationEvent(ParcelDeliveredEvent event) {
        Mail mail = constructDeliveredMail(event.getParcelId());
        mailService.sendMail(mail);
    }
}
