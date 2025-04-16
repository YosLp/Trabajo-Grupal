package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Subject;
import co.edu.udes.activity.backend.demo.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Optional<Subject> getSubjectById(Integer id) {
        return subjectRepository.findById(id);
    }

    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Subject updateSubject(Integer id, Subject updatedSubject) {
        return subjectRepository.findById(id).map(subject -> {
            subject.setName(updatedSubject.getName());
            subject.setContent(updatedSubject.getContent());
            subject.setObjetives(updatedSubject.getObjetives());
            subject.setCompetencies(updatedSubject.getCompetencies());
            subject.setPrerequisite(updatedSubject.getPrerequisite());
            return subjectRepository.save(subject);
        }).orElse(null);
    }

    public boolean deleteSubject(Integer id) {
        if (subjectRepository.existsById(id)) {
            subjectRepository.deleteById(id);
            return true;
        }
        return false;
    }
}