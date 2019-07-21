package com.example.dr.turis_huma


import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.dr.turis_huma.Helper.RectOverlay
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.wonderkiln.camerakit.*
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.fragment_camera.*
import java.lang.StringBuilder


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 *
 */
class CameraFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private var waiting_dialog: AlertDialog? = null

    override fun onResume() {
        super.onResume()
        camera_view.start()
    }

    override fun onPause() {
        super.onPause()
        camera_view.stop()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_camera, container, false)

        waiting_dialog = SpotsDialog.Builder().setMessage(R.string.wait_please).setCancelable(false).build()

        btn_detect.setOnClickListener {
            camera_view.start()
            camera_view.captureImage()
            graphic_overlay.clear()
        }

        camera_view.addCameraKitListener(object: CameraKitEventListener{
            override fun onVideo(p0: CameraKitVideo?) {

            }

            override fun onEvent(p0: CameraKitEvent?) {
            }

            override fun onImage(p0: CameraKitImage?) {
                waiting_dialog!!.show()
                var bitmap = p0!!.bitmap
                bitmap = Bitmap.createScaledBitmap(bitmap, camera_view.width, camera_view.height, false)
                camera_view.stop()

                runDetector(bitmap)

            }

            override fun onError(p0: CameraKitError?) {
            }

        })

        // Inflate the layout for this fragment
        return view
    }

    private fun runDetector(bitmap: Bitmap?) {

        val image = FirebaseVisionImage.fromBitmap(bitmap!!)

        val options = FirebaseVisionBarcodeDetectorOptions.Builder()
            .setBarcodeFormats(
                FirebaseVisionBarcode.FORMAT_QR_CODE,
                FirebaseVisionBarcode.FORMAT_CODABAR
            )
            .build()

        var barcodeDetector = FirebaseVision.getInstance().getVisionBarcodeDetector(options)
        var activity = activity as AppCompatActivity
        barcodeDetector.detectInImage(image)
            .addOnSuccessListener { result -> processResult(result, activity) }
            .addOnFailureListener{ e-> Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()}

    }

    private fun processResult(result: List<FirebaseVisionBarcode>, activity: AppCompatActivity) {
        for(item in result) {
            var bounds = item.boundingBox
            var raw_value = item.rawValue
            val value_type = item.valueType


            val rect = RectOverlay(graphic_overlay, bounds)
            graphic_overlay.add(rect)

            when(value_type) {

                FirebaseVisionBarcode.TYPE_TEXT -> {
                    val AlertDialog = AlertDialog.Builder(activity)
                    AlertDialog.setMessage(raw_value)
                    AlertDialog.setPositiveButton("OK", {dialog, which -> dialog.dismiss() })

                    val dialog = AlertDialog.create()
                    dialog.show()

                }

                FirebaseVisionBarcode.TYPE_URL -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(raw_value))
                    startActivity(intent)
                }

                FirebaseVisionBarcode.TYPE_CONTACT_INFO -> {

                    val name = item.contactInfo!!.name!!.formattedName
                    val addres = item.contactInfo!!.addresses[0].addressLines[0]
                    val email = item.contactInfo!!.emails[0].address

                    val info =  StringBuilder("Name").append(name).append("\n")
                        .append("Addres: ").append(addres).append("\n")
                        .append("Email:").append(email).toString()

                    val AlertDialog = AlertDialog.Builder(activity)
                    AlertDialog.setMessage(info)
                    AlertDialog.setPositiveButton("OK", {dialog, which -> dialog.dismiss() })

                    val dialog = AlertDialog.create()
                    dialog.show()
                }

            }

        }

        waiting_dialog!!.dismiss()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            // throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PrimerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CameraFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}
