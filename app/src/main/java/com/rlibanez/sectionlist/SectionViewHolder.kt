package com.rlibanez.sectionlist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rlibanez.sectionlist.data.Section
import com.rlibanez.sectionlist.databinding.ItemSectionBinding

class SectionViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemSectionBinding.bind(view)

    fun bind(section: Section){
        binding.ivSection.text = section.toString()
    }

}