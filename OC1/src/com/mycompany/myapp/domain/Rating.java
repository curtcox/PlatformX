package com.mycompany.myapp.domain;

/**
 *
 * @author Curt
 */
public final class Rating {

    final String value;
    
    public Rating(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
