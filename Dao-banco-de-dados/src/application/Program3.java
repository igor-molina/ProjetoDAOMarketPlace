package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.StoreDao;
import model.entities.Store;

public class Program3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		StoreDao storeDao = DaoFactory.createStoreDao();
		
		System.out.println("Find All:");
		List<Store> list = storeDao.findAll();
		for (Store s : list) {
			System.out.println(s);
		}

		System.out.println("\nInsert:");
		Store newStore = new Store(null, "Center", "Rio de Janeiro - Centro");
		storeDao.insert(newStore);
		System.out.println("Inserted! New id: " + newStore.getId());

		System.out.println("\nUpdate:");
		Store store2 = storeDao.findById(1);
		store2.setName("Alameda");
		storeDao.update(store2);
		System.out.println("Update completed");
		
		System.out.println("\nDelete");
		System.out.print("Enter id for delete: ");
		int id = sc.nextInt();
		storeDao.deleteById(id);
		System.out.println("Delete completed");

		sc.close();

	}

}
