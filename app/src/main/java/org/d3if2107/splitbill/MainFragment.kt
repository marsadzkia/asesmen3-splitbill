package org.d3if2107.splitbill

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import org.d3if2107.splitbill.databinding.FragmentMainBinding
import org.d3if2107.splitbill.db.SplitBillsDb
import org.d3if2107.splitbill.model.JumlahTagihan

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainViewModel by lazy {
        val db = SplitBillsDb.getInstance(requireContext())
        val factory = MainViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[MainViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.history_menu -> {
                findNavController().navigate(R.id.action_mainFragment_to_historyFragment)
                return true
            }
            R.id.rekomendasi_menu -> {
                findNavController().navigate(R.id.action_mainFragment_to_rekomendasiFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonHitung.setOnClickListener { splitBill() }
        binding.batalbutton.setOnClickListener {
            binding.jumlahOrangInp.text?.clear()
            binding.totalBillInp.text?.clear()
            binding.radioGroup.clearCheck()
            binding.totalBillTextView.text=""
            binding.patunganTextView.text=""
        }
        binding.shareButton.setOnClickListener { shareData() }
        binding.infoappButton.setOnClickListener {
            it.findNavController().navigate(
                R.id.action_mainFragment_to_aboutFragment)
        }
        viewModel.getJumlahTagihan().observe(requireActivity(), { lihatHasil (it) })
    }

    private fun splitBill() {
        val jmlhOrang = binding.jumlahOrangInp.text.toString()
        if (TextUtils.isEmpty(jmlhOrang)) {
            Toast.makeText(context, R.string.orang_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val tagihan = binding.totalBillInp.text.toString()
        if (TextUtils.isEmpty(tagihan)) {
            Toast.makeText(context, R.string.tagihan_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val selectedId = binding.radioGroup.checkedRadioButtonId
        if (selectedId == -1){
            Toast.makeText(context, R.string.pajak_invalid, Toast.LENGTH_LONG).show()
            return
        }
        if (binding.belumRadioButton.isChecked) {
            viewModel.hitungPatungan(
                jmlhOrang.toInt(),
                tagihan.toInt() + (tagihan.toInt() * 0.11)
            )
        } else {
            viewModel.hitungPatungan(
            jmlhOrang.toInt(),
            tagihan.toDouble()
        )}
    }

    @SuppressLint("StringFormatMatches")
    private fun lihatHasil(result: JumlahTagihan?) {
        if (result != null) {
            binding.patunganTextView.text = getString(R.string.tagihan_perorang, result.patungan.toFloat())
        }
        binding.shareButton.visibility = View.VISIBLE
        binding.infoappButton.visibility = View.VISIBLE
    }


    private fun shareData() {
        val message = getString(R.string.share_template, binding.patunganTextView.text)

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }
}