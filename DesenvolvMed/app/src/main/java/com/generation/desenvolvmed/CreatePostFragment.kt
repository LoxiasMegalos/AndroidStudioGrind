package com.generation.desenvolvmed

import android.annotation.SuppressLint
import android.os.Bundle
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
import com.generation.desenvolvmed.model.Tema



class CreatePostFragment : Fragment() {


    private lateinit var binding: FragmentCreatePostBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private var temaSelecionado = 0L

    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreatePostBinding.inflate(layoutInflater, container, false)

        //binding.dmToolbar.toolbarImg.drawable

        binding.botaoPublicar.setOnClickListener {
            inserirNoBanco()
        }

        return binding.root
    }

    private fun spinnerTema(listaTema: List<Tema>) {

        if (listaTema.isNullOrEmpty())
            throw IllegalArgumentException("Lista vazia!")


        binding.selecTemas.adapter =
            ArrayAdapter(
                requireContext(),
                androidx.transition.R.layout.support_simple_spinner_dropdown_item,
                listaTema
            )

        binding.selecTemas.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    val selected = binding.selecTemas.selectedItem as Tema

                    temaSelecionado = selected.id
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }
    }

    private fun validarCampos(titulo: String, conteudo: String, anexo: String): Boolean {
        return (
                (titulo.isNotBlank() || titulo.length in 20..255) &&
                        (conteudo.isNotBlank() || conteudo.length in 20..5000) &&
                        (anexo.isNotBlank() || anexo.length in 10..500)
                )
    }

    //@RequiresApi(Build.VERSION_CODES.O)
    private fun inserirNoBanco() {

        val titulo = binding.tituloText.text.toString()
        val conteudo = binding.postText.text.toString()
        val anexo = binding.textAnexo.text.toString()
        //val medico = binding.currentUser.medico.id
        //val medico = Medico(1, "CRM/SP 123546", Cadastro(1, "01754689720",
        //    "Joviraldo", "Robson", "2154154", "jovis@gmail.com", null ), null)
        //val data = LocalDateTime.now().toString()
        //val tema = Tema(temaSelecionado, null, null)

        if (validarCampos(titulo, conteudo, anexo)) {

            //val postagem = Postagem(0, titulo, conteudo, anexo , data ,tema, medico)
            //mainViewModel.addPostagem(postagem)
            Toast.makeText(context, "Postagem criada!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_createPostFragment_to_doctorFeedFragment)

        } else {
            Toast.makeText(context, "Verifique os campos!", Toast.LENGTH_SHORT).show()
        }
    }

}