package gymroutine.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import gymroutine.domain.CategoryRepository;
import gymroutine.domain.WeekRepository;
import gymroutine.domain.Workout;
import gymroutine.domain.WorkoutRepository;

@CrossOrigin
@Controller
public class WorkoutController {

	String url;

	@Autowired
	private WorkoutRepository workoutRepo;

	@Autowired
	private WeekRepository weekRepo;

	@Autowired
	private CategoryRepository categoryRepo;

	@GetMapping("/listworkouts")
	public String listWorkouts(Model model) {
		model.addAttribute("categories", categoryRepo.findAll());
		model.addAttribute("workouts", workoutRepo.findAll());
		return "listworkouts";
	}

	@GetMapping("/addworkout/{id}")
	public String addWorkout(@PathVariable("id") Long number, Model model, HttpServletRequest request) {
		model.addAttribute("categories", categoryRepo.findAll());
		model.addAttribute("workouts", workoutRepo.findAll());
		model.addAttribute("workout", new Workout());
		model.addAttribute("week", weekRepo.findById(number).get());
		url = request.getRequestURI().toString();
		return "addworkout";
	}

	@PostMapping("/saveworkout")
	public String saveWorkout(@ModelAttribute Workout newWorkout, Model model) {
		workoutRepo.save(newWorkout);
		List<String> urlList = Arrays.asList(url.split("/"));
		String redirectId = urlList.get(2);
		return "redirect:/addworkout/" + redirectId;
	}

	@GetMapping("/logworkout/{id}")
	public String logWorkout(@PathVariable("id") Long workout_id, Model model, HttpServletRequest request) {
		model.addAttribute("workout", workoutRepo.findById(workout_id).get());
		model.addAttribute("weeks", weekRepo.findById(workoutRepo.findById(workout_id).get().getWeek().getNumber()).get());
		model.addAttribute("category", categoryRepo.findAll());
		url = request.getRequestURI().toString();
		return "logworkout";
	}

	@PostMapping("/editworkout")
	public String editWorkout(@ModelAttribute Workout newWorkout, Model model) {
		LocalDate localDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        newWorkout.setDate(dateFormatter.format(localDate));
		workoutRepo.save(newWorkout);
		List<String> urlList = Arrays.asList(url.split("/"));
		String redirectId = urlList.get(2);
		Long id = Long.parseLong(redirectId);
		return "redirect:/logworkout/" + id;
	}

	@GetMapping("/delete/{id}/{id2}")
	public String deleteBook(@PathVariable("id") Long workout_id, @PathVariable("id2") int weekid, Model model, HttpServletRequest request) {
		workoutRepo.deleteById(workout_id);
		return "redirect:/listworkouts/" + weekid;
	}
}
