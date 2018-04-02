package ua.com.app.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.app.model.BankCard;

public interface CardRepository extends JpaRepository<BankCard, Long> {

}
