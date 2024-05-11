package com.phegondev.usersmanagementsystem.controller;

import com.phegondev.usersmanagementsystem.dto.ClientRequest;
import com.phegondev.usersmanagementsystem.dto.ClientRequestUp;
import com.phegondev.usersmanagementsystem.dto.ClientResp;
import com.phegondev.usersmanagementsystem.model.ClientModel;
import com.phegondev.usersmanagementsystem.repository.ClientRep;
import com.phegondev.usersmanagementsystem.service.ClientService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {
    @GetMapping("/nom/{nom}")
    public ResponseEntity<ClientResp> findByNom(@PathVariable String nom) {
        ClientModel clientModel = clientService.findByNom(nom);
        if (clientModel != null) {
            ClientResp response = new ClientResp(clientModel);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping("/matricule/{numeroMatricule}")
    public ResponseEntity<ClientResp> findByNumeroMatricule(@PathVariable Long numeroMatricule) {
        ClientModel clientModel = clientService.findByNumeroMatricule(numeroMatricule);
        if (clientModel != null) {
            ClientResp response = new ClientResp(clientModel);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping
    public ResponseEntity<List<ClientResp>> findAll(@RequestParam(defaultValue = "") String search) {
        List<ClientModel> clients = clientService.findAll(search);
        List<ClientResp> clientsResp = clients.stream()
                .map(ClientResp::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientsResp);
    }
    @GetMapping("/page")
    public ResponseEntity<Page<ClientResp>> findAllPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size,
            @RequestParam(defaultValue = "") String search) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ClientModel> clientsPage = clientService.findAllPage(search, pageable);
        return ResponseEntity.ok(clientsPage.map(ClientResp::new));
    }
    @PostMapping
    public ResponseEntity<ClientResp> createClient(@Valid @RequestBody ClientRequest clientReq) {
        ClientModel clientModel = clientService.createClient(clientReq);
        if (clientModel != null) {
            ClientResp response = new ClientResp(clientModel);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClientResp> editClient(@PathVariable Long id, @Valid @RequestBody ClientRequestUp clientReqUp) {
        ClientModel clientModel = clientService.editClient(id, clientReqUp);
        if (clientModel != null) {
            ClientResp response = new ClientResp(clientModel);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<ClientResp> deleteClient(@PathVariable Long id) {
        ClientResp response = new ClientResp(clientService.deleteClient(id));
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private final ClientService clientService;
}
