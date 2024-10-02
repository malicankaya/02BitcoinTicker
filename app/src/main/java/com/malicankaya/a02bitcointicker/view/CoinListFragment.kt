package com.malicankaya.a02bitcointicker.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.malicankaya.a02bitcointicker.R
import com.malicankaya.a02bitcointicker.adapter.CoinListAdapter
import com.malicankaya.a02bitcointicker.databinding.FragmentCoinListBinding
import com.malicankaya.a02bitcointicker.model.CoinModel
import com.malicankaya.a02bitcointicker.viewmodel.CoinListFragmentViewModel
import kotlinx.coroutines.launch

class CoinListFragment : Fragment() {

    private var _binding : FragmentCoinListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CoinListFragmentViewModel
    private var adapter = CoinListAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinListBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CoinListFragmentViewModel::class.java]
        viewModel.refreshCoinList()
        binding.rvCoinList.layoutManager = LinearLayoutManager(requireContext()).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        binding.rvCoinList.adapter = adapter

        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.isLoading.observe(viewLifecycleOwner){
            binding.rvCoinList.isVisible = !it
            binding.progressBar.isVisible = it
        }

        viewModel.coinList.observe(viewLifecycleOwner){
            adapter.updateList(it)
        }
    }
}