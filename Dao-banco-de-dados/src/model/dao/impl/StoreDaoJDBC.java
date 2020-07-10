package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.StoreDao;
import model.entities.Store;

public class StoreDaoJDBC implements StoreDao{
	
	private Connection conn;
	
	public StoreDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Store obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO store "
					+ "(Name, Address) "
					+ "VALUES "
					+ "(?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getAddress());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Store obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE store "
					+ "SET Name = ?, Address = ? "
					+ "WHERE Id = ?");
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getAddress());
			st.setInt(6, obj.getId());
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM store WHERE Id = ?");
			
			st.setInt(1, id);
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	private Store getStoreFromRs(ResultSet rs) throws SQLException {
		Store obj = new Store(); 
		obj.setId(rs.getInt("id")); 
		obj.setName(rs.getString("name")); 
		obj.setAddress(rs.getString("address"));
		
		return obj;
	}
	
	@Override
	public List<Store> findAll() {
		
		Statement st = null;
		ResultSet rs = null;
		
		List<Store> stores = new ArrayList<Store>();
		
		try {
			st = conn.createStatement();
			
			String SQL = ("SELECT * FROM Store");
			
			rs = st.executeQuery(SQL);
	        
	        while (rs.next()) {
	        	Store obj = getStoreFromRs(rs);
	        	
	        	stores.add(obj);
	        }
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
		return stores;		
	}

	@Override
	public Store findById(int id) {
		Statement st = null;
		ResultSet rs = null;
		
		List<Store> stores = new ArrayList<Store>();
		
		try {
			st = conn.createStatement();
			
			String SQL = ("SELECT * FROM Store WHERE Id = ?");
			
			rs = st.executeQuery(SQL);
	        
	        while (rs.next()) {
	        	Store obj = getStoreFromRs(rs);
	        	
	        	stores.add(obj);
	        }
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
		return (Store) stores;
	}
	
}
