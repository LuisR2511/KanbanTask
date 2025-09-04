package com.luisestevam.kanbantask.ui.adapter
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luisestevam.kanbantask.databinding.ItemTaskBinding


class TaskAdapter: RecyclerView.Adapter<TaskAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        TODO("Not yet implemented")
    }

    override fun onCreateViewhHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, attachToParent: false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val task = taskList[position]
        holder.binding.textDescription.text = task.description
    }

    inner class MyViewHolder(val binding : ItemTaskBinding): RecyclerView.ViewHolder(binding.root){

    }
}