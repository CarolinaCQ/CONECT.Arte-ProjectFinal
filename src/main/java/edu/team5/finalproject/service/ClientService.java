package edu.team5.finalproject.service;

import edu.team5.finalproject.entity.Client;
import edu.team5.finalproject.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public void create(Client newClient){
        Client client = new Client();

        client.setNickname(newClient.getNickname());
        client.setProfileImage(newClient.getProfileImage());
        client.setUser(newClient.getUser());

        clientRepository.save(client);
    }

    @Transactional
    public void update(Client newClient){
        Client client = clientRepository.findById(newClient.getId()).get();

        client.setNickname(newClient.getNickname());
        client.setProfileImage(newClient.getProfileImage());
        client.setUser(newClient.getUser());

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
}
