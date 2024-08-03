import services.UserServices
import services.IncomeService
import services.ExpenseService
import services.SavingGoalService
import services.BudgetService
import services.ReportServices
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    var loggedInUser: String? = null

    while (true) {
        println("Welcome to the Personal Finance Management System")
        if (loggedInUser == null) {
            println("1. Register")
            println("2. Login")
            println("9. Exit")
        } else {
            println("3. Add Income")
            println("4. Add Expense")
            println("5.get Expenses by category")
            println("6. get total expenses by category and date range")
            println("7. Set Savings Goal")
            println("8. Update Saved Amount")
            println("9. Set Budget")
            println("10. Generate Reports")
            println("11. Logout")
        }
        print("Choose an option: ")

        when (scanner.nextInt()) {
            1 -> {
                if (loggedInUser == null) {
                    try {
                        print("Enter username: ")
                        val username = scanner.next()
                        print("Enter password: ")
                        val password = scanner.next()
                        if (UserServices.register(username, password)) {
                            println("Registration successful")
                        } else {
                            println("Username already exists")
                        }
                    } catch (e: Exception) {
                        println("Error: ${e.message}")
                    }
                } else {
                    println("Invalid option")
                }
            }
            2 -> {
                if (loggedInUser == null) {
                    try {
                        print("Enter username: ")
                        val username = scanner.next()
                        print("Enter password: ")
                        val password = scanner.next()
                        if (UserServices.login(username, password)) {
                            loggedInUser = username
                            println("Login successful")
                        } else {
                            println("Invalid credentials")
                        }
                    } catch (e: Exception) {
                        println("Error: ${e.message}")
                    }
                } else {
                    println("Invalid option")
                }
            }
            3 -> {
                if (loggedInUser != null) {
                    try {
                        print("Enter income source: ")
                        val source = scanner.next()
                        print("Enter income amount: ")
                        val amount = scanner.nextDouble()
                        print("Enter income date (yyyy-mm-dd): ")
                        val date = scanner.next()
                        IncomeService.addincome(source, amount, date)
                        println("Income added")
                    } catch (e: Exception) {
                        println("Error: ${e.message}")
                    }
                } else {
                    println("Please log in first")
                }
            }
            4 -> {
                if (loggedInUser != null) {
                    try {
                        print("Enter expense category: ")
                        val category = scanner.next()
                        print("Enter expense amount: ")
                        val amount = scanner.nextDouble()
                        print("Enter expense date (yyyy-mm-dd): ")
                        val date = LocalDate.parse(scanner.next())
                        ExpenseService.addExpenses(category, amount, date)
                        println("Expense added")
                    } catch (e: Exception) {
                        println("Error: ${e.message}")
                    }
                } else {
                    println("Please log in first")
                }
            }
            5 ->{
                if(loggedInUser !=null){
                    println("enter the category to check its total expenses:")
                    val category= scanner.next()
                    val totalExpense= ExpenseService. getTotalExpensesByCategory(category)
                    println("the total expense of $category is $totalExpense")

                }else{
                    println("please log in first")
                }

            }
            6 ->{
                if(loggedInUser!=null){
                    println("enter the category to check the expense: ")
                    val category=scanner.next()
                    println("enter the starting date(yyyy-mm-dd): ")
                    val startDateInput= scanner.next()
                    println("enter the ending  date(yyyy-mm-dd): ")
                    val endDateInput= scanner.next()

                    val formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    val startDate=LocalDate.parse(startDateInput, formatter)
                    val endDate= LocalDate.parse(endDateInput, formatter)

                    val totalExpense=ExpenseService.getTotalExpensesByCategoryAndDateRange(category,startDate,endDate)

                    println("the expense of $category from $startDate to $endDate is:$totalExpense ")

                }else{
                    println("please log in first")
                }



            }
            7 -> {
                if (loggedInUser != null) {
                    try {
                        print("Enter goal name: ")
                        val goalName = scanner.next()
                        print("Enter target amount: ")
                        val targetAmount = scanner.nextDouble()
                        SavingGoalService.addGoal(goalName, targetAmount)
                        println("Savings goal added")
                    } catch (e: Exception) {
                        println("Error: ${e.message}")
                    }
                } else {
                    println("Please log in first")
                }
            }
            8 -> {
                if (loggedInUser != null) {
                    try {
                        print("Enter goal name: ")
                        val goalName = scanner.next()
                        print("Enter amount to add: ")
                        val amount = scanner.nextDouble()
                        SavingGoalService.updateSavedAmount(goalName, amount)
                        println("Saved amount updated")
                    } catch (e: Exception) {
                        println("Error: ${e.message}")
                    }
                } else {
                    println("Please log in first")
                }
            }
            9 -> {
                if (loggedInUser != null) {
                    try {
                        print("Enter budget category: ")
                        val category = scanner.next()
                        print("Enter budget amount: ")
                        val amount = scanner.nextDouble()
                        BudgetService.setBudget(category, amount)
                        println("Budget set")
                    } catch (e: Exception) {
                        println("Error: ${e.message}")
                    }
                } else {
                    println("Please log in first")
                }
            }
            10 -> {
                if (loggedInUser != null) {
                    try {
                        println(ReportServices.generateIncomeReport())
                        println(ReportServices.generateExpensesReport(LocalDate.now().minusMonths(1), LocalDate.now()))
                        println(ReportServices.generateSavingsGoalsReport())
                        println(ReportServices.generateBudgetReport())
                    } catch (e: Exception) {
                        println("Error: ${e.message}")
                    }
                } else {
                    println("Please log in first")
                }
            }
            11 -> {
                if (loggedInUser == null) {
                    println("Exiting...")
                    return
                } else {
                    loggedInUser = null
                    println("Logged out successfully")
                }
            }
            else -> println("Invalid option")
        }
    }
}
