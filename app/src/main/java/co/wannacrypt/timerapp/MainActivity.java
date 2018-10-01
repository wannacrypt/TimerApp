package co.wannacrypt.timerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import entities.City;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "co.wannacrypt.timerapp.LOCATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.timerListView);

        final ArrayList<City> cityList = initCities();

        ArrayAdapter<City> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cityList);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), CityActivity.class);
                City current = cityList.get(position);
                intent.putExtra(EXTRA_MESSAGE, current.getContinent()  + '/' + current.getCity());
                startActivity(intent);
            }
        });
    }

    private ArrayList<City> initCities() {
        ArrayList<City> cities = new ArrayList<>();

        cities.add(new City("Europe", "Amsterdam"));
        cities.add(new City("Europe", "Athens"));
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
