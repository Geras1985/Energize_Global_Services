package com.hotel.villa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "address")
@Data
public class Address extends BaseEntity<Long> implements Serializable {


    @Column(name = "res_address", length = 80)
    private String residenceAddress;

    @Column(name = "reg_address", length = 80)
    private String registrationAddress;

    @Column(name = "city", length = 80)
    private String city;

    @Column(name = "country", length = 80)
    private String country;

    @Column(name = "postal_code", length = 20)
    private String postalCode;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private Hotel hotel;

}
