package ru.nobird.hse2021.sample.fragment.arguments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import ru.nobird.android.myapplication.fragment.arguments.Person
import ru.nobird.hse2021.sample.R

class ArgumentsFragment : Fragment(R.layout.fragment_arguments) {
    companion object {
        const val TAG = "ArgumentsFragment"
        private const val PERSON_ARG = "person"

        fun newInstance(person: Person): Fragment {
            val bundle = Bundle().apply {
                putParcelable(PERSON_ARG, person)
            }
            return ArgumentsFragment().apply { arguments = bundle }
        }

//   Good way
//        fun newInstance(person: Person): Fragment =
//            ArgumentsFragment().apply {
//                this.person = person
//            }
    }

    private lateinit var person: Person

//    Good way
//    private var person: Person by argument()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        person = arguments?.getParcelable(PERSON_ARG) ?: throw IllegalArgumentException()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.information).text =
            resources.getString(R.string.name_and_age, "${person.firstName} ${person.lastName}", person.age )
    }
}