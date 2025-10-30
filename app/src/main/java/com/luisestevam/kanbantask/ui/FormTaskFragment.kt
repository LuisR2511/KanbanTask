package com.luisestevam.kanbantask.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.luisestevam.kanbantask.R
import com.luisestevam.kanbantask.data.model.Status
import com.luisestevam.kanbantask.data.model.Task
import com.luisestevam.kanbantask.databinding.FragmentFormTaskBinding
import com.luisestevam.kanbantask.util.initToolbar
import com.luisestevam.kanbantask.util.showBottomSheet

class FormTaskFragment : Fragment() {

    private var _binding: FragmentFormTaskBinding? = null
    private val binding get() = _binding!!
    private lateinit var task: com.luisestevam.kanbantask.data.model.Task
    private var newTask: Boolean=true
    private var status: Status= Status.TODO

    //Banco de Dados
    private lateinit var reference: DatabaseReference

    //Acesso ao serviço de autentication
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFormTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.toolbar)
        //Instanciando o banco de dados e a autenticação
        reference= Firebase.database.reference
        auth= Firebase.auth

        initListener()
    }

    private fun saveTask(){
        reference
            .child("task")
            .child(auth.currentUser?.uid ?:"")
            .child(task.id).setValue(task).addOnCompleteListener { result ->
                if (result.isSuccessful){
                    Toast.makeText(requireContext(), R.string.text_save_sucess_task_fragment, Toast.LENGTH_SHORT).show()
                    if (newTask){
                        findNavController().popBackStack()
                    }else{
                        binding.progressBar.isVisible=false
                    }
                }else{
                    binding.progressBar.isVisible=false
                    showBottomSheet(message = getString(R.string.error_generic))
                }
            }


    }

    private fun initListener() {
        binding.buttonSave.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        val description = binding.editTextDescricao.text.toString().trim()
        if (description.isNotBlank()) {
            if (newTask) task = Task("0","")
            task.id=reference.database.reference.push().key ?:""
            task.description=description
            task.status=status
        } else {
            showBottomSheet(message = getString(R.string.description_empty_form_task_fragment))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}