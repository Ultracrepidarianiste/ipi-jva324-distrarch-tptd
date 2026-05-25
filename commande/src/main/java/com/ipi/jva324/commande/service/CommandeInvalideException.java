package com.ipi.jva324.commande.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CommandeInvalideException extends Exception {
    public CommandeInvalideException(String message) {
        super(message);
    }
}
