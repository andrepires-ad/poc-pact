package com.appdirect.pact.consumer;

public class Product {

    private int value;

    public Product() {
    }

    public Product(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (value != product.value) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
