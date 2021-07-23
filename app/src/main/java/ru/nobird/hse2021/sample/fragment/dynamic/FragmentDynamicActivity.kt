package ru.nobird.hse2021.sample.fragment.dynamic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.commitNow
import ru.nobird.hse2021.sample.R
import ru.nobird.hse2021.sample.databinding.ActivityFragmentDynamicBinding

class FragmentDynamicActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityFragmentDynamicBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityFragmentDynamicBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.commitNow {
                // Динамическое добавление
                val fragmentOne = DynamicFragmentOne()
                add(R.id.container, fragmentOne, DynamicFragmentOne.TAG)
                viewBinding.replaceButton.isVisible = true
            }
        }

        // Замена фрагмента

        viewBinding.replaceButton.setOnClickListener {
            supportFragmentManager.commitNow {
                replace(R.id.container, DynamicFragmentTwo())
            }
        }
    }
}