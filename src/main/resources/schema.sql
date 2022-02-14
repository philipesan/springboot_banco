DROP TABLE IF EXISTS tb_clientes;
DROP TABLE IF EXISTS tb_transac;

CREATE TABLE Clientes (
                          nome CHARACTER NOT NULL,
                          exclusive BOOLEAN NOT NULL,
                          saldo NUMERIC(15,2) NOT NULL,
                          num_conta CHARACTER PRIMARY KEY,
                          dt_nascimento DATE
);

CREATE TABLE Transacao (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           valor NUMERIC(15,2) NOT NULL,
                           dt_transacao TIMESTAMP NOT NULL
);