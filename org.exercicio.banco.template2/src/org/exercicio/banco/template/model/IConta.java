// Declaração da interface IConta
package org.exercicio.banco.template.model;

import java.math.BigDecimal;
import java.util.List;

// Interface que define métodos básicos para uma conta bancária
public interface IConta {

    // Método para verificar o status da conta (ativo ou inativo)
    public boolean isStatus();

    // Método para obter o número da conta
    public Integer getNumeroConta();

    // Método para obter o saldo da conta
    public BigDecimal getSaldo();

    // Método para depositar uma quantia na conta
    public void depositar(BigDecimal quantia);

    // Método para transferir uma quantia para outra conta
    public void transferir(IConta destino, BigDecimal quantia);

    // Método para sacar uma quantia da conta
    public void sacar(BigDecimal quantia);

    // Método para definir o saldo da conta
    public void setSaldo(BigDecimal add);

    // Método para obter a lista de transações associadas à conta
    public List<RegistroTransacao> getTransacoes();
}