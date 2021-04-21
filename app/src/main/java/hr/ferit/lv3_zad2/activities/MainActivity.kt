package hr.ferit.lv3_zad2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import hr.ferit.lv3_zad2.R
import hr.ferit.lv3_zad2.databinding.ActivityMainBinding
import hr.ferit.lv3_zad2.fragments.InspiringPersonListFragment
import hr.ferit.lv3_zad2.fragments.NewInspiringPersonFragment
import hr.ferit.lv3_zad2.listeners.OnInspiringPersonClickListener
import hr.ferit.lv3_zad2.model.InspiringPerson

class MainActivity : AppCompatActivity(), OnInspiringPersonClickListener {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.fl_fragmentContainer, InspiringPersonListFragment.create(), InspiringPersonListFragment.TAG)
                .commit()
        }
    }

    override fun onInspiringPersonSelected(inspiringPerson: InspiringPerson) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_fragmentContainer,
                NewInspiringPersonFragment.create(inspiringPerson),
                NewInspiringPersonFragment.TAG)
            .addToBackStack(null)
            .commit()
    }

    override fun onInspiringPersonQuote(inspiringPerson: InspiringPerson) {
        Toast.makeText(applicationContext, inspiringPerson.quotes.random(), Toast.LENGTH_SHORT).show()
    }
}