package com.test.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Table(name = "deposit_box")
@Entity
public class DepositBoxEntity extends AbstractEntity {

    @ManyToOne
    private ParcelLockerEntity parcelLocker;

    @OneToMany(mappedBy = "depositBox", fetch = FetchType.EAGER)
    private List<ParcelEntity> parcelEntities;

    @Column(columnDefinition = "Decimal (1,0) default '4'")
    private Long size;

    public Long getSize() {
        if (this.size == null) {
            return 4L;
        } else {
            return this.size;
        }
    }
}
