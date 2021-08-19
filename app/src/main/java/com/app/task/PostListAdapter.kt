package com.app.task
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.app.task.Model.PostInfo
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class PostListAdapter : RecyclerView.Adapter<MyViewHolder>() {

    var userList = mutableListOf<PostInfo>()

    var clickListener: ListClickListener<PostInfo>? = null

    fun setUsers(users: ArrayList<PostInfo>) {
        this.userList = users.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_story, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val user = userList[position]
        holder.tv_Name.text = user.userName

        Glide.with(holder.item_memberImage.context).load(user.photoUrl).diskCacheStrategy(DiskCacheStrategy.ALL)
            .skipMemoryCache(true).circleCrop().placeholder(R.drawable.avatar)
            .into(holder.item_memberImage)

        Glide.with(holder.attachementImage.context).load(user.attachmentsArrayList.get(0).attachmentUrl).diskCacheStrategy(DiskCacheStrategy.ALL)
            .skipMemoryCache(true).placeholder(null)
            .into(holder.attachementImage)

//        val layoutParams =  holder.attachementImage.layoutParams as ConstraintLayout.LayoutParams
//        layoutParams.dimensionRatio = user.ratio
//        holder.attachementImage.layoutParams = layoutParams

        //System.out.println("Ratio values >>>"+user.ratio)

        holder.attachementImage.updateLayoutParams<ConstraintLayout.LayoutParams> {

            when (user.ratio) {
                "0.8" -> {
                    dimensionRatio = "4:5"
                }
               "1.91" -> {
                   dimensionRatio = "1.9:1"
                }
                "1.0" -> {
                    dimensionRatio = "1:1"
                }

            }
        }



    }

    fun setOnItemClick(listClickListener: ListClickListener<PostInfo>) {
        this.clickListener = listClickListener
    }

}
class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val attachementImage = view.findViewById<ImageView>(R.id.attachementImage)
    val item_memberImage = view.findViewById<ImageView>(R.id.item_memberImage)
    val tv_Name = view.findViewById<TextView>(R.id.tv_Name)
}
interface ListClickListener<T> {
    fun onClick(data: T, position: Int)
    fun onDelete(user: T)
}
