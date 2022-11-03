package com.murillo.gradeufabc.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.murillo.gradeufabc.MainViewModel
import com.murillo.gradeufabc.data.Materia
import com.murillo.gradeufabc.databinding.CardLayoutBinding
import java.time.LocalDate

class MateriaAdapter(
    val mainViewModel: MainViewModel
) : RecyclerView.Adapter<MateriaAdapter.MateriaViewHolder>() {

    private var listMateria = emptyList<Materia>()

    class MateriaViewHolder (val binding: CardLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MateriaViewHolder {
        return MateriaViewHolder(CardLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }


    override fun onBindViewHolder(holder: MateriaViewHolder, position: Int) {
        val materia = listMateria[position]

        holder.binding.nomeMateriaCard.text = materia.nome
        holder.binding.salaMateriaCard.text = materia.sala
        holder.binding.professorMateriaCard.text = materia.professor

    }


    override fun getItemCount(): Int {
        return listMateria.size
    }

    fun setList(list: List<Materia>){
        listMateria = list.sortedByDescending { it.idM }
        notifyDataSetChanged()
    }
}