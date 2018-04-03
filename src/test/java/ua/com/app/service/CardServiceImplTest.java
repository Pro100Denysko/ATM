package ua.com.app.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.app.DAO.CardRepository;
import ua.com.app.model.BankCard;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
@SpringBootTest
public class CardServiceImplTest {

  @Mock
  private CardRepository cardRepository;

  private CardServiceImpl cardService;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    this.cardService = new CardServiceImpl(cardRepository);
  }

  @Test
  public void save() {
    BankCard card = createCard();
    when(cardRepository.save(any(BankCard.class))).thenReturn(card);
    BankCard newCard = cardService.save(card);

    assertEquals("name", newCard.getName());
    assertNotNull(newCard);
  }

  @Test
  public void findByNumber() {
    BankCard card = createCard();
    when(cardRepository.findByNumberOfCard(any(String.class))).thenReturn(card);
    cardService.save(card);
    BankCard found = cardService.findByNumber(card.getNumberOfCard());

    assertEquals("name", found.getName());
    assertNotNull(found);
  }

  @Test
  public void findAll() {
    BankCard one=createCard();
    BankCard two=createCard();
    one.setName("one");
    two.setName("two");
    List<BankCard> list = new ArrayList<>();
    list.add(one);
    list.add(two);
    when(cardRepository.findAll()).thenReturn(list);
    cardService.save(one);
    cardService.save(two);
    List newList= cardService.findAll();

    assertEquals(list, newList);
    assertNotNull(one);
    assertNotNull(two);
  }

  public BankCard createCard() {
    BankCard card = new BankCard();
    card.setName("name");
    card.setNumberOfCard("1234567890123456");
    card.setBirthDate("birthDate");
    card.setAddress("address");
    card.setSex("male");
    return card;
  }
}