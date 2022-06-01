
package edu.team5.finalproject.service;

import edu.team5.finalproject.entity.User;
import edu.team5.finalproject.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    
    public void create(User newUser){
       User user = userRepository.findById(newUser.getId()).get();
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());
        
        userRepository.save(newUser);
        
        
    }
      @Transactional
    public void update(User newUser){
        User user = userRepository.findById(newUser.getId()).get();
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());
      
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
    
}
