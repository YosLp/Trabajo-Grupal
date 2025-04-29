package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.dto.GroupDTO;
import co.edu.udes.activity.backend.demo.models.Course;
import co.edu.udes.activity.backend.demo.models.Group;
import co.edu.udes.activity.backend.demo.models.Teacher;
import co.edu.udes.activity.backend.demo.repositories.CourseRepository;
import co.edu.udes.activity.backend.demo.repositories.GroupRepository;
import co.edu.udes.activity.backend.demo.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    private GroupDTO convertToDTO(Group group) {
        GroupDTO dto = new GroupDTO();
        dto.setId(group.getId());
        dto.setName(group.getName());
        dto.setStudentsAmount(group.getStudentsAmount());
        dto.setTeacherName(group.getTeacher() != null ? group.getTeacher().getFirstName() + " " + group.getTeacher().getLastName() : "Sin asignar");
        dto.setCourseName(group.getCourse() != null ? group.getCourse().getName() : "Sin asignar");
        return dto;
    }

    public List<GroupDTO> getAllGroups() {
        return groupRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<GroupDTO> getGroupById(Long id) {
        return groupRepository.findById(id).map(this::convertToDTO);
    }

    public GroupDTO saveGroup(Group group) {
        return convertToDTO(groupRepository.save(group));
    }

    public GroupDTO updateGroup(Long id, Group updatedGroup) {
        return groupRepository.findById(id).map(group -> {
            group.setName(updatedGroup.getName());
            group.setStudentsAmount(updatedGroup.getStudentsAmount());
            group.setTeacher(updatedGroup.getTeacher());
            group.setCourse(updatedGroup.getCourse());
            return convertToDTO(groupRepository.save(group));
        }).orElse(null);
    }

    public boolean deleteGroup(Long id) {
        if (groupRepository.existsById(id)) {
            groupRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean assignCourse(int groupId, int teacherId, int courseId) {
        Optional<Group> groupOpt = groupRepository.findById((long) groupId);
        Optional<Teacher> teacherOpt = teacherRepository.findById((long) teacherId);
        Optional<Course> courseOpt = courseRepository.findById((long) courseId);

        if (groupOpt.isPresent() && teacherOpt.isPresent() && courseOpt.isPresent()) {
            Group group = groupOpt.get();
            group.setTeacher(teacherOpt.get());
            group.setCourse(courseOpt.get());
            groupRepository.save(group);
            return true;
        }
        return false;
    }

    public boolean unassignCourse(int groupId) {
        Optional<Group> groupOpt = groupRepository.findById((long) groupId);
        if (groupOpt.isPresent()) {
            Group group = groupOpt.get();
            group.setTeacher(null);
            group.setCourse(null);
            groupRepository.save(group);
            return true;
        }
        return false;
    }
}
