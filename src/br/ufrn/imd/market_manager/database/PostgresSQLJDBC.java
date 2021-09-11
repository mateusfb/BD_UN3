package br.ufrn.imd.market_manager.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgresSQLJDBC {

	Connection connection;
	PreparedStatement statement;
	Long generatedKey;
	
	Boolean debugMode = true;
	
	public void connect() {
		
		try {
			Class.forName("org.postgresql.Driver");
			this.connection = DriverManager
						 .getConnection("jdbc:postgresql://localhost:5432/BD_UN3",
								 "postgres", "36632435");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		
		if(debugMode) System.out.println("[DEBUG] Connected to dadabase...");
	}
	
	public void closeConnection() {
		try {
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet query(String sql, Object... params) {
		
		ResultSet rs = null;
		
		try {
			this.statement = connection.prepareStatement(sql);
			
			if(debugMode) System.out.println("[DEBUG] Executing statement: " + sql);
			
			for(int i = 0; i < params.length; i++) {
				this.statement.setObject(i + 1, params[i]);
			}
			
			rs = this.statement.executeQuery();
			
			if(debugMode) System.out.println("[DEBUG] Executed succesfully!");
		} catch (SQLException e) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		}
		
		return rs;
	}
	
	public void execute(String sql, Object... params) {
		try {
			
			this.statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			if(debugMode) System.out.println("[DEBUG] Executing statement: " + sql);
			
			for(int i = 0; i < params.length; i++) {
				this.statement.setObject(i + 1, params[i]);
			}
			
			this.statement.executeUpdate();
			ResultSet rsKey = statement.getGeneratedKeys();
			
			if(rsKey.next()) {
				this.generatedKey = rsKey.getLong("id");
			}
			
			if(debugMode) System.out.println("[DEBUG] Executed succesfully!");
		} catch (SQLException e) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public PreparedStatement getStatement() {
		return statement;
	}

	public void setStatement(PreparedStatement statement) {
		this.statement = statement;
	}

	public Boolean getDebugMode() {
		return debugMode;
	}

	public void setDebugMode(Boolean debugMode) {
		this.debugMode = debugMode;
	}

	public Long getGeneratedKey() {
		return generatedKey;
	}

	public void setGeneratedKey(Long generatedKey) {
		this.generatedKey = generatedKey;
	}
}
