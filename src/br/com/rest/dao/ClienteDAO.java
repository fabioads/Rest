package br.com.rest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.rest.factory.ConnectionFactory;
import br.com.rest.model.Cliente;

/**
 * 
 * Classe responsável por conter os metodos do CRUD
 *
 * @author Fábio Henrique Pires <fabioh.ads@@gmail.com>
 * @since 18/06/2015
 * 
 */
public class ClienteDAO extends ConnectionFactory {

	private static ClienteDAO instance;
	
	
	/**
	 * 
	 * M�todo respons�vel por criar uma instancia da classe ClienteDAO (Singleton)
	 *
	 * @return
	 * @author Douglas Costa <douglas.cst90@gmail.com.br>
	 * @since 17/02/2013 02:03:47
	 * @version 1.0
	 */
	public static ClienteDAO getInstance(){
		if(instance == null)
			instance = new ClienteDAO();
		return instance;
	}
	
	/**
	 * Método responsável por listar todos os clientes do banco
	 * @return ArrayList<CLiente> clientes
	 */
	public ArrayList<Cliente> listarTodos(){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Cliente> clientes = null;
		
		conexao = criarConexao();
		clientes = new ArrayList<Cliente>();
		try {
			pstmt = conexao.prepareStatement("select * from cliente order by nome");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Cliente cliente = new Cliente();
				
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setEndereco(rs.getString("endereco"));
				
				clientes.add(cliente);
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao tentar listar todos os clientes: " + e);
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return clientes;
	}
	
	public Cliente getCliente(Integer id){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Cliente cliente = null;
		conexao = criarConexao();
		try {
			pstmt = conexao.prepareStatement("select * from cliente where id="+id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				cliente = new Cliente();
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setEndereco(rs.getString("endereco"));
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao tentar cosultar cliente " + e);
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return cliente;
	}
	
	public Integer addCliente(Cliente cliente){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		conexao = criarConexao();
		Integer inseriu = 0;
		try {
			pstmt = conexao.prepareStatement("insert into cliente(nome, cpf, endereco) values(?,?,?)");
			pstmt.setString(1, cliente.getNome());
			pstmt.setString(2, cliente.getCpf());
			pstmt.setString(3, cliente.getEndereco());
			inseriu = pstmt.executeUpdate();
	
		} catch (Exception e) {
			System.out.println("Erro ao tentar Incluir cliente " + e);
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, null);
		}
		return inseriu;
	}
	
	public Integer updateCliente(Cliente cliente){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		conexao = criarConexao();
		Integer alterou = 0;
		try {
			pstmt = conexao.prepareStatement("update cliente set nome=?, cpf=?, endereco=? where id=?");
			pstmt.setString(1, cliente.getNome());
			pstmt.setString(2, cliente.getCpf());
			pstmt.setString(3, cliente.getEndereco());
			pstmt.setInt(4, cliente.getId());
			alterou = pstmt.executeUpdate();
	
		} catch (Exception e) {
			System.out.println("Erro ao tentar alterar cliente " + e);
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, null);
		}
		return alterou;
	}
	
	public Integer removeCliente(Integer id){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		conexao = criarConexao();
		Integer removeu = 0;
		try {
			pstmt = conexao.prepareStatement("delete from cliente where id=?");
			pstmt.setInt(1, id);
			removeu = pstmt.executeUpdate();
	
		} catch (Exception e) {
			System.out.println("Erro ao tentar remover cliente " + e);
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, null);
		}
		return removeu;
	}
	
}
