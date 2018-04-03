package ua.com.app.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.com.app.model.BankCard;
import ua.com.app.service.CardServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CardControllerTest {

  private MockMvc mockMvc;

  @Mock
  private CardServiceImpl cardService;

  @InjectMocks
  private CardController cardController;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders
        .standaloneSetup(cardController)
        .addFilters(new org.apache.catalina.filters.CorsFilter())
        .build();
  }

  @Test
  public void testCreateCardSuccess() throws Exception {
    BankCard card = createCard();

    when(cardService.saveNewCard(any(BankCard.class))).thenReturn(card);
    mockMvc.perform(
        post("/api/cards")
            .contentType(APPLICATION_JSON)
            .content(asJsonString(card)))
        .andExpect(status().isCreated());
  }

  @Test
  public void testCreateCardWithoutName() throws Exception {
    BankCard card = createCard();
    card.setName(null);

    mockMvc.perform(
        post("/api/cards")
            .contentType(APPLICATION_JSON)
            .content(asJsonString(card)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testCreateCardWithoutNumberOfCard() throws Exception {
    BankCard card = createCard();
    card.setNumberOfCard(null);

    mockMvc.perform(
        post("/api/cards")
            .contentType(APPLICATION_JSON)
            .content(asJsonString(card)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testCreateCardWithoutBirthDate() throws Exception {
    BankCard card = createCard();
    card.setBirthDate(null);

    mockMvc.perform(
        post("/api/cards")
            .contentType(APPLICATION_JSON)
            .content(asJsonString(card)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testCreateCardWithoutSex() throws Exception {
    BankCard card = createCard();
    card.setSex(null);

    mockMvc.perform(
        post("/api/cards")
            .contentType(APPLICATION_JSON)
            .content(asJsonString(card)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testCreateCardWithoutAddress() throws Exception {
    BankCard card = createCard();
    card.setAddress(null);

    mockMvc.perform(
        post("/api/cards")
            .contentType(APPLICATION_JSON)
            .content(asJsonString(card)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testCreateCardWithLongNumberOfCard() throws Exception {
    BankCard card = createCard();
    card.setNumberOfCard("12345678901234567890123456789");

    mockMvc.perform(
        post("/api/cards")
            .contentType(APPLICATION_JSON)
            .content(asJsonString(card)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testCreateCardWithShortNumberOfCard() throws Exception {
    BankCard card = createCard();
    card.setNumberOfCard("12345");

    mockMvc.perform(
        post("/api/cards")
            .contentType(APPLICATION_JSON)
            .content(asJsonString(card)))
        .andExpect(status().isBadRequest());
  }

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public BankCard createCard() {
    BankCard card = new BankCard();
    card.setName("name");
    card.setNumberOfCard("1234567890123456");
    card.setBirthDate("birthDate");
    card.setSex("male");
    card.setAddress("address");
    return card;
  }
}
