/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.bancodedados.gpm.model.dao;

import br.bancodedados.gpm.controller.MySQLConnection;
import br.bancodedados.gpm.model.Warehouse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author joao_
 */
public class WarehouseDAO {

    boolean sucess = false;

    public boolean insertWarehouse(MySQLConnection connection, Warehouse warehouse) {
        String insertCommand = "INSERT INTO deposito (rua,num,bairro,telefone)VALUES(?,?,?,?)";
        try {
            connection.setPreparedStatement(connection.getConnection().prepareStatement(insertCommand));
            connection.getPreparedStatement().setString(1, warehouse.getStreet());
            connection.getPreparedStatement().setInt(2, warehouse.getNumber());
            connection.getPreparedStatement().setString(3, warehouse.getDistrict());
            connection.getPreparedStatement().setString(4, warehouse.getPhone());
            connection.getPreparedStatement().execute();
            sucess = true;
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            sucess = false;
        }/*finally{
            try{
                connection.getConnection().close();
                connection.getPreparedStatement().close();
            }catch(SQLException e){
                System.out.println("Error: " + e.getMessage());
            }
        }*/

        return sucess;

    }

    public ArrayList<Warehouse> listWarehouseTable(MySQLConnection connection) {

        ArrayList<Warehouse> warehouseList = new ArrayList<>();

        String selectCommand = "SELECT * FROM deposito";
        try {
            connection.setStatement(connection.getConnection().createStatement());
            connection.setResult(connection.getStatement().executeQuery(selectCommand));
            while (connection.getResult().next()) {
                Warehouse warehouse = new Warehouse();
                warehouse.setStreet(connection.getResult().getString("rua"));
                warehouse.setNumber(connection.getResult().getInt("num"));
                warehouse.setPhone(connection.getResult().getString("telefone"));
                warehouse.setDistrict(connection.getResult().getString("bairro"));

                warehouseList.add(warehouse);
            }
            sucess = true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            sucess = false;
        }/*finally{
            try{
                connection.getConnection().close();
                connection.getStatement().close();
            }catch(SQLException e){
                System.out.println("Error: " + e.getMessage());
            }
        }*/
        return warehouseList;
    }

    public boolean editWarehouse(MySQLConnection connection, Warehouse warehouse) {
        String editCommand = "UPDATE deposito SET rua = ?, num = ?, bairro = ?, telefone = ? WHERE rua = ?";
        try {
            connection.setPreparedStatement(connection.getConnection().prepareStatement(editCommand));
            connection.getPreparedStatement().setString(1, warehouse.getStreet());
            connection.getPreparedStatement().setInt(2, warehouse.getNumber());
            connection.getPreparedStatement().setString(3, warehouse.getDistrict());
            connection.getPreparedStatement().setString(4, warehouse.getPhone());
            connection.getPreparedStatement().setString(5, warehouse.getStreet());
            connection.getPreparedStatement().execute();
            sucess = true;
            JOptionPane.showMessageDialog(null, "Atualizado com Sucesso!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            sucess = false;
        }

        return sucess;
    }

    public boolean deleteWarehouse(MySQLConnection connection, String street) {
        
        String deleteCommand = "DELETE FROM deposito WHERE rua=?";
        
        try {
            connection.setPreparedStatement(connection.getConnection().prepareStatement(deleteCommand));
            connection.getPreparedStatement().setString(1, street);
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
