package co.wannacrypt.timerapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class CityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        final Date time = Calendar.getInstance().getTime();
        final SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat sdfDay = new SimpleDateFormat("DD");
        SimpleDateFormat sdfCurrent = new SimpleDateFormat("DD");
        sdf.setTimeZone(TimeZone.getTimeZone(message));
        sdfDay.setTimeZone(TimeZone.getTimeZone(message));
        sdfCurrent.setTimeZone(Calendar.getInstance().getTimeZone());

        String day;

        if (Integer.valueOf(sdfDay.format(time)) > Integer.valueOf(sdfCurrent.format(time))) {
            day = "Tomorrow";
        } else if (Integer.valueOf(sdfDay.format(time)).equals(Integer.valueOf(sdfCurrent.format(time)))) {
            day = "Today";
        } else {
            day = "Yesterday";
        }

        SimpleDateFormat sdfTZ = new SimpleDateFormat("X");
        sdfTZ.setTimeZone(TimeZone.getTimeZone(message));
        SimpleDateFormat sdfTZC = new SimpleDateFormat("X");
        sdfTZC.setTimeZone(Calendar.getInstance().getTimeZone());

        Integer n1 = Integer.valueOf(sdfTZ.format(time));
        Integer n2 = Integer.valueOf(sdfTZC.format(time));
        Integer diff = n1 - n2;
        day += ", ";
        if (diff > 0)
            day += '+';
        day +=+ diff + "HRS";

        TextView dayTextView = findViewById(R.id.dayTextView);
        dayTextView.setText(day);
        TextView cityTextView = findViewById(R.id.cityTextView);
        cityTextView.setText(message.split("/")[1].replace('_', ' '));
        final TextView timeTextView = findViewById(R.id.timeTextView);
        timeTextView.setText(sdf.format(time));

        new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timeTextView.setText(sdf.format(Calendar.getInstance().getTime()));
            }

            @Override
            public void onFinish() {
            }
        }.start();

    }
}
