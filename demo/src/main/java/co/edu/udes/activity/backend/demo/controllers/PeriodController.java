package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.models.Period;
import co.edu.udes.activity.backend.demo.services.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/periods")
public class PeriodController {

    @Autowired
    private PeriodService periodService;

    @GetMapping
    public List<Period> getAllPeriods() {
        return periodService.getAllPeriods();
    }

    @GetMapping("/{id}")
    public Optional<Period> getPeriodById(@PathVariable Integer id) {
        return periodService.getPeriodById(id);
    }

    @PostMapping
    public Period createPeriod(@RequestBody Period period) {
        return periodService.savePeriod(period);
    }

    @PutMapping("/{id}")
    public Period updatePeriod(@PathVariable Integer id, @RequestBody Period updatedPeriod) {
        return periodService.updatePeriod(id, updatedPeriod);
    }

    @DeleteMapping("/{id}")
    public String deletePeriod(@PathVariable Integer id) {
        boolean deleted = periodService.deletePeriod(id);
        return deleted ? "Period deleted successfully" : "Period not found with id: " + id;
    }
}