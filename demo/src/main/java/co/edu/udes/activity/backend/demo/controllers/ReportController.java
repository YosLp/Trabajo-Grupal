package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.dto.ReportDTO;
import co.edu.udes.activity.backend.demo.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public ResponseEntity<List<ReportDTO>> getAllReports() {
        return ResponseEntity.ok(reportService.getAllReports());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportDTO> getReportById(@PathVariable Long id) {
        return reportService.getReportById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ReportDTO> createReport(@RequestBody ReportDTO reportDTO) {
        return ResponseEntity.ok(reportService.saveReport(reportDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReportDTO> updateReport(@PathVariable Long id, @RequestBody ReportDTO reportDTO) {
        ReportDTO updated = reportService.updateReport(id, reportDTO);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReport(@PathVariable Long id) {
        boolean deleted = reportService.deleteReport(id);
        return deleted ? ResponseEntity.ok("Reporte eliminado correctamente") :
                ResponseEntity.notFound().build();
    }
}
