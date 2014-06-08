package oc1.stores;

import oc1.domain.ID;
import oc1.domain.Rating;

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
