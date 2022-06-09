package edu.team5.finalproject.service;

import edu.team5.finalproject.dto.ClientUserDto;
import edu.team5.finalproject.entity.Client;
import edu.team5.finalproject.entity.User;
import edu.team5.finalproject.entity.enums.Role;
import edu.team5.finalproject.exception.MyException;
import edu.team5.finalproject.mapper.GenericModelMapper;
import edu.team5.finalproject.repository.ClientRepository;
import edu.team5.finalproject.repository.UserRepository;
import edu.team5.finalproject.utility.Utility;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final GenericModelMapper mapper;
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    public void create(ClientUserDto dto) throws MyException { 
        Client client = mapper.map(dto, Client.class);
        validateClient(client);                                                 

        User user = userRepository.findByEmail(dto.getUserEmail()).get();

        client.setUser(user);
        client.getUser().setRole(Role.CLIENT);               
        clientRepository.save(client);
    }

    @Transactional
    public void update(ClientUserDto dto) throws MyException {
        Client client = mapper.map(dto, Client.class);  
        validateClient(client);
        clientRepository.save(client);
    }

    @Transactional(readOnly = true)
    public Client getById(Long id) {
        return clientRepository.findById(id).get();
    }

    @Transactional
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    private void validateClient(Client client) throws MyException {
        if (client == null) throw new MyException("Exception message here.");
        
        Utility.validate(Utility.ONLY_NAMES, client.getNickname());
        Utility.validate(Utility.PASSWORD_PATTERN, client.getUser().getPassword());
        Utility.validate(Utility.MAIL_PATTERN, client.getUser().getEmail());
    }

   

}
