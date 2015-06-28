package pl.javastart.ap.maps;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import pl.javastart.ap.R;

public class MapsActivity extends Activity implements OnMapReadyCallback, MapTypeDialogFragment.MapTypeSelectionListener {

    private GoogleMap googleMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setRotateGesturesEnabled(false);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(51.9976972, 19.2078602), 5));

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(51.109462, 17.032679));
        googleMap.addMarker(markerOptions);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.google_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_change_map_type:
                MapTypeDialogFragment fragment = new MapTypeDialogFragment();
                Bundle args = new Bundle();
                args.putInt(MapTypeDialogFragment.ARG_CURRENT_MAP_TYPE, googleMap.getMapType());
                fragment.setArguments(args);
                fragment.show(getFragmentManager(), "mapTypeSelection");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void mapSelection(int type) {
        googleMap.setMapType(type);
    }
}