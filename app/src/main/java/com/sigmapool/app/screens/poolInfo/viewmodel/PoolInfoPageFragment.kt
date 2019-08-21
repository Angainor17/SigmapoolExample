package com.sigmapool.app.screens.poolInfo.viewmodel

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sigmapool.app.R



class PoolInfoPageFragment: Fragment() {


    var pageNumber: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments()?.getInt(ARGUMENT_PAGE_NUMBER) ?: 0
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View = inflater.inflate(R.layout.pool_info_page_fragment, null)
        view.setBackgroundColor(if(pageNumber == 0) Color.RED else Color.CYAN)
        return view
    }

    companion object {
        val ARGUMENT_PAGE_NUMBER: String = "arg_page_number"
        fun newInstance(page: Int): Fragment{
            val pageFragment = PoolInfoPageFragment()
            val arguments = Bundle()
            arguments.putInt(ARGUMENT_PAGE_NUMBER, page)
            pageFragment.arguments = arguments
            return pageFragment
        }
    }
}




