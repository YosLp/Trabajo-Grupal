package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.dto.CutDTO;
import co.edu.udes.activity.backend.demo.dto.CutRequestDTO;
import co.edu.udes.activity.backend.demo.models.Cut;
import co.edu.udes.activity.backend.demo.models.Enrollment;
import co.edu.udes.activity.backend.demo.repositories.CutRepository;
import co.edu.udes.activity.backend.demo.repositories.EnrollmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CutService {

    @Autowired
    private CutRepository cutRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<CutDTO> getAllCuts() {
        return cutRepository.findAll()
                .stream()
                .map(cut -> modelMapper.map(cut, CutDTO.class))
                .collect(Collectors.toList());
    }

    public CutDTO getCutById(Long id) {
        Cut cut = cutRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Corte no encontrado"));
        return modelMapper.map(cut, CutDTO.class);
    }

    public CutDTO createCut(CutRequestDTO dto) {
        Cut cut = modelMapper.map(dto, Cut.class);
        Enrollment enrollment = enrollmentRepository.findById(dto.getEnrollmentId())
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrada"));
        cut.setEnrollment(enrollment);
        return modelMapper.map(cutRepository.save(cut), CutDTO.class);
    }

    public CutDTO updateCut(Long id, CutRequestDTO dto) {
        Cut cut = cutRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Corte no encontrado"));

        cut.setName(dto.getName());
        cut.setPercentage(dto.getPercentage());
        cut.setCutValue(dto.getCutValue());

        return modelMapper.map(cutRepository.save(cut), CutDTO.class);
    }

    public void deleteCut(Long id) {
        cutRepository.deleteById(id);
    }

    public List<CutDTO> getCutsByEnrollmentId(Long enrollmentId) {
        return cutRepository.findByEnrollmentId(enrollmentId)
                .stream()
                .map(cut -> modelMapper.map(cut, CutDTO.class))
                .collect(Collectors.toList());
    }

    public Double calculateFinalScoreByEnrollment(Long enrollmentId) {
        List<Cut> cuts = cutRepository.findByEnrollmentId(enrollmentId);
        return cuts.stream()
                .mapToDouble(c -> c.getCutValue() * c.getPercentage())
                .sum();
    }
}
