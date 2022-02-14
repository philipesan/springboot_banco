package com.example.demo.cliente;

import com.example.demo.excecoes.RespostaApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }

    public ResponseEntity<?> adicionaNovoCliente(Cliente cliente) {
        Optional<Cliente> clienteNumConta = clienteRepository
                .findClienteByNumConta(cliente.getNumConta());

        RespostaApi respostaApi;

        if (!cliente.getNumConta().matches("^[0-9]+$")) {
            respostaApi = new RespostaApi("NOK"
                    , "Código de Conta Invalido");
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(respostaApi);
        }

        if(clienteNumConta.isPresent()) {
            respostaApi = new RespostaApi("NOK"
                    , "Usuário já criado");
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(respostaApi);
        }

        cliente = clienteRepository.save(cliente);

        if (cliente.getNumConta() != null) {
            respostaApi = new RespostaApi("OK"
                    , "Usuário Criado com sucesso"
                    , List.of(cliente));

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(respostaApi);
        } else {
            respostaApi = new RespostaApi("NOK"
                    , "Erro não identificado durante a criação");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(respostaApi);
        }
    }
}