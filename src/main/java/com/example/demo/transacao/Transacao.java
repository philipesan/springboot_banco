package com.example.demo.transacao;

import com.example.demo.cliente.Cliente;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table
public class Transacao {
    @Id

    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )

    public Integer id;
    public BigDecimal valor;
    public LocalDateTime dtTransacao;
    public Boolean saque;
    public Boolean sucesso;

    @ManyToOne
    public Cliente cliente;

    public Transacao(BigDecimal valor, LocalDateTime dtTransacao, Boolean saque, Cliente cliente) {
        this.valor = valor;
        this.dtTransacao = dtTransacao;
        this.saque = saque;
        this.cliente = cliente;
    }

    public Transacao(BigDecimal valor, LocalDateTime dtTransacao, Boolean saque) {
        this.valor = valor;
        this.dtTransacao = dtTransacao;
        this.saque = saque;
    }

    public Transacao() {

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }


    public LocalDateTime getDtTransacao() {
        return dtTransacao;
    }

    public void setDtTransacao(LocalDateTime dtTransacao) {
        this.dtTransacao = dtTransacao;
    }

    public Boolean getSaque() {
        return saque;
    }

    public void setSaque(Boolean saque) {
        this.saque = saque;
    }

    public Boolean getSucesso() {
        return sucesso;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", valor=" + valor +
                ", dtTransacao=" + dtTransacao +
                ", saque=" + saque +
                ", sucesso=" + sucesso +
                ", cliente=" + cliente +
                '}';
    }

    public Boolean validaSaque() {
        this.valor = valor.add(this.cliente.getTaxaSaque(valor));

        return this.cliente.saldoSaque(this.valor);
    }
    public Boolean executaSaque() {

        if (!validaSaque()) {
            this.sucesso = false;
        } else {
            this.cliente.reduzSaldo(this.valor);
            this.sucesso = true;
        }
        return this.sucesso;
    }
    public Boolean executaDeposito() {

        this.cliente.aumentaSaldo(this.valor);
        this.sucesso = true;

        return this.sucesso;
    }
}
