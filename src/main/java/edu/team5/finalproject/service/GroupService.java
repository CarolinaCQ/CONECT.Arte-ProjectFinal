package edu.team5.finalproject.service;

import edu.team5.finalproject.dto.GroupUserContactDto;
import edu.team5.finalproject.entity.Contact;
import edu.team5.finalproject.entity.Group;
import edu.team5.finalproject.entity.User;
import edu.team5.finalproject.entity.enums.Locale;
import edu.team5.finalproject.entity.enums.Role;
import edu.team5.finalproject.entity.enums.Style;
import edu.team5.finalproject.exception.ExceptionMessages;
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
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class GroupService {

    private final GenericModelMapper mapper;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final ContactRepository contactRepository;
    private final ImageService imageService;

    public void create(GroupUserContactDto dto, MultipartFile image) throws MyException {
        Group group = mapper.map(dto, Group.class);

        group.setProfileImage((!image.isEmpty()) ? imageService.imageToString(image) : imageService.defaultImage());    

        validateCreate(group);

        User user = userRepository.findByEmail(dto.getUserEmail()).get();
        Contact contact = contactRepository.findByWhatsAppNumber(dto.getContactWhatsAppNumber()).get();

        group.setUser(user);
        group.setContact(contact);
        group.setDeleted(false);
        group.getUser().setRole(Role.GROUP);
        groupRepository.save(group);
    }

    @Transactional
    public void update(GroupUserContactDto dto, MultipartFile image, List<MultipartFile> imageList) throws MyException {
        Group group = mapper.map(dto, Group.class); 
       
        if(!image.isEmpty()) group.setProfileImage(imageService.imageToString(image));
        //if(!imageList.isEmpty()) group.setImageList(imageService.imagesToString(imageList));

        group.setUser(groupRepository.findById(dto.getId()).get().getUser());
        group.setContact(groupRepository.findById(dto.getId()).get().getContact());

        validateUpdate(group);
        groupRepository.save(group);
    }

    @Transactional
    public void updateEnableById(Long id) {
        groupRepository.enableById(id);
    }

    @Transactional
    public void deleteById(Long id) {
        groupRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Group getById(Long id) {
        return groupRepository.findById(id).get();
    }
    
    @Transactional(readOnly = true)
    public Group getByIdUser(Long id) {
        return groupRepository.getByIdUser(id).get();
    }

    @Transactional(readOnly = true)
    public List<Group> getAll() {
        return groupRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Group> getAllDance() {
        return groupRepository.getByTypeDance();
    }
    
    @Transactional(readOnly = true)
    public List<Group> getAllMusic() {
        return groupRepository.getByTypeMusic();
    }
    
    @Transactional(readOnly = true)
    public List<Group> getByStyleAndLocaleDance(Style style, Locale locale) {
        return groupRepository.getByStyleAndLocaleDance(style, locale);
    }
    
    @Transactional(readOnly = true)
    public List<Group> getByStyleAndLocaleMusic(Style style, Locale locale) {
        return groupRepository.getByStyleAndLocaleMusic(style, locale);
    }

    private void validateCreate(Group group) throws MyException { // fijarse que mas falta validar
        if (!Utility.validate(Utility.NAME_PATTERN, group.getName()))
            throw new MyException(ExceptionMessages.INVALID_GROUP_NAME.get());

        if (!Utility.validate(Utility.EMAIL_PATTERN, group.getUser().getEmail()))
            throw new MyException(ExceptionMessages.INVALID_EMAIL.get());

        if (!Utility.validate(Utility.PASSWORD_PATTERN, group.getUser().getPassword()))
            throw new MyException(ExceptionMessages.INVALID_PASSWORD.get());

        if (groupRepository.existsByName(group.getName())) 
            throw new MyException(ExceptionMessages.ALREADY_EXISTS_GROUP_NAME.get());
    }

    private void validateUpdate(Group group) throws MyException { // fijarse que mas falta validar
        if (!Utility.validate(Utility.NAME_PATTERN, group.getName()))
            throw new MyException(ExceptionMessages.INVALID_GROUP_NAME.get());

        if (groupRepository.existsByName(group.getName())) 
            throw new MyException(ExceptionMessages.ALREADY_EXISTS_GROUP_NAME.get());
    }
}
