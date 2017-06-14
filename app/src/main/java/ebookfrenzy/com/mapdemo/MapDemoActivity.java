package ebookfrenzy.com.mapdemo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.app.ActivityCompat;
import android.Manifest;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import static com.google.android.gms.internal.zzt.TAG;
import static ebookfrenzy.com.mapdemo.R.id.map;

public class MapDemoActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener,
        OnMapReadyCallback,GoogleMap.InfoWindowAdapter {

    private static final int LOCATION_REQUEST_CODE = 101;
    private String TAG = "MapDemo";
    private GoogleMap mMap;
    String value="";
    double lat=27.7003;
    double lon=31.782;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_map_demo);


        requestPermission(Manifest.permission.ACCESS_FINE_LOCATION,
                LOCATION_REQUEST_CODE);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(map);
        mapFragment.getMapAsync(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("key");
        }
        //Log.e(TAG, value );
        callRetrofitReviews retrofitMovies = callRetrofitReviews.getInstance(getApplicationContext());

        retrofitMovies.retrofitCall(new NetworkResponse<java.util.ArrayList<ebookfrenzy.com.mapdemo.List>>() {
            public static final String TAG = "Ss";

            @Override
            public void onSucess(java.util.ArrayList<ebookfrenzy.com.mapdemo.List> result) {
                String s;
                double lat;
                double lon;
                double Temp;

                for (int i = 0; i < result.size(); i++) {
                    mMap.setInfoWindowAdapter(new PopupAdapter(getLayoutInflater()));

                    s = result.get(i).getName();
                    lat = result.get(i).getCoord().getLat();
                    lon = result.get(i).getCoord().getLon();
                    Temp = result.get(i).getMain().getTemp();
                     LatLng Minia = new LatLng(lat, lon);
                     Marker minia = mMap.addMarker(new MarkerOptions()
                            .position(Minia)
                            .title(s)
                            .snippet("Temp : " + Temp + "C " + "\n" + "Min Temp : " + result.get(i).getMain().getTempMin() + "C " + "\n" + "Max Temp : " + result.get(i).getMain().getTempMax() + "C " + "\n" + "Humidity : " + result.get(i).getMain().getHumidity() + "\n" + "Pressure :" +
                                    result.get(i).getMain().getHumidity() + "\n" + "Wind : " + "\n" + "Speed : " + result.get(i).getWind().getSpeed() + " " + " Degree : " + result.get(i).getWind().getSpeed() + "\n" + "Weather Desc : " + result.get(i).getWeather().get(0).getDescription() + " "));

                    if(value.equals(s)){
                        Camera( lat,lon);
                        minia.showInfoWindow();}
                    //       .position(Minia).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("rain",100,100))));
                }

                Button b = (Button) findViewById(R.id.button2);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in = new Intent(MapDemoActivity.this,MainActivity.class);
                        startActivity(in);

                    }
                });


            }


            @Override
            public void onFailure() {
                Toast.makeText(getApplication().getApplicationContext(), "Not_success", Toast.LENGTH_LONG).show();
            }
        });
    }




    protected void requestPermission(String permissionType, int
            requestCode) {
        int permission = ContextCompat.checkSelfPermission(this,
                permissionType);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{permissionType}, requestCode
            );

        }
        Camera(lat,lon);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[]
                                                   grantResults) {
        switch (requestCode) {
            case LOCATION_REQUEST_CODE: {
                if (grantResults.length == 0
                        || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Unable to show location - permission required", Toast.LENGTH_LONG).show();
                }
                if (mMap != null) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    mMap.setMyLocationEnabled(true);

                }
                Camera(lat,lon);
                return;
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

            //mapSettings.setScrollGesturesEnabled(true);
            //mapSettings.setRotateGesturesEnabled(true);
            Camera(lat,lon);
       // Markers();
            //mMap.animateCamera(CameraUpdateFactory.zoomIn());
           //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MUSEUM, 10));
          // mapSettings.CompassEnabled();
        }
public  void Camera (double lat,double lon){
    if (mMap != null) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        UiSettings mapSettings;
        mapSettings = mMap.getUiSettings();
        mapSettings.setZoomControlsEnabled(true);
        mapSettings.setTiltGesturesEnabled(true);
    LatLng CamCent = new LatLng(lat,lon);
    CameraPosition cameraPosition = new CameraPosition.Builder()
            .target(CamCent)
            .zoom((float) 6.2)
            .bearing(-25)
            .tilt(25)
            .build();
    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

}
}



/*public Bitmap resizeMapIcons(String iconName, int width, int height){

        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(iconName, "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        resizedBitmap.setHasAlpha(true);
        return resizedBitmap;
    }
*/
    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        return false;
    }
}

