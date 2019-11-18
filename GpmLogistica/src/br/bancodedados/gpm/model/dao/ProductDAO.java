/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.bancodedados.gpm.model.dao;

import br.bancodedados.gpm.controller.MySQLConnection;
import br.bancodedados.gpm.model.Product;
import br.bancodedados.gpm.model.Warehouse;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author joao_
 */
public class ProductDAO {

    boolean sucess = false;

    public boolean insertProduct(MySQLConnection connection, Product product) {
        String insertCommand = "INSERT INTO produto (nome,tipo,fornecedor)VALUES(?,?,?)";
        try {
            connection.setPreparedStatement(connection.getConnection().prepareStatement(insertCommand));
            connection.getPreparedStatement().setString(1, product.getName());
            connection.getPreparedStatement().setString(2, product.getType());
            connection.getPreparedStatement().setString(3, product.getProvider());
            connection.getPreparedStatement().execute();
            sucess = true;
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            sucess = false;
        }

        return sucess;
    }

    public ArrayList<Product> listProductTable(MySQLConnection connection) {

        ArrayList<Product> productList = new ArrayList<>();

        String selectCommand = "SELECT * FROM produto";
        try {
            connection.setStatement(connection.getConnection().createStatement());
            connection.setResult(connection.getStatement().executeQuery(selectCommand));
            while (connection.getResult().next()) {
                Product product = new Product();
                product.setProductID(connection.getResult().getInt("id_produto"));
                product.setName(connection.getResult().getString("nome"));
                product.setType(connection.getResult().getString("tipo"));
                product.setProvider(connection.getResult().getString("fornecedor"));

                productList.add(product);
            }
            sucess = true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            sucess = false;
        }

        return productList;
    }

    public boolean editProduct(MySQLConnection connection, Product product) {
        String editCommand = "UPDATE produto SET nome = ?, tipo = ?, fornecedor = ? WHERE id_produto = ?";
        try {
            connection.setPreparedStatement(connection.getConnection().prepareStatement(editCommand));
            connection.getPreparedStatement().setString(1, product.getName());
            connection.getPreparedStatement().setString(2, product.getType());
            connection.getPreparedStatement().setString(3, product.getProvider());
            connection.getPreparedStatement().setInt(4, product.getProductID());
            connection.getPreparedStatement().execute();
            sucess = true;
            JOptionPane.showMessageDialog(null, "Atualizado com Sucesso!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            sucess = false;
        }

        return sucess;
    }

    public boolean deleteProduct(MySQLConnection connection, int id) {

        String deleteCommand = "DELETE FROM produto WHERE id_produto = ?";

        try {
            connection.setPreparedStatement(connection.getConnection().prepareStatement(deleteCommand));
            connection.getPreparedStatement().setInt(1, id);
            connection.getPreparedStatement().execute();
            sucess = true;
            JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Error = " + e.getMessage());
            sucess = false;
        }

        return sucess;
    }

    public ArrayList<Product> searchProduct(MySQLConnection connection, String filter, String value) {
        ArrayList<Product> productList = new ArrayList<>();

        String selectCommand = "SELECT * FROM produto WHERE " + filter + " = " + value;
        try {
            connection.setStatement(connection.getConnection().createStatement());
            connection.setResult(connection.getStatement().executeQuery(selectCommand));
            while (connection.getResult().next()) {
                Product product = new Product();
                product.setProductID(connection.getResult().getInt("id_produto"));
                product.setName(connection.getResult().getString("nome"));
                product.setType(connection.getResult().getString("tipo"));
                product.setProvider(connection.getResult().getString("fornecedor"));

                productList.add(product);
            }
            sucess = true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            sucess = false;
        }

        return productList;
    }

}
