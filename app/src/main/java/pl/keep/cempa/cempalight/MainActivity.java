package pl.keep.cempa.cempalight;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    RelativeLayout relativeLayout;
    SensorManager SensorManager;
    Sensor mCempaLight;
    TextView textView;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mCempaLight = SensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        relativeLayout = (RelativeLayout)findViewById(R.id.layout_id);
        textView = (TextView) findViewById(R.id.txt);
        textView2 = (TextView) findViewById(R.id.txt2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SensorManager.registerListener(this, mCempaLight, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            textView.setText("" + event.values[0]);
        }

        if (event.values[0] < 10 ) {
            textView2.setText("Ciemne tło");
            relativeLayout.setBackgroundResource(R.color.colorPrimaryDark);
        } else{
            textView2.setText("Jasne tło");
        relativeLayout.setBackgroundResource(R.color.colorPrimary);
    }
    }

     @Override
     public void onAccuracyChanged (Sensor sensor, int accuracy) {

     }

}
