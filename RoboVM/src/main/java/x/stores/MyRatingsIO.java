package x.stores;

import x.domain.ID;
import x.domain.Rating;

/**
 * For reading and writing my ratings.
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
