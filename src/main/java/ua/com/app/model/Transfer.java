package ua.com.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "transfer")
public class Transfer {

  @Id
  @GeneratedValue
  private Long id;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "sender")
  private User sender;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "recipients_card")
  private BankCard recipientsCard;

  @Transient
  @NotNull(message = "numberOfRecipientsCard can not be null.")
  private String numberOfRecipientsCard;

  @NotNull(message = "amount can not be null.")
  private double amount;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getSender() {
    return sender;
  }

  public void setSender(User sender) {
    this.sender = sender;
  }

  public BankCard getRecipientsCard() {
    return recipientsCard;
  }

  public void setRecipientsCard(BankCard recipientsCard) {
    this.recipientsCard = recipientsCard;
  }

  public String getNumberOfRecipientsCard() {
    return numberOfRecipientsCard;
  }

  public void setNumberOfRecipientsCard(String numberOfRecipientsCard) {
    this.numberOfRecipientsCard = numberOfRecipientsCard;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }
}
