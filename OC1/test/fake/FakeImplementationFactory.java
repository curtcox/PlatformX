package fake;

import com.codename1.impl.CodenameOneImplementation;
import com.codename1.impl.ImplementationFactory;

/**
 * For testing.
 */
final class FakeImplementationFactory extends ImplementationFactory {

    public FakeImplementationFactory() {}
    
    @Override
    public CodenameOneImplementation createImplementation() {
        return new FakeCodenameOneImplementation();
    }
}
