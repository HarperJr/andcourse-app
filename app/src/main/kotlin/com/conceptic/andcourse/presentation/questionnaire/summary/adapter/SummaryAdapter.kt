package com.conceptic.andcourse.presentation.questionnaire.summary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.conceptic.andcourse.R
import com.conceptic.andcourse.data.model.Feature
import kotlinx.android.synthetic.main.item_summary_auth_proposal.view.*
import kotlinx.android.synthetic.main.item_summary_feature_widget.view.*

/**
 * Additional item's types
 */
object AuthProposal

object RetryProposal

private val diffUtilsCallback = object : DiffUtil.ItemCallback<Any>() {
    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean = when {
        oldItem is Feature && newItem is Feature -> oldItem.type == newItem.type
        else -> false
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = oldItem::class == newItem::class
}

class SummaryAdapter(private val onRetryBtnClickListener: () -> Unit) : ListAdapter<Any, RecyclerView.ViewHolder>(diffUtilsCallback) {
    var items: List<Any> = emptyList()
        set(value) {
            field = listOf(*value.toTypedArray(), RetryProposal)
            notifyDataSetChanged()
        }
    private var onSignUpBtnClickListener: (() -> Unit)? = null

    fun showAuthProposal(onSignUpBtnClickListener: () -> Unit) {
        if (items.last() !is AuthProposal) {
            this.onSignUpBtnClickListener = onSignUpBtnClickListener
            items = listOf(*items.toTypedArray(), AuthProposal)
        }
    }

    override fun getItemViewType(position: Int): Int = when (items[position]) {
        is Feature -> FEATURE_VIEW
        is AuthProposal -> AUTH_PROPOSAL_VIEW
        is RetryProposal -> RETRY_PROPOSAL_VIEW
        else -> throw IllegalStateException("Unable to resolve viewType of item at position=$position")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        with(LayoutInflater.from(parent.context)) {
            when (viewType) {
                FEATURE_VIEW -> FeatureViewHolder(inflate(R.layout.item_summary_feature_widget, parent, false))
                AUTH_PROPOSAL_VIEW -> AuthProposalViewHolder(inflate(R.layout.item_summary_auth_proposal, parent, false))
                RETRY_PROPOSAL_VIEW -> RetryProposalViewHolder(inflate(R.layout.item_summary_retry_proposal, parent, false))
                else -> throw IllegalStateException("Unable to resolve view holder of item by view type=$viewType")
            }
        }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FeatureViewHolder)
            holder.bind(items[position] as Feature)
    }

    inner class FeatureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Feature) = with(itemView) {
            item_summary_title.text = context.getString(item.type.description)
        }
    }

    inner class AuthProposalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            with(itemView) {
                item_summary_proposal_btn.setOnClickListener {
                    onSignUpBtnClickListener?.invoke()
                }
            }
        }
    }
    
    inner class RetryProposalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            with(itemView) {
                
            }
        }
    }

    companion object {
        private const val FEATURE_VIEW = 0
        private const val AUTH_PROPOSAL_VIEW = 1
        private const val RETRY_PROPOSAL_VIEW = 2
    }
}
