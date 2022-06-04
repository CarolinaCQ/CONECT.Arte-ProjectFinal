package edu.team5.finalproject.service;

import edu.team5.finalproject.dto.GroupUserContactDto;
import edu.team5.finalproject.entity.Group;
import edu.team5.finalproject.exception.MyException;
import edu.team5.finalproject.mapper.GenericModelMapper;
import edu.team5.finalproject.repository.GroupRepository;
import edu.team5.finalproject.utility.Utility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class GroupService {

    private final GenericModelMapper mapper;
    private final GroupRepository groupRepository;

    public void create(GroupUserContactDto dto) throws MyException{
        Group group = mapper.mapToGroup(dto);        
        validateGroup(group);        
        groupRepository.save(group);
    }

    @Transactional
    public void update(GroupUserContactDto dto) throws MyException{
        Group group = mapper.mapToGroup(dto);        
        validateGroup(group);        
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

    private void validateGroup(Group newGroup) throws MyException{
        if(newGroup == null) throw new MyException("Exception message here.");
        Utility.validate(Utility.ONLY_NAMES, newGroup.getName());      

    }
}
