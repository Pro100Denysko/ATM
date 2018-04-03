package ua.com.app.service;

import ua.com.app.model.BankCard;

public interface CardService {

  BankCard findByNumber(String number);

  BankCard saveNewCard(BankCard card);

}
