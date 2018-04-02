package ua.com.app.service;

import ua.com.app.model.BankCard;

public interface CardService {

  public BankCard findByNumber(String number);

}
