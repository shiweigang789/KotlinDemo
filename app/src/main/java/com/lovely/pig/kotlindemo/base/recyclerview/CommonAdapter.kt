package com.lovely.pig.kotlindemo.base.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * 作者 swg
 * 时间 2019/5/31 10:22
 * 文件 KotlinDemo
 * 描述
 */
abstract class CommonAdapter<T>(var mContext: Context, var mData: ArrayList<T>,
                                private var mLayoutId: Int) :
        RecyclerView.Adapter<ViewHolder>() {

    protected var mInflater: LayoutInflater? = null
    private var mTypeSupport: MultipleType<T>? = null

    //使用接口回调点击事件
    private var mItemClickListener: OnItemClickListener? = null

    //使用接口回调点击事件
    private var mItemLongClickListener: OnItemLongClickListener? = null

    init {
        mInflater = LayoutInflater.from(mContext)
    }

    constructor(context: Context, data: ArrayList<T>, typeSupport: MultipleType<T>) : this(context, data, -1) {
        this.mTypeSupport = typeSupport
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (mTypeSupport != null){
            mLayoutId = viewType
        }
        val view = mInflater?.inflate(mLayoutId, parent, false)
        return ViewHolder(view!!)
    }

    override fun getItemViewType(position: Int): Int {
        return mTypeSupport?.getLayoutId(mData[position], position) ?: super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //绑定数据
        bindData(holder, mData[position], position)

        //条目点击事件
        mItemClickListener?.let {
            holder.itemView.setOnClickListener { mItemClickListener!!.onItemClick(mData[position], position) }
        }

        //长按点击事件
        mItemLongClickListener?.let {
            holder.itemView.setOnLongClickListener { mItemLongClickListener!!.onItemLongClick(mData[position], position) }
        }
    }

    /**
     * 将必要参数传递出去
     *
     * @param holder
     * @param data
     * @param position
     */
    protected abstract fun bindData(holder: ViewHolder, data: T, position: Int)

    override fun getItemCount(): Int {
        return mData.size
    }

    fun setOnItemClickListener(itemClickListener: OnItemClickListener) {
        this.mItemClickListener = itemClickListener
    }

    fun setOnItemLongClickListener(itemLongClickListener: OnItemLongClickListener) {
        this.mItemLongClickListener = itemLongClickListener
    }

}