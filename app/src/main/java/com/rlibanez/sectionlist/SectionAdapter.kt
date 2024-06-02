package com.rlibanez.sectionlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rlibanez.sectionlist.data.Section

class SectionAdapter(val sections: List<Section>): RecyclerView.Adapter<SectionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SectionViewHolder(layoutInflater.inflate(R.layout.item_section, parent, false))
    }

    override fun getItemCount(): Int = sections.size

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        val item = sections[position]
        holder.bind(item)
    }

}