package com.hotel.villa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hotel.villa.enums.CardType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name = "cards")
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "payment_mode", discriminatorType = DiscriminatorType.STRING)
public class Card extends BaseEntity<Long> implements Serializable {

    @Column(name = "amount")
    private Double amount;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_type")
    private CardType cardType;

    @Column(name = "CVV")
    private int cvv;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


}
