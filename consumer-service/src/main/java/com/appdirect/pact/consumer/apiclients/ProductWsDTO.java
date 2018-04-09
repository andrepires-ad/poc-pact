package com.appdirect.pact.consumer.apiclients;


/**
* The Product model
**/
public class ProductWsDTO {

    /**
    * The product id
    */
    private String id;

    /**
    * The product name
    */
    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

