package ua.com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.com.app.model.BankCard;
import ua.com.app.model.User;
import ua.com.app.service.CardServiceImpl;

@RestController
@RequestMapping("/api")
public class CardController {

  private CardServiceImpl cardService;

  @Autowired
  public CardController(CardServiceImpl cardService) {
    this.cardService = cardService;
  }

  @RequestMapping(value = "/create-card", method = RequestMethod.POST)
  public ResponseEntity<String> createBankCard(@RequestBody BankCard card) {
    BankCard newCard = cardService.save(card);
    if (newCard != null) {
      return ResponseEntity.status(HttpStatus.CREATED).build();
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  @RequestMapping(value = "/authCard", method = RequestMethod.POST)
  public ResponseEntity<?> authCard(@RequestBody User user) {
    BankCard newCard = cardService.findByNumber(user.getNumberOfCard());
    if (newCard != null && newCard.getUser().getPassword().equals(user.getPassword())) {
      return ResponseEntity.status(HttpStatus.OK).build();
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }
}