package iuh.fit.baitap_week5;

import com.neovisionaries.i18n.CountryCode;
import iuh.fit.baitap_week5.backend.models.Address;
import iuh.fit.baitap_week5.backend.models.Candidate;
import iuh.fit.baitap_week5.backend.reponsitories.AddressRepository;
import iuh.fit.baitap_week5.backend.reponsitories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;

@SpringBootApplication
public class BaiTapWeek5Application {

	public static void main(String[] args) {
		SpringApplication.run(BaiTapWeek5Application.class, args);
	}


//	@Autowired
//	private CandidateRepository candidateRepository;
//	@Autowired
//	private AddressRepository addressRepository;
//	@Bean
//	CommandLineRunner initData(){
//		return args -> {
//			Random rnd =new Random();
//			for (int i = 1; i < 1000; i++) {
//				Address add = new Address("Street #"+i,
//						"City #"+i,
//						CountryCode.VN,
//						rnd.nextInt(1,1000)+"",
//						rnd.nextInt(1000000,9999999)+"");
//				addressRepository.save(add);
//
//				Candidate can=new Candidate("Name #"+i,
//						LocalDate.of(1998,rnd.nextInt(1,13),rnd.nextInt(1,29)),
//						add,
//						rnd.nextLong(1111111111L,9999999999L)+"",
//						"email_"+i+"@gmail.com");
//				candidateRepository.save(can);
//				System.out.println("Added: " +can);
//			}
//		};
//	}

}
