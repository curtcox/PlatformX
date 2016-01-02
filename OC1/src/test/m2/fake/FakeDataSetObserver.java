package fake;

import android.database.DataSetObserver;

public class FakeDataSetObserver
    extends DataSetObserver
{
    public boolean onChangedCalled;
    public boolean onInvalidatedCalled;

    public void onChanged() {
        onChangedCalled = true;
    }

    public void onInvalidated(){
        onInvalidatedCalled = true;
    }
}
