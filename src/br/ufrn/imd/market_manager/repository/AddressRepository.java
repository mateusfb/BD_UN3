package br.ufrn.imd.market_manager.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufrn.imd.market_manager.database.PostgresSQLJDBC;
import br.ufrn.imd.market_manager.entity.Address;

public class AddressRepository {
	
	private PostgresSQLJDBC database; //Access database object

	/**
	 * Default constructor 
	 */
	public AddressRepository() {
		this.database = new PostgresSQLJDBC();
	}

	/**
	 * Constructor with database parameter
	 * @param database
	 */
	public AddressRepository(PostgresSQLJDBC database) {
		this.database = database;
	}
	
	/**
	 * Query for the address with the given id
	 * @param id of the address
	 * @return Address
	 */
	public Address findById(Long id) {
		String sql = "SELECT address.* FROM address WHERE address.id = ? LIMIT 1";
		
		database.connect();
		
		ResultSet rs = database.query(sql, id);
		
		try {
			if(rs.next()) {
				Address address = new Address();
				
				address.setId(rs.getLong("id"));
				address.setPlace(rs.getString("place"));
				address.setNumber(rs.getString("number"));
				address.setZipcode(rs.getString("zipcode"));
				address.setCity(rs.getString("city"));
				address.setState(rs.getString("state"));
				
				return address;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		database.closeConnection();
		
		return null;
	}
	
	/**
	 * Save the given address to the database
	 * @param Address a to be saved
	 */
	public void save(Address a) {
		String sql = "INSERT INTO ADDRESS (place, number, zipcode, city, state)\r\n"
				+ "VALUES (" + a.toString() + ")\r\n"
				+ "ON CONFLICT (id)\r\n"
				+ "DO UPDATE SET\r\n"
				+ "	place = '" + a.getPlace() + "',\r\n"
				+ "	number = '" + a.getNumber() + "',\r\n"
				+ "	zipcode = '" + a.getZipcode() + "',\r\n"
				+ "	city = '" + a.getCity() + "',\r\n"
				+ "	state = '" + a.getState()  + "'";
		
		database.connect();
		database.execute(sql);
		database.closeConnection();
	}
	
	public PostgresSQLJDBC getDatabase() {
		return database;
	}

	public void setDatabase(PostgresSQLJDBC database) {
		this.database = database;
	}
}
