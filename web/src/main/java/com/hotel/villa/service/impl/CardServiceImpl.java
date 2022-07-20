package com.hotel.villa.service.impl;

import com.hotel.villa.entity.Card;
import com.hotel.villa.entity.User;
import com.hotel.villa.enums.CardType;
import com.hotel.villa.repository.CardRepo;
import com.hotel.villa.repository.UserRepo;
import com.hotel.villa.service.CardService;
import com.hotel.villa.util.Helper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class CardServiceImpl implements CardService {
    private final CardRepo cardRepo;
    private final UserRepo userRepo;

    public CardServiceImpl(CardRepo cardRepo, UserRepo userRepo) {
        this.cardRepo = cardRepo;
        this.userRepo = userRepo;
    }

    /**
     * This method get all cards and returns as a list .
     */
    public List<Card> getAll() {
        return cardRepo.findAll();
    }

    @Override
    public Card findByUsername(String username) {
        return null;
    }

    /**
     * Created Card required user ID and choosing MasterCard or VisaCard.
     * */

    public ResponseEntity<Card> createCard(Long userId, String cardName) {
        //Default amount for user card.
        double amount = 0;
        //Generated random cvv for card.
        int cvv = Integer.parseInt(Helper.generate("", 3));

        User user = userRepo.findUserById(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));

        LocalDate date = LocalDate.now();

        switch (cardName) {
            case "MasterCard": {
                Card masterCard = new Card();
                boolean exists = false;
                while (!exists) {
                    String cardNumber = Helper.generate("43181", 16);
                    boolean valid = Helper.checkLuhn(cardNumber);
                    if (valid) {
                        Card card = cardRepo.findCardByCardNumber(cardNumber);
                        if (card == null) {
                            masterCard.setUser(user);
                            masterCard.setCardNumber(cardNumber);
                            masterCard.setCvv(cvv);
                            masterCard.setCardType(CardType.MASTER_CARD);
//                            masterCard.setValidDate(date.plusYears(3));
                            masterCard.setAmount(amount);
                            cardRepo.save(masterCard);
                            exists = true;
                        }
                    }
                }
                return new ResponseEntity<>(masterCard, HttpStatus.OK);
            }
            case "VisaCard": {
                Card visaCard = new Card();
                boolean exists = false;
                while (!exists) {
                    String cardNumber = Helper.generate("40331", 16);
                    boolean valid1 = Helper.checkLuhn(cardNumber);
                    if (valid1) {
                        Card card = cardRepo.findCardByCardNumber(cardNumber);
                        if (card == null) {
                            visaCard.setCardNumber(cardNumber);
                            visaCard.setUser(user);
                            visaCard.setCvv(cvv);
                            visaCard.setCardType(CardType.VISA_CARD);
//                            visaCard.setValidDate(date.plusYears(3));
                            visaCard.setAmount(amount);
                            cardRepo.save(visaCard);
                            exists = true;
                        }
                    }
                }
                return new ResponseEntity<>(visaCard, HttpStatus.OK);
            }
            case "ArCa": {
                Card arcaCard = new Card();
                boolean exists = false;
                while (!exists) {
                    String cardNumber = Helper.generate("40300", 16);
                    boolean valid = Helper.checkLuhn(cardNumber);
                    if (valid) {
                        Card card = cardRepo.findCardByCardNumber(cardNumber);
                        if (card == null) {
                            arcaCard.setCardNumber(cardNumber);
                            arcaCard.setUser(user);
                            arcaCard.setCvv(cvv);
                            arcaCard.setCardType(CardType.VISA_CARD);
//                            arcaCard.setValidDate(date.plusYears(3));
                            arcaCard.setAmount(amount);
                            cardRepo.save(arcaCard);
                            exists = true;
                        }
                    }
                }
                return new ResponseEntity<>(arcaCard, HttpStatus.OK);
            }
            default:
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This method delete the card by id.
     */
    @Override
    public void deleteCard(Long id) {
        cardRepo.deleteById(id);
    }

    @Override
    public Card getById(Long id) {
        return cardRepo.getById(id);
    }
}
