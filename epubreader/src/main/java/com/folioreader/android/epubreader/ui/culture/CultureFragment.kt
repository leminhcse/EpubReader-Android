package com.folioreader.android.epubreader.ui.culture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.folioreader.android.epubreader.R
import com.folioreader.android.epubreader.base.BaseFragment

class CultureFragment: BaseFragment() {

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
    }
}