package services

import models.Expense
import java.time.LocalDate

object ExpenseService {
    private val expenses = mutableListOf<Expense>()

    fun addExpenses(category: String, amount: Double, date: LocalDate) {
        try {
            expenses.add(Expense(category, amount, date))
        } catch (e: Exception) {
            println("Error adding expense: ${e.message}")
        }
    }

    fun getTotalExpensesByCategory(category: String): Double {
        val normalizedCategory = category.trim().toLowerCase()
        return expenses.filter { it.category.trim().toLowerCase() == normalizedCategory }.sumOf { it.amount }
    }


    fun getTotalExpenses(): Double {
        return expenses.sumOf { it.amount }
    }

    fun getExpensesByDateRange(startDate: LocalDate, endDate: LocalDate): List<Expense> {
        return expenses.filter { it.date.isAfter(startDate) && it.date.isBefore(endDate) }

    }
    fun getTotalExpensesByCategoryAndDateRange(category: String, startDate: LocalDate, endDate: LocalDate): Double {
        val normalizedCategory = category.trim().lowercase()
        return expenses.filter {
            it.category.trim().lowercase() == normalizedCategory &&
                    it.date.isAfter(startDate.minusDays(1)) &&
                    it.date.isBefore(endDate.plusDays(1))
        }.sumOf { it.amount }
    }

}
