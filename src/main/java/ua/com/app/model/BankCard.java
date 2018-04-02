package ua.com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cards")
public class BankCard {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "name")
  @NotNull(message = "name can not be null.")
  private String name;

  @Column(name = "number_of_card")
  @NotNull(message = "numberOfCard can not be null.")
  @Size(min = 16, max = 16)
  private String numberOfCard;

  @Column(name = "birth_date")
  @NotNull(message = "birthDate can not be null.")
  private String birthDate;

  @Column(name = "sex")
  @NotNull(message = "sex can not be null.")
  private String sex;

  @Column(name = "address")
  @NotNull(message = "address can not be null.")
  private String address;

  @OneToOne(mappedBy = "card")
  private User user;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNumberOfCard() {
    return numberOfCard;
  }

  public void setNumberOfCard(String numberOfCard) {
    this.numberOfCard = numberOfCard;
  }

  public String getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(String birthDate) {
    this.birthDate = birthDate;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
