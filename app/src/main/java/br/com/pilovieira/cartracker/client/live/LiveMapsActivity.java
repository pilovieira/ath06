package br.com.pilovieira.cartracker.client.live;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import br.com.pilovieira.cartracker.client.R;
import br.com.pilovieira.cartracker.core.tag.service.LiveTrackerPosition;

public class LiveMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<LiveTrackerPosition> positions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        positions = new LiveTrackerPositionCache(this).getPositions();

        mMap.addPolyline(buildPolylineOptions());
        List<Marker> markers = paintMarkers(mMap);
        refreshZoom(markers);
    }

    private PolylineOptions buildPolylineOptions() {
        PolylineOptions options = new PolylineOptions();
        options.color(Color.BLUE);
        options.width(5);

        for (LiveTrackerPosition pos : positions)
            options.add(new LatLng(pos.getLatitude(), pos.getLongitude()));

        return options;
    }

    private List<Marker> paintMarkers(GoogleMap map) {
        List<Marker> redMarkers = new ArrayList<>();
        for (LiveTrackerPosition position : positions)
            redMarkers.add(map.addMarker(buildMarkerOptions(position)));
        return redMarkers;
    }

    private MarkerOptions buildMarkerOptions(LiveTrackerPosition position) {
        MarkerOptions options = new MarkerOptions();
        options.position(new LatLng(position.getLatitude(), position.getLongitude()));
        options.title("Marker Nº" + positions.indexOf(position));
        options.snippet(String.format("%s | Speed:%s Km/h", position, position.getSpeed() * 3.6));

        return options;
    }

    private void refreshZoom(List<Marker> markers) {
        if (markers.isEmpty())
            return;

        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        for (Marker marker : markers)
            builder.include(marker.getPosition());

        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 400, 400, 0));
    }
}
