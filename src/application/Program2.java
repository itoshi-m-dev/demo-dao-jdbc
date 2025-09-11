package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {
    public static void main(String[] args) {
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        System.out.println("===== TEST 1 : insertDepartment");
        departmentDao.insert(new Department(null,"abcde"));
        System.out.println("New department inserted");

        System.out.println("===== TEST 2 updateDepartment");
        Department dp1 = new Department(1, "DUDADEPARTMENT");
        departmentDao.update(dp1);
        System.out.println("Department updated, with id: " + dp1.getId());




    }
}
