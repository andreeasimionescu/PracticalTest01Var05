package ro.pub.cs.systems.eim.practicaltest01var05;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PracticalTest01Var05Service extends Service {
    public static final String ACTION_BROADCAST = "ro.pub.cs.systems.eim.practicaltest01var05.ACTION_BROADCAST";
    public static final String EXTRA_MESSAGE = "message";
    public static final String EXTRA_TEMPLATE = "template";
    private static final int INTERVAL_MS = 5000; // Interval de 5 secunde
    private int index = 0;
    private String[] elements;
    public PracticalTest01Var05Service() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}