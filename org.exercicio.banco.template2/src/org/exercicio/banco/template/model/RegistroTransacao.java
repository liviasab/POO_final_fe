// Pacote que contém a classe
package org.exercicio.banco.template.model;

// Importações necessárias
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

import org.exercicio.banco.template.model.enumerator.TipoTransacao;

// Definição da classe RegistroTransacao que implementa a interface Serializable
public class RegistroTransacao implements Serializable {

    // Número de versão da serialização
    private static final long serialVersionUID = 1L;

    // Atributos da classe
    private Integer id;              // Identificador único da transação
    private BigDecimal valor;         // Valor da transação
    private TipoTransacao tipo;       // Tipo da transação (enumeração)
    private LocalDateTime data;       // Data e hora da transação

    // Construtor da classe
    public RegistroTransacao(BigDecimal valor, TipoTransacao tipo, LocalDateTime data) {
        // Gera um ID aleatório para a transação
        this.id = new Random().nextInt(999999999);
        // Inicializa os demais atributos com os valores passados como parâmetros
        this.valor = valor;
        this.tipo = tipo;
        this.data = data;
    }

    // Métodos de acesso (getters e setters)

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

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    // Métodos hashCode e equals para comparação de objetos

    @Override
    public int hashCode() {
        return Objects.hash(data, id, tipo, valor);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        RegistroTransacao other = (RegistroTransacao) obj;
        return Objects.equals(data, other.data) && Objects.equals(id, other.id) && tipo == other.tipo
                && Objects.equals(valor, other.valor);
    }

    // Método toString para representação em string da transação

    @Override
    public String toString() {
        return "RegistroTransacao [id=" + id + ", valor=" + valor + ", tipo=" + tipo + ", data=" + data + "]";
    }
}