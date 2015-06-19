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
		System.out.println("Retornando dados");
		return ClienteDAO.getInstance().listarTodos();
	}
	
}
