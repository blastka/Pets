package com.example.myapplication.animal.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.details.DetailsFragment
import com.example.myapplication.animal.presentation.ShowFragment

class AnimalFragment : Fragment() {

    private var showFragment: ShowFragment = ShowFragment.Empty()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        showFragment = context as ShowFragment
    }

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
            showFragment.show(DetailsFragment.newInstance("some info"))
        }
    }

    override fun onDetach() {
        super.onDetach()
        showFragment = ShowFragment.Empty()
    }
}
