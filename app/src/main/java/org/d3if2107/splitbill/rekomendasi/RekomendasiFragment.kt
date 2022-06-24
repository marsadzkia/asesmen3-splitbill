package org.d3if2107.splitbill.rekomendasi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.d3if2107.splitbill.databinding.FragmentRekomendasiBinding
import org.d3if2107.splitbill.internet.RekomendasiStatus


class RekomendasiFragment : Fragment() {
    private val viewModel: RekomendasiViewModel by lazy {
        ViewModelProvider(this).get(RekomendasiViewModel::class.java)
    }
    private lateinit var binding: FragmentRekomendasiBinding
    private lateinit var myAdapter: RekomendasiAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRekomendasiBinding.inflate(layoutInflater, container, false)
        myAdapter = RekomendasiAdapter()
        with (binding.recyclerView) {
            adapter = myAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData().observe(viewLifecycleOwner, {
            myAdapter.updateData(it)
        })

        viewModel.getStatus().observe(viewLifecycleOwner, {
            updateProgress(it)
        })
    }

    private fun updateProgress(status: RekomendasiStatus) {
        when (status) {
            RekomendasiStatus.LOADING -> {
                binding.loading.visibility = View.VISIBLE
            }
            RekomendasiStatus.SUCCESS -> {
                binding.loading.visibility = View.GONE
            }
            RekomendasiStatus.FAILED -> {
                binding.loading.visibility = View.GONE
                binding.koneksiError.visibility = View.VISIBLE
            }
        }
    }
}