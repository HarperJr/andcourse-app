package com.conceptic.andcourse.presentation.questionnaire.question.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.conceptic.andcourse.R
import com.conceptic.andcourse.data.model.Answer
import com.conceptic.andcourse.data.model.Question
import kotlinx.android.synthetic.main.item_question.view.*

class QuestionsAdapter(
    private val context: Context,
    private val onAnswerBtnClickedListener: (Answer) -> Unit
) : RecyclerView.Adapter<QuestionsAdapter.ViewHolder>() {

    var items = listOf<Question>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_question,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Question) {
            with(itemView) {
                item_question_content.text = item.content
                item_question_yes_btn.setOnClickListener { onAnswerBtnClickedListener.invoke(Answer.YES) }
                item_question_no_btn.setOnClickListener { onAnswerBtnClickedListener.invoke(Answer.NO) }
            }
        }
    }
}
