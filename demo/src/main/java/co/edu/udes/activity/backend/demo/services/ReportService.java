package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Report;
import co.edu.udes.activity.backend.demo.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Optional<Report> getReportById(int id) {
        return reportRepository.findById(id);
    }

    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }

    public Report updateReport(int id, Report updatedReport) {
        return reportRepository.findById(id).map(report -> {
            report.setReportType(updatedReport.getReportType());
            report.setGenerationDate(updatedReport.getGenerationDate());
            report.setContent(updatedReport.getContent());
            report.setSubject(updatedReport.getSubject());
            report.setStudent(updatedReport.getStudent());
            report.setAcademicRecord(updatedReport.getAcademicRecord());
            return reportRepository.save(report);
        }).orElse(null);
    }

    public boolean deleteReport(int id) {
        if (reportRepository.existsById(id)) {
            reportRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Report generateReport(String type) {
        Report report = new Report();
        report.setReportType(type);
        report.setGenerationDate(new Date());
        report.setContent("Contenido generado automáticamente para tipo: " + type);
        return reportRepository.save(report);
    }

    public void exportToPDF() {
        System.out.println("Exportando reporte a PDF...");
    }

    public void exportToExcel() {
        System.out.println("Exportando reporte a Excel...");
    }
}

