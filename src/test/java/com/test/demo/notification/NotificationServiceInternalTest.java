package com.test.demo.notification;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.RecordApplicationEvents;

import static org.junit.jupiter.api.Assertions.*;
@RecordApplicationEvents
@ExtendWith(MockitoExtension.class)
class NotificationServiceInternalTest {

        @InjectMocks
        private NotificationServiceInternal notificationServiceInternal;

        @Mock
        private ApplicationEventPublisher applicationEventPublisher;

        @Captor
        private ArgumentCaptor<ParcelDeliveredEvent> eventArgumentCaptor;


        @Test
        void publishingParcelDeliveredEvent_ShouldPublishEvent() {
            //given
            Long parcelId = 1L;
            ParcelDeliveredEvent parcelDeliveredEvent = new ParcelDeliveredEvent(this, parcelId);
            //when
            notificationServiceInternal.publishParcelDeliveredEvent(parcelId);
            //then
            Mockito.verify(applicationEventPublisher, Mockito.times(1)).publishEvent(eventArgumentCaptor.capture());
            assertEquals(parcelDeliveredEvent.getParcelId(), eventArgumentCaptor.getValue().getParcelId());
        }

}