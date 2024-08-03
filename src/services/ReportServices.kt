package services

import java.time.LocalDate

object ReportServices {

    fun generateIncomeReport():String{
        val gettotalIncome=IncomeService.totalIncome()
        return "Total Income: $gettotalIncome"
    }

    fun generateExpensesReport(startDate: LocalDate, endDate: LocalDate ): String{
        val expensees= ExpenseService.getExpensesByDateRange(startDate,endDate)
        val totalExpenses= expensees.sumOf { it.amount }
        return "Total Expenses: $totalExpenses"
    }



    fun generateSavingsGoalsReport(): String {
        val goals = SavingGoalService.getAllGoals()
        val report = StringBuilder("Savings Goals Report:\n")
        goals.forEach { goal ->
            val savedAmount = goal.savedAmount
            val targetAmount = goal.targetAmount
            val percentageSaved = if (targetAmount > 0) (savedAmount / targetAmount) * 100 else 0.0
            val remainingAmount = targetAmount - savedAmount
            report.append("Goal: ${goal.goalName}\n")
            report.append("Target Amount: $targetAmount\n")
            report.append("Saved Amount: $savedAmount\n")
            report.append("Percentage Saved: $percentageSaved%\n")
            report.append("Remaining Amount: $remainingAmount\n")
            report.append("--------------------\n")
        }
        return report.toString()
    }





    fun generateBudgetReport(): String{
        val report= StringBuilder("Budget Report: \n")
        BudgetService.getAllBudgets().forEach{(category, amount)->
            report.append("$category: $amount\n")
        }

        return report.toString()
    }



}