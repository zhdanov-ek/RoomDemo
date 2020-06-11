package com.example.roomdemo

import com.example.roomdemo.db.Employee

interface EmployeeListClickListener {
    fun onEmployeeClick(employee: Employee?)
}