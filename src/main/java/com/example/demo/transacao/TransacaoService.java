package com.example.demo.transacao;

import com.example.demo.cliente.Cliente;
import com.example.demo.cliente.ClienteRepository;
import com.example.demo.excecoes.RespostaApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.List;

import java.util.Optional;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public TransacaoService(TransacaoRepository transacaoRepository, ClienteRepository clienteRepository) {
        this.transacaoRepository = transacaoRepository;
        this.clienteRepository = clienteRepository;
    }

    public Optional<List<Transacao>> getTransacaoPorData(String data) {
        return transacaoRepository.findAllByDtTransacao(data);
    }

    public ResponseEntity sacaValor(Transacao transacao) {

        RespostaApi respostaApi;
        ResponseEntity resposta;

        transacao.setCliente(validaConta(transacao.cliente.getNumConta()));

        if (!transacao.getSaque()) {
            respostaApi = new RespostaApi("NOK"
                    , "Tipo de transação inválida");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(respostaApi);
        }

        if (transacao.getCliente() == null) {
            respostaApi = new RespostaApi("NOK"
                    , "Conta Inexistente");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(respostaApi);
        }

        if (transacao.executaSaque()) {
            salvaTransacaoCliente(transacao);
            respostaApi = new RespostaApi("OK"
                    ,"Transação realizada com sucesso"
                    , List.of(transacao));

            resposta = ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(respostaApi);
        } else {
            salvaTransacaoCliente(transacao);
            respostaApi = new RespostaApi("NOK"
                    , "Transação" + transacao.getId()
                    + "não aceita, verifique o saldo do cliente");

            resposta = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(respostaApi);

        }

        return resposta;
    }

    public ResponseEntity depositaValor(Transacao transacao) {
        RespostaApi respostaApi;
        ResponseEntity resposta;

        transacao.setCliente(validaConta(transacao.cliente.getNumConta()));

        if (transacao.getSaque()) {
            respostaApi = new RespostaApi("NOK"
                    , "Tipo de transação inválida");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(respostaApi);
        }

        if (transacao.getCliente() == null) {
            respostaApi = new RespostaApi("NOK"
                    , "Conta Inexistente");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(respostaApi);
        }

        if (transacao.executaDeposito()) {
            transacao = salvaTransacaoCliente(transacao);
            respostaApi = new RespostaApi("OK"
                        ,"Transação realizada com sucesso"
                        , List.of(transacao));

            resposta = ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(respostaApi);
        } else {
            transacao = salvaTransacaoCliente(transacao);
            respostaApi = new RespostaApi("NOK"
                    , "Transação" + transacao.getId()
                    + "não aceita, verifique os dados do cliente");

            resposta = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(respostaApi);

        }

        return resposta;
    }

    public Transacao salvaTransacaoCliente(Transacao transacao) {
        transacao = transacaoRepository.save(transacao);
        clienteRepository.save(transacao.getCliente());
        return transacao;
    }

    public Cliente validaConta(String numConta) {
        Optional<Cliente> clienteNumConta = clienteRepository
                .findClienteByNumConta(numConta);

        if (clienteNumConta.isEmpty()) {
            return null;
        }
        return clienteNumConta.get();
    }
}

