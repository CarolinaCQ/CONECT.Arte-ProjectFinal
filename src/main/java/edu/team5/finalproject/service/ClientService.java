package edu.team5.finalproject.service;

import edu.team5.finalproject.dto.ClientUserDto;
import edu.team5.finalproject.entity.Client;
import edu.team5.finalproject.entity.User;
import edu.team5.finalproject.entity.enums.Role;
import edu.team5.finalproject.exception.ExceptionMessages;
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

        if (clientRepository.existsByNickname(client.getNickname()))
            throw new MyException(ExceptionMessages.ALREADY_EXISTS_USERNAME.get());

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

    @Transactional
    public void updateEnableById(Long id) {       
        userRepository.enableById(clientRepository.findById(id).get().getUser().getId());
        clientRepository.enableById(id);        
    }

    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(clientRepository.findById(id).get().getUser().getId());
        clientRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Client getById(Long id) {
        return clientRepository.findById(id).get();
    }

    private void validateClient(Client client) throws MyException {
        if (!Utility.validate(Utility.USERNAME_PATTERN, client.getNickname()))
            throw new MyException(ExceptionMessages.INVALID_USERNAME.get());

        if (!Utility.validate(Utility.PASSWORD_PATTERN, client.getUser().getPassword()))
            throw new MyException(ExceptionMessages.INVALID_PASSWORD.get());

        if (!Utility.validate(Utility.EMAIL_PATTERN, client.getUser().getEmail()))
            throw new MyException(ExceptionMessages.INVALID_EMAIL.get());
    }

}
