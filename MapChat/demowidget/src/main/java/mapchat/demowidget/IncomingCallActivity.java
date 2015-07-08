package mapchat.demowidget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import mapchat.demowidget.view.GlassIncomingWaveView;


public class IncomingCallActivity extends ActionBarActivity {

    private GlassIncomingWaveView waveView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incoming_call);

        waveView = (GlassIncomingWaveView) findViewById(R.id.incoming_wave);

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        this.registerReceiver(ScreenBroadcast, filter);
    }

    private BroadcastReceiver ScreenBroadcast = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Intent.ACTION_SCREEN_ON.equals(action)) {
                waveView.showWaveAnimation();
            } else if (Intent.ACTION_SCREEN_OFF.equals(action)) {
                waveView.cancalWaveAnimation();
            }
        }
    };

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.i("screen", "KeyCode ：" + event.getKeyCode());
        return super.dispatchKeyEvent(event);
    }
}
