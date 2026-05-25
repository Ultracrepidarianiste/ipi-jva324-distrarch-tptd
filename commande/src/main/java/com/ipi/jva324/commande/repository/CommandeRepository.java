package com.ipi.jva324.commande.repository;

import com.ipi.jva324.commande.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
}
