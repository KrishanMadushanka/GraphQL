package com.example.graphql.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.graphql.R
import com.example.graphql.models.Edge
import de.hdodenhof.circleimageview.CircleImageView


class TopReposAdapter(private val dataSet: List<Edge>, private val context: Context) :
    RecyclerView.Adapter<TopReposAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val main: LinearLayout
        val rowtxtGitName: TextView
        val rowtxtName: TextView
        val image: CircleImageView
        val rowtxtdesc: TextView
        val txtStarCount: TextView
        val txtLang: TextView

        init {
            main =  view.findViewById(R.id.main)
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
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.width = 900
        params.setMargins(10,10,10,10)
        viewHolder.main.setLayoutParams(params)
        viewHolder.rowtxtName.text = dataSet.get(position).node.name
        viewHolder.rowtxtGitName.text = dataSet.get(position).node.owner.login
        viewHolder.rowtxtdesc.text = dataSet.get(position).node.description
        viewHolder.txtStarCount.text = dataSet.get(position).node.stargazerCount.toString()
        if (dataSet.get(position).node.primaryLanguage != null)
            viewHolder.txtLang.text = dataSet.get(position).node.primaryLanguage.name

        Glide.with(context)
            .load(dataSet.get(position).node.owner.avatarUrl)
            .centerCrop()
            .error(R.mipmap.ic_launcher)
            .into(viewHolder.image)
    }

    override fun getItemCount() = dataSet.size

}