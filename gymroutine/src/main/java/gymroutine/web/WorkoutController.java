package gymroutine.web;

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

	@GetMapping("/workoutlist")
	public String listWorkouts(Model model) {
		model.addAttribute("workouts", workoutRepo.findAll());
		return "workoutlist";
	}

	@GetMapping("/addworkout/{id}")
	public String addWorkout(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		model.addAttribute("workouts", workoutRepo.findAll());
		model.addAttribute("workout", new Workout());
		model.addAttribute("week", weekRepo.findById(id).get());

		url = request.getRequestURI().toString();
		System.out.println(url);
		return "addworkout";
	}

	@PostMapping(value = "/saveworkout")
	public String saveWorkout(@ModelAttribute Workout newWorkout, Model model) {
		workoutRepo.save(newWorkout);
		List<String> urlList = Arrays.asList(url.split("/"));
		String redirectId = urlList.get(2);
		return "redirect:/addworkout/" + redirectId;
	}
}