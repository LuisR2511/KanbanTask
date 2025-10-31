package com.luisestevam.kanbantask.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.luisestevam.kanbantask.databinding.FragmentLoginBinding
import com.luisestevam.kanbantask.R
import com.luisestevam.kanbantask.util.showBottomSheet

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializa o FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Inicializa os listeners de clique
        initListener()
    }

    private fun initListener() {
        // Botão de login
        binding.buttonLogin.setOnClickListener {
            validateData()
        }

        // Ir para cadastro
        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        // Ir para recuperação de senha
        binding.btnRecover.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_recoverAccountFragment)
        }
    }

    // Valida se os campos estão preenchidos antes de tentar login
    private fun validateData() {
        val email = binding.editTextEmail.text.toString().trim()
        val senha = binding.editTextPassword.text.toString().trim()

        if (email.isNotBlank()) {
            if (senha.isNotBlank()) {
                binding.progressBar.isVisible = true
                loginUser(email, senha)
            } else {
                showBottomSheet(message = getString(R.string.password_empty))
            }
        } else {
            showBottomSheet(message = getString(R.string.email_empty))
        }
    }

    // Faz o login com FirebaseAuth
    private fun loginUser(email: String, senha: String) {
        try {
            auth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener { task ->
                    binding.progressBar.isVisible = false // sempre esconder após tentativa

                    if (task.isSuccessful) {
                        // Login bem-sucedido
                        Toast.makeText(requireContext(), "Login realizado com sucesso!", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_global_homeFragment)
                    } else {
                        // Falha na autenticação
                        Toast.makeText(
                            requireContext(),
                            "Falha na autenticação: ${task.exception?.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        } catch (e: Exception) {
            binding.progressBar.isVisible = false
            Toast.makeText(requireContext(), e.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
