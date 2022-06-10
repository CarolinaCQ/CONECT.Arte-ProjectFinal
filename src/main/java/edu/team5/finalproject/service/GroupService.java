package edu.team5.finalproject.service;

import edu.team5.finalproject.dto.GroupUserContactDto;
import edu.team5.finalproject.entity.Contact;
import edu.team5.finalproject.entity.Group;
import edu.team5.finalproject.entity.User;
import edu.team5.finalproject.entity.enums.Role;
import edu.team5.finalproject.exception.MyException;
import edu.team5.finalproject.mapper.GenericModelMapper;
import edu.team5.finalproject.repository.ContactRepository;
import edu.team5.finalproject.repository.GroupRepository;
import edu.team5.finalproject.repository.UserRepository;
import edu.team5.finalproject.utility.Utility;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class GroupService {

    private final GenericModelMapper mapper;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final ContactRepository contactRepository;

    public void create(GroupUserContactDto dto) throws MyException{
        Group group = mapper.map(dto, Group.class);
        validateGroup(group);

        User user = userRepository.findByEmail(dto.getUserEmail()).get();
        Contact contact = contactRepository.findByWhatsAppNumber(dto.getContactWhatsAppNumber()).get();

        group.setUser(user);
        group.setContact(contact);
        group.getUser().setRole(Role.GROUP);
        groupRepository.save(group);
    }

    @Transactional
    public void update(GroupUserContactDto dto) throws MyException{
        Group group = mapper.map(dto, Group.class);
        validateGroup(group);
        groupRepository.save(group);
    }

    @Transactional
    public void updateDeleted(Long id) { 
        Group group = groupRepository.findById(id).get();
        group.getUser().setDeleted(true);
        group.getContact().setDeleted(true);
        groupRepository.save(group);
    }

    @Transactional
    public void deleteById(Long id) {
        updateDeleted(id);
        groupRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Group getById(Long id) {
        return groupRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    private void validateGroup(Group group) throws MyException {
        if (group == null) throw new MyException("Exception message here.");
        Utility.validate(Utility.ONLY_NAMES, group.getName());
        Utility.validate(Utility.MAIL_PATTERN, group.getUser().getEmail());
        Utility.validate(Utility.PASSWORD_PATTERN, group.getUser().getPassword());        
    }
}
