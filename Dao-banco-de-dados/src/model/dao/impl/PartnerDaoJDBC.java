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
import model.dao.PartnerDao;
import model.entities.Partner;
import model.entities.Store;

public class PartnerDaoJDBC implements PartnerDao {
	
private Connection conn;
	
	public PartnerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Partner obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO partner "
					+ "(Name) "
					+ "VALUES "
					+ "(?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getName());
			
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
	public void update(Partner obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE store "
					+ "SET Name = ?"
					+ "WHERE Id = ?");
			
			st.setString(1, obj.getName());
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
			st = conn.prepareStatement("DELETE FROM partner WHERE Id = ?");
			
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
		

	@Override
	public List<Partner> findAll() {
		Statement st = null;
		ResultSet rs = null;
		
		List<Partner> partners = new ArrayList<Partner>();
		
		try {
			st = conn.createStatement();
			
			String SQL = ("SELECT * FROM Partner");
			
			rs = st.executeQuery(SQL);
	        
	        while (rs.next()) {
	        	Partner obj = getPartnerFromRs(rs);
	        	
	        	partners.add(obj);
	        }
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
		return partners;
	}
	
	private Partner getPartnerFromRs(ResultSet rs) throws SQLException {
		Partner obj = new Partner(); 
		obj.setId(rs.getInt("id")); 
		obj.setName(rs.getString("name")); 
		
		return obj;
	}

	@Override
	public Partner findById(int id) {
		Statement st = null;
		ResultSet rs = null;
		
		List<Partner> partners = new ArrayList<Partner>();
		
		try {
			st = conn.createStatement();
			
			String SQL = ("SELECT * FROM Partner WHERE Id = ?");
			
			rs = st.executeQuery(SQL);
	        
	        while (rs.next()) {
	        	Partner obj = getPartnerFromRs(rs);
	        	
	        	partners.add(obj);
	        }
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
		return (Partner) partners;
	}
	

}
