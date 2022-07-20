package com.hotel.villa.service;

import com.hotel.villa.entity.Card;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CardService {
    ResponseEntity<Card> createCard(Long userId, String cardName);

    List<Card> getAll();

    Card findByUsername(String username);

    Card getById(Long id);

    void deleteCard(Long id);


}
