package br.com.rest.controller;

import java.util.ArrayList;

import br.com.rest.dao.ClienteDAO;
import br.com.rest.model.Cliente;

/**
 * 
 * Classe responsável por ser o controlador entre o resource e a camada DAO
 *
 * @author Fábio Henrique Pires <fabioh.ads@gmail.com>
 * @since 18/06/2015 
 * 
 */
public class ClienteController {
	
	public ArrayList<Cliente> listarTodos(){
		System.out.println("Retornando todos os clientes");
		return ClienteDAO.getInstance().listarTodos();
	}
	
	public Cliente getCliente(Integer id){
		System.out.println("Retornando cliente");
		return ClienteDAO.getInstance().getCliente(id);
	}
	
	public String addCliente(Cliente cliente){
		String msg = "Erro ao inserir cliente";
		System.out.println("adicionando: "+ cliente.toString());
		Integer inseriu = ClienteDAO.getInstance().addCliente(cliente);
		if(inseriu > 0){
			msg = "Cliente:" + cliente.getNome() + "inserido com sucesso!";
		}
		return msg;
	}
	
	public String updateCliente(Cliente cliente){
		String msg = "Erro ao Alterar cliente";
		System.out.println("Alterando: "+ cliente.getNome());
		Integer alterou = ClienteDAO.getInstance().updateCliente(cliente);
		if(alterou > 0){
			msg = "Cliente:" + cliente.getNome() + " Alterado com sucesso!";
		}
		return msg;
	}
	
	public String removeCliente(Integer id){
		String msg = "Erro ao remover cliente";
		System.out.println("Removendo");
		Integer removeu = ClienteDAO.getInstance().removeCliente(id);
		if(removeu > 0){
			msg = "Cliente removido com sucesso!";
		}
		return msg;
	}
	
	
}
