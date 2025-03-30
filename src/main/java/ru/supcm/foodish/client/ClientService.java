package ru.supcm.foodish.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepo repository;

    public Client getClientByPhone(String phoneNumber) throws NoSuchClientException {
        return repository.findByPhoneNumber(phoneNumber).orElseThrow(NoSuchClientException::new);
    }

    public Client addClient(Client client) throws ClientAlreadyExistsException {
        if(repository.findByPhoneNumber(client.getPhoneNumber()).isPresent())
            throw new ClientAlreadyExistsException();
        return repository.save(client);
    }
}
