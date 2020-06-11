package com.example.roomdemo

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.roomdemo.db.AppDatabase
import com.example.roomdemo.db.Employee
import com.example.roomdemo.db.EmployeeDao

class MainRepository(application: Application) {
    private var employeeDao: EmployeeDao? = null

    init {
        val db = AppDatabase.getAppDataBase(application)
        employeeDao = db?.employeeDao()
    }

    fun getEmployees(): LiveData<List<Employee>>? {
        return employeeDao?.getAll()
    }

    fun addEmployee(employee: Employee) {
        AsyncTask.execute { employeeDao?.insert(employee) }
    }

    fun removeEmployee(employee: Employee) {
        AsyncTask.execute { employeeDao?.delete(employee) }
    }

    fun deleteAllEmployees() {
        AsyncTask.execute { employeeDao?.deleteAllEmployees() }
    }

    fun fillData() {
        AsyncTask.execute { fillEmployees(30) }
    }

    private fun fillEmployees(count: Int) {
        var currentRowsCount = employeeDao?.getRowCount()
        if (currentRowsCount == null)
            currentRowsCount = 0

        for (employeeNum in 0 + currentRowsCount..count + currentRowsCount) {
            employeeDao?.insert(Employee(null, "name$employeeNum", "2000$employeeNum"))
        }
    }

}