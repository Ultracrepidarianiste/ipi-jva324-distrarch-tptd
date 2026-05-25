package com.ipi.jva324.commande;

import com.ipi.jva324.commande.model.Commande;
import com.ipi.jva324.commande.service.CommandeInvalideException;
import com.ipi.jva324.commande.service.CommandeService;
import com.ipi.jva324.commande.service.StockInsuffisantCommandeException;
import com.ipi.jva324.stock.model.ProduitEnStock;
import com.ipi.jva324.stock.repository.ProduitEnStockRepository;
import com.ipi.jva324.stock.service.ProduitService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestLocalConfiguration.class)
public class CommandeTest {

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private CommandeService commandeService;

    @Autowired
    private ProduitEnStockRepository produitEnStockRepository;

    @Autowired
    private ProduitService produitService;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void testStockSuffisant() throws StockInsuffisantCommandeException, CommandeInvalideException {
        ProduitEnStock p1 = produitService.getProduits().get(0);
        Commande c1 = commandeService.createCommande(new Commande(p1.getId(), 1l));
        Assertions.assertEquals("created", c1.getStatus());
        commandeService.validateCommande(c1);
        Assertions.assertEquals("validated", c1.getStatus());
    }

    @Test
    public void testStockPasSuffisant() throws CommandeInvalideException {
        ProduitEnStock p1 = produitService.getProduits().get(0);
        Commande c1 = commandeService.createCommande(new Commande(p1.getId(), 30l));
        Assertions.assertEquals("created", c1.getStatus());
        try {
            commandeService.validateCommande(c1);
            Assertions.fail("Le stock devrait etre insuffisant");
        } catch (StockInsuffisantCommandeException e) {
            Assertions.assertEquals(true, e.getMessage().contains("insuffisant"));
        }
    }
}
