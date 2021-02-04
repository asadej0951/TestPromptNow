package com.example.testpromptnow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.testpromptnow.databinding.ItemDataBinding

class AdapterData(
    private val mData: ArrayList<String>,
    private val onClickCheck: MutableLiveData<Int>
) : RecyclerView.Adapter<AdapterData.ViewHolderData>() {
    class ViewHolderData(internal val binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderData {
        val itemData: ItemDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_data, parent, false
        )
        return ViewHolderData(itemData)
    }

    override fun onBindViewHolder(holder: ViewHolderData, position: Int) {
        holder.binding.checkbox.isChecked = false
        holder.binding.nameData.text = mData[position]
        holder.binding.checkbox.setOnClickListener {
            onClickCheck.value = position
        }
    }

    override fun getItemCount() = mData.size
}