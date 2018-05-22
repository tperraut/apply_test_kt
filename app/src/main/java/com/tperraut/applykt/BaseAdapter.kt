package com.tperraut.applykt

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_view.view.*

class BaseAdapter(private val listener: BaseAdapter.Listener): RecyclerView.Adapter<BaseAdapter.ViewHolder>() {

    lateinit var mDataSet: ArrayList<Model>

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val v: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }

    override fun onBindViewHolder(holder: BaseAdapter.ViewHolder, position: Int) {
        val data: Model = mDataSet[position]
        holder.itemView.item_title.setText(data.mTitleRes)
        holder.itemView.item_description.setText(data.mDescriptionRes)
        holder.itemView.item_image.setImageResource(data.mImageRes)
        holder.itemView.item_share_bt.setOnClickListener { listener.onShareRequested(data) }
        holder.itemView.item_details_bt.setOnClickListener { listener.onDetailsRequested(data) }
    }

    fun addItem(@StringRes titleRes: Int, @StringRes descriptionRes: Int, @DrawableRes imageRes: Int) {
        mDataSet.add(0, Model(titleRes, descriptionRes, imageRes))
        notifyItemInserted(0)
    }

    fun removeItem() {
        mDataSet.removeAt(0)
        notifyItemRemoved(0)
    }

    interface Listener {
        fun onShareRequested(model: Model)
        fun onDetailsRequested(model: Model)
    }
}