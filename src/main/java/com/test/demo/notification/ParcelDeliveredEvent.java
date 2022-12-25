package com.test.demo.notification;

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
