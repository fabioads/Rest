package br.com.rest.resource;


import java.util.ArrayList;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;

import br.com.rest.controller.ClienteController;
import br.com.rest.model.Cliente;



/**
 * 
 * Classe responsável por conter os metodos REST de acesso ao webservice
 *
 * @author Fábio Henrique Pires <fabioh.ads@gmail.com>
 * @since 18/06/2015
 * 
 */
@Path("/api.teste")
public class ClienteResource {

	/** 
	 * Método responsável por fazer chamada ao controller
	 * e Listar todos os clientes
	 *
	 * @return ArrayList<Cliente> 
	 */
	@GET
	@Path("/clientes")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Cliente> listarTodos(){
		return new ClienteController().listarTodos();
	}
	
	/** 
	 * Método responsável por fazer chamada ao controller
	 * e Listar todos os clientes
	 *
	 */
	@GET
	@Path("/clientes/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente listarPorId(@PathParam("id") Integer id){
		return new ClienteController().getCliente(id);
	}
	
	/** 
	 * Método responsável por fazer chamada ao controller
	 * e Criar um cliente.
	 *
	 */
	
	@POST
	@Path("/clientes/inserir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)  
	public String addCliente(Cliente cliente) {
		return new ClienteController().addCliente(cliente);
	}
	
	@PUT
	@Path("/clientes/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)  
	public String updateCliente(Cliente cliente, @PathParam("id") Integer id) {
		cliente.setId(id);
		return new ClienteController().updateCliente(cliente);
	}
	
	@DELETE
	@Path("/clientes/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String removeCliente(@PathParam("id") Integer id) {
		return new ClienteController().removeCliente(id);
	}
	
}