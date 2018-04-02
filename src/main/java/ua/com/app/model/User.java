package ua.com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue
  private Long id;

  @OneToOne
  @JoinColumn(name = "card")
  private BankCard card;

  @NotNull
  @Transient
  private String numberOfCard;

  @Column(name = "password")
  private String password;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BankCard getCard() {
    return card;
  }

  public void setCard(BankCard card) {
    this.card = card;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getNumberOfCard() {
    return numberOfCard;
  }

  public void setNumberOfCard(String numberOfCard) {
    this.numberOfCard = numberOfCard;
  }
}
