package hr.ferit.lv3_zad2.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hr.ferit.lv3_zad2.R
import hr.ferit.lv3_zad2.adapters.InspiringPersonAdapter
import hr.ferit.lv3_zad2.databinding.FragmentInspiringPersonListBinding
import hr.ferit.lv3_zad2.listeners.OnInspiringPersonClickListener
import hr.ferit.lv3_zad2.persistence.InspiringPersonDao
import hr.ferit.lv3_zad2.persistence.InspiringPersonRepository
import hr.ferit.lv3_zad2.persistence.InspiringPersonsDatabaseBuilder

class InspiringPersonListFragment : Fragment() {

    private lateinit var onInspiringPersonClickListener: OnInspiringPersonClickListener
    private lateinit var inspiringPersonListBinding: FragmentInspiringPersonListBinding
    private val inspiringPersonRepository: InspiringPersonDao by lazy {
        InspiringPersonsDatabaseBuilder.getInstance().inspiringPersonDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inspiringPersonListBinding = FragmentInspiringPersonListBinding.inflate(inflater, container, false)
        inspiringPersonListBinding.fabAddNote.setOnClickListener{ createNewInspiringPerson() }
        setupRecyclerView()
        return inspiringPersonListBinding.root
    }

    private fun createNewInspiringPerson() {
        // This is the only way I could figure out how to replace fragment within same fragment
        activity!!.supportFragmentManager.beginTransaction()
            .replace(
                R.id.fl_fragmentContainer,
                NewInspiringPersonFragment.create(),
                NewInspiringPersonFragment.TAG)
            .addToBackStack(null)
            .commit()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnInspiringPersonClickListener) onInspiringPersonClickListener = context
    }
    override fun onResume() {
        super.onResume()
        (inspiringPersonListBinding.rvPersons.adapter as InspiringPersonAdapter).refreshData(inspiringPersonRepository.getAll())
    }
    private fun setupRecyclerView() {
        inspiringPersonListBinding.rvPersons.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        inspiringPersonListBinding.rvPersons.adapter = InspiringPersonAdapter(inspiringPersonRepository.getAll(), onInspiringPersonClickListener)
    }


    companion object {
        const val TAG = "Inspiring Person List"
        fun create(): InspiringPersonListFragment {
            return InspiringPersonListFragment()
        }
    }
}