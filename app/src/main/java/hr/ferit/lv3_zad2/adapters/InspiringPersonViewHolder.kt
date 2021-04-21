package hr.ferit.lv3_zad2.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hr.ferit.lv3_zad2.databinding.ItemInspiringPersonBinding
import hr.ferit.lv3_zad2.listeners.OnInspiringPersonClickListener
import hr.ferit.lv3_zad2.model.InspiringPerson

class InspiringPersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(inspiringPerson: InspiringPerson, clickListener: OnInspiringPersonClickListener) {
        val itemBinding = ItemInspiringPersonBinding.bind(itemView)
        Picasso.get().load(inspiringPerson.imageLink).into(itemBinding.ivPersonImage)
        itemBinding.tvPersonName.text = inspiringPerson.name
        val date = inspiringPerson.dateOfBirth + " / " + inspiringPerson.dateOfDeath
        itemBinding.tvPersonDate.text = date
        itemBinding.tvPersonDescription.text = inspiringPerson.shortDescription
        itemBinding.ivPersonImage.setOnClickListener{ clickListener.onInspiringPersonQuote(inspiringPerson) }
        itemView.setOnClickListener{ clickListener.onInspiringPersonSelected(inspiringPerson) }
    }
}