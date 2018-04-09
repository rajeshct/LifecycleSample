package invoice.scan.com.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

/**
 * Created by admin on 3/23/2018.
 */

public class LifecycleComponent implements LifecycleObserver {
    Lifecycle activeLifecycle;
    Callback callback;

    public LifecycleComponent(Lifecycle activeLifecycle, Callback callback) {
        this.callback = callback;
        this.activeLifecycle = activeLifecycle;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        Log.e("lifecycle", "ON_CREATE");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        Log.e("lifecycle", "ON_START");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        Log.e("lifecycle", "ON_RESUME");

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        Log.e("lifecycle", "ON_PAUSE");

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        if (activeLifecycle.getCurrentState().isAtLeast(Lifecycle.State.CREATED)) {
            Log.e("lifecycle", "ON_STOP_TRUE");
            callback.sendData("ab");
        }
        Log.e("lifecycle", "ON_STOP");

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        if (activeLifecycle.getCurrentState().isAtLeast(Lifecycle.State.DESTROYED)) {
            Log.e("lifecycle", "ON_DEST_TRUE");

            callback.sendData("bcd");
        }
        Log.e("lifecycle", "ON_DEST");

    }

    interface Callback {
        void sendData(String a);
    }
}
