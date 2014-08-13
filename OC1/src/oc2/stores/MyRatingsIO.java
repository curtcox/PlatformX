package oc2.stores;

import oc1.domain.ID;
import oc2.domain.Rating;
import oc1.stores.AbstractPairIO;

/**
 * For reading and writing my ratings.
 * @author Curt
 */
final class MyRatingsIO
    extends AbstractPairIO
{
    MyRatingsIO() {
        super("=");
    }
    
    @Override
    public ID keyFrom(String string) {
        return new ID(string);
    }

    @Override
    public Rating valueFrom(String string) {
        return new Rating(string);
    }       
    
}
