package com.folioreader.android.epubreader.ui.adapter

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.folioreader.android.epubreader.R
import com.folioreader.android.epubreader.data.Book
import com.folioreader.android.epubreader.databinding.BookItemBinding
import com.folioreader.android.epubreader.extensions.inflateWithBinding
import com.folioreader.android.epubreader.utils.GeneralUtils.screenWidth
import com.folioreader.android.epubreader.utils.ViewUtils.dip2px

class BookAdapter(context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var bookList: ArrayList<Book>? = null
    private val mContext: Context = context
    var spanCount: Int = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewBinding = parent.inflateWithBinding<BookItemBinding>(R.layout.book_item)
        return BookViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val book = bookList?.get(position)
        if (book != null) {
            (holder as BookViewHolder).bind(book)
        }
    }

    override fun getItemCount(): Int {
        return bookList?.size ?: 0
    }

    private inner class BookViewHolder(private val binding: BookItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            binding.apply {
                this.book = book
                binding.executePendingBindings()

                container.layoutParams.apply {
                    height = screenWidth / spanCount + dip2px(mContext, 90)
                    width = screenWidth / spanCount - dip2px(mContext, 12)
                }
            }
        }
    }

    companion object {
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(view: ImageView, res: Int) {
            Glide.with(view.context)
                .load(res)
                .transform(CenterCrop(), RoundedCorners(24))
                .into(view)
        }
    }

    fun setBookList(list: ArrayList<Book>) {
        bookList = list
    }
}