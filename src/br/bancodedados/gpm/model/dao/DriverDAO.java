/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.bancodedados.gpm.model.dao;

import br.bancodedados.gpm.controller.MySQLConnection;
import br.bancodedados.gpm.model.Driver;
import br.bancodedados.gpm.model.Truck;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author joao_
 */
public class DriverDAO {

    boolean sucess = false;

    public boolean insertDriver(MySQLConnection connection, Driver driver) {
        String insertCommand = "INSERT INTO motorista (nome,idade,salario,sexo,data_entrada)VALUES(?,?,?,?,?)";
        try {
            connection.setPreparedStatement(connection.getConnection().prepareStatement(insertCommand));
            connection.getPreparedStatement().setString(1, driver.getName());
            connection.getPreparedStatement().setInt(2, driver.getAge());
            connection.getPreparedStatement().setString(3, driver.getSalary());
            connection.getPreparedStatement().setString(4, driver.getGender());
            connection.getPreparedStatement().setString(5, driver.getDate());
            connection.getPreparedStatement().execute();
            sucess = true;
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            sucess = false;
        }
            
        return sucess;

    }

    public ArrayList<Driver> listDriverTable(MySQLConnection connection) {

        ArrayList<Driver> driverList = new ArrayList<>();

        String selectCommand = "SELECT * FROM motorista";
        try {
            connection.setStatement(connection.getConnection().createStatement());
            connection.setResult(connection.getStatement().executeQuery(selectCommand));
            while (connection.getResult().next()) {
                Driver driver = new Driver();
                driver.setName(connection.getResult().getString("nome"));
                driver.setAge(connection.getResult().getInt("idade"));
                driver.setSalary(connection.getResult().getString("salario"));
                driver.setGender(connection.getResult().getString("sexo"));
                driver.setDate(connection.getResult().getString("data_entrada"));
             
                driverList.add(driver);
            }
            sucess = true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            sucess = false;
        }

        return driverList;
    }

    public boolean deleteDriver(MySQLConnection connection, String name) {

        String deleteCommand = "DELETE FROM motorista WHERE nome=?";

        try {
            connection.setPreparedStatement(connection.getConnection().prepareStatement(deleteCommand));
            connection.getPreparedStatement().setString(1, name);
            connection.getPreparedStatement().execute();
            sucess = true;
            JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Error = " + e.getMessage());
            sucess = false;
        }

        return sucess;
    }

}
