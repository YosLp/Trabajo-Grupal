package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.models.Career;
import co.edu.udes.activity.backend.demo.services.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/careers")
public class CareerController {

    @Autowired
    private CareerService careerService;

    @GetMapping
    public List<Career> getAllCareers() {
        return careerService.getAllCareers();
    }

    @GetMapping("/{id}")
    public Optional<Career> getCareerById(@PathVariable Integer id) {
        return careerService.getCareerById(id);
    }

    @PostMapping
    public Career createCareer(@RequestBody Career career) {
        return careerService.saveCareer(career);
    }

    @PutMapping("/{id}")
    public Career updateCareer(@PathVariable Integer id, @RequestBody Career updatedCareer) {
        return careerService.updateCareer(id, updatedCareer);
    }

    @DeleteMapping("/{id}")
    public String deleteCareer(@PathVariable Integer id) {
        boolean deleted = careerService.deleteCareer(id);
        return deleted ? "Career deleted successfully" : "Career not found with id: " + id;
    }
}