package com.malicankaya.a02bitcointicker.view

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.malicankaya.a02bitcointicker.R
import com.malicankaya.a02bitcointicker.databinding.FragmentLoginBinding
import com.malicankaya.a02bitcointicker.firebase.AuthManager
import com.malicankaya.a02bitcointicker.viewmodel.LoginFragmentViewModel
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: LoginFragmentViewModel
    private lateinit var auth: FirebaseAuth
    private var authManager = AuthManager()
    private var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        user = auth.currentUser
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginFragmentViewModel::class.java]

        binding.btnGoogleSignIn.setOnClickListener {
            lifecycleScope.launch {
                authenticate()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        updateUI(user)
    }

    private suspend fun authenticate() {
        authManager.googleSignInRequest(requireContext()).let {
            val idToken = it?.idToken
            when {
                idToken != null -> {
                    var firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
                    auth.signInWithCredential(firebaseCredential)
                        .addOnCompleteListener(requireActivity()) {
                            if (it.isSuccessful) {
                                user = auth.currentUser
                                updateUI(user)
                            } else {
                                // If sign in fails, display a message to the user.
                            }
                        }
                }

                else -> {
                    Log.d(TAG, "No ID token!")
                }
            }
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment?
            if(navHostFragment != null){
                val navController = navHostFragment.navController
                val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
                navGraph.setStartDestination(R.id.coinListFragment)
                navController.graph = navGraph
            }else{
                Toast.makeText(
                    requireContext(),
                    "NavHost null geldi",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}