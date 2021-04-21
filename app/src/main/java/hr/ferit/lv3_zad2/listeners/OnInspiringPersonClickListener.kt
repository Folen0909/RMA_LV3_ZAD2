package hr.ferit.lv3_zad2.listeners

import hr.ferit.lv3_zad2.model.InspiringPerson

interface OnInspiringPersonClickListener {
    fun onInspiringPersonSelected(inspiringPerson: InspiringPerson)
    fun onInspiringPersonQuote(inspiringPerson: InspiringPerson)
}