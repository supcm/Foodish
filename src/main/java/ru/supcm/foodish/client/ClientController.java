package ru.supcm.foodish.client;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/{clientPhone}")
    public Client getClient(@PathVariable String clientPhone) {
        //TODO: mb change finding from phone to uuid?
        return clientService.getClientByPhone(clientPhone);
    }

    @PostMapping("/add")
    public Client addClient(@RequestBody Client client) {
        try {
            return clientService.addClient(client);
        } catch (ClientAlreadyExistsException e) {
            return clientService.getClientByPhone(client.getPhoneNumber());
        }
    }
}
