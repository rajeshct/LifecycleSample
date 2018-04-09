package invoice.scan.com.lifecycle;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

public class MainActivity extends Activity implements LifecycleOwner {

    LifecycleRegistry getLifecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLifecycle = new LifecycleRegistry(this);
        getLifecycle.markState(Lifecycle.State.CREATED);
        getLifecycle().addObserver(new LifecycleComponent(getLifecycle()
                , new LifecycleComponent.Callback() {
            @Override
            public void sendData(String s) {
                ((TextView) findViewById(R.id.tv)).setText(s);
            }
        }));
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return getLifecycle;
    }

    @Override
    protected void onStart() {
        super.onStart();
        getLifecycle.markState(Lifecycle.State.STARTED);

    }
}
