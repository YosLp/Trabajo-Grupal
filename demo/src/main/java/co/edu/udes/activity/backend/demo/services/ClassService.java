package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.dto.ClassDTO;
import co.edu.udes.activity.backend.demo.dto.ClassRequestDTO;
import co.edu.udes.activity.backend.demo.models.Class;
import co.edu.udes.activity.backend.demo.models.Classroom;
import co.edu.udes.activity.backend.demo.models.Group;
import co.edu.udes.activity.backend.demo.repositories.ClassRepository;
import co.edu.udes.activity.backend.demo.repositories.ClassroomRepository;
import co.edu.udes.activity.backend.demo.repositories.GroupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ClassDTO createClass(ClassRequestDTO dto) {
        Group group = groupRepository.findById(dto.getGroupId())
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));

        Classroom classroom = classroomRepository.findById(dto.getClassroomId())
                .orElseThrow(() -> new RuntimeException("Salón no encontrado"));

        Class newClass = new Class();
        newClass.setFecha(dto.getFecha());
        newClass.setStarHour(dto.getStarHour());
        newClass.setEndHour(dto.getEndHour());
        newClass.setGroup(group);
        newClass.setClassroom(classroom);

        return modelMapper.map(classRepository.save(newClass), ClassDTO.class);
    }

    public List<ClassDTO> getAllClasses() {
        return classRepository.findAll()
                .stream()
                .map(c -> modelMapper.map(c, ClassDTO.class))
                .collect(Collectors.toList());
    }

    public List<ClassDTO> getClassesByGroup(Long groupId) {
        return classRepository.findByGroupId(groupId)
                .stream()
                .map(c -> modelMapper.map(c, ClassDTO.class))
                .collect(Collectors.toList());
    }

    public ClassDTO updateClass(Long id, ClassRequestDTO dto) {
        Class classEntity = classRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        classEntity.setFecha(dto.getFecha());
        classEntity.setStarHour(dto.getStarHour());
        classEntity.setEndHour(dto.getEndHour());

        return modelMapper.map(classRepository.save(classEntity), ClassDTO.class);
    }

    public void deleteClass(Long id) {
        classRepository.deleteById(id);
    }
}