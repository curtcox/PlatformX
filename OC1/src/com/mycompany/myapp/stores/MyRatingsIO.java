package com.mycompany.myapp.stores;

import com.mycompany.myapp.domain.ID;
import com.mycompany.myapp.domain.Rating;

/**
 * For reading and writing my ratings.
 * @author Curt
 */
final class MyRatingsIO
    extends AbstractPairIO
{
    
    @Override
    public ID keyFrom(String string) {
        return new ID(string);
    }

    @Override
    public Rating valueFrom(String string) {
        return new Rating(string);
    }       
    
}
