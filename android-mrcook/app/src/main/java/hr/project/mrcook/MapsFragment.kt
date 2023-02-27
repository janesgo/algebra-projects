package hr.project.mrcook

import android.graphics.*
import android.location.Geocoder
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import hr.project.mrcook.databinding.FragmentMapsBinding
import hr.project.mrcook.framework.fetchRecipes
import hr.project.mrcook.framework.startActivity
import hr.project.mrcook.model.Recipe
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MapsFragment : Fragment(), GoogleMap.OnMarkerClickListener {

    private lateinit var binding: FragmentMapsBinding
    private lateinit var recipes: MutableList<Recipe>
    private lateinit var geoCoder: Geocoder
    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        var marker: Marker

        googleMap.uiSettings.isZoomControlsEnabled = true
        GlobalScope.launch {
            try {
                recipes.forEach {
                    val location = geoCoder.getFromLocationName(it.area, 1)
                    if (location.isNotEmpty()) {
                        Handler(Looper.getMainLooper()).post {
                            val latLng = LatLng(location[0].latitude, location[0].longitude)
                            marker = googleMap.addMarker(
                                MarkerOptions()
                                    .position(latLng)
                                    .title(it.title)
                                    .icon(
                                        BitmapDescriptorFactory.fromBitmap(
                                            resizeBitmap(it.imagePath, 70, 70)?.let { it1 ->
                                                getCircledBitmap(
                                                    it1
                                                )
                                            }
                                        )
                                    )
                            )
                            marker.tag = it.title
                        }
                    }
                }
            } catch (c: Exception) {
                println(c.message)
            }
        }
        googleMap.setOnMarkerClickListener(this)
    }

    private fun getCircledBitmap(bitmap: Bitmap): Bitmap? {
        val output = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)
        val paint = Paint()
        val rect = Rect(0, 0, bitmap.width, bitmap.height)
        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        canvas.drawCircle((bitmap.width / 2).toFloat(),
            (bitmap.height / 2).toFloat(), (bitmap.width / 2).toFloat(), paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, rect, rect, paint)
        return output
    }

    private fun resizeBitmap(imagePath: String?, width: Int, height: Int): Bitmap? =
        Bitmap.createScaledBitmap(BitmapFactory.decodeFile(imagePath), width, height, false)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        geoCoder = Geocoder(requireContext())
        binding = FragmentMapsBinding.inflate(inflater, container, false)
        recipes = requireContext().fetchRecipes()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = getString(R.string.recipes_map)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        val title = marker.tag as? String

        context?.startActivity<RecipePagerActivity>(
            RECIPE_POSITION,
            recipes.indexOf(recipes.first { r -> r.title == title })
        )
        return false
    }
}