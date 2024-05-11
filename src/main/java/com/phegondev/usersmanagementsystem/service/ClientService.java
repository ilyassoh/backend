package com.phegondev.usersmanagementsystem.service;

import com.phegondev.usersmanagementsystem.dto.ClientRequest;
import com.phegondev.usersmanagementsystem.dto.ClientRequestUp;
import com.phegondev.usersmanagementsystem.model.ClientModel;
import com.phegondev.usersmanagementsystem.repository.ClientRep;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
    public ClientModel findByNom(String nom) {
        return clientRep.findByNom(nom);
    }
    public ClientModel findByNumeroMatricule(Long numeroMatricule) {
        return clientRep.findByNumeroMatricule(numeroMatricule);
    }
    public List<ClientModel> findAll(String se) {
        if (se != null && !se.isEmpty()) {
            ClientModel client = clientRep.findByNumeroMatricule(Long.valueOf(se));
            if (client != null) {
                return Collections.singletonList(client);
            } else {
                return Collections.emptyList();
            }
        } else {
            return clientRep.findAll();
        }
    }
    public Page<ClientModel> findAllPage(String se, Pageable p) {
        if (se != null && !se.isEmpty()) {
            Long numeroMatricule = Long.valueOf(se);
            return clientRep.findByNumeroMatriculeAndNom(numeroMatricule, se, p);
        } else {
            return clientRep.findAll(p);
        }
    }
    public ClientModel createClient(ClientRequest clientReq) {
        ClientModel existingClient = clientRep.findByNumeroMatricule(clientReq.getNumeroMatricule());
        if (existingClient != null) {
            return null;
        }
        ClientModel newClient = new ClientModel();
        newClient.setNumeroMatricule(clientReq.getNumeroMatricule());
        newClient.setNumeroTelephone(clientReq.getNumeroTelephone());
        newClient.setAdresse(clientReq.getAdresse());
        newClient.setNom(clientReq.getNom());
        newClient.setEmail(clientReq.getEmail());
        newClient.setGenre(clientReq.getGenre());
        newClient.setAge(clientReq.getAge());
        return clientRep.save(newClient);
    }
    @Transactional
    public ClientModel editClient(Long id, ClientRequestUp clientRequestUp) {
        Optional<ClientModel> existingClientOptional = clientRep.findById(id);
        if (existingClientOptional.isEmpty()) {
            return null;
        }
        ClientModel existingClient = existingClientOptional.get();
        existingClient.setNumeroMatricule(clientRequestUp.getNumeroMatricule());
        existingClient.setNumeroTelephone(clientRequestUp.getNumeroTelephone());
        existingClient.setAdresse(clientRequestUp.getAdresse());
        existingClient.setNom(clientRequestUp.getNom());
        existingClient.setEmail(clientRequestUp.getEmail());
        existingClient.setGenre(clientRequestUp.getGenre());
        existingClient.setAge(clientRequestUp.getAge());
        return clientRep.save(existingClient);
    }
    @Transactional
    public ClientModel deleteClient(Long id) {
        Optional<ClientModel> existingClientOptional = clientRep.findById(id);
        if (existingClientOptional.isEmpty()) {
            return null;
        }
        ClientModel existingClient = existingClientOptional.get();
        clientRep.deleteById(id);
        return existingClient;
    }
    private final ClientRep clientRep;
}
