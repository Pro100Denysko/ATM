package ua.com.app.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.app.DAO.CardRepository;
import ua.com.app.model.BankCard;

@Service
public class CardServiceImpl implements CardService {

  private final CardRepository cardRepository;

  @Autowired
  public CardServiceImpl(CardRepository cardRepository) {
    this.cardRepository = cardRepository;
  }

  @Override
  public BankCard saveNewCard(BankCard card) {
    card.setBalance(0);
    return cardRepository.save(card);
  }

  public BankCard save(BankCard card) {
    return cardRepository.save(card);
  }

  @Override
  public BankCard findByNumber(String number) {
    return cardRepository.findByNumberOfCard(number);
  }

  public List<BankCard> findAll() {
    List<BankCard> listOfCards = cardRepository.findAll();
    listOfCards.sort(Comparator.comparing(BankCard::getNumberOfCard));
    return listOfCards;
  }
}