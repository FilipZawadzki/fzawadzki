package com.test.demo.notification;

import com.test.demo.entity.ParcelEntity;
import com.test.demo.mail.Mail;
import com.test.demo.repository.ParcelRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NotificationServiceInternal {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishParcelDeliveredEvent(Long parcelId) {
        applicationEventPublisher.publishEvent(new ParcelDeliveredEvent(this, parcelId));
    }

}
