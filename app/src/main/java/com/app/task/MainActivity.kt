package com.app.task
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.*
import com.android.volley.toolbox.JsonArrayRequest
import com.app.task.Database.UserRepository
import com.app.task.Model.Attachments
import com.app.task.Model.PostInfo
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONException
import java.net.MalformedURLException
import java.net.URL
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter: PostListAdapter
    val repo: UserRepository by lazy {
        UserRepository(this)
    }
    //private var gson: Gson? = null
    var postInfoArrayList: ArrayList<PostInfo>? = ArrayList<PostInfo>()
    var recyclerview: RecyclerView? = null
    private lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        if (gson == null)
//        {
//            gson = GsonBuilder().serializeNulls().create()
//        }

        adapter = PostListAdapter()
        recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.adapter = adapter


       if (Utils.isNetworkConnectionAvailable(applicationContext))
       {
           getData()
       }else{
           Toast.makeText(
               this@MainActivity,
               "No network connection",
               Toast.LENGTH_SHORT
           ).show()
       }

    }
    override fun onResume() {
        super.onResume()
        fetchUsers()
    }
    fun fetchUsers() {
        postInfoArrayList = repo.getAllUsers() as ArrayList<PostInfo>
        System.out.println("Users Get >>>"+postInfoArrayList?.size)
        //System.out.println("Users Name >>>"+allUsers.get(0).name)
        adapter.setUsers(postInfoArrayList!!)
    }
    private fun getData() {

        var url: URL? = null

        try {
            url = URL("https://api.uat.leagues.ai/api/v1/wall-management/organisations/orgu1/posts?limit=10&page=0")
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        println("Get data url >>> " + url.toString())
        val volleyJsonReques: JsonArrayRequest = object : JsonArrayRequest(
            Method.GET,
            url.toString(), null,
            Response.Listener { response ->
                println("Success response >>>$response")

                //Also used Gson Concept for response parsing
//                postInfoArrayList = gson?.fromJson(
//                    response.toString(),
//                    object : TypeToken<List<PostInfo?>?>() {}.type
//                )

                parseData(response)

            }, Response.ErrorListener { error ->
                println("Error response >>>$error")

            }) {
            @RequiresApi(Build.VERSION_CODES.M)
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headerMap: MutableMap<String, String> = HashMap()
                headerMap["Accept"] = "application/json"
                headerMap["Authorization"] = "Bearer "+Utils.Api_Token
                return headerMap
            }
        }
        volleyJsonReques.retryPolicy = DefaultRetryPolicy(
            0,
            -1,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        VolleyHelper.getInstance(this@MainActivity).addToRequestQueue(volleyJsonReques)
    }

    private fun parseData(jsonArray: JSONArray){

        repo.deleteAll()

        try {
            for (i in 0 until jsonArray.length()) {

                val postObj = jsonArray.getJSONObject(i)

                val model = PostInfo();

                model.postId = postObj.getInt("postId")
                model.parentPostId = postObj.getInt("parentPostId")
                model.text = postObj.getString("text")
                model.type = postObj.getString("type")
                model.orgId = postObj.getString("orgId")
                model.createdAt = postObj.getString("createdAt")
                model.commentsCount = postObj.getInt("commentsCount")
                model.sharesCount = postObj.getInt("sharesCount")
                model.ratio = postObj.getString("ratio")

                val personDetails = postObj.getJSONObject("createdBy")
                model.userName = personDetails.getString("userName")
                model.name = personDetails.getString("name")
                model.photoUrl = personDetails.getString("photoUrl")

                val attachmentsArray = postObj.getJSONArray("attachments");
                var attachmentsList: ArrayList<Attachments>? =  ArrayList<Attachments>()
                if (attachmentsArray.length()>0) {
                    for (j in 0 until attachmentsArray.length()) {
                        val attaObj = attachmentsArray.getJSONObject(j)
                        val model2 = Attachments();
                        model2.id = attaObj.getInt("id")
                        model2.attachmentUrl = attaObj.getString("attachmentUrl")
                        model2.thumbnailUrl = attaObj.getString("thumbnailUrl")
                        model2.mimeType = attaObj.getString("mimeType")
                        model2.updatedAt = attaObj.getString("updatedAt")
                        model2.createdAt = attaObj.getString("createdAt")
                        model2.orgId = attaObj.getString("orgId")
                        model2.userId = attaObj.getString("userId")
                        attachmentsList?.add(model2)
                    }
                    model.attachmentsArrayList = attachmentsList
                }

                postInfoArrayList?.add(model)

                repo.insertUser(model)

            }

            adapter.notifyDataSetChanged()
            System.out.println("Insert Data >>>"+postInfoArrayList?.size)

            //repo.insertUser(postInfoArrayList)

        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }


}
