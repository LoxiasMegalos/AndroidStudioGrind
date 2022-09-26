package com.generation.dicefragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.fragment.findNavController


class d6 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_d6, container, false)


        val botaoJogar = view.findViewById<Button>(R.id.d6GameButton)
        val textoJogo = view.findViewById<TextView>(R.id.textoD6)
        botaoJogar.setOnClickListener{
            val jogo = JogandoDados
            textoJogo.text = jogo.jogandoDados(6)
        }

        val botaoVoltar = view.findViewById<ImageButton>(R.id.voltarD6Game)
        botaoVoltar.setOnClickListener{
            findNavController().navigate(R.id.action_d6_to_choice)
        }



        return view
    }


}