package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.dto.ReportDTO;
import co.edu.udes.activity.backend.demo.models.*;
import co.edu.udes.activity.backend.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AcademicRecordRepository academicRecordRepository;

    public List<ReportDTO> getAllReports() {
        return reportRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<ReportDTO> getReportById(Long id) {
        return reportRepository.findById(id).map(this::convertToDTO);
    }

    public ReportDTO saveReport(ReportDTO dto) {
        Report report = convertToEntity(dto);
        report.setGenerationDate(new Date()); // Puede sobrescribirse aquí si lo deseas.
        return convertToDTO(reportRepository.save(report));
    }

    public ReportDTO updateReport(Long id, ReportDTO dto) {
        return reportRepository.findById(id).map(report -> {
            report.setReportType(dto.getReportType());
            report.setGenerationDate(dto.getGenerationDate());
            report.setContent(dto.getContent());

            subjectRepository.findById((int) dto.getSubjectId()).ifPresent(report::setSubject);
            studentRepository.findById(dto.getStudentId()).ifPresent(report::setStudent);
            academicRecordRepository.findById(dto.getAcademicRecordId()).ifPresent(report::setAcademicRecord);

            return convertToDTO(reportRepository.save(report));
        }).orElse(null);
    }

    public boolean deleteReport(Long id) {
        if (reportRepository.existsById(id)) {
            reportRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public ReportDTO generateReport(String type) {
        Report report = new Report();
        report.setReportType(type);
        report.setGenerationDate(new Date());
        report.setContent("Contenido generado automáticamente para tipo: " + type);
        return convertToDTO(reportRepository.save(report));
    }

    public void exportToPDF() {
        System.out.println("Exportando reporte a PDF...");
    }

    public void exportToExcel() {
        System.out.println("Exportando reporte a Excel...");
    }

    private ReportDTO convertToDTO(Report report) {
        ReportDTO dto = new ReportDTO();
        dto.setIdReport(report.getIdReport());
        dto.setReportType(report.getReportType());
        dto.setGenerationDate(report.getGenerationDate());
        dto.setContent(report.getContent());
        dto.setSubjectId(report.getSubject().getIdSubject());
        dto.setStudentId(report.getStudent().getId());
        dto.setAcademicRecordId(report.getAcademicRecord().getIdAcademicRecord());
        return dto;
    }

    private Report convertToEntity(ReportDTO dto) {
        Report report = new Report();
        report.setIdReport(dto.getIdReport());
        report.setReportType(dto.getReportType());
        report.setGenerationDate(dto.getGenerationDate());
        report.setContent(dto.getContent());

        subjectRepository.findById((int) dto.getSubjectId()).ifPresent(report::setSubject);
        studentRepository.findById(dto.getStudentId()).ifPresent(report::setStudent);
        academicRecordRepository.findById(dto.getAcademicRecordId()).ifPresent(report::setAcademicRecord);

        return report;
    }
}
