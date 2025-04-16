package co.edu.udes.activity.backend.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.udes.activity.backend.demo.models.Report;
import co.edu.udes.activity.backend.demo.services.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }

    @GetMapping("/{id}")
    public Optional<Report> getReportById(@PathVariable Long id) {
        return reportService.getReportById(id);
    }

    @PostMapping
    public Report createReport(@RequestBody Report report) {
        return reportService.saveReport(report);
    }

    @PutMapping("/{id}")
    public Report updateReport(@PathVariable Long id, @RequestBody Report updatedReport) {
        return reportService.updateReport(id, updatedReport);
    }

    @DeleteMapping("/{id}")
    public String deleteReport(@PathVariable Long id) {
        boolean deleted = reportService.deleteReport(id);
        return deleted ? "Reporte eliminado correctamente" :
                         "No se encontró el reporte con ID: " + id;
    }
}
