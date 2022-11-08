package gymroutine.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import gymroutine.domain.Week;
import gymroutine.domain.WeekRepository;
import gymroutine.domain.WorkoutRepository;

@CrossOrigin
@Controller
public class WeekController {

	@Autowired
	private WeekRepository weekRepo;

	@Autowired
	private WorkoutRepository workoutRepo;
	
	// List all queries
	@GetMapping(value={"/", "/weeklist"})
	public String getWeek(Model model) {
		model.addAttribute("weeks", weekRepo.findAll());
		model.addAttribute("workouts", workoutRepo.findAll());
		return "weeklist";
	}

	// Add new Query
	@GetMapping("/addweek")
	public String addWeek(Model model) {
		model.addAttribute("week", new Week());
		return "addweek";
	}

	// Save new Query
	@PostMapping(value = "/save")
	public String saveWeek(@ModelAttribute Week newWeek, Model model) {
		weekRepo.save(newWeek);
		return "redirect:weeklist";
	}

	// Add questions for query based on it's id (NOT FINISHED)
	@GetMapping("/listworkouts/{id}")
	public String listWeekWorkouts(@PathVariable("id") Long week_id, Model model) {
		model.addAttribute("week", weekRepo.findById(week_id).get());
		return "listworkouts";
	}
}

