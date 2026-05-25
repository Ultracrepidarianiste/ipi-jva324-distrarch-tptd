package com.ipi.jva324.commande.service;

import com.ipi.jva324.commande.model.Commande;
import com.ipi.jva324.commande.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandeInitService implements CommandLineRunner {

    @Autowired
    private CommandeService commandeService;

    @Autowired
    private CommandeRepository commandeRepository;

    @Override
    public void run(String... strings) throws Exception {
        if (!this.commandeService.getCommandes().isEmpty()) {
            return;
        }
        Commande c1 = new Commande(1l, 0, "test");
        c1.setStatus("created");
        commandeRepository.save(c1);
    }
}
