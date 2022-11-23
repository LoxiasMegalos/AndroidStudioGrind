package com.murillo.helpdeaftoy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.murillo.helpdeaftoy.databinding.FragmentInfosBinding

class InfosFragment : Fragment() {

    private lateinit var binding: FragmentInfosBinding
    private val mainViewModel : MainViewModel by activityViewModels()
    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.from_bottom_anim) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.to_bottom_anim) }
    private var clicked = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfosBinding.inflate(layoutInflater, container, false)

        binding.textNomeUser.text = mainViewModel.serieLogada.value?.nome.toString()
        binding.textSerialNumberUser.text = mainViewModel.serieLogada.value?.numeroSerie.toString()


        binding.perfilButton.setOnClickListener{
            clicked = !clicked
            Toast.makeText(context, "Indo para o perfíl!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_infosFragment_to_perfilFragment)
        }

        binding.documentButton.setOnClickListener{
            clicked = !clicked
            Toast.makeText(context, "Indo para as instruções!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_infosFragment_to_instrucoesFragment)
        }

        binding.menuButton.setOnClickListener {
            onHamburguerButtonClicked()
        }

        return binding.root
    }

    private fun onHamburguerButtonClicked(){
        setVisibility(clicked)
        setAnimation(clicked)
        clicked = !clicked
    }

    private fun setAnimation(clicked: Boolean) {
        if(!clicked){
            binding.documentButton.startAnimation(fromBottom)
            binding.perfilButton.startAnimation(fromBottom)
            binding.menuButton.startAnimation(rotateOpen)
        } else{
            binding.documentButton.startAnimation(toBottom)
            binding.perfilButton.startAnimation(toBottom)
            binding.menuButton.startAnimation(rotateClose)
        }
    }

    private fun setVisibility(clicked: Boolean) {
        if(!clicked){
            binding.documentButton.visibility = VISIBLE
            binding.perfilButton.visibility = VISIBLE
        } else{
            binding.documentButton.visibility = INVISIBLE
            binding.perfilButton.visibility = INVISIBLE
        }
    }

}