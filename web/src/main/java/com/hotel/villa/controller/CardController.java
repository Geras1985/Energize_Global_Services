package com.hotel.villa.controller;

import com.hotel.villa.entity.Card;
import com.hotel.villa.entity.User;
import com.hotel.villa.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/card")
@EnableWebMvc
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/list")
    public List<Card> cardsList(Model model) {
        List<Card> cardList = cardService.getAll();
        model.addAttribute("cardList", cardList);
        return cardList;
    }

    @PostMapping("/add")
    public ResponseEntity<Card> addCard(Card card){
        User user = new User();
        Card card1 = new Card();
        card1.setCardNumber(card.getCardNumber());
        card1.setAmount(0.0);
        card1.setUser(card.getUser());
        card1.setName(card.getName());

        if (card.getCardNumber()!=null){
            cardService.createCard(user.getId(), "MasterCard");
            return new ResponseEntity<>(card,HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCard(Long id){
        if (id!=null){
            Card card = cardService.getById(id);
            if (card!=null) {
                cardService.deleteCard(id);
            }return new ResponseEntity<>("The card was deleted successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("This ID card was not found", HttpStatus.NOT_FOUND);
    }
}
