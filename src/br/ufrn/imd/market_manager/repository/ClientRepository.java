package br.ufrn.imd.market_manager.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import br.ufrn.imd.market_manager.database.PostgresSQLJDBC;
import br.ufrn.imd.market_manager.entity.Client;
import br.ufrn.imd.market_manager.enums.EnumSex;

public class ClientRepository {
	
	private PostgresSQLJDBC database; //Access database object

	/**
	 * Default constructor 
	 */
	public ClientRepository() {
		this.database = new PostgresSQLJDBC();
	}
	
	/**
	 * Constructor with database parameter
	 * @param database
	 */
	public ClientRepository(PostgresSQLJDBC database) {
		this.database = database;
	}
	
	/**
	 * Query for a client with the given cpf on database
	 * @param cpf of searched client
	 * @return Client
	 */
	public Client findByCpf(String cpf) {
		String sql = "SELECT client.* FROM client WHERE client.cpf = ? LIMIT 1";
		
		database.connect();
		
		ResultSet rs = database.query(sql, cpf);
		
		try {
			//if the query returned a row, map it to the Client object
			if(rs.next()) {
				Client client = new Client();
				
				client.setId(rs.getLong("id"));
				client.setName(rs.getString("name"));
				client.setCpf(rs.getString("cpf"));
				client.setBirthDate(rs.getTimestamp("birth_date") != null ? rs.getTimestamp("birth_date").toLocalDateTime().toLocalDate() : null);
				client.setSex(rs.getString("sex") != null ? EnumSex.valueOf(rs.getString("sex")) : null);
				client.setPhone(rs.getString("phone"));
				client.setAddress(new AddressRepository().findById(rs.getLong("id_address")));
				
				return client;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		database.closeConnection();
		
		//if nothing is found return null
		return null;
	}
	
	/**
	 * Query for a client with the given id on database
	 * @param id of searched client
	 * @return Client
	 */
	public Client findById(Long id) {
		String sql = "SELECT client.* FROM client WHERE client.id = ? LIMIT 1";
		
		database.connect();
		
		ResultSet rs = database.query(sql, id);
		
		try {
			//if the query returned a row, map it to the Client object
			if(rs.next()) {
				Client client = new Client();
				
				client.setId(rs.getLong("id"));
				client.setName(rs.getString("name"));
				client.setCpf(rs.getString("cpf"));
				client.setBirthDate(rs.getTimestamp("birth_date") != null ? rs.getTimestamp("birth_date").toLocalDateTime().toLocalDate() : null);
				client.setSex(rs.getString("sex") != null ? EnumSex.valueOf(rs.getString("sex")) : null);
				client.setPhone(rs.getString("phone"));
				client.setAddress(new AddressRepository().findById(rs.getLong("id_address")));
				
				return client;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		database.closeConnection();

		//if nothing is found return null
		return null;
	}
	
	/**
	 * Saves the given client in the database
	 * @param Client c to be saved
	 */
	public void save(Client c) {
		
		String sql = "";
		
		if(c.getAddress().getId() == null) {
			AddressRepository addressRepository = new AddressRepository();
			addressRepository.save(c.getAddress());
			c.getAddress().setId(addressRepository.getDatabase().getGeneratedKey());
		}
		
		if(c.getId() == null) {
			sql = "INSERT INTO CLIENT(name, cpf, birth_date, sex, phone, id_address)\r\n"
					+ "VALUES (" + c.toString() + ")\r\n"
					+ "ON CONFLICT (id)\r\n"
					+ "DO UPDATE SET\r\n";
		} else {
			sql = "INSERT INTO CLIENT(id, name, cpf, birth_date, sex, phone, id_address)\r\n"
					+ "VALUES (" + c.getId().toString() + ", " + c.toString() + ")\r\n"
					+ "ON CONFLICT (id)\r\n"
					+ "DO UPDATE SET\r\n"
					+ "id = " + c.getId().toString() + ",\r\n";
		}
		
		sql += " name = '" + c.getName() +  "',\r\n"
				+ "	cpf = '" + c.getCpf() + "',\r\n"
				+ "	birth_date = '" + Timestamp.valueOf(c.getBirthDate().atStartOfDay()) + "',\r\n"
				+ "	sex = '" + c.getSex().toString() + "',\r\n"
				+ "	phone = '" + c.getPhone() + "',\r\n"
				+ "	id_address = " + c.getAddress().getId().toString();
		
		database.connect();
		database.execute(sql);
		database.closeConnection();
	}
	
	public void remove(String cpf) {
		String sql = "DELETE FROM CLIENT "
				+ "WHERE client.cpf = ?";
		
		database.connect();
		database.execute(sql, cpf);
		database.closeConnection();
	}
	
	public PostgresSQLJDBC getDatabase() {
		return database;
	}

	public void setDatabase(PostgresSQLJDBC database) {
		this.database = database;
	}
}
