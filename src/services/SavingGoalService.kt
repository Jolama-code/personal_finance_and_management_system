package services

import SavingGoals

object SavingGoalService {
    private val goals= mutableListOf<SavingGoals>()

    fun addGoal(goalName:String, targetAmount: Double){
        goals.add(SavingGoals(goalName,targetAmount))
    }

    fun updateSavedAmount(goalName: String, amount: Double) {
        val goal = goals.find { it.goalName == goalName }
        goal?.let {
            it.savedAmount += amount
        }
    }

    fun getGoalProgress(goalName: String): Pair<Double, Double>? {
        val goal = goals.find { it.goalName == goalName }
        return goal?.let { Pair(it.savedAmount, it.targetAmount) }
    }
    fun getPercentageSaved(goalName: String): Double? {
        val (savedAmount, targetAmount) = getGoalProgress(goalName) ?: return null
        return if (targetAmount > 0) (savedAmount / targetAmount) * 100 else 0.0
    }

    fun getRemainingAmount(goalName: String): Double? {
        val (savedAmount, targetAmount) = getGoalProgress(goalName) ?: return null
        return targetAmount - savedAmount
    }

    fun checkGoalStatus(goalName: String): String? {
        val remainingAmount = getRemainingAmount(goalName) ?: return null
        val percentageSaved = getPercentageSaved(goalName) ?: return null

        return when {
            remainingAmount <= 0 -> "Goal achieved!"
            percentageSaved >= 90 -> "You are very close to achieving your goal!"
            else -> "You have saved $percentageSaved% of your goal."
        }
    }
    fun getAllGoals(): List<SavingGoals> {
        return goals
    }



}