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
 * Class responsible for contain the REST methods of access to the webservice
 * @author Fábio Henrique Pires <fabioh.ads@gmail.com>
 * @since 18/06/2015
 */
@Path("/api.teste")
public class ClienteResource {

	/** 
	 * List all customers
	 * @return ArrayList<Cliente> 
	 */
	@GET
	@Path("/clientes")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Cliente> listarTodos(){
		return new ClienteController().listAll();
	}
	
	/** 
	 * get customer by id
	 * @param id
	 */
	@GET
	@Path("/clientes/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente listarPorId(@PathParam("id") Integer id){
		return new ClienteController().getCliente(id);
	}
	
	/** 
	 * Create a customer.
	 * @param cliente
	 * @return
	 */
	
	@POST
	@Path("/clientes/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)  
	public String addCliente(Cliente cliente) {
		return new ClienteController().addCliente(cliente);
	}
	
	/**
	 * Change customer 
	 * @param cliente
	 * @param id
	 */
	
	@PUT
	@Path("/clientes/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)  
	public String updateCliente(Cliente cliente, @PathParam("id") Integer id) {
		cliente.setId(id);
		return new ClienteController().updateCliente(cliente);
	}
	
	/**
	 * delete customer
	 * @param id
	 */
	@DELETE
	@Path("/clientes/remove/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String removeCliente(@PathParam("id") Integer id) {
		return new ClienteController().removeCliente(id);
	}
	
}