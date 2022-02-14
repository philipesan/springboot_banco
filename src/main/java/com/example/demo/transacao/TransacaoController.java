package com.example.demo.transacao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/transacoes")
public class TransacaoController {
    private final TransacaoService transacaoService;

    @Autowired
    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @GetMapping
    public Optional<List<Transacao>> getTransacoes(@RequestParam String data) {
        try{
            //DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            //LocalDate dataLocal = LocalDate.parse(data, formato);
            //LocalDateTime dataHoraLocal = dataLocal.atStartOfDay();
            return transacaoService.getTransacaoPorData(data);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping (path="/saque", consumes = {"application/json"})
    public ResponseEntity sacaValor(@RequestBody Transacao transacao) {
        try {
            return transacaoService.sacaValor(transacao);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping (path="/deposito", consumes = {"application/json"})
    public ResponseEntity depositaValor(@RequestBody Transacao transacao) {
        try {
            return transacaoService.depositaValor(transacao);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
