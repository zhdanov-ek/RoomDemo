package com.example.roomdemo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.roomdemo.db.Employee

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val mainRepository = MainRepository(application)

    public fun getEmployees(): LiveData<List<Employee>>? {
        return mainRepository.getEmployees()
    }

    public fun addEmployee(employee: Employee) {
        mainRepository.addEmployee(employee)
    }

    public fun removeEmployee(employee: Employee) {
        mainRepository.removeEmployee(employee)
    }
}