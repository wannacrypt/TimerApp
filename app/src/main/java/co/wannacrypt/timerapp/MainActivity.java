package co.wannacrypt.timerapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import entities.City;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "co.wannacrypt.timerapp.LOCATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.mySpinner);

        final ArrayList<City> cityList = initCities();

        ArrayAdapter<City> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cityList);

        spinner.setAdapter(adapter);

        final Handler handler = new Handler();
        final Runnable[] runnable = {null};

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                handler.removeCallbacks(runnable[0]);
                String location = cityList.get(position).getContinent() + '/' + cityList.get(position).getCity();
                final Date time = Calendar.getInstance().getTime();
                final SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
                SimpleDateFormat sdfDay = new SimpleDateFormat("DD");
                SimpleDateFormat sdfCurrent = new SimpleDateFormat("DD");
                sdf.setTimeZone(TimeZone.getTimeZone(location));
                sdfDay.setTimeZone(TimeZone.getTimeZone(location));
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
                sdfTZ.setTimeZone(TimeZone.getTimeZone(location));
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
                cityTextView.setText(location.split("/")[1].replace('_', ' '));
                final TextView timeTextView = findViewById(R.id.timeTextView);
                timeTextView.setText(sdf.format(time));

                Runnable run = new Runnable() {
                    @Override
                    public void run() {
                        timeTextView.setText(sdf.format(Calendar.getInstance().getTime()));
                        handler.postDelayed(this, 1000);
                    }
                };

                handler.post(run);
                runnable[0] = run;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private ArrayList<City> initCities() {
        ArrayList<City> cities = new ArrayList<>();

        cities.add(new City("Europe", "Amsterdam"));
        cities.add(new City("Europe", "Athens"));
        cities.add(new City("Asia", "Atyrau"));
        cities.add(new City("Asia", "Beijing"));
        cities.add(new City("Europe", "Berlin"));
        cities.add(new City("America", "Buenos_Aires"));
        cities.add(new City("Africa", "Cairo"));
        cities.add(new City("Europe", "London"));
        cities.add(new City("America", "Los_Angeles"));
        cities.add(new City("Europe", "Madrid"));
        cities.add(new City("Europe", "Moscow"));
        cities.add(new City("America", "New_York"));
        cities.add(new City("Europe", "Paris"));
        cities.add(new City("Asia", "Seoul"));
        cities.add(new City("Australia", "Sydney"));
        cities.add(new City("Asia", "Tashkent"));
        cities.add(new City("Asia", "Tokyo"));

        return cities;
    }
}
