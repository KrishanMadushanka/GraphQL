package com.example.graphql.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.graphql.R
import com.example.graphql.models.Node
import de.hdodenhof.circleimageview.CircleImageView

class PinnedReposAdapter(private val dataSet: List<Node>,private  val context: Context) :
    RecyclerView.Adapter<PinnedReposAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rowtxtGitName: TextView
        val rowtxtName: TextView
        val image: CircleImageView
        val rowtxtdesc: TextView
        val txtStarCount: TextView
        val txtLang: TextView

        init {
            rowtxtName = view.findViewById(R.id.rowtxtName)
            rowtxtGitName = view.findViewById(R.id.rowtxtGitName)
            image = view.findViewById(R.id.row_profile_image)
            rowtxtdesc = view.findViewById(R.id.rowtxtdesc)
            txtStarCount = view.findViewById(R.id.txtStarCount)
            txtLang = view.findViewById(R.id.txtLang)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_row, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.rowtxtName.text = dataSet.get(position).name
        viewHolder.rowtxtGitName.text = dataSet.get(position).owner.login
        viewHolder.rowtxtdesc.text = dataSet.get(position).description
        viewHolder.txtStarCount.text = dataSet.get(position).stargazerCount.toString()
        if (dataSet.get(position).primaryLanguage != null)
            viewHolder.txtLang.text = dataSet.get(position).primaryLanguage.name

        Glide.with(context)
            .load(dataSet.get(position).owner.avatarUrl)
            .centerCrop()
            .error(R.mipmap.ic_launcher)
            .into(viewHolder.image)
    }

    override fun getItemCount() = dataSet.size

}