package models

import java.time.LocalDate

data class Expense (val category: String, val amount: Double, val date:LocalDate)
