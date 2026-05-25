package com.ipi.jva324.commande.service;

import com.ipi.jva324.commande.model.Commande;
import com.ipi.jva324.commande.repository.CommandeRepository;
import com.ipi.jva324.stock.model.ProduitEnStock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {

    protected Logger logger = LoggerFactory.getLogger(CommandeService.class);

    private int port;

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private CommandeProduitService commandeProduitService;

    @EventListener
    void onWebInit(WebServerInitializedEvent event) {
        this.port = event.getWebServer().getPort();
    }

    public List<Commande> getCommandes() {
        return commandeRepository.findAll();
    }

    public Commande getCommande(long id) {
        Optional<Commande> res = commandeRepository.findById(id);
        return res.isPresent() ? res.get() : null;
    }

    public Commande createCommande(Commande commande) {
        commande.setStatus("created");
        logger.debug("createCommande produitId=" + commande.getProduitId());
        ProduitEnStock produitEnStockFound = commandeProduitService.getProduit(commande.getProduitId());
        long quantiteDisponible = (produitEnStockFound == null) ? 0 : produitEnStockFound.getQuantiteDisponible();
        commande.setQuantiteDisponibleStockConnu(quantiteDisponible);
        return commandeRepository.save(commande);
    }

    public Commande validateCommande(Commande commande)
            throws StockInsuffisantCommandeException, CommandeInvalideException {
        if (commande.getQuantite() <= 0) {
            throw new CommandeInvalideException("quantite doit etre au moins 1");
        }
        ProduitEnStock produitEnStockFound = commandeProduitService.getProduit(commande.getProduitId());
        long quantiteDisponible = (produitEnStockFound == null) ? 0 : produitEnStockFound.getQuantiteDisponible();
        commande.setQuantiteDisponibleStockConnu(quantiteDisponible);
        if (commande.getQuantite() > quantiteDisponible) {
            throw new StockInsuffisantCommandeException();
        }
        commande.setStatus("validated");
        return commandeRepository.save(commande);
    }

    public Commande updateCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    public void deleteCommande(Commande commande) {
        commandeRepository.deleteById(commande.getId());
    }
}
