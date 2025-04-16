package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Group;
import co.edu.udes.activity.backend.demo.repository.GroupRepository;

import co.edu.udes.activity.backend.demo.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Optional<Group> getGroupById(int id) {
    public Optional<Group> getGroupById(long id) {
        return groupRepository.findById(id);
    }

    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }

    public Group updateGroup(int id, Group updatedGroup) {
    public Group updateGroup(long id, Group updatedGroup) {
        return groupRepository.findById(id).map(group -> {
            group.setStudentsAmount(updatedGroup.getStudentsAmount());
            group.setTeacher(updatedGroup.getTeacher());
            group.setCourse(updatedGroup.getCourse());
            return groupRepository.save(group);
        }).orElse(null);
    }

    public boolean deleteGroup(int id) {
    public boolean deleteGroup(long id) {
        if (groupRepository.existsById(id)) {
            groupRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
