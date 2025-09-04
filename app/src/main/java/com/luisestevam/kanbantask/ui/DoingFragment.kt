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
import com.luisestevam.kanbantask.databinding.FragmentDoingBinding
import com.luisestevam.kanbantask.ui.adapter.TaskAdapter

class DoingFragment : Fragment() {
    private var _binding: FragmentDoingBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskAdapter: TaskAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoingBinding.inflate(inflater, container,false)
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
        Task("7", "Implementar integração com API externa", Status.DOING),
        Task("8", "Refatorar layout da tela de cadastro", Status.DOING),
        Task("9", "Corrigir bug de validação de e-mail", Status.DOING),
        Task("10", "Ajustar responsividade no tablet", Status.DOING),
        Task("11", "Revisar fluxo de autenticação", Status.DOING),
    )




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}