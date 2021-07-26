package com.example.graphql

import android.app.AlertDialog
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.graphql.adapters.PinnedReposAdapter
import com.example.graphql.adapters.StarredReposAdapter
import com.example.graphql.adapters.TopReposAdapter
import com.example.graphql.models.Data
import com.example.graphql.models.Edge
import com.example.graphql.models.Node
import com.example.graphql.models.modelX
import com.example.graphql.util.CheckNetwork
import com.google.gson.Gson
import de.hdodenhof.circleimageview.CircleImageView
import dmax.dialog.SpotsDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Retrofit
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    private var nodes: List<Node> = ArrayList<Node>()
    private var edges: List<Edge> = ArrayList<Edge>()

    private lateinit var image: CircleImageView
    private lateinit var txtName: TextView
    private lateinit var txtGitName: TextView
    private lateinit var txtEmail: TextView
    private lateinit var txtFollowers: TextView
    private lateinit var txtFollowing: TextView
    private lateinit var pinnedList: RecyclerView
    private lateinit var topList: RecyclerView

    private lateinit var dialog: AlertDialog

    var retrofit: Retrofit? = null
        @Inject set

    var gson: Gson? = null
        @Inject set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        (application as MyApp).appComponent!!.inject(this)
        dialog = SpotsDialog.Builder().setContext(this).build()
        image = findViewById(R.id.profile_image)
        txtName = findViewById(R.id.txtName)
        txtGitName = findViewById(R.id.txtGitName)
        txtEmail = findViewById(R.id.txtEmail)
        txtFollowers = findViewById(R.id.txtFollowers)
        txtFollowing = findViewById(R.id.txtFollowing)
        pinnedList = findViewById(R.id.listPinned)
        topList = findViewById(R.id.listTop)
        pinnedList.layoutManager = LinearLayoutManager(this)
        topList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        if (CheckNetwork.isNetworkAvailable(this)) {
            post()
        }else{
            Toast.makeText(this, "No Internet!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun post() {
        dialog.show()
        val retrofitClient = retrofit?.create(GraphQLService::class.java)
        val paramObject = JSONObject()
        paramObject.put("query", Constants.QUERY)

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = retrofitClient?.getData(
                    paramObject.toString(),
                    Constants.TOKEN
                )
                val jsonString = response?.body().toString()

                var model = gson?.fromJson(jsonString, modelX::class.java)
                if (model != null) {
                    updateBasicDetails(model.data)
                }

                if (model != null) {
                    nodes = model.data.repositoryOwner.pinnedItems.nodes
                }
                updatePinnedList(nodes)

                if (model != null) {
                    edges = model.data.repositoryOwner.topRepositories.edges
                }
                updateTopList(edges)
                dialog.dismiss()
            } catch (e: java.lang.Exception) {
                dialog.dismiss()
                e.printStackTrace()
            }
        }
    }

    private fun updateBasicDetails(data: Data) {
        Glide.with(this)
            .load(data.repositoryOwner.avatarUrl)
            .centerCrop()
            .error(R.mipmap.ic_launcher)
            .into(image)
        txtName.text = data.repositoryOwner.name
        txtGitName.text = data.repositoryOwner.login
        txtEmail.text = data.repositoryOwner.email
        txtFollowing.text = data.repositoryOwner.following.totalCount.toString() + " " + "following"
        txtFollowers.text = data.repositoryOwner.followers.totalCount.toString() + " " + "followers"
    }

    private fun updatePinnedList(nodes: List<Node>) {
        var pinnedReposAdapter = PinnedReposAdapter(nodes, this)
        pinnedList.adapter = pinnedReposAdapter
        pinnedReposAdapter.notifyDataSetChanged()
    }

    private fun updateTopList(edges: List<Edge>) {
        var topReposAdapter = TopReposAdapter(edges, this)
        topList.adapter = topReposAdapter
        topReposAdapter.notifyDataSetChanged()
    }
}