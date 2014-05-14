package com.mycompany.myapp.domain;

/**
 *
 * @author Curt
 */
public final class ID {

    final String value;
    
    public ID(String name) {
        this.value = name;
    }

    @Override
    public String toString() {
        return value;
    }
}
