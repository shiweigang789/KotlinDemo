package com.lovely.pig.kotlindemo.ui.adapter

import android.content.Context
import android.graphics.Typeface
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.lovely.pig.kotlindemo.MyApplication
import com.lovely.pig.kotlindemo.R
import com.lovely.pig.kotlindemo.base.recyclerview.CommonAdapter
import com.lovely.pig.kotlindemo.base.recyclerview.MultipleType
import com.lovely.pig.kotlindemo.base.recyclerview.ViewHolder
import com.lovely.pig.kotlindemo.durationFormat
import com.lovely.pig.kotlindemo.glide.GlideApp
import com.lovely.pig.kotlindemo.glide.GlideRoundTransform
import com.lovely.pig.kotlindemo.mvp.model.bean.HomeBean

/**
 * 作者 swg
 * 时间 2019/6/12 12:44
 * 文件 KotlinDemo
 * 描述 视频详情
 */
class VideoDetailAdapter(mContext: Context, data: ArrayList<HomeBean.Issue.Item>) :
        CommonAdapter<HomeBean.Issue.Item>(mContext, data, object : MultipleType<HomeBean.Issue.Item> {
            override fun getLayoutId(item: HomeBean.Issue.Item, position: Int): Int {
                return when {
                    position == 0 -> R.layout.item_video_detail_info
                    data[position].type == "textCard" -> R.layout.item_video_text_card
                    data[position].type == "videoSmallCard" -> R.layout.item_video_small_card
                    else -> throw IllegalAccessException("Api 解析出错了，出现其他类型")
                }
            }
        }) {

    private var textTypeface: Typeface? = null

    init {
        textTypeface = Typeface.createFromAsset(MyApplication.context.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")
    }

    /**
     * 添加视频的详细信息
     */
    fun addData(item: HomeBean.Issue.Item) {
        mData.clear()
        notifyDataSetChanged()
        mData.add(item)
        notifyItemInserted(0)
    }

    /**
     * 添加相关推荐等数据 Item
     */
    fun addData(item: ArrayList<HomeBean.Issue.Item>) {
        mData.addAll(item)
        notifyItemRangeInserted(1, item.size)
    }

    /**
     * Kotlin的函数可以作为参数，写callback的时候，可以不用interface了
     */
    private var mOnItemClickRelatedVideo: ((item: HomeBean.Issue.Item) -> Unit)? = null

    fun setOnItemDetailClick(mItemRelatedVideo: ((item: HomeBean.Issue.Item) -> Unit)) {
        this.mOnItemClickRelatedVideo = mItemRelatedVideo
    }

    /**
     * 绑定数据
     */
    override fun bindData(holder: ViewHolder, data: HomeBean.Issue.Item, position: Int) = when {
        position == 0 -> setVideoDetailInfo(data, holder)
        data.type == "textCard" -> {
            holder.setText(R.id.tv_text_card, data.data?.text!!)
            //设置方正兰亭细黑简体
            holder.getView<TextView>(R.id.tv_text_card).typeface =textTypeface
        }
        data.type == "videoSmallCard" -> {
            with(holder) {
                setText(R.id.tv_title, data.data?.title!!)
                setText(R.id.tv_tag, "#${data.data.category} / ${durationFormat(data.data.duration)}")
                setImagePath(R.id.iv_video_small_card, object : ViewHolder.HolderImageLoader(data.data.cover.detail) {
                    override fun loadImage(iv: ImageView, path: String) {
                        GlideApp.with(mContext)
                                .load(path)
                                .optionalTransform(GlideRoundTransform())
                                .placeholder(R.drawable.placeholder_banner)
                                .into(iv)
                    }
                })
            }
            // 判断onItemClickRelatedVideo 并使用
            holder.itemView.setOnClickListener { mOnItemClickRelatedVideo?.invoke(data) }
        }
        else -> throw IllegalAccessException("Api 解析出错了，出现其他类型")
    }

    /**
     * 设置视频详情数据
     */
    private fun setVideoDetailInfo(data: HomeBean.Issue.Item, holder: ViewHolder) {
        data.data?.apply {
            holder.setText(R.id.tv_title, title)
            //视频简介
            holder.setText(R.id.expandable_text, description)

            //标签
            holder.setText(R.id.tv_tag, "#$category / ${durationFormat(duration)}")
            //喜欢
            holder.setText(R.id.tv_action_favorites, consumption.collectionCount.toString())
            //分享
            holder.setText(R.id.tv_action_share, consumption.shareCount.toString())
            //评论
            holder.setText(R.id.tv_action_reply, consumption.replyCount.toString())
            with(holder){
                setText(R.id.tv_author_name, author.name)
                setText(R.id.tv_author_desc, author.description)
            }

            with(holder) {
                getView<TextView>(R.id.tv_action_favorites).setOnClickListener {
                    Toast.makeText(MyApplication.context, "喜欢", Toast.LENGTH_SHORT).show()
                }
                getView<TextView>(R.id.tv_action_share).setOnClickListener {
                    Toast.makeText(MyApplication.context, "分享", Toast.LENGTH_SHORT).show()
                }
                getView<TextView>(R.id.tv_action_reply).setOnClickListener {
                    Toast.makeText(MyApplication.context, "评论", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}