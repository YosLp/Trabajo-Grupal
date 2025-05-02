package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.dto.SubjectDTO;
import co.edu.udes.activity.backend.demo.dto.SubjectRequestDTO;
import co.edu.udes.activity.backend.demo.models.Subject;
import co.edu.udes.activity.backend.demo.repositories.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ModelMapper modelMapper;

    public SubjectDTO createSubject(SubjectRequestDTO dto) {
        Subject subject = modelMapper.map(dto, Subject.class);
        return modelMapper.map(subjectRepository.save(subject), SubjectDTO.class);
    }

    public List<SubjectDTO> getAllSubjects() {
        return subjectRepository.findAll()
                .stream()
                .map(s -> modelMapper.map(s, SubjectDTO.class))
                .collect(Collectors.toList());
    }

    public SubjectDTO assignPrerequisite(Long subjectId, Long prerequisiteId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));

        Subject prerequisite = subjectRepository.findById(prerequisiteId)
                .orElseThrow(() -> new RuntimeException("Prerequisito no encontrado"));

        subject.getPrerequisite().add(prerequisite);
        return modelMapper.map(subjectRepository.save(subject), SubjectDTO.class);
    }

    public List<SubjectDTO> getPrerequisites(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));

        return subject.getPrerequisite()
                .stream()
                .map(p -> modelMapper.map(p, SubjectDTO.class))
                .collect(Collectors.toList());
    }

    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }
}