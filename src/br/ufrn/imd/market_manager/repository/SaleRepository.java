package br.ufrn.imd.market_manager.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.market_manager.database.PostgresSQLJDBC;
import br.ufrn.imd.market_manager.entity.Product;
import br.ufrn.imd.market_manager.entity.Sale;

public class SaleRepository {

	private PostgresSQLJDBC database; //Access database object

	/**
	 * Default constructor 
	 */
	public SaleRepository() {
		this.database = new PostgresSQLJDBC();
	}

	/**
	 * Constructor with database parameter
	 * @param database
	 */
	public SaleRepository(PostgresSQLJDBC database) {
		this.database = database;
	}
	
	/**
	 * Query for the sales of the product with the given barcode
	 * @param barcode
	 * @return List<Sale>
	 */
	public List<Sale> findAllByProductBarcode(String barcode) {
		String sql = "SELECT DISTINCT sale.* FROM product_sale "
				+ "INNER JOIN product ON product.id = product_sale.id_product "
				+ "INNER JOIN sale ON sale.id = product_sale.id_sale WHERE product.barcode = ?";
		
		database.connect();
		
		ResultSet rs = database.query(sql, barcode);
		
		try {
			if(rs.next()) {
				ClientRepository clientRepository = new ClientRepository();
				List<Sale> sales = new ArrayList<Sale>();
				
				do {
					Sale sale = new Sale();
					
					sale.setId(rs.getLong("id"));
					sale.setTotal(rs.getDouble("total"));
					sale.setClient(clientRepository.findById(rs.getLong("id_client")));
					sale.setDate(rs.getTimestamp("date") != null ? rs.getTimestamp("date").toLocalDateTime() : null);
					
					sales.add(sale);
				} while (rs.next());
				
				return sales;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		database.closeConnection();
		
		return null;
	}
	
	/**
	 * Query for the sales by the client with the given cpf
	 * @param cpf
	 * @return List<Sale>
	 */
	public List<Sale> findAllByClientCpf(String cpf) {
		String sql = "SELECT DISTINCT sale.* FROM product_sale "
				+ "INNER JOIN sale ON sale.id = product_sale.id_sale "
				+ "INNER JOIN client ON client.id = sale.id_client WHERE client.cpf = ?";
		
		database.connect();
		
		ResultSet rs = database.query(sql, cpf);
		
		try {
			if(rs.next()) {
				ClientRepository clientRepository = new ClientRepository();
				List<Sale> sales = new ArrayList<Sale>();
				
				do {
					Sale sale = new Sale();
					
					sale.setId(rs.getLong("id"));
					sale.setTotal(rs.getDouble("total"));
					sale.setClient(clientRepository.findById(rs.getLong("id_client")));
					sale.setDate(rs.getTimestamp("date") != null ? rs.getTimestamp("date").toLocalDateTime() : null);
					
					sales.add(sale);
				} while (rs.next());
				
				return sales;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		database.closeConnection();
		
		return null;
	}
	
	/**
	 * Query for the number of sales for the clients who have the given zipcode in their address
	 * @param zipcode
	 * @return Long
	 */
	public Long countByZipcode(String zipcode) {
		String sql = "SELECT DISTINCT COUNT(product_sale.id_sale) FROM product_sale "
				+ "INNER JOIN sale ON sale.id = product_sale.id_sale "
				+ "INNER JOIN client ON client.id = sale.id_client "
				+ "INNER JOIN address ON client.id_address = address.id WHERE address.zipcode = ?";
		
		database.connect();
		
		ResultSet rs = database.query(sql, zipcode);
		
		try {
			if(rs.next()) {
				return rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		database.closeConnection();
		
		return (long) 0;
	}
	
	public void save(Sale s) {
		String sql = "INSERT INTO SALE (id_client, total, date)\r\n"
				+ "VALUES (" + s.toString() + ")\r\n"
				+ "ON CONFLICT(id)\r\n"
				+ "DO UPDATE SET\r\n"
				+ "	id_client = " + s.getClient().getId().toString() + ",\r\n"
				+ "	total = " + s.getTotal().toString() + ",\r\n"
				+ "	date = '" + Timestamp.valueOf(s.getDate()) + "'";
		
		database.connect();
		database.execute(sql);
		database.closeConnection();
		
		Long saleId = database.getGeneratedKey();
		
		for(Product p : s.getProducts().keySet()) {
			saveProductSale(p.getId(), saleId, s.getProducts().get(p));
		}
	}
	
	private void saveProductSale(Long idProduct, Long idSale, Long amount) {
		String sql = "INSERT INTO PRODUCT_SALE (id_product, id_sale, product_amount)\r\n"
				+ "VALUES (" + idProduct.toString() + ", " + idSale.toString() + ", " + amount.toString() + ")\r\n"
				+ "ON CONFLICT(id)\r\n"
				+ "DO UPDATE SET\r\n"
				+ "	id_product = " + idProduct.toString() + ",\r\n"
				+ "	id_sale = " + idSale.toString() + ",\r\n"
				+ "	product_amount = " + amount.toString();
		
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
