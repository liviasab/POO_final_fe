package org.exercicio.banco.template.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import org.exercicio.banco.template.model.enumerator.TipoTransacao;

// Implementa a interface IConta, definindo os métodos obrigatórios para uma conta poupança.
public class ContaCorrente implements Serializable, IConta {

    private static final long serialVersionUID = 1L;

    private Integer numeroConta;
    private BigDecimal saldo;
    private LocalDateTime dataAbertura;
    private boolean status;
    private List<RegistroTransacao> transacoes;

    // Construtor que inicializa os atributos da conta poupança.
    public ContaCorrente() {
        this.numeroConta = new Random().nextInt(999999999);
        this.saldo = BigDecimal.ZERO;
        saldo.setScale(4, RoundingMode.HALF_UP);
        this.dataAbertura = LocalDateTime.now();
        this.status = true;
        transacoes = new ArrayList<>();
    }

    // Métodos getter e setter para o número da conta.
    public Integer getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Integer numeroConta) {
        this.numeroConta = numeroConta;
    }

    // Métodos getter e setter para o saldo da conta.
    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    // Método getter para a data de abertura da conta.
    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    // Método getter para verificar o status da conta.
    public boolean isStatus() {
        return status;
    }

    // Método setter para definir o status da conta.
    public void setStatus(boolean status) {
        this.status = status;
    }

    // Método getter para obter a lista de transações da conta.
    public List<RegistroTransacao> getTransacoes() {
        return transacoes;
    }

    // Implementação do método hashCode da interface IConta.
    @Override
    public int hashCode() {
        return Objects.hash(numeroConta);
    }

    // Implementação do método equals da interface IConta.
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ContaCorrente other = (ContaCorrente) obj;
        return Objects.equals(numeroConta, other.getNumeroConta());
    }

    // Implementação do método toString da interface IConta.
    @Override
    public String toString() {
        return "Conta Corrente [numeroConta=" + numeroConta + ", saldo=" + saldo + ", dataAbertura=" + dataAbertura
                + ", status=" + status + "]";
    }

    // Implementação do método depositar da interface IConta.
    public void depositar(BigDecimal quantia) {
        if (status) {
            if (quantia.compareTo(BigDecimal.ZERO) > 0) {
                this.saldo = this.saldo.add(quantia);
                transacoes.add(new RegistroTransacao(quantia, TipoTransacao.CREDITO, LocalDateTime.now()));
                System.out.println("Depósito realizado com sucesso.");
            } else {
                System.out.println("Valor inválido para depósito.");
            }
        } 
    }

    // Implementação do método sacar da interface IConta.
    public void sacar(BigDecimal quantia) {
        if (status) {
            if (quantia.compareTo(BigDecimal.ZERO) > 0) {
                if (this.saldo.compareTo(quantia) > 0) {
                    this.saldo = this.saldo.subtract(quantia);
                    transacoes.add(new RegistroTransacao(quantia, TipoTransacao.DEBITO, LocalDateTime.now()));
                    System.out.println("Saque realizado com sucesso!");
                } else {
                    System.out.println("Saldo insuficiente.");
                }
            }
    }
        }

    // Implementação do método transferir da interface IConta.
    public void transferir(IConta c, BigDecimal quantia) {
        BigDecimal taxa = BigDecimal.ZERO;
        BigDecimal taxaPorcentagem = new BigDecimal("0.3");
        if (status && c.isStatus()) {
            if (quantia.compareTo(BigDecimal.ZERO) < 0) {
                System.out.println("Valor inválido para transferência.");
            } else {
                if (c instanceof ContaCorrente) {
                    taxa = quantia.multiply(taxaPorcentagem);
                }
                if (quantia.compareTo(saldo.add(taxa)) <= 0) {
                    setSaldo(saldo.subtract(quantia).subtract(taxa));
                    c.setSaldo(c.getSaldo().add(quantia));
                    c.getTransacoes().add(new RegistroTransacao(quantia, TipoTransacao.TRANSACAO_CREDITO, LocalDateTime.now()));
                    transacoes.add(new RegistroTransacao(quantia, TipoTransacao.TRANSACAO_DEBITO, LocalDateTime.now()));
                    System.out.println("Transferência realizada com sucesso.");
                } else {
                    System.out.println("Saldo insuficiente para realizar a transferência.");
                }
            }
        } 
        
    }

    // Implementação do método para imprimir o extrato da conta para um determinado mês e ano.
    public void imprimirExtratoConta(int mes, int year) {
        System.out.println("Extrato da conta: " + numeroConta + ".");
        System.out.println("Data\t\tTipo\t\tValor");
        for (RegistroTransacao transacao : transacoes) {
            if (transacao.getData().getMonthValue() == mes && transacao.getData().getYear() == year) {
                System.out.println(transacao.getData() + "\t" + transacao.getTipo() + "\t\t" + transacao.getValor());
            }
        }
   

     }

    // Método não utilizado, talvez seja necessário removê-lo ou implementá-lo.
    public List<RegistroTransacao> transacoes() {
        return null;
    }
}