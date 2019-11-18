/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.bancodedados.gpm.model;

/**
 *
 * @author joao_
 */
public class Product {
    private String name;
    private String type;
    private String provider;
    private int productID;

    public int getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProvider() {
        return provider;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
    
    
}
