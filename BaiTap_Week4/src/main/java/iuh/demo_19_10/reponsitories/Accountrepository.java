package iuh.demo_19_10.reponsitories;

import iuh.demo_19_10.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface Accountrepository extends PagingAndSortingRepository<Account, Long> {


    List<Account> findByUsername(String username);
    List<Account> findByBalanceGreaterThan(double balance, Pageable pageable);
    Account findById(Long id);

    void save(Account account);

    Iterable<Object> findAll();
}
