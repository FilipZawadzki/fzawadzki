package com.test.demo.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@Table(name = "parcel")
@Entity
public class ParcelEntity extends AbstractEntity {

    @JoinColumn(nullable = false)
    @ManyToOne
    private ClientEntity sender;

    @JoinColumn(nullable = false)
    @ManyToOne
    private ClientEntity receiver;

    @ManyToOne
    private DepositBoxEntity depositBox;

    @JoinColumn(nullable = false)
    @ManyToOne
    private ParcelLockerEntity parcelLockerWanted;

    @ManyToOne
    private ParcelLockerEntity parcelLockerActual;

    @ManyToOne
    private ParcelLockerEntity parcelLockerWantedSubstitute;

    @Column(nullable = false)
    private Long parcelSize;

    @Enumerated(EnumType.STRING)
    private ParcelStatus status;

    private LocalDateTime sentDate;
    private LocalDateTime inParcelLockerDate;
    private LocalDateTime receivedDate;
    private LocalDateTime returnedDate;

}
