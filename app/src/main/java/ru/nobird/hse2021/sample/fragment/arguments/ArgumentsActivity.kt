package ru.nobird.hse2021.sample.fragment.arguments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commitNow
import ru.nobird.android.myapplication.fragment.arguments.Person
import ru.nobird.hse2021.sample.R

class ArgumentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_container)
        if (savedInstanceState == null) {
            supportFragmentManager.commitNow {
                val person = Person("Clark", "Kent", 32)
                setReorderingAllowed(true)
                add(R.id.container, ArgumentsFragment.newInstance(person), ArgumentsFragment.TAG)
            }
        }
    }
}