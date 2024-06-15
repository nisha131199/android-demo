package com.example.demoapp.adapter.diff_util

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.example.demoapp.model.ListItem

class ListItemDiffUtilCallback(oldList: List<ListItem>, newList: List<ListItem>) :
    DiffUtil.Callback() {
    private val oldList: List<ListItem>
    private val newList: List<ListItem>

    init {
        this.oldList = oldList
        this.newList = newList
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldData = oldList[oldItemPosition]
        val newData = newList[newItemPosition]
        return oldData.title == newData.title
    }

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}