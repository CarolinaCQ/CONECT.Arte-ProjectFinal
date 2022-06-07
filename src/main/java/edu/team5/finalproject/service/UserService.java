
package edu.team5.finalproject.service;

import edu.team5.finalproject.dto.UserDto;
import edu.team5.finalproject.entity.User;
import edu.team5.finalproject.exception.MyException;
import edu.team5.finalproject.mapper.GenericModelMapper;
import edu.team5.finalproject.repository.UserRepository;
import edu.team5.finalproject.utility.Utility;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final GenericModelMapper mapper;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public void create(UserDto dto) throws MyException {
        User user = mapper.mapToUser(dto);        
        validateUser(user);  
        user.setPassword(encoder.encode(user.getPassword()));              
        userRepository.save(user);
    }

    @Transactional
    public void update(UserDto dto) throws MyException {
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

    private void validateUser(User user) throws MyException {
        if (user == null)
            throw new MyException("Exception message here.");
        Utility.validate(Utility.MAIL_PATTERN, user.getEmail());
        Utility.validate(Utility.PASSWORD_PATTERN, user.getPassword());
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(( )-> new UsernameNotFoundException(""));
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole());
        
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(true);
        
        session.setAttribute("id", user.getId());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("role", user.getRole());
        
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Collections.singletonList(authority));
    } 


}
