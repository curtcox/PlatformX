package an.a22.util;

import android.app.Activity;
import x.Registry;
import x.util.Runner;

public final class AnRunner
        implements Runner
{
    @Override
    public void invokeLater(Runnable runnable) {
        activity().runOnUiThread(runnable);
    }

    private Activity activity() {
        return Registry.get(Activity.class);
    }
}
