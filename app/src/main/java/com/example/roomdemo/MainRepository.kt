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

    public fun getEmployees(): LiveData<List<Employee>>? {
        return employeeDao?.getAll()
    }

    public fun addEmployee(employee: Employee) {
        AsyncTask.execute { employeeDao?.insert(employee) }
    }

    public fun removeEmployee(employee: Employee) {
        AsyncTask.execute { employeeDao?.delete(employee) }
    }
}