package com.test.demo.controller;

import com.test.demo.IntegrationTestBase;
import com.test.demo.entity.*;
import com.test.demo.mail.MailService;
import com.test.demo.service.ParcelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static com.test.demo.SampleTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@EnableWebMvc
@ExtendWith(MockitoExtension.class)
class ParcelControllerTest extends IntegrationTestBase {

    private MockMvc mockMvc;

    @Autowired
    private ParcelService parcelService;


    @BeforeEach
    public void setup() {
        ParcelController parcelController = new ParcelController(parcelService);
        mockMvc = MockMvcBuilders.standaloneSetup(parcelController).build();
    }


    @Test
    void shouldGetParcel_WhenGettingParcelById() throws Exception {
        //given
        ParcelLockerEntity plWanted = createParcelLocker(parcelLocker("54-234"));
        ClientEntity receiver = createClient(client());
        ClientEntity sender = createClient(client());
        ParcelEntity parcel = createParcel(parcel(receiver, sender, plWanted, 4L));

        //when + then
        mockMvc.perform(MockMvcRequestBuilders.get(String.format("/parcel/%s", parcel.getId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath(("$.id")).value(parcel.getId()))
                .andExpect(jsonPath(("$.senderId")).value(sender.getId()))
                .andExpect(jsonPath(("$.receiverId")).value(receiver.getId()))
                .andExpect(jsonPath(("$.parcelLockerWantedId")).value(plWanted.getId()))
                .andExpect(jsonPath(("$.parcelLockerActualId")).doesNotExist())
                .andExpect(jsonPath(("$.depositBoxId")).doesNotExist())
                .andExpect(jsonPath(("$.parcelSize")).value(parcel.getParcelSize()))
                .andExpect(jsonPath("$.parcelStatus").doesNotExist());
    }

    @Test
    void shouldDeliverParcel() throws Exception {
        //given
        ParcelLockerEntity plWanted = createParcelLocker(parcelLocker("54-234"));
        ClientEntity receiver = createClient(client());
        ClientEntity sender = createClient(client());
        DepositBoxEntity depositBox = createDepositBox(depositBox(plWanted));
        ParcelEntity parcel = createParcel(parcel(receiver, sender, plWanted, 4L));

        //when + then
        mockMvc.perform(MockMvcRequestBuilders.put(String.format("/parcel/deliver/%s", parcel.getId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath(("$.id")).value(parcel.getId()))
                .andExpect(jsonPath(("$.senderId")).value(sender.getId()))
                .andExpect(jsonPath(("$.receiverId")).value(receiver.getId()))
                .andExpect(jsonPath(("$.parcelLockerWantedId")).value(plWanted.getId()))
                .andExpect(jsonPath(("$.parcelLockerActualId")).value(plWanted.getId()))
                .andExpect(jsonPath(("$.depositBoxId")).value(depositBox.getId()))
                .andExpect(jsonPath(("$.parcelSize")).value(parcel.getParcelSize()))
                .andExpect(jsonPath("$.parcelStatus").value(ParcelStatus.IN_PARCEL_LOCKER.toString()));
    }
}