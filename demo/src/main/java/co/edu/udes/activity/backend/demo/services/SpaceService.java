package co.edu.udes.activity.backend.demo.services;


import co.edu.udes.activity.backend.demo.models.Space;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.udes.activity.backend.demo.repositories.SpaceRepository;
import java.util.List;
import java.util.Optional;

@Service
public class SpaceService {

    @Autowired
    private SpaceRepository spaceRepository;

    public List<Space> getAllSpaces() {
        return spaceRepository.findAll();
    }

    public Optional<Space> getSpaceById(Long id) {
        return spaceRepository.findById(id);
    }

    public Space saveSpace(Space space) {
        return spaceRepository.save(space);
    }

    public Space updateSpace(Long id, Space updatedSpace) {
        return spaceRepository.findById(id).map(space -> {
            space.setName(updatedSpace.getName());
            space.setType(updatedSpace.getType());
            space.setCapacity(updatedSpace.getCapacity());
            space.setAvailable(updatedSpace.isAvailable());
            return spaceRepository.save(space);
        }).orElse(null);
    }

    public boolean deleteSpace(Long id) {
        if (spaceRepository.existsById(id)) {
            spaceRepository.deleteById(id);
            return true;
        }
        return false;
    }
}