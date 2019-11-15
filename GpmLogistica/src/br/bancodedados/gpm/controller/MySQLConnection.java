/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.bancodedados.gpm.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author joao_
 */
public class MySQLConnection {
    
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet result;
    
    //Atributos do Banco
    private String databaseName = "transportadora";
    private String url = "jdbc:mysql://localhost:3306/" + databaseName + "?useTimezone=true&serverTimezone=UTC&useSSL=false";
    private String user = "root";
    private String password = "root";
    
    public void connectMySQL(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Successful Connection!");
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
