package iuh.demo_19_10;

import iuh.demo_19_10.models.Account;
import iuh.demo_19_10.reponsitories.Accountrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootApplication
public class Demo1910Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Demo1910Application.class, args);
    }
    @Autowired
    private Accountrepository accountrepository;
    @Override
    public void run(String... args) throws Exception {
//            for (long i = 0; i < 10; i++) {
//                Account account = new Account(i, 1000 + i,"user" + i);
//                accountrepository.save(account);
//            }
         //   accountrepository.findAll().forEach(System.out::println);
        Pageable request = PageRequest.of(1,5);
        accountrepository.findByBalanceGreaterThan(1000, request).forEach(System.out::println);
    }
}
