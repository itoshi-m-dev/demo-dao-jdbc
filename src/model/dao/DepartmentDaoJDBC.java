package model.dao;

import db.DB;
import db.DbException;
import model.entities.Department;

import java.sql.*;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    public Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO department (name) VALUES (?) ", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                else{
                    throw new DbException("Failed to insert department");
                }
            }
        } catch (SQLException e ){
            throw new DbException("Failed to insert department");
        } finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public void update(Department obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE department "
                    + "SET Name = ? "
                    + "WHERE Id = ? "
            );
            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());;

            int rowsAffected = st.executeUpdate();
            if (rowsAffected == 0){
                throw new DbException("Failed to update department, id not found");
            }
        } catch (SQLException e ){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "DELETE FROM department WHERE Id = ? "
            );
            st.setInt(1,id);

            int affectedRows = st.executeUpdate();
            if (affectedRows == 0){
                throw new DbException("Department not found");
            }
        } catch (SQLException e ){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM department WHERE Id = ?"
            );
            st.setInt(1, id);
           rs = st.executeQuery();
           if (rs.next()){
               Department dep =  new Department();
               dep.setId(rs.getInt("Id"));
               dep.setName(rs.getString("Name"));
               return dep;
           }
           return null;
        } catch (SQLException e ){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Department> findAll() {
        return List.of();
    }
}
