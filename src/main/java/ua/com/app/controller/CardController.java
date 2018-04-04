package ua.com.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.com.app.model.BankCard;
import ua.com.app.model.Transfer;
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

  @RequestMapping(value = "/cards", method = RequestMethod.POST)
  public ResponseEntity<BankCard> createBankCard(@RequestBody BankCard card) {
    BankCard newCard = cardService.saveNewCard(card);
    if (newCard != null) {
      return ResponseEntity.status(HttpStatus.CREATED).build();
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  @RequestMapping(value = "/authCard", method = RequestMethod.POST)
  public ResponseEntity<User> authCard(@RequestBody User user) {
    BankCard newCard = cardService.findByNumber(user.getNumberOfCard());
    if (newCard != null && newCard.getUser().getPassword().equals(user.getPassword())) {
      return ResponseEntity.status(HttpStatus.OK).build();
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }

  @RequestMapping(value = "/transfer", method = RequestMethod.POST)
  public ResponseEntity<BankCard> transfer(@RequestBody Transfer transfer) {
    BankCard sendersCard = cardService.findByNumber(transfer.getSender().getNumberOfCard());
    BankCard recipientsCard = cardService.findByNumber(transfer.getNumberOfRecipientsCard());
    if (sendersCard != null && recipientsCard != null && sendersCard.getUser().getPassword()
        .equals(transfer.getSender().getPassword())) {
      if (sendersCard.getBalance() - transfer.getAmount() > 0) {
        sendersCard.setBalance(sendersCard.getBalance() - transfer.getAmount());
        recipientsCard.setBalance(recipientsCard.getBalance() + transfer.getAmount());
        cardService.save(sendersCard);
        cardService.save(recipientsCard);

        //This fill is null because the user data enters the loop
        sendersCard.getUser().setCard(null);
        return ResponseEntity.status(HttpStatus.OK).body(sendersCard);
      } else {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
      }
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }

  @RequestMapping(value = "/cards", method = RequestMethod.GET)
  public ResponseEntity<List<BankCard>> findAllCards() {
    List<BankCard> listOfCards = cardService.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(listOfCards);
  }
}
