package com.example.demo.cliente;


import com.example.demo.transacao.Transacao;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    private String numConta;
    private String nome;
    private Boolean exclusive;
    private BigDecimal saldo;
    private LocalDate dtNascimento;

    @OneToMany
    private List<Transacao> transacoes;

    public Cliente() {
    }

    public Cliente(String nome
                , Boolean exclusive
                , BigDecimal saldo
                , String numConta
                , LocalDate dtNascimento) {

        this.nome = nome;
        this.exclusive = exclusive;
        this.saldo = saldo;
        this.numConta = numConta;
        this.dtNascimento = dtNascimento;
    }

    public Cliente(String numConta) {
        this.numConta = numConta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getExclusive() {
        return exclusive;
    }

    public void setExclusive(Boolean exclusive) {
        this.exclusive = exclusive;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getNumConta() {
        return numConta;
    }

    public void setNumConta(String numConta) {
        this.numConta = numConta;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                ", nome='" + nome + '\'' +
                ", exclusive=" + exclusive +
                ", saldo=" + saldo +
                ", numConta='" + numConta + '\'' +
                ", dtNascimento=" + dtNascimento +
                '}';
    }

    public Boolean saldoSaque(BigDecimal valorSacado) {
        return this.saldo.subtract(valorSacado).
                compareTo(BigDecimal.valueOf(0.00)) > -1;
    }

    public BigDecimal getTaxaSaque(BigDecimal valorSacado) {
        if (this.exclusive ||
                valorSacado.compareTo(BigDecimal.valueOf(100.01)) < 0)  {
            return BigDecimal.valueOf(0.0);
        } else if (valorSacado.compareTo(BigDecimal.valueOf(300.01)) < 0) {
            return valorSacado.multiply(BigDecimal.valueOf(0.004));
        } else {
            return valorSacado.multiply(BigDecimal.valueOf(0.01));
        }
    }

    public void aumentaSaldo(BigDecimal aumentoSaldo) {
        this.saldo = this.saldo.add(aumentoSaldo);
    }

    public void reduzSaldo(BigDecimal reduzSaldo) {
        this.saldo = this.saldo.subtract(reduzSaldo);
    }
}
