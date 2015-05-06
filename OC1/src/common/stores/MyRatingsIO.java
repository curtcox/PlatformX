package common.stores;

import common.domain.ID;
import common.domain.Rating;
import common.stores.AbstractPairIO;

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
