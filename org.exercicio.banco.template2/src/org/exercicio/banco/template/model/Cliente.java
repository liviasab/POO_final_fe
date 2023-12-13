package org.exercicio.banco.template.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.exercicio.banco.template.persistence.PersistenciaEmArquivo;

public class Cliente implements Serializable{

	/**
	 * Número de série para a serialização.
	 */
	private static final long serialVersionUID = 1L;

	// Atributos da classe
	private String cpf;
	private String nome;
	
	// Lista de contas associadas ao cliente
	private List<IConta> contas;
	
	// Construtor padrão
	public Cliente() {
		
	}
	
	// Construtor com parâmetros
	public Cliente(String cpf, String nome) {
		this.cpf = cpf;
		this.nome = nome;
		contas = new ArrayList<>();
	}

	// Getters e Setters
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<IConta> getContas() {
		return contas;
	}

	public void setContas(List<IConta> contas) {
		this.contas = contas;
	}

	// Sobrescrita dos métodos equals, hashCode e toString
	@Override
	public String toString() {
		return "Cliente [cpf=" + cpf + ", nome=" + nome + ", contas=" + contas + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cpf, other.cpf);
	}
	
	// Métodos adicionais

	// Adiciona uma conta à lista de contas do cliente
	public void adicionarConta(IConta conta) {
	    if (contas.contains(conta)) {
	        System.out.println("A conta já está associada a este cliente.");
	    } else {
	        this.contas.add(conta);
	        PersistenciaEmArquivo.getInstance().salvarDadosEmArquivo();
	        System.out.println("Conta adicionada com sucesso!");
	    }
	}

    public void adicionarContaa(ContaCorrente conta1) {
    	if (contas.contains(conta1)) {
    		System.out.println("A conta já está associada a este cliente.");
    	} else {
    		this.contas.add((IConta) conta1);
    		PersistenciaEmArquivo.getInstance().salvarDadosEmArquivo();
    		System.out.println("Conta adicionada com sucesso!");
    	}
    }

    // Remove uma conta da lista de contas do cliente
    public void removerConta(IConta c) {
    	if(contas.contains(c)) {
    		this.contas.remove(c);
    		PersistenciaEmArquivo.getInstance().salvarDadosEmArquivo();
    		System.out.println("Conta removida com sucesso!");
    	} else {
    		System.out.println("A conta não está associada a este cliente.");
    	}
    }

    // Localiza uma conta pelo número
    public IConta localizarContaNumero(int numero) {
		for (int i = 0; i < contas.size(); i++) {
			IConta c = contas.get(i);
			if (c.getNumeroConta() == numero) {
				System.out.println("Conta encontrada!");
				return c;
			}
		}
		System.out.println("Conta não encontrada.");
		return null;
    }

    // Calcula o balanço total entre as contas do cliente
    public double balancoEntreContas() {
		double valorSaldo = 0.0;
		for (int i = 0; i < contas.size(); i++) {
			IConta c = contas.get(i);
			valorSaldo += c.getSaldo().doubleValue();
		}
		System.out.println("Balanço entre contas: R$" + valorSaldo);
		return valorSaldo;
    }
}