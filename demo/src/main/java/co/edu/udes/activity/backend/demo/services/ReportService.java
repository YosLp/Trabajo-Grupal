package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Report;
import co.edu.udes.activity.backend.demo.repositories.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Optional<Report> getReportById(long id) {
        return reportRepository.findById(id);
    }

    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }

    public Report updateReport(long id, Report updatedReport) {
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

    public boolean deleteReport(long id) {
        if (reportRepository.existsById(id)) {
            reportRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
