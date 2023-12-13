// Pacote que contém a classe
package org.exercicio.banco.template.persistence;

// Importações necessárias
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.exercicio.banco.template.model.Cliente;

// Classe responsável pela persistência de dados em arquivo
public class PersistenciaEmArquivo implements Serializable {

    private static final long serialVersionUID = 1L;

    // Lista para armazenar os dados dos clientes
    private List<Cliente> cadastroClientes = new ArrayList<Cliente>();

    // Instância única da classe (Singleton)
    private static PersistenciaEmArquivo instance;

    // Construtor privado para garantir Singleton e carregar dados do arquivo
    public PersistenciaEmArquivo() {
        carregarDadosDeArquivo();
    }

    // Método para obter a instância única da classe (Singleton)
    public static PersistenciaEmArquivo getInstance() {
        if (instance != null)
            return instance;
        else
            return new PersistenciaEmArquivo();
    }

    // Método para salvar um cliente no cadastro
    public void salvarCliente(Cliente c) {
        if (!cadastroClientes.contains(c)) {
            cadastroClientes.add(c);
            salvarDadosEmArquivo();
            System.out.println("Cliente cadastrado com sucesso!");
        } else
            System.err.println("Cliente já cadastrado no sistema!");
    }

    // Método para remover um cliente do cadastro
    public void removerCliente(Cliente c) {
        if (cadastroClientes.contains(c)) {
            cadastroClientes.remove(c);
            salvarDadosEmArquivo();
            System.out.println("Cliente removido com sucesso!");
        } else
            System.err.println("Cliente não encontrado no sistema!");
    }

    // Método para localizar um cliente pelo CPF
    public Cliente localizarClientePorCPF(String cpf) {
        Cliente c = new Cliente();
        c.setCpf(cpf);
        if (cadastroClientes.contains(c)) {
            int index = cadastroClientes.indexOf(c);
            c = cadastroClientes.get(index);
            return c;
        } else
            return null;
    }

    // Método para atualizar os dados de um cliente no cadastro
    public void atualizarClienteCadastro(Cliente c) {
        if (cadastroClientes.contains(c)) {
            int index = cadastroClientes.indexOf(c);
            cadastroClientes.set(index, c);
            salvarDadosEmArquivo();
        } else
            System.err.println("Cliente não encontrado!");
    }

    // Método para salvar os dados no arquivo
    public void salvarDadosEmArquivo() {
        try {
            // Criação de fluxos de saída para salvar os dados em um arquivo
            FileOutputStream fos = new FileOutputStream("dados");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(cadastroClientes);
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para carregar os dados do arquivo
    @SuppressWarnings("unchecked")
    private void carregarDadosDeArquivo() {
        try {
            // Criação de fluxos de entrada para ler os dados de um arquivo
            FileInputStream fis = new FileInputStream("dados");
            ObjectInputStream ois = new ObjectInputStream(fis);
            cadastroClientes = (ArrayList<Cliente>) ois.readObject();
            ois.close();
            fis.close();

        } catch (FileNotFoundException e) {
            // Se o arquivo não existir, será criado na próxima vez que os dados forem salvos
            cadastroClientes = new ArrayList<Cliente>();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos os clientes cadastrados
    public void listarClientes() {
        for (Cliente cliente : cadastroClientes) {
            System.out.println("CPF: " + cliente.getCpf() + " - Nome: " + cliente.getNome());
        }
			return;

	}

}