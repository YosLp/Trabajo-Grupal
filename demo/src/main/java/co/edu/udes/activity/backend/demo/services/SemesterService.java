package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.dto.CareerDTO;
import co.edu.udes.activity.backend.demo.dto.SemesterDTO;
import co.edu.udes.activity.backend.demo.dto.SemesterRequestDTO;
import co.edu.udes.activity.backend.demo.models.Career;
import co.edu.udes.activity.backend.demo.models.Semester;
import co.edu.udes.activity.backend.demo.repositories.CareerRepository;
import co.edu.udes.activity.backend.demo.repositories.SemesterRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SemesterService {

    @Autowired
    private SemesterRepository semesterRepository;

    @Autowired
    private CareerRepository careerRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<SemesterDTO> getAllSemesters() {
        return semesterRepository.findAll()
                .stream()
                .map(s -> modelMapper.map(s, SemesterDTO.class))
                .collect(Collectors.toList());
    }

    public SemesterDTO getSemesterById(Long id) {
        Semester semester = semesterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Semestre no encontrado"));
        return modelMapper.map(semester, SemesterDTO.class);
    }

    public SemesterDTO createSemester(SemesterRequestDTO dto) {
        Semester semester = new Semester();
        semester.setName(dto.getName());
        return modelMapper.map(semesterRepository.save(semester), SemesterDTO.class);
    }

    public SemesterDTO addCareerToSemester(Long semesterId, Long careerId) {
        Semester semester = semesterRepository.findById(semesterId)
                .orElseThrow(() -> new RuntimeException("Semestre no encontrado"));
        Career career = careerRepository.findById(careerId)
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        semester.getCareers().add(career);
        Semester updated = semesterRepository.save(semester);
        return modelMapper.map(updated, SemesterDTO.class);
    }

    public void deleteSemester(Long id) {
        semesterRepository.deleteById(id);
    }

    public List<CareerDTO> getCareersBySemester(Long semesterId) {
        Semester semester = semesterRepository.findById(semesterId)
                .orElseThrow(() -> new RuntimeException("Semestre no encontrado"));

        return semester.getCareers()
                .stream()
                .map(career -> modelMapper.map(career, CareerDTO.class))
                .collect(Collectors.toList());
    }
}