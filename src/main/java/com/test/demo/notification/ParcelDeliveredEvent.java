package com.test.demo.notification;

import com.test.demo.dto.ParcelDto;
import com.test.demo.entity.ParcelEntity;
import com.test.demo.mail.Mail;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ParcelDeliveredEvent extends ApplicationEvent {
    private final Long parcelId;

    public ParcelDeliveredEvent(Object source, Long parcelId) {
        super(source);
        this.parcelId = parcelId;
    }
}
