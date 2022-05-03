package com.folioreader.android.epubreader.ui.culture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.folioreader.android.epubreader.R
import com.folioreader.android.epubreader.base.BaseFragment
import com.folioreader.android.epubreader.databinding.FragmentCultureBinding
import com.folioreader.android.epubreader.extensions.viewBinding
import com.folioreader.android.epubreader.utils.FolioReaderUtil

class CultureFragment: BaseFragment(), View.OnClickListener {

    private val binding by viewBinding(FragmentCultureBinding::bind)

    override val layoutId: Int get() = R.layout.fragment_culture

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_raw -> {
                FolioReaderUtil.openBookByRaw()
            }
            R.id.btn_assest -> {
                FolioReaderUtil.openBookByAssest()
            }
        }
    }

    private fun initViewPager() {
        binding.btnRaw.setOnClickListener(this)
        binding.btnAssest.setOnClickListener(this)

        context?.let { FolioReaderUtil.setupFolioReader(it) }
    }
}