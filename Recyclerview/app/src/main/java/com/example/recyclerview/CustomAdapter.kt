import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.ItemsViewModel
import com.example.recyclerview.R

class CustomAdapter(private val mList: List<ItemsViewModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    //    RecyclerView.Adapter<CustomAdapter.ViewHolder>()
    //    create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(ItemsViewModel.profile)

        // sets the text to the textview from our itemHolder class
        holder.name.text = ItemsViewModel.name
        holder.message.text = ItemsViewModel.message
        holder.number.text = ItemsViewModel.time

//        holder.time.text = ItemsViewModel.time.toInt()


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.profile)
        val name: TextView = itemView.findViewById(R.id.number)
        val message :TextView = itemView.findViewById(R.id.message)
        val number :TextView = itemView.findViewById(R.id.sPtime)



      
    }
}
