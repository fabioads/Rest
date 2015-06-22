package br.com.rest.controller;

import java.util.ArrayList;

import br.com.rest.dao.ClienteDAO;
import br.com.rest.model.Cliente;

/**
 * 
 * Class responsible for being the controller of the resource and the DAO layer
 *
 * @author Fábio Henrique Pires <fabioh.ads@gmail.com>
 * @since 18/06/2015 
 * 
 */
public class ClienteController {
	
	public ArrayList<Cliente> listAll(){
		System.out.println("Retornando todos os clientes");
		return ClienteDAO.getInstance().listAll();
	}
	
	public Cliente getCliente(Integer id){
		System.out.println("Retornando cliente");
		Cliente cli = ClienteDAO.getInstance().getCliente(id);
		cli.getEndereco().setId(null);
		return cli;
	}
	
	public String addCliente(Cliente cliente){
		String msg = "Erro ao inserir cliente";
		System.out.println("adicionando: "+ cliente.toString());
		if(ClienteDAO.getInstance().addCliente(cliente)){
			msg = "Cliente:" + cliente.getNome() + "inserido com sucesso!";
		}
		return msg;
	}
	
	public String updateCliente(Cliente cliente){
		String msg = "Erro ao Alterar cliente";
		System.out.println("Alterando: "+ cliente.getNome());
		if(ClienteDAO.getInstance().updateCliente(cliente)){
			msg = "Cliente:" + cliente.getNome() + " Alterado com sucesso!";
		}
		return msg;
	}
	
	public String removeCliente(Integer id){
		String msg = "Erro ao remover cliente";
		System.out.println("Removendo");
		if(ClienteDAO.getInstance().removeCliente(id)){
			msg = "Cliente removido com sucesso!";
		}
		return msg;
	}
}
