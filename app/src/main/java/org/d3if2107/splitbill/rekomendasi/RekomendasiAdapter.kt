package org.d3if2107.splitbill.rekomendasi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.d3if2107.splitbill.databinding.RekomendasiItemBinding
import org.d3if2107.splitbill.model.Rekomendasi

class RekomendasiAdapter : RecyclerView.Adapter<RekomendasiAdapter.ViewHolder>() {
    private val data = mutableListOf<Rekomendasi>()

    fun updateData(newData: List<Rekomendasi>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RekomendasiItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(private val binding: RekomendasiItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(rekomendasi: Rekomendasi) = with(binding) {
                gambarRekomendasi.setImageResource(rekomendasi.logo)
                textRekomendasi.text = rekomendasi.deskripsi
            }
        }
}