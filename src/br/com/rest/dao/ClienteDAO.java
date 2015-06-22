package br.com.rest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.rest.factory.ConnectionFactory;
import br.com.rest.model.Cliente;

/**
 * 
 * Class responsible for contain the methods of CRUD
 *
 * @author Fábio Henrique Pires <fabioh.ads@gmail.com>
 * @since 18/06/2015
 * 
 */
public class ClienteDAO extends ConnectionFactory {

	private static ClienteDAO instance;
	/**
	 * 
	 * Create class instance 
	 * @pattern (Singleton)
	 * @author Fábio Henrique Pires <fabioh.ads@gmail.com>
	 * 
	 */
	public static ClienteDAO getInstance(){
		if(instance == null)
			instance = new ClienteDAO();
		return instance;
	}
	
	/**
	 * Method responsible by listing all customers
	 * @return ArrayList<CLiente> clientes
	 */
	public ArrayList<Cliente> listAll(){
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Cliente> clientes = null;
		connection = createConnection();
		clientes = new ArrayList<Cliente>();
		try {
			pstmt = connection.prepareStatement("select * from cliente inner join endereco on cliente.endereco_id = endereco.id");
			rs = pstmt.executeQuery();
			while(rs.next()){
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("cliente_id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.getEndereco().setEndereco(rs.getString("endereco"));
				cliente.getEndereco().setBairro(rs.getString("bairro"));
				cliente.getEndereco().setCidade(rs.getString("cidade"));
				cliente.getEndereco().setEstado(rs.getString("estado"));
				cliente.getEndereco().setNumero(rs.getString("numero"));
				cliente.getEndereco().setComplemento(rs.getString("complemento"));
				cliente.getEndereco().setCep(rs.getString("cep"));
				clientes.add(cliente);
			}
			
		} catch (Exception e) {
			System.out.println("Error trying to list all customers: " + e);
			e.printStackTrace();
		} finally {
			closeConnection(connection, pstmt, rs);
		}
		return clientes;
	}
	
	public Cliente getCliente(Integer id){
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Cliente cliente = null;
		connection = createConnection();
		try {
			pstmt = connection.prepareStatement("select * from cliente inner join endereco on cliente.endereco_id = endereco.id where cliente_id="+id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				cliente = new Cliente();
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.getEndereco().setId(rs.getInt("id"));
				cliente.getEndereco().setEndereco(rs.getString("endereco"));
				cliente.getEndereco().setBairro(rs.getString("bairro"));
				cliente.getEndereco().setCidade(rs.getString("cidade"));
				cliente.getEndereco().setEstado(rs.getString("estado"));
				cliente.getEndereco().setNumero(rs.getString("numero"));
				cliente.getEndereco().setComplemento(rs.getString("complemento"));
				cliente.getEndereco().setCep(rs.getString("cep"));
			}
			
		} catch (Exception e) {
			System.out.println("Error trying to consult customer" + e);
			e.printStackTrace();
		} finally {
			closeConnection(connection, pstmt, rs);
		}
		return cliente;
	}
	
	public Boolean addCliente(Cliente cliente){
		Connection connection = null;
		PreparedStatement pstmt = null;
		connection = createConnection();
		boolean isInserted = false;
		Integer nextIdAddress = getNextIdAddress();
		
		try {
			pstmt = connection.prepareStatement("insert into endereco(id, endereco, bairro, cidade, estado, numero, complemento, cep) values(?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, nextIdAddress);
			pstmt.setString(2, cliente.getEndereco().getEndereco());
			pstmt.setString(3, cliente.getEndereco().getBairro());
			pstmt.setString(4, cliente.getEndereco().getCidade());
			pstmt.setString(5, cliente.getEndereco().getEstado());
			pstmt.setString(6, cliente.getEndereco().getNumero());
			pstmt.setString(7, cliente.getEndereco().getComplemento());
			pstmt.setString(8, cliente.getEndereco().getCep());
			if(pstmt.executeUpdate() > 0){
				isInserted = true;
			};
			
			if(isInserted == true){
				pstmt = connection.prepareStatement("insert into cliente(nome, cpf, endereco_id) values(?,?,?)");
				pstmt.setString(1, cliente.getNome());
				pstmt.setString(2, cliente.getCpf());
				pstmt.setInt(3, nextIdAddress);
				if(pstmt.executeUpdate() == 0){
					isInserted = false;
				}
			}
	
		} catch (Exception e) {
			System.out.println("Error trying to include customer " + e);
			e.printStackTrace();
		} finally {
			closeConnection(connection, pstmt, null);
		}
		return isInserted;
	}
	
	public boolean updateCliente(Cliente cliente){
		Connection connection = null;
		PreparedStatement pstmt = null;
		connection = createConnection();
		boolean isChanged = false;
		Integer idAddress;
		try {
			idAddress = getCliente(cliente.getId()).getEndereco().getId();
			
			pstmt = connection.prepareStatement("update cliente set nome=?, cpf=? where cliente_id=?");
			pstmt.setString(1, cliente.getNome());
			pstmt.setString(2, cliente.getCpf());
			pstmt.setInt(3, cliente.getId());
			if(pstmt.executeUpdate() > 0){
				isChanged = true;
			}
			
			if(isChanged == true){
				pstmt = connection.prepareStatement("update endereco set endereco=?, bairro=?, cidade=?, estado=?, numero=?, complemento=?, cep=? where id=?");
				pstmt.setString(1, cliente.getEndereco().getEndereco());
				pstmt.setString(2, cliente.getEndereco().getBairro());
				pstmt.setString(3, cliente.getEndereco().getCidade());
				pstmt.setString(4, cliente.getEndereco().getEstado());
				pstmt.setString(5, cliente.getEndereco().getNumero());
				pstmt.setString(6, cliente.getEndereco().getComplemento());
				pstmt.setString(7, cliente.getEndereco().getCep());
				pstmt.setInt(8, idAddress);
				if(pstmt.executeUpdate() == 0){
					isChanged = false;
				}
			}
	
		} catch (Exception e) {
			System.out.println("Error trying to change customer " + e);
			e.printStackTrace();
		} finally {
			closeConnection(connection, pstmt, null);
		}
		return isChanged;
	}
	
	public boolean removeCliente(Integer id){
		Connection connection = null;
		PreparedStatement pstmt = null;
		connection = createConnection();
		boolean isRemoved = false;
		Integer idAddress;
		
		try {
			idAddress = getCliente(id).getEndereco().getId();
			
			pstmt = connection.prepareStatement("delete from cliente where cliente_id=?");
			pstmt.setInt(1, id);
			if(pstmt.executeUpdate() > 0){
				isRemoved = true;
			}
			
			if(isRemoved == true ){
				pstmt = connection.prepareStatement("delete from endereco where id=?");
				pstmt.setInt(1, idAddress);
				if(pstmt.executeUpdate() == 0){
					isRemoved = false;
				}
			}
	
		} catch (Exception e) {
			System.out.println("Error trying to remove client " + e);
			e.printStackTrace();
		} finally {
			closeConnection(connection, pstmt, null);
		}
		return isRemoved;
	}
	
	public Integer getNextIdAddress(){
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		connection = createConnection();
		Integer nextId = null;
		try {
			pstmt = connection.prepareStatement("select nextval('endereco_id_seq') as next_id");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				nextId = rs.getInt("next_id");
			}
			
		} catch (Exception e) {
			System.out.println("error finding next id" + e);
			e.printStackTrace();
		} finally {
			closeConnection(connection, pstmt, rs);
		}
		return nextId;
	}
	
}
