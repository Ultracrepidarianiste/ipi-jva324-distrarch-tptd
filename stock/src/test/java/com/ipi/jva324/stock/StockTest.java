package com.ipi.jva324.stock;

import com.ipi.jva324.stock.model.ProduitEnStock;
import com.ipi.jva324.stock.service.ProduitService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StockTest {

    @Autowired
    private ProduitService produitService;

    @Test
    public void testGetProduitsNominal() {
        List<ProduitEnStock> produits = produitService.getProduits();
        Assertions.assertNotNull(produits);
        Assertions.assertFalse(produits.isEmpty());
        ProduitEnStock p = produits.get(0);
        Assertions.assertNotNull(p.getNom());
        Assertions.assertTrue(p.getQuantiteDisponible() >= 0);
    }
}
