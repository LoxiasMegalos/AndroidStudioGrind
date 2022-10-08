package com.generation.desenvolvmed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.generation.desenvolvmed.databinding.FragmentUpdateDoctorProfileBinding
import com.generation.desenvolvmed.databinding.FragmentUpdateUserProfileBinding
import com.generation.desenvolvmed.model.MedicoCadastro


class updateDoctorProfile : Fragment() {

    private lateinit var binding: FragmentUpdateDoctorProfileBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpdateDoctorProfileBinding.inflate(layoutInflater, container, false)

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                binding.textNovaSenha.hint = binding.spinner.selectedItem.toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        binding.buttonConfirmaUpdate.setOnClickListener {
            cadastraUpdate()
        }

        return binding.root
    }

    private fun cadastraUpdate() {

        var parametro = binding.textNovaSenha.text.toString()
        binding.textNovaSenha.text.clear()
        if(binding.spinner.selectedItem.toString() == "Nome"){
            if(parametro.isNotBlank() && parametro.length in 1..255){
                mainViewModel.attMedico(MedicoCadastro(
                    mainViewModel.medicoLogado.value?.body()?.cadastro?.id!!.toLong(),
                    mainViewModel.medicoLogado.value?.body()?.cadastro?.cpf.toString(),
                    parametro,
                    mainViewModel.medicoLogado.value?.body()?.cadastro?.sobrenome.toString(),
                    mainViewModel.medicoLogado.value?.body()?.cadastro?.senha.toString(),
                    mainViewModel.medicoLogado.value?.body()?.cadastro?.email.toString(),
                    mainViewModel.medicoLogado.value?.body()?.crm.toString(),
                ), mainViewModel.medicoLogado.value?.body()?.cadastro?.email.toString())
            } else{
                Toast.makeText(context, "Nome Incorreto", Toast.LENGTH_SHORT).show()
            }
        } else if (binding.spinner.selectedItem.toString() == "Sobrenome") {
            if(parametro.isNotBlank() && parametro.length in 1..255){
                    mainViewModel.attMedico(MedicoCadastro(
                    mainViewModel.medicoLogado.value?.body()?.cadastro?.id!!.toLong(),
                    mainViewModel.medicoLogado.value?.body()?.cadastro?.cpf.toString(),
                    mainViewModel.medicoLogado.value?.body()?.cadastro?.nome.toString(),
                    parametro,
                    mainViewModel.medicoLogado.value?.body()?.cadastro?.senha.toString(),
                    mainViewModel.medicoLogado.value?.body()?.cadastro?.email.toString(),
                    mainViewModel.medicoLogado.value?.body()?.crm.toString(),
                ), mainViewModel.medicoLogado.value?.body()?.cadastro?.email.toString())
            } else{
                Toast.makeText(context, "Sobrenome Incorreto", Toast.LENGTH_SHORT).show()
            }
        } else if (binding.spinner.selectedItem.toString() == "E-mail") {
            if(parametro.isNotBlank() && parametro.length in 1..255){
                mainViewModel.attMedico(MedicoCadastro(
                    mainViewModel.medicoLogado.value?.body()?.cadastro?.id!!.toLong(),
                    mainViewModel.medicoLogado.value?.body()?.cadastro?.cpf.toString(),
                    mainViewModel.medicoLogado.value?.body()?.cadastro?.nome.toString(),
                    mainViewModel.medicoLogado.value?.body()?.cadastro?.sobrenome.toString(),
                    mainViewModel.medicoLogado.value?.body()?.cadastro?.senha.toString(),
                    parametro,
                    mainViewModel.medicoLogado.value?.body()?.crm.toString(),
                ), parametro)
            } else{
                Toast.makeText(context, "E-mail Incorreto", Toast.LENGTH_SHORT).show()
            }
        } else{
            if(parametro.isNotBlank() && parametro.length in 1..255){
                mainViewModel.attMedico(MedicoCadastro(
                    mainViewModel.medicoLogado.value?.body()?.cadastro?.id!!.toLong(),
                    mainViewModel.medicoLogado.value?.body()?.cadastro?.cpf.toString(),
                    mainViewModel.medicoLogado.value?.body()?.cadastro?.nome.toString(),
                    mainViewModel.medicoLogado.value?.body()?.cadastro?.sobrenome.toString(),
                    parametro,
                    mainViewModel.medicoLogado.value?.body()?.cadastro?.email.toString(),
                    mainViewModel.medicoLogado.value?.body()?.crm.toString(),
                ), mainViewModel.medicoLogado.value?.body()?.cadastro?.email.toString())
            } else{
                Toast.makeText(context, "Senha Invalida", Toast.LENGTH_SHORT).show()
            }
        }
    }

}