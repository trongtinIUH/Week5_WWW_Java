package iuh.fit.baitap_week6;

import iuh.fit.baitap_week6.backend.models.User;
import iuh.fit.baitap_week6.backend.reponsitories.PostComment_Reponsitory;
import iuh.fit.baitap_week6.backend.reponsitories.Post_Reponsitory;
import iuh.fit.baitap_week6.backend.reponsitories.User_Reponsitory;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@SpringBootApplication
public class BaiTapWeek6Application {

    public static void main(String[] args) {
        SpringApplication.run(BaiTapWeek6Application.class, args);
    }
//
//    @Autowired
//    private Post_Reponsitory post_reponsitory;
//    @Autowired
//    private PostComment_Reponsitory postComment_reponsitory;
//    @Autowired
//    private User_Reponsitory user_reponsitory;
//
//    @Bean
//    CommandLineRunner initData() {
//        return args -> {
//            Faker faker = new Faker();
//
//
//            for (int i = 0; i < 10; i++) {
//                User user = new User();
//                user.setEmail(faker.internet().emailAddress());
//                user.setIntro(faker.lorem().sentence());
//                user.setMobile(faker.phoneNumber().cellPhone());
//                user.setPasswordHash(faker.internet().password());
//                user.setProfile(faker.internet().url());
//                user.setFirstName(faker.name().firstName());
//                user.setLastName(faker.name().lastName());
//                user.setLastLogin(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
//                user.setMiddleName(faker.name().nameWithMiddle());
//                user.setRegisteredAt(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
//
//
//                user_reponsitory.save(user);
//                System.out.println("user: " + user);
//            }
//        };
//    }
}