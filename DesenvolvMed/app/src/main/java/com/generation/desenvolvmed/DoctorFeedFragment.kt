package com.generation.desenvolvmed

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.generation.desenvolvmed.adapter.PostagemAdapter
import com.generation.desenvolvmed.databinding.ActivityMainBinding.inflate
import com.generation.desenvolvmed.databinding.FragmentDoctorFeedBinding
import com.generation.desenvolvmed.model.Postagem


class DoctorFeedFragment : Fragment() {


    private lateinit var binding: FragmentDoctorFeedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentDoctorFeedBinding.inflate(layoutInflater, container, false)

        /*
        val listPostagens = listOf(
            Postagem(
                2,
                Tema(1, "Vacina", null ),
                Medico(1, "CRM/SP 123546", Cadastro(1, "01754689720",
                    "Joviraldo", "Robson", "2154154", "jovis@gmail.com",
                    null ),null,),
                "Cerveja",
                "A cerveja é muito importante para a sua saude pois faz parte de 98% do seu organismo",
                "https://imgur.com/vpVts7m"
            ),
            Postagem(
                1,
                Tema(1, "Vacina", null ),
                Medico(1, "CRM/SP 123546", Cadastro(1, "01754689720",
                    "Joviraldo", "Robson", "2154154", "jovis@gmail.com",
                    null ),null,),
                "Sake",
                "A sake é muito importante para a sua saude pois faz parte de 98% do seu organismo",
                "https://imgur.com/vpVts7m"
            ),
        )*/

        val postagemAdapter = PostagemAdapter()
        binding.recyclerPostagem.layoutManager = LinearLayoutManager(context)
        binding.recyclerPostagem.adapter = postagemAdapter
        binding.recyclerPostagem.setHasFixedSize(true)

       // postagemAdapter.setList(listPostagens)

        binding.addPostButton.setOnClickListener{
            findNavController().navigate(R.id.action_doctorFeedFragment_to_createPostFragment)
        }

        return binding.root


    }

}