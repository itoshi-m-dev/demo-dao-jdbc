package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;
import model.impl.SellerDaoJDBC;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println("==== test 1: seller findbyid ====");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println("\n=== TEST 2: seller findByDepartment ====");
        List<Seller> list = sellerDao.findByDepartment(new Department(2, null));
        for (Seller s : list) {
            System.out.println(s);
        }
        System.out.println("\n=== TEST 3: seller findAll ====");
        List<Seller> findAll = sellerDao.findAll();
        for (Seller s : findAll) {
            System.out.println(s);
        }
        System.out.println("\n=== TEST 4: seller Insert ====");
        Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.00, new Department(2,null));
        sellerDao.insert(newSeller);
        System.out.println("inserted! new id = " + newSeller.getId());

        System.out.println("\n=== TEST 5: seller Insert ====");
        seller = sellerDao.findById(1);
        seller.setName("Martha Waine");
        sellerDao.update(seller);
        System.out.println("updated completed");

        System.out.println("\n=== TEST 6: seller deleteById ====");
       System.out.println("Enter id for delete test: ");
       int id = sc.nextInt();
       sellerDao.deleteById(id);
       System.out.println("deleted completed");

       sc.close();
    }
}
