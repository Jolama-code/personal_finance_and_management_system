package services

import models.Income

object IncomeService {
    private val incomes = mutableListOf<Income>()

    fun addincome(source: String, amount: Double, date: String) {
        try {
            incomes.add(Income(source, amount, date))
        } catch (e: Exception) {
            println("Error adding income: ${e.message}")
        }
    }

    fun totalIncome(): Double {
        return incomes.sumOf { it.amount }
    }

    fun getIncomeBySource(source: String): List<Income> {
        return incomes.filter { it.source == source }
    }
}
