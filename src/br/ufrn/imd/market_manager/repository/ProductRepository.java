package br.ufrn.imd.market_manager.repository;

import java.sql.ResultSet;

import br.ufrn.imd.market_manager.database.PostgresSQLJDBC;
import br.ufrn.imd.market_manager.entity.Product;
import br.ufrn.imd.market_manager.enums.EnumDepartment;

public class ProductRepository {
	
	private PostgresSQLJDBC database; //Access database object

	/**
	 * Default constructor 
	 */
	public ProductRepository() {
		this.database = new PostgresSQLJDBC();
	}

	/**
	 * Constructor with database parameter
	 * @param database
	 */
	public ProductRepository(PostgresSQLJDBC database) {
		this.database = database;
	}
	
	/**
	 * Query for a product with the given barcode
	 * @param barcode of searched product
	 * @return Product
	 */
	public Product findByBarcode(String barcode) {
		String sql = "SELECT product.* FROM product WHERE product.barcode = ? LIMIT 1";
		
		database.connect();
		
		ResultSet rs = database.query(sql, barcode);
		
		try {
			if(rs.next()) {
				Product product = new Product();
				
				product.setId(rs.getLong("id"));
				product.setDescription(rs.getString("description"));
				product.setBarcode(rs.getString("barcode"));
				product.setDepartment(rs.getString("department") != null ? EnumDepartment.valueOf(rs.getString("department")) : null);
				product.setQuantityInStock(rs.getLong("quantity_in_stock"));
				product.setPrice(rs.getDouble("price"));
				
				return product;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		database.closeConnection();
		
		return null;
	}
	
	/**
	 * Save the product in the database
	 * @param Product p to be saved
	 */
	public void save(Product p) {
		String sql = "";
		
		if(p.getId() == null) {
			sql += "INSERT INTO PRODUCT (description, barcode, department, quantity_in_stock, price) "
					+ "VALUES (" + p.toString() + ") "
					+ "ON CONFLICT (id) "
					+ "DO UPDATE SET "
					+ "description = '" + p.getDescription() + "', ";
		} else {
			sql += "INSERT INTO PRODUCT (id, description, barcode, department, quantity_in_stock, price) "
					+ "VALUES (" + p.getId().toString() + ", " + p.toString() + ") "
					+ "ON CONFLICT (id) "
					+ "DO UPDATE SET "
					+ "description = '" + p.getDescription() + "', ";
		}
		
		sql += " barcode = '" + p.getBarcode() + "', "
				+ " department = '" + p.getDepartment().toString() + "', "
				+ " quantity_in_stock = " + p.getQuantityInStock().toString() + ", "
				+ " price = " + p.getPrice().toString();
		
		database.connect();
		database.execute(sql);
		database.closeConnection();
	}
	
	public void remove(String barcode) {
		String sql = "DELETE FROM PRODUCT WHERE product.barcode = ?";
		
		database.connect();
		database.execute(sql, barcode);
		database.closeConnection();
	}

	public PostgresSQLJDBC getDatabase() {
		return database;
	}

	public void setDatabase(PostgresSQLJDBC database) {
		this.database = database;
	}
}
