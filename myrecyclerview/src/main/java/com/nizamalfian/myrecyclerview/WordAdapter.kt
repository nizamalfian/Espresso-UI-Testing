package com.nizamalfian.myrecyclerview

import android.view.LayoutInflater.from
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class WordAdapter(private val callback: WordViewHolder.Companion.Callback)
    : RecyclerView.Adapter<WordViewHolder>() {

    private val words: LinkedList<String> by lazy {
        LinkedList<String>()
    }

    fun updateData(words: List<String>) {
        this.words.addAll(words)
        notifyDataSetChanged()
    }

    fun updateData(position: Int, word: String) {
        this.words[position] = word
        notifyItemChanged(position)
    }

    fun addData(word: String) {
        val wordsSize = itemCount
        this.words.addLast(word)
        notifyItemInserted(wordsSize)
    }

    fun getData() = this.words

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder(
            from(parent.context).inflate(R.layout.viewitem_word, parent, false),
            callback
        )
    }

    override fun getItemCount(): Int = this.words.size

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.onBind(words.getOrNull(position) ?: "")
    }
}
