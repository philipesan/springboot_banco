package com.example.demo.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/cliente")

public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> getClientes() {
        try {
            return clienteService.getClientes();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping(path="/novo")
    public ResponseEntity registraCliente(@RequestBody Cliente cliente) {
        try{
            return clienteService.adicionaNovoCliente(cliente);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
