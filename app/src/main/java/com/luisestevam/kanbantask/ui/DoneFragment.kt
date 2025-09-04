package com.luisestevam.kanbantask.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.luisestevam.kanbantask.R
import com.luisestevam.kanbantask.data.model.Status
import com.luisestevam.kanbantask.data.model.Task
import com.luisestevam.kanbantask.databinding.FragmentDoneBinding
import com.luisestevam.kanbantask.ui.adapter.TaskAdapter


class DoneFragment : Fragment() {
    private var _binding: FragmentDoneBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskAdapter: TaskAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoneBinding.inflate(inflater, container,  false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView(getTask())
    }
    private fun initRecyclerView(taskList: List<Task>) {
        taskAdapter = TaskAdapter(requireContext(), taskList)
        binding.recyclerViewTask.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewTask.setHasFixedSize(true)

        binding.recyclerViewTask.adapter = taskAdapter
    }
    private fun getTask() = listOf(
        Task("12", "Configurar tema principal do app", Status.DONE),
        Task("13", "Criar tela de splash", Status.DONE),
        Task("14", "Implementar navegação entre fragments", Status.DONE),
        Task("15", "Desenvolver tela de recuperação de senha", Status.DONE),
        Task("16", "Adicionar ícones personalizados", Status.DONE),
    )



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}