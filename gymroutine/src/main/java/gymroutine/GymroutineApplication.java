package gymroutine;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import gymroutine.domain.Week;
import gymroutine.domain.WeekRepository;
import gymroutine.domain.Workout;
import gymroutine.domain.WorkoutRepository;

@SpringBootApplication
public class GymroutineApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymroutineApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner routine(WeekRepository weekRepository, WorkoutRepository workoutRepository) {
		return (args) -> {
			Week week1 = new Week();
			weekRepository.save(week1);
			
			Week week2 = new Week();
			weekRepository.save(week2);

			Workout workout1 = new Workout("Chest & Biceps", "Benchpress", 5, 10, 20.0, week1);
			Workout workout2 = new Workout("Back", "Deadlift", 5, 20, 30.0, week1);
			Workout workout3 = new Workout("Shoulders & Triceps", "Militarypress", 3, 20, 30.0, week2);

			workoutRepository.save(workout1);
			workoutRepository.save(workout2);
			workoutRepository.save(workout3);
		};
	}
}
