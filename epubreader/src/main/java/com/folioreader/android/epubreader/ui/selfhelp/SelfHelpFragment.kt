package com.folioreader.android.epubreader.ui.selfhelp

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.folioreader.android.epubreader.R
import com.folioreader.android.epubreader.base.BaseFragment
import com.folioreader.android.epubreader.data.Book
import com.folioreader.android.epubreader.databinding.FragmentSelfhelpBinding
import com.folioreader.android.epubreader.extensions.viewBinding
import com.folioreader.android.epubreader.ui.adapter.BookAdapter
import com.folioreader.android.epubreader.utils.GeneralUtils

class SelfHelpFragment: BaseFragment() {

    override val layoutId: Int get() = R.layout.fragment_selfhelp

    private val binding by viewBinding(FragmentSelfhelpBinding::bind)

    private lateinit var bookAdapter: BookAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    private fun initData() {
        val sc = if (GeneralUtils.getOrientation(requireActivity()) == GeneralUtils.PORTRAIT) 2 else 5

        val bookList = ArrayList<Book>()
        bookList.add(Book(1, "Bố già", "Mario Puzzo", "Tiểu thuyết", R.drawable.godfather))
        bookList.add(Book(2, "Harry potter và hòn đá phù thủy", "Rowling", "Tiểu thuyết", R.drawable.harry_potter))
        bookList.add(Book(3, "Đắc nhân tâm", "Tolkien", "Tiểu thuyết", R.drawable.dacnhantam))
        bookList.add(Book(4, "Hai vạn dặm dưới đáy biển", "Tolkien", "Tiểu thuyết", R.drawable.haivandam))
        bookList.add(Book(5, "Cây cam ngọt của tôi", "José Mauro de Vasconcelos", "Tiểu thuyết", R.drawable.cay_cam_ngot_cua_toi))
        bookList.add(Book(6, "7 thói quen của người thành đạt", "Tolkien", "Tiểu thuyết", R.drawable.thoi_quen))

        bookAdapter = BookAdapter(requireContext()).apply {
            spanCount = sc
            setBookList(bookList)
        }

        binding.recyclerView.apply {
            val gridLayoutManager = GridLayoutManager(context, sc)
            binding.recyclerView.apply {
                layoutManager = gridLayoutManager
                adapter = bookAdapter
            }
        }
    }
}