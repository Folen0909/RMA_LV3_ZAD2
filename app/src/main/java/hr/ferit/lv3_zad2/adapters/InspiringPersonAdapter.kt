package hr.ferit.lv3_zad2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.ferit.lv3_zad2.R
import hr.ferit.lv3_zad2.listeners.OnInspiringPersonClickListener
import hr.ferit.lv3_zad2.model.InspiringPerson

class InspiringPersonAdapter(inspiringPersons: List<InspiringPerson>,
                             private val clickListener: OnInspiringPersonClickListener
) : RecyclerView.Adapter<InspiringPersonViewHolder>() {

    private val inspiringPersons: MutableList<InspiringPerson> = mutableListOf()

    init {
        refreshData(inspiringPersons)
    }

    fun refreshData(inspiringPersons: List<InspiringPerson>) {
        this.inspiringPersons.clear()
        this.inspiringPersons.addAll(inspiringPersons)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InspiringPersonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_inspiring_person, parent, false)
        return InspiringPersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: InspiringPersonViewHolder, position: Int) {
        val inspiringPerson = inspiringPersons[position]
        holder.bind(inspiringPerson, clickListener)
    }

    override fun getItemCount(): Int {
        return inspiringPersons.size
    }
}