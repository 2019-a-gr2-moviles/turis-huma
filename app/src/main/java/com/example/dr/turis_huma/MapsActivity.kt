package com.example.dr.turis_huma

import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsActivity : AppCompatActivity(),
    OnMapReadyCallback,
    GoogleMap.OnCameraMoveStartedListener,
    GoogleMap.OnCameraMoveListener,
    GoogleMap.OnCameraIdleListener,
    GoogleMap.OnPolylineClickListener,
    GoogleMap.OnPolygonClickListener{

    private lateinit var mMap: GoogleMap
    private var tienePermisosLocalizacion = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        getLocationPermissions()
        setMapConfiguration(mMap)
        setListenersMapMovements(mMap)
        val foch = LatLng(-0.202760, -78.490813)
        val titulo = "Plaza foch"
        val zoom = 17f
        addMarker(foch, titulo)
        moveCameraWithZoom(foch, zoom)
        setNearByPlaces(googleMap)
    }


    fun setListenersMapMovements(map:GoogleMap){
        with(map) {
            setOnCameraIdleListener(this@MapsActivity)
            setOnCameraMoveStartedListener(this@MapsActivity)
            setOnCameraMoveListener(this@MapsActivity)

            setOnPolylineClickListener(this@MapsActivity)
            setOnPolygonClickListener(this@MapsActivity)
        }
    }

    fun addMarker(latLng: LatLng, title:String){
        mMap.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(title)
        )
    }

    fun moveCameraWithZoom(latLng: LatLng, zoom: Float = 10f){
        mMap.moveCamera(
            CameraUpdateFactory
                .newLatLngZoom(latLng, zoom)
        )
    }

    fun setMapConfiguration(mapa:GoogleMap){
        val contexto = this.applicationContext
        with(mapa){

            val permisoFineLocation = ContextCompat
                .checkSelfPermission(
                    contexto,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            val tienePermiso = permisoFineLocation == PackageManager.PERMISSION_GRANTED
            if(tienePermiso){
                mapa.isMyLocationEnabled = true
            }
            this.uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true
        }
    }

    fun getLocationPermissions(){
        val permisoFineLocation = ContextCompat
            .checkSelfPermission(
                this.applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )

        val tienePermiso = permisoFineLocation == PackageManager.PERMISSION_GRANTED

        if(tienePermiso){
            Log.i("mapa","Tiene permisos de FINE_LOCATION")
            this.tienePermisosLocalizacion = true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1  // Codigo que vamos a esperar
            )
        }
    }

    fun setNearByPlaces(googleMap: GoogleMap){
        val poligonoUno = googleMap
            .addPolygon(
                PolygonOptions()
                    .clickable(true)
                    .add(
                        LatLng(-0.209431, -78.490078),
                        LatLng(-0.208734, -78.488951),
                        LatLng(-0.209431, -78.488286),
                        LatLng(-0.210085, -78.489745)
                    )
            )
        poligonoUno.fillColor = -0xc771c4

        val poligonoDos = googleMap
            .addPolygon(
                PolygonOptions()
                    .clickable(true)
                    .add(
                        LatLng(-0.208743, -78.496033),
                        LatLng(-0.210741, -78.498213),
                        LatLng(-0.210462, -78.500616),
                        LatLng(-0.206886, -78.499252)
                    )
            )
        poligonoDos.fillColor = -0xc771c4

    }



    override fun onCameraMove() {
        Log.i("map","Me estoy moviendo")
    }

    override fun onCameraIdle() {
        Log.i("map","Me quede quieto")
    }

    override fun onCameraMoveStarted(p0: Int) {
        Log.i("map","Me voy a empezar a mover")
    }

    override fun onPolylineClick(p0: Polyline?) {
        Log.i("map","Polylinea ${p0.toString()}")
    }

    override fun onPolygonClick(p0: Polygon?) {
        Log.i("map","Polygono ${p0.toString()}")
    }




}
