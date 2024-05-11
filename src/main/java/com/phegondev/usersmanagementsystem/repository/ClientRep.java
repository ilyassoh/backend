package com.phegondev.usersmanagementsystem.repository;

import com.phegondev.usersmanagementsystem.model.ClientModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRep extends JpaRepository<ClientModel, Long> {
    ClientModel findByNom(String nom);
    ClientModel findByNumeroMatricule(Long numeroMatricule);
    List<ClientModel> findAll();
    Page<ClientModel> findByNumeroMatriculeAndNom(Long numeroMatricule, String nom, Pageable p);
}
