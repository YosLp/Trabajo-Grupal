package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.dto.CutDTO;
import co.edu.udes.activity.backend.demo.dto.CutRequestDTO;
import co.edu.udes.activity.backend.demo.services.CutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuts")
public class CutController {

    @Autowired
    private CutService cutService;

    @GetMapping
    public List<CutDTO> getAllCuts() {
        return cutService.getAllCuts();
    }

    @GetMapping("/{id}")
    public CutDTO getCutById(@PathVariable Long id) {
        return cutService.getCutById(id);
    }

    @PostMapping
    public CutDTO createCut(@RequestBody CutRequestDTO dto) {
        return cutService.createCut(dto);
    }

    @PutMapping("/{id}")
    public CutDTO updateCut(@PathVariable Long id, @RequestBody CutRequestDTO dto) {
        return cutService.updateCut(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteCut(@PathVariable Long id) {
        cutService.deleteCut(id);
    }

    @GetMapping("/enrollment/{enrollmentId}")
    public List<CutDTO> getCutsByEnrollment(@PathVariable Long enrollmentId) {
        return cutService.getCutsByEnrollmentId(enrollmentId);
    }

    @GetMapping("/enrollment/{enrollmentId}/final-score")
    public Double calculateFinalScore(@PathVariable Long enrollmentId) {
        return cutService.calculateFinalScoreByEnrollment(enrollmentId);
    }
}
