package com.mycompany.myapp.domain;

/**
 *
 * @author Curt
 */
public final class Name {

    final String value;
    
    public Name(String name) {
        this.value = name;
    }

    @Override
    public String toString() {
        return value;
    }
}
