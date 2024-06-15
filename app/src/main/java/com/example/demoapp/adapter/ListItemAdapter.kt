package com.example.demoapp.adapter

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.demoapp.adapter.diff_util.ListItemDiffUtilCallback
import com.example.demoapp.databinding.ListItemBinding
import com.example.demoapp.databinding.ListItemVerticalBinding
import com.example.demoapp.enums.ListType
import com.example.demoapp.model.ListItem
import com.example.demoapp.util.ImageGlider.setImageUrl
import java.text.SimpleDateFormat

class ListItemAdapter: RecyclerView.Adapter<ListItemAdapter.VH>() {

    private lateinit var bindingVertical: ListItemVerticalBinding
    private lateinit var bindingHorizontal: ListItemBinding
    private val items = mutableListOf<ListItem>()
    private var listType: ListType = ListType.HORIZONTAL

    fun setItems(items: MutableList<ListItem>, listType: ListType) {
        this.listType = listType
        this.items.clear()
        val diffUtilCallback = ListItemDiffUtilCallback(this.items, items)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        this.items.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return when (listType) {
            ListType.HORIZONTAL -> {
                bindingHorizontal = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                VH(bindingHorizontal.root)
            }

            ListType.VERTICAL -> {
                bindingVertical = ListItemVerticalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                VH(bindingVertical.root)
            }
        }
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        when (listType) {
            ListType.HORIZONTAL -> {
                BindHorizontalViewHolder().onBind(items[position])
            }
            ListType.VERTICAL -> {
                BindVerticalViewHolder().onBind(items[position])
            }
        }
    }

    override fun getItemCount(): Int  = items.size

    inner class VH(item: View): ViewHolder(item)

    inner class BindHorizontalViewHolder {
        fun onBind(item: ListItem) {
            bindingHorizontal.tvTitle.text = item.title
            bindingHorizontal.tvDescription.spannableText(item.heading, item.description)
            bindingHorizontal.root.context.setImageUrl(item.image, bindingHorizontal.ivItemPicture)
            try {
                val date = SimpleDateFormat("yyyy-MM-dd").parse(item.date)
                val dateInString = SimpleDateFormat("dd MMM yyyy").format(date)
                bindingHorizontal.tvDate.text = dateInString
            } catch (_: Exception) {}
        }
    }

    inner class BindVerticalViewHolder {
        fun onBind(item: ListItem) {
            bindingVertical.tvTitle.text = item.title
            bindingVertical.tvDescription.spannableText(item.heading, item.description)
            bindingVertical.root.context.setImageUrl(item.image, bindingVertical.ivItemPicture)
            try {
                val date = SimpleDateFormat("yyyy-MM-dd").parse(item.date)
                val dateInString = SimpleDateFormat("dd MMM yyyy").format(date)
                bindingVertical.tvDate.text = dateInString
            } catch (_: Exception) {}
        }
    }

    fun TextView.spannableText(heading: String, description: String) {
        val spannable = SpannableString("$heading: $description")
        val indexStart = 0
        val indexEnd = indexStart + heading.length + 1
        spannable.setSpan(StyleSpan(Typeface.BOLD), indexStart, indexEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        text = spannable
    }
}