package com.ipi.jva324.commande.web;

import com.ipi.jva324.commande.model.Commande;
import com.ipi.jva324.commande.service.CommandeInvalideException;
import com.ipi.jva324.commande.service.CommandeService;
import com.ipi.jva324.commande.service.StockInsuffisantCommandeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/commandes")
public class CommandeApi {

    @Autowired
    private CommandeService commandeService;

    @PostMapping
    public Commande createCommande(@RequestBody Commande commande) {
        return commandeService.createCommande(commande);
    }

    @PutMapping("{id}")
    public Commande updateCommande(@RequestBody Commande commande) {
        return commandeService.updateCommande(commande);
    }

    @PostMapping("{id}/validate")
    public Commande validateCommande(@RequestBody Commande commande)
            throws StockInsuffisantCommandeException, CommandeInvalideException {
        return commandeService.validateCommande(commande);
    }

    @GetMapping
    public List<Commande> getCommandes() {
        return commandeService.getCommandes();
    }
}
