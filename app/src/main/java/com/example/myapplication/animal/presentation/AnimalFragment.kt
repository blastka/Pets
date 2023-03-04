package com.example.myapplication.animal.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.details.DetailsFragment

class AnimalFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_animal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE

        view.findViewById<View>(R.id.factButton).setOnClickListener {
            val detailsFragment = DetailsFragment()
            detailsFragment.arguments = Bundle().apply {
                putString("details", "some information")
            }
            requireActivity().supportFragmentManager.beginTransaction()
                .add(R.id.container, detailsFragment)
                .addToBackStack(detailsFragment.javaClass.simpleName)
                .commit()
        }
    }
}