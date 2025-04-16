package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Period;
import co.edu.udes.activity.backend.demo.repositories.PeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeriodService {

    @Autowired
    private PeriodRepository periodRepository;

    public List<Period> getAllPeriods() {
        return periodRepository.findAll();
    }

    public Optional<Period> getPeriodById(Integer id) {
        return periodRepository.findById(id);
    }

    public Period savePeriod(Period period) {
        return periodRepository.save(period);
    }

    public Period updatePeriod(Integer id, Period updatedPeriod) {
        return periodRepository.findById(id).map(period -> {
            period.setName(updatedPeriod.getName());
            period.setStardate(updatedPeriod.getStardate());
            period.setEnddate(updatedPeriod.getEnddate());
            return periodRepository.save(period);
        }).orElse(null);
    }

    public boolean deletePeriod(Integer id) {
        if (periodRepository.existsById(id)) {
            periodRepository.deleteById(id);
            return true;
        }
        return false;
    }
}