package co.edu.udes.activity.backend.demo.services;


import co.edu.udes.activity.backend.demo.dto.SpaceDTO;
import co.edu.udes.activity.backend.demo.dto.SpaceRequestDTO;
import co.edu.udes.activity.backend.demo.models.Space;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.udes.activity.backend.demo.repositories.SpaceRepository;
import java.util.List;
import java.util.stream.Collectors;


@Service

public class SpaceService {

    @Autowired
    private SpaceRepository spaceRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<SpaceDTO> getAllSpaces() {
        return spaceRepository.findAll().stream()
                .map(space -> modelMapper.map(space, SpaceDTO.class))
                .collect(Collectors.toList());
    }

    public SpaceDTO getSpaceById(Long id) {
        return spaceRepository.findById(id)
                .map(space -> modelMapper.map(space, SpaceDTO.class))
                .orElse(null);
    }

    public SpaceDTO saveSpace(SpaceRequestDTO spaceRequestDTO) {
        Space space = modelMapper.map(spaceRequestDTO, Space.class);
        return modelMapper.map(spaceRepository.save(space), SpaceDTO.class);
    }

    public SpaceDTO updateSpace(Long id, SpaceRequestDTO updatedDTO) {
        return spaceRepository.findById(id).map(space -> {
            space.setName(updatedDTO.getName());
            space.setType(updatedDTO.getType());
            space.setCapacity(updatedDTO.getCapacity());
            space.setAvailable(updatedDTO.isAvailable());
            return modelMapper.map(spaceRepository.save(space), SpaceDTO.class);
        }).orElse(null);
    }

    public boolean deleteSpace(Long id) {
        if (spaceRepository.existsById(id)) {
            spaceRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean checkAvailability(Long spaceId) {
        return spaceRepository.findById(spaceId)
                .map(Space::isAvailable)
                .orElse(false);
    }

    public boolean updateAvailability(Long spaceId, boolean available) {
        return spaceRepository.findById(spaceId).map(space -> {
            space.setAvailable(available);
            spaceRepository.save(space);
            return true;
        }).orElse(false);
    }
}
