package model.dao;

import java.util.List;

import model.entities.Store;

public interface StoreDao {
	void insert(Store obj);
	void update(Store obj);
	void deleteById(Integer id);
	List<Store> findAll();
	Store findById(int id);
}
