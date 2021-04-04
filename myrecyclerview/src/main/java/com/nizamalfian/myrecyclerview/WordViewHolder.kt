package com.nizamalfian.myrecyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.viewitem_word.view.*
import java.lang.ref.WeakReference

class WordViewHolder(itemView: View,
                     callback: Callback) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    private val callback: WeakReference<Callback> by lazy {
        WeakReference(callback)
    }

    fun onBind(word: String) {
        itemView.word.text = word
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        callback.get()?.onItemClicked(layoutPosition)
    }

    companion object {
        interface Callback {
            fun onItemClicked(position: Int)
        }
    }
}
