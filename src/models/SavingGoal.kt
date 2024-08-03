package models

data class SavingGoal (val goalName:String, val targetAmount: Double, var savedAmount: Double = 0.0)
