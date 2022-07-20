package com.hotel.villa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "bank")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bank extends BaseEntity<Long> implements Serializable {
    private String name;


}
