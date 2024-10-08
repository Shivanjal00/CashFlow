package com.example.cashflow.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cashflow.R
import com.example.cashflow.Utils
import com.example.cashflow.data.ExpenseDataBase
import com.example.cashflow.data.dao.ExpenseDao
import com.example.cashflow.data.model.ExpenseEntity

class HomeViewModel(dao: ExpenseDao) : ViewModel() {

    val expenses = dao.getAllExpenses()

    fun getBalance(list: List<ExpenseEntity>) : String{
        var total = 0.0
        list.forEach {
            if (it.type == "Income") {
                total += it.amount
            } else {
                total -= it.amount
            }
        }
        return "INR ${Utils.formatToDecimallValue(total)}"
    }

    fun getTotalExpenses(list: List<ExpenseEntity>) : String{
        var total = 0.0
        list.forEach {
            if (it.type == "Expense") {
                total += it.amount
            }
        }
        return "INR ${Utils.formatToDecimallValue(total)}"
    }

    fun getTotalIncome(list: List<ExpenseEntity>) : String{
        var total = 0.0
        list.forEach {
            if (it.type == "Income") {
                total += it.amount
            }
        }
        return "INR ${Utils.formatToDecimallValue(total)}"
    }

    fun getItemIcon(item:ExpenseEntity) : Int{

        if(item.category == "Salary"){
            return R.drawable.upwork
        }else if(item.category == "Paypal"){
            return R.drawable.paypal
        }else if(item.category == "Netflix"){
            return R.drawable.netflix
        }else if(item.category == "Starbucks"){
            return R.drawable.starbucks
        }
        return R.drawable.money
    }

}

class HomeViewModelFactory(private val context: Context) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass : Class<T>): T{
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            val dao = ExpenseDataBase.getDatabase(context).expenseDao()
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}