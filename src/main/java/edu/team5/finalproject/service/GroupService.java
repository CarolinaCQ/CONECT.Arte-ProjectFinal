package edu.team5.finalproject.service;

import edu.team5.finalproject.entity.Client;
import edu.team5.finalproject.entity.Group;
import edu.team5.finalproject.repository.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public void create(Group newGroup){
        Group group = new Group();

        group.setName(newGroup.getName());
        group.setProfileImage(newGroup.getProfileImage());
        group.setDescription(newGroup.getDescription());
        group.setStyle(newGroup.getStyle());
        group.setMobility(newGroup.getMobility());
        group.setService(newGroup.getService());
        group.setImageList(newGroup.getImageList());
        group.setType(newGroup.getType());
        group.setLocale(newGroup.getLocale());
        group.setContact(newGroup.getContact());
        group.setUser(newGroup.getUser());

        groupRepository.save(group);
    }

    @Transactional
    public void update(Group newGroup){
        Group group = groupRepository.findById(newGroup.getId()).get();

        group.setName(newGroup.getName());
        group.setProfileImage(newGroup.getProfileImage());
        group.setDescription(newGroup.getDescription());
        group.setStyle(newGroup.getStyle());
        group.setMobility(newGroup.getMobility());
        group.setService(newGroup.getService());
        group.setImageList(newGroup.getImageList());
        group.setType(newGroup.getType());
        group.setLocale(newGroup.getLocale());
        group.setContact(newGroup.getContact());
        group.setUser(newGroup.getUser());

        groupRepository.save(group);
    }

    @Transactional(readOnly = true)
    public Group getById(Long id) {
        return groupRepository.findById(id).get();
    }

    @Transactional
    public void deleteById(Long id) {
        groupRepository.deleteById(id);
    }
}
