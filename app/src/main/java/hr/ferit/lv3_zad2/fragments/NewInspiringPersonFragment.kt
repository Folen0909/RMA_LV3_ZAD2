package hr.ferit.lv3_zad2.fragments

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import hr.ferit.lv3_zad2.databinding.FragmentNewInspiringPersonBinding
import hr.ferit.lv3_zad2.model.InspiringPerson
import hr.ferit.lv3_zad2.persistence.InspiringPersonDao
import hr.ferit.lv3_zad2.persistence.InspiringPersonDatabase
import hr.ferit.lv3_zad2.persistence.InspiringPersonRepository
import hr.ferit.lv3_zad2.persistence.InspiringPersonsDatabaseBuilder
import java.text.SimpleDateFormat
import java.util.*

class NewInspiringPersonFragment : Fragment() {

    lateinit var newInspiringPersonBinding: FragmentNewInspiringPersonBinding
    private val inspiringPersonRepository: InspiringPersonDao by lazy {
        InspiringPersonsDatabaseBuilder.getInstance().inspiringPersonDao()
    }
    private lateinit var inspiringPerson: InspiringPerson

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newInspiringPersonBinding = FragmentNewInspiringPersonBinding.inflate(inflater, container, false)
        newInspiringPersonBinding.bNewPersonSave.setOnClickListener{ saveInspiringPerson() }
        newInspiringPersonBinding.bDeletePerson.isEnabled = false
        newInspiringPersonBinding.bDeletePerson.isClickable = false
        newInspiringPersonBinding.etNewPersonBirthInput.transformIntoDatePicker(requireContext(), "dd.MM.yyyy.")
        newInspiringPersonBinding.etNewPersonDeathInput.transformIntoDatePicker(requireContext(), "dd.MM.yyyy.")
        arguments?.let {
            inspiringPerson = it.getSerializable(KEY_INSPIRING_PERSON) as InspiringPerson
            newInspiringPersonBinding.etNewPersonNameInput.setText(inspiringPerson.name)
            newInspiringPersonBinding.etNewPersonBirthInput.setText(inspiringPerson.dateOfBirth)
            newInspiringPersonBinding.etNewPersonDeathInput.setText(inspiringPerson.dateOfDeath)
            newInspiringPersonBinding.etNewPersonDescriptionInput.setText(inspiringPerson.shortDescription)
            newInspiringPersonBinding.etNewPersonImageLinkInput.setText(inspiringPerson.imageLink)
            var quotes = ""
            for(quote in inspiringPerson.quotes) quotes += quote + "\n"
            quotes = quotes.trimEnd()
            newInspiringPersonBinding.etNewPersonQuotesInput.setText(quotes)
            newInspiringPersonBinding.bDeletePerson.setOnClickListener{ deleteInspiringPerson(inspiringPerson) }
            newInspiringPersonBinding.bDeletePerson.isEnabled = true
            newInspiringPersonBinding.bDeletePerson.isClickable = true
        }
        return newInspiringPersonBinding.root
    }

    private fun deleteInspiringPerson(inspiringPerson: InspiringPerson) {
        inspiringPersonRepository.delete(inspiringPerson)
        fragmentManager?.popBackStack();
    }

    private fun saveInspiringPerson() {
        if (this::inspiringPerson.isInitialized) inspiringPersonRepository.delete(inspiringPerson)
        val id = 0L
        val name = newInspiringPersonBinding.etNewPersonNameInput.text.toString()
        val birth = newInspiringPersonBinding.etNewPersonBirthInput.text.toString()
        val death = newInspiringPersonBinding.etNewPersonDeathInput.text.toString()
        val description = newInspiringPersonBinding.etNewPersonDescriptionInput.text.toString()
        val imageLink = newInspiringPersonBinding.etNewPersonImageLinkInput.text.toString()
        val quotes = newInspiringPersonBinding.etNewPersonQuotesInput.text.toString().split("\n")
        inspiringPerson = InspiringPerson(id, name, birth, death, description, imageLink, quotes.toMutableList())
        if (checkInput()) {
            inspiringPersonRepository.insert(inspiringPerson)
            fragmentManager?.popBackStack();
        }
    }

    private fun checkInput() : Boolean {
        var emptyInput = ""
        when {
            inspiringPerson.name.trim().isEmpty() -> emptyInput = "Invalid name."
            inspiringPerson.dateOfBirth.isEmpty() -> emptyInput = "Invalid date of birth."
            inspiringPerson.dateOfDeath.isEmpty() -> emptyInput = "Invalid date of death."
            inspiringPerson.imageLink.trim().isEmpty() -> emptyInput = "Invalid image link."
            inspiringPerson.quotes.isEmpty() -> emptyInput = "Invalid quotes."
            inspiringPerson.shortDescription.trim().isEmpty() -> emptyInput = "Invalid description."
        }
        if (emptyInput != "") Toast.makeText(activity, emptyInput, Toast.LENGTH_SHORT).show()
        return emptyInput == ""
    }

    private fun EditText.transformIntoDatePicker(context: Context, format: String, maxDate: Date? = null) {
        isFocusableInTouchMode = false
        isClickable = true
        isFocusable = false

        val myCalendar = Calendar.getInstance()
        val datePickerOnDataSetListener =
            DatePickerDialog.OnDateSetListener{ _, year, monthOfYear, dayOfMonth ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, monthOfYear)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val simpleDateFormat = SimpleDateFormat(format, Locale.forLanguageTag("HR"))
                setText(simpleDateFormat.format(myCalendar.time))
            }

        setOnClickListener{
            DatePickerDialog(
                context, datePickerOnDataSetListener, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).run {
                maxDate?.time?.also { datePicker.maxDate = it }
                show()
            }
        }
    }

    companion object {
        const val TAG = "New Inspiring Person"
        private const val KEY_INSPIRING_PERSON = "Inspiring Person"

        fun create(inspiringPerson: InspiringPerson): NewInspiringPersonFragment {
            val args = Bundle()
            args.putSerializable(KEY_INSPIRING_PERSON, inspiringPerson)
            val fragment = NewInspiringPersonFragment()
            fragment.arguments = args
            return fragment
        }
        fun create(): NewInspiringPersonFragment {
            return NewInspiringPersonFragment()
        }
    }
}