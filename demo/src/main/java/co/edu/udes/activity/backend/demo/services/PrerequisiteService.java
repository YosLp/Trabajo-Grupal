package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Prerequisite;
import co.edu.udes.activity.backend.demo.repositories.PrerequisiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrerequisiteService {

    @Autowired
    private PrerequisiteRepository prerequisiteRepository;

    public List<Prerequisite> getAllPrerequisites() {
        return prerequisiteRepository.findAll();
    }

    public Optional<Prerequisite> getPrerequisiteById(Integer id) {
        return prerequisiteRepository.findById(id);
    }

    public Prerequisite savePrerequisite(Prerequisite prerequisite) {
        return prerequisiteRepository.save(prerequisite);
    }

    public Prerequisite updatePrerequisite(Integer id, Prerequisite updatedPrerequisite) {
        return prerequisiteRepository.findById(id).map(prereq -> {
            prereq.setSubject(updatedPrerequisite.getSubject());
            prereq.setDescription(updatedPrerequisite.getDescription());
            return prerequisiteRepository.save(prereq);
        }).orElse(null);
    }

    public boolean deletePrerequisite(Integer id) {
        if (prerequisiteRepository.existsById(id)) {
            prerequisiteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}