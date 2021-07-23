package ru.nobird.hse2021.sample.fragment.dynamic

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.nobird.hse2021.sample.R
import ru.nobird.hse2021.sample.databinding.FragmentDynamicBinding

class DynamicFragmentTwo : Fragment(R.layout.fragment_dynamic) {
    private var viewBinding: FragmentDynamicBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentDynamicBinding.bind(view)
        viewBinding?.dynamicText?.text = TAG
    }

    override fun onDestroyView() {
        viewBinding = null
        super.onDestroyView()
    }

    companion object {
        const val TAG = "DynamicFragmentTwo"
    }
}