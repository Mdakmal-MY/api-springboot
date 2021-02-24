package student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
	
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository) {
		return args ->{
			Student akmal = new Student(
					1L,
					"Muhammad Akmal",
					"makmal@gmail.com",
					LocalDate.of(1996, Month.APRIL, 6)
					);
			
			Student yusoff = new Student(
					2L,
					"yusoff",
					"yusoff@gmail.com",
					LocalDate.of(2000, Month.DECEMBER, 6)
					);
			
			repository.saveAll(List.of(akmal, yusoff));
		};
	}

}
