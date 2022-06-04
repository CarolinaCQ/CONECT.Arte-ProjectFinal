
package edu.team5.finalproject.service;

import edu.team5.finalproject.dto.ClientUserDto;
import edu.team5.finalproject.entity.User;
import edu.team5.finalproject.exception.MyException;
import edu.team5.finalproject.mapper.GenericModelMapper;
import edu.team5.finalproject.repository.UserRepository;
import edu.team5.finalproject.utility.Utility;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final GenericModelMapper mapper;
    private final UserRepository userRepository;

    public void create(ClientUserDto dto) throws MyException {
        User user = mapper.mapToUser(dto);        
        validateUser(user);        
        userRepository.save(user);
    }

    @Transactional
    public void update(ClientUserDto dto) throws MyException {
        User user = mapper.mapToUser(dto);        
        validateUser(user);        
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    private void validateUser(User newUser) throws MyException {
        if (newUser == null)
            throw new MyException("Exception message here.");
        Utility.validate(Utility.MAIL_PATTERN, newUser.getEmail());
        Utility.validate(Utility.PASSWORD_PATTERN, newUser.getPassword());

    }

}
