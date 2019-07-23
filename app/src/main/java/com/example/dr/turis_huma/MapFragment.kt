package com.example.dr.turis_huma


import android.content.Context
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.ActionBarContextView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.*
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.view.*
import android.support.v7.app.AppCompatActivity
import android.view.MenuInflater






private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MapFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {

    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null


    private var mDrawer: DrawerLayout? = null
    private var mToolbar: Toolbar? = null
    private var mDrawerToggle: ActionBarDrawerToggle? = null
    private var mDesignNavigationView: NavigationView? = null
    private var mActionBar: ActionBar? = null



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
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        //(activity as AppCompatActivity).supportActionBar!!.setSubtitle()
        mToolbar = view.findViewById(R.id.toolbar)
        var activity = activity as AppCompatActivity?
        activity!!.setSupportActionBar(mToolbar)
        mDrawer = view.findViewById(R.id.drawer_layout)
        activity!!.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mDrawerToggle = ActionBarDrawerToggle(activity, mDrawer, mToolbar, R.string.open, R.string.closed)
        mDrawer!!.addDrawerListener(mDrawerToggle!!)
        mDrawerToggle !!.syncState()
        mDesignNavigationView = view.findViewById(R.id.design_navigation_view)
        mDesignNavigationView!!.setNavigationItemSelectedListener(this)


        return view
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.buscar -> {
                Log.i("BUSCAR", "YEESSSS!!!")
            }
            else -> {

            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
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
            MapFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }







}


