package com.jayadev.location_gps;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LocationListener {

    String cityname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER,3000,1, this);
    }



    public void onLocationChanged(Location loc) {

        String longitude = "Lon: " + loc.getLongitude();
        String latitude = "Lat: " + loc.getLatitude();
        //String cityName = "";

        Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
        List<Address> addresses;

        try             {
            addresses = gcd.getFromLocation(loc.getLatitude(),loc.getLongitude(), 1);

            if (addresses.size() > 0)
                System.out.println(addresses.get(0).getLocality());
            cityname = addresses.get(0).getLocality();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


       String  s = longitude + "\n" + latitude +"\nLocation: " + cityname;
        //   Toast.makeText(getBaseContext(),s, Toast.LENGTH_LONG).show();


    }


    public void onProviderDisabled(String provider) {
        //public
        Toast.makeText(getBaseContext(), "Gps turned off ", Toast.LENGTH_LONG).show();
    }


    public void onProviderEnabled(String provider) {
        //default

        Toast.makeText(getBaseContext(), "Gps turned on ", Toast.LENGTH_LONG).show();

    }


    public void onStatusChanged(String provider, int status, Bundle extras) {

        //expected fault
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
