package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.dto.GroupDTO;
import co.edu.udes.activity.backend.demo.dto.GroupRequestDTO;
import co.edu.udes.activity.backend.demo.models.Group;
import co.edu.udes.activity.backend.demo.models.Subject;
import co.edu.udes.activity.backend.demo.models.Teacher;
import co.edu.udes.activity.backend.demo.repositories.GroupRepository;
import co.edu.udes.activity.backend.demo.repositories.SubjectRepository;
import co.edu.udes.activity.backend.demo.repositories.TeacherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ModelMapper modelMapper;

    public GroupDTO createGroup(GroupRequestDTO dto) {
        Group group = new Group();
        group.setName(dto.getName());
        group.setStudentsAmount(dto.getStudentsAmount());

        Subject subject = subjectRepository.findById(dto.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));

        Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Docente no encontrado"));

        group.setSubject(subject);
        group.setTeacher(teacher);

        return modelMapper.map(groupRepository.save(group), GroupDTO.class);
    }

    public List<GroupDTO> getAllGroups() {
        return groupRepository.findAll()
                .stream()
                .map(g -> modelMapper.map(g, GroupDTO.class))
                .collect(Collectors.toList());
    }

    public List<GroupDTO> getGroupsBySubject(Long subjectId) {
        return groupRepository.findBySubjectId(subjectId)
                .stream()
                .map(g -> modelMapper.map(g, GroupDTO.class))
                .collect(Collectors.toList());
    }

    public List<GroupDTO> getGroupsByTeacher(Long teacherId) {
        return groupRepository.findByTeacherId(teacherId)
                .stream()
                .map(g -> modelMapper.map(g, GroupDTO.class))
                .collect(Collectors.toList());
    }

    public GroupDTO updateStudentAmount(Long groupId, int amount) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));

        group.setStudentsAmount(amount);
        return modelMapper.map(groupRepository.save(group), GroupDTO.class);
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }
}
