package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.dto.CareerDTO;
import co.edu.udes.activity.backend.demo.dto.CareerRequestDTO;
import co.edu.udes.activity.backend.demo.dto.SubjectDTO;
import co.edu.udes.activity.backend.demo.models.Career;
import co.edu.udes.activity.backend.demo.models.Semester;
import co.edu.udes.activity.backend.demo.models.Subject;
import co.edu.udes.activity.backend.demo.repositories.CareerRepository;
import co.edu.udes.activity.backend.demo.repositories.SemesterRepository;
import co.edu.udes.activity.backend.demo.repositories.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CareerService {

    @Autowired
    private CareerRepository careerRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SemesterRepository semesterRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CareerDTO createCareer(CareerRequestDTO dto) {
        Career career = modelMapper.map(dto, Career.class);
        return modelMapper.map(careerRepository.save(career), CareerDTO.class);
    }

    public List<CareerDTO> getAllCareers() {
        return careerRepository.findAll()
                .stream()
                .map(c -> modelMapper.map(c, CareerDTO.class))
                .collect(Collectors.toList());
    }

    public CareerDTO assignSubjectToCareer(Long careerId, long subjectId) {
        Career career = careerRepository.findById(careerId)
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));

        career.getSubjects().add(subject);
        return modelMapper.map(careerRepository.save(career), CareerDTO.class);
    }

    public List<SubjectDTO> getSubjectsByCareer(Long careerId) {
        Career career = careerRepository.findById(careerId)
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        return career.getSubjects()
                .stream()
                .map(subject -> modelMapper.map(subject, SubjectDTO.class))
                .collect(Collectors.toList());
    }

    public List<CareerDTO> getCareersBySemester(Long semesterId) {
        Semester semester = semesterRepository.findById(semesterId)
                .orElseThrow(() -> new RuntimeException("Semestre no encontrado"));

        return semester.getCareers()
                .stream()
                .map(c -> modelMapper.map(c, CareerDTO.class))
                .collect(Collectors.toList());
    }

    public void deleteCareer(Long idCareer) {
        careerRepository.deleteById(idCareer);
    }
}