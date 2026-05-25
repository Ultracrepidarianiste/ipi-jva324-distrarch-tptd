package com.ipi.jva324.commande.service;

import com.ipi.jva324.stock.model.ProduitEnStock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

// @Component
public class CommandeProduitServiceRESTHALImpl implements CommandeProduitService {

    @Value("${stock.service.url:http://localhost:8081}")
    private String stockServiceUrl;

    @Override
    public ProduitEnStock getProduit(Long produitId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = stockServiceUrl + "/api/data-rest/produitEnStocks/" + produitId;
        try {
            return restTemplate.getForObject(url, ProduitEnStock.class);
        } catch (Exception e) {
            return null;
        }
    }
}
