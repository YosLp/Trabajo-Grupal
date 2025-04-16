package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Career;
import co.edu.udes.activity.backend.demo.repositories.CareerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CareerService {

    @Autowired
    private CareerRepository careerRepository;

    public List<Career> getAllCareers() {
        return careerRepository.findAll();
    }

    public Optional<Career> getCareerById(Integer id) {
        return careerRepository.findById(id);
    }

    public Career saveCareer(Career career) {
        return careerRepository.save(career);
    }

    public Career updateCareer(Integer id, Career updatedCareer) {
        return careerRepository.findById(id).map(career -> {
            career.setName(updatedCareer.getName());
            career.setDescription(updatedCareer.getDescription());
            return careerRepository.save(career);
        }).orElse(null);
    }

    public boolean deleteCareer(Integer id) {
        if (careerRepository.existsById(id)) {
            careerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}