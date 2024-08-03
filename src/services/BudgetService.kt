package services

object BudgetService {
    val budgets = mutableMapOf<String, Double>()

    fun setBudget(category: String, amount: Double) {
        try {
            budgets[category] = amount
        } catch (e: Exception) {
            println("Error setting budget: ${e.message}")
        }
    }

    fun getBudget(category: String): Double {
        return budgets.getOrDefault(category, 0.0)
    }

    fun checkBudget(category: String, expenseAmount: Double): Boolean {
        val currentBudget = getBudget(category)
        return expenseAmount <= currentBudget
    }

    fun getAllBudgets(): Map<String, Double> {
        return budgets
    }
}
