package com.generation.dicefragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController


class choice : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_choice, container, false)

        val botaoSaida = view.findViewById<ImageButton>(R.id.saidaButton)

        botaoSaida.setOnClickListener{
            findNavController().navigate(R.id.action_choice_to_mainActivity)
        }

        val botaoD6 = view.findViewById<ImageButton>(R.id.d6Button)

        botaoD6.setOnClickListener{
            findNavController().navigate(R.id.action_choice_to_d6)
        }

        return view


    }

}