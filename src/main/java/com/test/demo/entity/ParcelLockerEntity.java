package com.test.demo.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Table(name = "parcel_locker")
@Entity
public class ParcelLockerEntity extends AbstractEntity {

    @Column(nullable = false)
    private String postCode;

    private String code;

    private String address;

    @OneToMany(mappedBy = "parcelLocker", cascade = CascadeType.REMOVE)
    private List<DepositBoxEntity> depositBoxes;

}
