package ru.nobird.hse2021.sample.fragment.navigation

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import ru.nobird.hse2021.sample.R

class NavigationThreeFragment : Fragment(R.layout.fragment_navigation) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.navigationText).text = 3.toString()
    }

    companion object {
        const val TAG = "NavigationFourFragment"
    }
}