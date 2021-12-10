package com.badmitry.vtbhackaton.rvadapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.badmitry.domain.entities.vtbcreditrequest.VtbApplicationId
import com.badmitry.vtbhackaton.databinding.RvItemApplicationsBinding
import com.badmitry.vtbhackaton.view.OnApplicationDeleteView

class ApplicationRVAdapter(
    private val context: Context,
    private val onApplicationDeleteView: OnApplicationDeleteView
) :
    RecyclerView.Adapter<ApplicationRVAdapter.ViewHolder>() {
    private lateinit var adapterBinding: RvItemApplicationsBinding

    private var applications = listOf<VtbApplicationId>()

    fun notify(appList: List<VtbApplicationId>) {
        applications = appList
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: RvItemApplicationsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged")
        fun bind(application: VtbApplicationId) {
            binding.applicationId.text = application.applicationId
            binding.btnDelete.setOnClickListener{
                onApplicationDeleteView.deleteApplicationId(application.applicationId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        adapterBinding =
            RvItemApplicationsBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(adapterBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(applications[position])
    }

    override fun getItemCount(): Int {
        return applications.size
    }
}