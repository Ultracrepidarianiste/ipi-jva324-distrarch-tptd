package com.ipi.jva324.stock.web;

import com.ipi.jva324.stock.model.ProduitEnStock;
import com.ipi.jva324.stock.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
public class StockApi {

    @Autowired
    private ProduitService produitService;

    @GetMapping("/{id}")
    public ProduitEnStock getProduit(@PathVariable Long id) {
        return produitService.getProduit(id);
    }

    @GetMapping
    public List<ProduitEnStock> getProduits() {
        return produitService.getProduits();
    }
}
