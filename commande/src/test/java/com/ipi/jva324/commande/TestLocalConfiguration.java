package com.ipi.jva324.commande;

import com.ipi.jva324.commande.service.CommandeProduitService;
import com.ipi.jva324.commande.service.CommandeProduitServiceLocalImpl;
import com.ipi.jva324.stock.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestLocalConfiguration {

    @Autowired
    private ProduitService produitService;

    @Bean
    @Primary
    public CommandeProduitService commandeProduitService() {
        return new CommandeProduitServiceLocalImpl(produitService);
    }
}
