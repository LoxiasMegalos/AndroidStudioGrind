package com.murillo.helpdeaftoy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.murillo.helpdeaftoy.data.Serie
import com.murillo.helpdeaftoy.databinding.FragmentSplashScreenBinding


class SplashScreenFragment : Fragment() {

    private lateinit var binding: FragmentSplashScreenBinding
    private val mainViewModel:MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashScreenBinding.inflate(layoutInflater, container, false)

        binding.logoView.alpha = 0F
        binding.textLogoView.alpha = 0F

        binding.logoView.animate().setDuration(3000).alpha(1f).withEndAction{
            activity?.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
        binding.textLogoView.animate().setDuration(3000).alpha(1f).withEndAction{
            activity?.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

            mainViewModel.selectSeries.observe(viewLifecycleOwner){
                if(mainViewModel.selectSeries.value?.size!! > 0){
                    val serie = Serie(mainViewModel.selectSeries.value?.get(0)?.id!!.toLong(),
                        mainViewModel.selectSeries.value?.get(0)?.nome.toString(),
                        mainViewModel.selectSeries.value?.get(0)?.numeroSerie.toString())
                    mainViewModel.serieLogada.value = serie
                    
                    findNavController().navigate(R.id.action_splashScreenFragment_to_infosFragment)
                } else{
                    findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
                }
            }


        }

        return binding.root
    }

}