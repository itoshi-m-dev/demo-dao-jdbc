package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        System.out.println("===== TEST 1 : insertDepartment");
        departmentDao.insert(new Department(null,"abcde"));
        System.out.println("New department inserted");

        System.out.println("===== TEST 2 updateDepartment");
        Department dp1 = new Department(1, "DUDADEPARTMENT");
        departmentDao.update(dp1);
        System.out.println("Department updated, with id: " + dp1.getId());

        System.out.println("===== TEST 3 updateDepartment");
        System.out.println("Type a id to remove their department: ");
        int id =  sc.nextInt();
        departmentDao.deleteById(id);

        






    }
}
