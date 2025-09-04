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
        taskAdapter = TaskAdapter(taskList)
        binding.recyclerViewTask.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewTask.setHasFixedSize(true)

        binding.recyclerViewTask.adapter = taskAdapter
    }
    private fun getTask() = listOf(
        Task("0", "Criar nova tela do app", Status.TODO),
        Task("0", "Validar informações na tela de login", Status.TODO),
        Task("0", "Adicionar nova funcionalidade no app", Status.TODO),
        Task("0", "Salvar token localmente", Status.TODO),
        Task("0", "Criar funcionalidade de logout no app", Status.TODO),
    )

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}