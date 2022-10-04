package com.generation.desenvolvmed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.generation.desenvolvmed.databinding.FragmentUserSignUpBinding

class UserSignUpFragment : Fragment() {

    private lateinit var binding : FragmentUserSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserSignUpBinding.inflate(layoutInflater, container, false)

        binding.buttonUserFeed.setOnClickListener{
            findNavController().navigate(R.id.action_userSignUpFragment_to_userFeedFragment)
        }



        return binding.root
    }

}