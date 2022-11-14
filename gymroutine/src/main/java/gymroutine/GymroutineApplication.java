package gymroutine;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import gymroutine.domain.Category;
import gymroutine.domain.CategoryRepository;
import gymroutine.domain.User;
import gymroutine.domain.UserRepository;
import gymroutine.domain.Week;
import gymroutine.domain.WeekRepository;
import gymroutine.domain.WorkoutRepository;

@SpringBootApplication
public class GymroutineApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymroutineApplication.class, args);
	}

	@Bean
	public CommandLineRunner routine(WeekRepository weekRepository, WorkoutRepository workoutRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
		return (args) -> {
			userRepository.save(new User("user", "$2y$10$iyEr.tfpe9q2NrY0B9ayAeVsd4ljb5VHP8y/ioV/rFXrz2fv624yG", "USER"));
			userRepository.save(new User("admin", "$2y$10$6P79CpftG0MpMstXlAgieuIZA3fRCD89iXHc8L6zTAcTLPyxjDAse", "ADMIN"));
		
			Week week1 = new Week();
			weekRepository.save(week1);

			Category category1 = new Category("Chest & Biceps");
			categoryRepository.save(category1);

			Category category2 = new Category("Legs");
			categoryRepository.save(category2);

			Category category3 = new Category("Shoulders & Triceps");
			categoryRepository.save(category3);

			Category category4 = new Category("Back");
			categoryRepository.save(category4);
		};
	}
}
