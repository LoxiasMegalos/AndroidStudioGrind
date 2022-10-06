package com.generation.desenvolvmed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.generation.desenvolvmed.adapter.PostagemAdapter
import com.generation.desenvolvmed.databinding.FragmentUserFeedBinding
import com.generation.desenvolvmed.databinding.FragmentUserProfileBinding


class UserFeedFragment : Fragment() {

    private lateinit var binding: FragmentUserFeedBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserFeedBinding.inflate(layoutInflater, container, false)

        mainViewModel.listPostagem()

        val postagemAdapter = PostagemAdapter()
        binding.recyclerPostagem.layoutManager = LinearLayoutManager(context)
        binding.recyclerPostagem.adapter = postagemAdapter
        binding.recyclerPostagem.setHasFixedSize(true)

        mainViewModel.myPostagemResponse.observe(viewLifecycleOwner){
                response -> if(response.body() != null){
                    postagemAdapter.setList(response.body()!!)
                }
        }

        binding.homeButton.setOnClickListener{
            mainViewModel.listPostagem()
        }

        binding.perfilButton.setOnClickListener {
            findNavController().navigate(R.id.action_userFeedFragment_to_userProfileFragment)
        }

        return binding.root
    }


}