package com.test.demo.notification;

import com.test.demo.entity.ClientEntity;
import com.test.demo.entity.ParcelEntity;
import com.test.demo.entity.ParcelLockerEntity;
import com.test.demo.mail.Mail;
import com.test.demo.mail.MailConfig;
import com.test.demo.mail.MailService;
import com.test.demo.mail.impl.MailServiceImpl;
import com.test.demo.repository.ParcelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.RecordApplicationEvents;

import java.util.Optional;

import static com.test.demo.SampleTestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RecordApplicationEvents
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class ParcelDeliveredListenerTest {

    @InjectMocks
    private ParcelDeliveredListener parcelDeliveredListener;

    @Mock
    private MailService mailService;

    @Captor
    private ArgumentCaptor<Mail> eventArgumentCaptor;

    @Mock
    private ParcelRepository parcelRepository;

    @Mock
    private MailConfig mailConfig;


    @Test
    void shouldConstructEmailAndSend_FromGivenParcel() {
        //given
        Mail mailResult = new Mail();
        ParcelLockerEntity parcelLocker = parcelLocker("54-234");
        ClientEntity receiver = client();
        ParcelEntity parcel = parcel(receiver, client(), parcelLocker, 2L);
        mailResult.setMessage(String.format("Parcel has been delivered to: %s", parcel.getParcelLockerWanted().getCode()));
        mailResult.setSubject("Parcel delivered");
        mailResult.setTo(receiver.getEmailAddress());
        parcel.setId(1L);
        when(parcelRepository.findById(1L)).thenReturn(Optional.of(parcel));
        when(mailConfig.getFrom()).thenReturn(mailResult.getFrom());
        when(mailConfig.getSubject()).thenReturn(mailResult.getSubject());
        when(mailConfig.getMessage()).thenReturn(mailResult.getMessage());
        ParcelDeliveredEvent parcelDeliveredEvent = new ParcelDeliveredEvent(this, parcel.getId());
        //when
        parcelDeliveredListener.onApplicationEvent(parcelDeliveredEvent);
        //then
        Mockito.verify(mailService, Mockito.times(1)).sendMail(mailResult);
    }

}