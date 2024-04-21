package com.example.revision

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.revision.databinding.ActivityMainBinding
import nl.joery.animatedbottombar.AnimatedBottomBar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class UpdatesFragment : Fragment() {

    private lateinit var bottomBar: AnimatedBottomBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_updates, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomBar = view.findViewById(R.id.bottom_bar)

        bottomBar.setOnTabSelectListener(object : AnimatedBottomBar.OnTabSelectListener {
            override fun onTabSelected(lastIndex: Int, lastTab: AnimatedBottomBar.Tab?, newIndex: Int, newTab: AnimatedBottomBar.Tab) {
                when (newTab.id) {
                    R.id.updates -> {
                        // Handle updates tab click
                        childFragmentManager.beginTransaction()
                            .replace(R.id.updates, UpdatesFragment())
                            .commit()
                        true
                    }
                    R.id.communities -> {
                        // Handle communities tab click
                        childFragmentManager.beginTransaction()
                            .replace(R.id.updates, CommunitiesFragment())
                            .commit()
                        true
                    }
                    R.id.calls -> {
                        // Handle calls tab click
                        childFragmentManager.beginTransaction()
                            .replace(R.id.updates, CallsFragment())
                            .commit()
                        true
                    }
                }
            }

            override fun onTabReselected(index: Int, tab: AnimatedBottomBar.Tab) {
                // Handle tab reselection
            }
        })
    }

    companion object {
        fun newInstance(): UpdatesFragment {
            return UpdatesFragment()
        }
    }
}