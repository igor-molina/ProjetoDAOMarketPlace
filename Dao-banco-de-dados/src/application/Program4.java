package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.PartnerDao;
import model.entities.Partner;

public class Program4 {

	public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
		
		PartnerDao partnerDao = DaoFactory.createPartnerDao();
		
		System.out.println("Find All:");
		List<Partner> list = partnerDao.findAll();
		for (Partner p : list) {
			System.out.println(p);
		}

		System.out.println("\nInsert:");
		Partner newPartner = new Partner(null, "HyperX");
		partnerDao.insert(newPartner);
		System.out.println("Inserted! New id: " + newPartner.getId());

		System.out.println("\nUpdate:");
		Partner partner2 = partnerDao.findById(1);
		partner2.setName("Alameda");
		partnerDao.update(partner2);
		System.out.println("Update completed");
		
		System.out.println("\nDelete");
		System.out.print("Enter id for delete: ");
		int id = sc.nextInt();
		partnerDao.deleteById(id);
		System.out.println("Delete completed");

		sc.close();

	}

}
