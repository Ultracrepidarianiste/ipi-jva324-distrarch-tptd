package com.ipi.jva324.commande.service;

public class StockInsuffisantCommandeException extends CommandeInvalideException {
    public StockInsuffisantCommandeException() {
        super("Quantite en stock insuffisante, commande non validee !");
    }
}
