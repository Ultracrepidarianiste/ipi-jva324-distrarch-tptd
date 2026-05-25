package com.ipi.jva324.commande.service;

import com.ipi.jva324.stock.model.ProduitEnStock;
import com.ipi.jva324.stock.service.ProduitService;

public class CommandeProduitServiceLocalImpl implements CommandeProduitService {

    private final ProduitService produitService;

    public CommandeProduitServiceLocalImpl(ProduitService produitService) {
        this.produitService = produitService;
    }

    @Override
    public ProduitEnStock getProduit(Long produitId) {
        return produitService.getProduit(produitId);
    }
}
