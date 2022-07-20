package com.hotel.villa.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "hotel_entity")
@Data
public class Hotel extends BaseEntity<Long> implements Serializable {

    @Column(name = "hotel_name", length = 200, nullable = false)
    private String name;

    @Column(name = "hotel_phone", length = 13, nullable = false)
    private String phone;

    @Column(name = "hotel_email", nullable = false)
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Address> address;

}
