package com.conceptic.andcourse.presentation.questionnaire.intro.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.conceptic.andcourse.R
import kotlinx.android.synthetic.main.item_view_intro_page.view.*

class IntroPagerAdapter(context: Context, private val introPages: List<IntroPageItem>) :
    RecyclerView.Adapter<IntroPagerAdapter.ViewHolder>() {
    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return viewHolder(inflater.inflate(R.layout.item_view_intro_page, parent, false))
    }

    override fun getItemCount(): Int = introPages.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(introPages[position])
    }

    private fun viewHolder(view: View) = ViewHolder(view)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: IntroPageItem) = with(item) {
            itemView.item_view_intro_page_background.setImageResource(backgroundRes)
            itemView.item_view_intro_page_description.setText(descriptionRes)
        }
    }
}

data class IntroPageItem(
    @DrawableRes val backgroundRes: Int,
    @StringRes val descriptionRes: Int
)