package com.syc.sycmodule

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RecyclerViewEmptySupport : RecyclerView {

    private var emptyView: View? = null
    var mTextView: TextView? = null

    private val emptyObserver: AdapterDataObserver = object : AdapterDataObserver() {
        override fun onChanged() {
            val adapter = adapter
            if (adapter != null && emptyView != null) {
                if (adapter.itemCount == 0) {
                    emptyView!!.visibility = View.VISIBLE
                    this@RecyclerViewEmptySupport.visibility = View.GONE
                } else {
                    emptyView!!.visibility = View.GONE
                    this@RecyclerViewEmptySupport.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        super.setAdapter(adapter)

        if (this.emptyView == null) {
            createEmptyTextView()
        }

        adapter?.registerAdapterDataObserver(emptyObserver)
        emptyObserver.onChanged()
    }

    fun setEmptyView(emptyView: View?) {
        this.emptyView = emptyView
    }

    fun createEmptyTextView() {
        if (mTextView != null) {
            mTextView = TextView(context)
            this.addView(mTextView)
            val param = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            mTextView?.layoutParams = param

            mTextView?.setTextColor(Color.BLACK)
            mTextView?.text = "데이터가 없습니다."
            mTextView?.textSize = 20f
        }

        if (this.emptyView == null) {
            this.emptyView = mTextView
        }
    }

    fun removeEmptyTextView() {
        if (this.parent != null) {
            this.removeView(mTextView)
        }
    }

    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
    }
}