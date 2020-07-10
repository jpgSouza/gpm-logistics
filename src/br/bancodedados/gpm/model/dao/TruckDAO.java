/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.bancodedados.gpm.model.dao;

import br.bancodedados.gpm.controller.MySQLConnection;
import br.bancodedados.gpm.model.Truck;
import br.bancodedados.gpm.model.Warehouse;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author joao_
 */
public class TruckDAO {

    boolean sucess = false;

    public boolean insertTruck(MySQLConnection connection, Truck truck) {
        String insertCommand = "INSERT INTO caminhao (modelo,capacidade)VALUES(?,?)";
        try {
            connection.setPreparedStatement(connection.getConnection().prepareStatement(insertCommand));
            connection.getPreparedStatement().setString(1, truck.getModel());
            connection.getPreparedStatement().setInt(2, truck.getCapacity());
            connection.getPreparedStatement().execute();
            sucess = true;
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            sucess = false;
        }
        return sucess;

    }

    public boolean insertProductInTruck(MySQLConnection connection, int idProduct, int idTruck) {
        String insertCommand = "UPDATE caminhao SET id_produto = ? WHERE id_caminhao = ?";
        try {
            connection.setPreparedStatement(connection.getConnection().prepareStatement(insertCommand));
            connection.getPreparedStatement().setInt(1, idProduct);
            connection.getPreparedStatement().setInt(2, idTruck);
            connection.getPreparedStatement().execute();
            sucess = true;
            JOptionPane.showMessageDialog(null, "Adicionado com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            sucess = false;
        }
        return sucess;

    }

    public ArrayList<Truck> listTruckTable(MySQLConnection connection) {

        ArrayList<Truck> truckList = new ArrayList<>();

        String selectCommand = "SELECT * FROM caminhao";
        try {
            connection.setStatement(connection.getConnection().createStatement());
            connection.setResult(connection.getStatement().executeQuery(selectCommand));
            while (connection.getResult().next()) {
                Truck truck = new Truck();
                truck.setCapacity(connection.getResult().getInt("capacidade"));
                truck.setModel(connection.getResult().getString("modelo"));
                truck.setId(connection.getResult().getInt("id_caminhao"));
                truckList.add(truck);
            }
            sucess = true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            sucess = false;
        }

        return truckList;
    }

    public boolean deleteTruck(MySQLConnection connection, String modelo) {

        String deleteCommand = "DELETE FROM caminhao WHERE modelo=?";

        try {
            connection.setPreparedStatement(connection.getConnection().prepareStatement(deleteCommand));
            connection.getPreparedStatement().setString(1, modelo);
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
