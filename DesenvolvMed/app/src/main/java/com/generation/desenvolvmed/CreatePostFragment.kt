package com.generation.desenvolvmed

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.generation.desenvolvmed.databinding.FragmentCreatePostBinding
import com.generation.desenvolvmed.model.*
import java.time.LocalDateTime


class CreatePostFragment : Fragment() {


    private lateinit var binding: FragmentCreatePostBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private var temaSelecionado = 0L

    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreatePostBinding.inflate(layoutInflater, container, false)

        mainViewModel.listTemas()

        mainViewModel.myTemaResponse.observe(viewLifecycleOwner){
                response -> Log.d("Requisicao", response.body().toString())
                spinnerTema(response.body())
        }

        binding.botaoPublicar.setOnClickListener {
            inserirNoBanco()
        }

        return binding.root
    }

    private fun spinnerTema(listaTema: List<Tema>?) {
        if(listaTema != null){
            binding.selecTemas.adapter =
                ArrayAdapter(
                    requireContext(),
                    androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                    listaTema
                )

            binding.selecTemas.onItemSelectedListener=
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        val selected = binding.selecTemas.selectedItem as Tema

                        temaSelecionado = selected.id
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }


                }

        }
    }

    private fun validarCampos(titulo: String, conteudo: String, anexo: String): Boolean {
        return (
                (titulo.isNotBlank() && titulo.length in 20..255) &&
                        (conteudo.isNotBlank() &&  conteudo.length in 20..5000) &&
                        (anexo.isNotBlank() &&  anexo.length in 10..500)
                )
    }



    private fun inserirNoBanco() {

        val titulo = binding.tituloText.text.toString()
        val conteudo = binding.postText.text.toString()
        val anexo = binding.textAnexo.text.toString()
        //val medico = binding.currentUser.medico.id
        val medico = mainViewModel.medicoLogado.value?.body()

        val dataPostagem = LocalDateTime.now().toString()

        val tema = Tema(temaSelecionado, null, null)

        if (validarCampos(titulo, conteudo, anexo)) {

            val postagem =
                medico?.let { Postagem(0, titulo, conteudo, anexo , dataPostagem ,tema, it) }
            if (postagem != null) {
                mainViewModel.addPostagem(postagem)
            }

            Toast.makeText(context, "Postagem criada!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_createPostFragment_to_doctorFeedFragment)

        } else {
            Toast.makeText(context, "Verifique os campos!", Toast.LENGTH_SHORT).show()
        }
    }

}