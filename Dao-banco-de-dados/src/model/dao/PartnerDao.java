package model.dao;

import java.util.List;

import model.entities.Partner;

public interface PartnerDao {
	
	void insert(Partner obj);
	void update(Partner obj);
	void deleteById(Integer id);
	List<Partner> findAll();
	Partner findById(int id);
}
