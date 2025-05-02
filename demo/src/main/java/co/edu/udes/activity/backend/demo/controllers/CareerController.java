package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.dto.CareerDTO;
import co.edu.udes.activity.backend.demo.dto.CareerRequestDTO;
import co.edu.udes.activity.backend.demo.dto.SubjectDTO;
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

    @PostMapping
    public CareerDTO createCareer(@RequestBody CareerRequestDTO dto) {
        return careerService.createCareer(dto);
    }

    @GetMapping
    public List<CareerDTO> getAllCareers() {
        return careerService.getAllCareers();
    }

    @PostMapping("/{careerId}/subjects/{subjectId}")
    public CareerDTO assignSubjectToCareer(
            @PathVariable long careerId,
            @PathVariable long subjectId) {
        return careerService.assignSubjectToCareer(careerId, subjectId);
    }

    @GetMapping("/{careerId}/subjects")
    public List<SubjectDTO> getSubjectsByCareer(@PathVariable Long careerId) {
        return careerService.getSubjectsByCareer(careerId);
    }

    @GetMapping("/semester/{semesterId}")
    public List<CareerDTO> getCareersBySemester(@PathVariable Long semesterId) {
        return careerService.getCareersBySemester(semesterId);
    }

    @DeleteMapping("/{id}")
    public void deleteCareer(@PathVariable Long id) {
        careerService.deleteCareer(id);
    }
}