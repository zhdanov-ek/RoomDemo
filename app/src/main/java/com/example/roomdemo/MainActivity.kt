package com.example.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdemo.db.Employee
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), EmployeeListClickListener {
    private lateinit var viewModel: MainViewModel

    private lateinit var employeeName: EditText
    private lateinit var employeeSalary: EditText
    private lateinit var rvAdapter: EmployeeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = MainViewModel(application)
        findViewById<Button>(R.id.addButton).setOnClickListener { addEmployee() }
        findViewById<Button>(R.id.deleteAllButton).setOnClickListener { deleteAll() }
        findViewById<Button>(R.id.fillDBButton).setOnClickListener { fillDb() }
        employeeName = findViewById(R.id.employeeNameEditText)
        employeeSalary = findViewById(R.id.employeeSalaryEditText)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = (LinearLayoutManager(this))
        rvAdapter = EmployeeAdapter(this, this)
        recyclerView.adapter = rvAdapter

        viewModel.getEmployees()?.observe(this, Observer { updateUI(it) } )
    }

    private fun addEmployee() {
        val name = employeeName.text.toString().trim()
        val salary = employeeSalary.text.toString().trim()

        if (!TextUtils.isEmpty(name)
            && !TextUtils.isEmpty(salary)) {
            viewModel.addEmployee(Employee(null, name, salary))
            employeeName.text = null
            employeeSalary.text = null
        }
    }

    private fun updateUI(employees: List<Employee>?) {
        rvAdapter.swapData(employees)
    }

    override fun onEmployeeClick(employee: Employee?) {
        employee?.let {
            Snackbar.make(employeeName, "Remove item ${it.name}?", Snackbar.LENGTH_SHORT)
                .setAction(R.string.remove) { viewModel.removeEmployee(employee) }
                .show()
        }
    }

    private fun deleteAll() {
        viewModel.deleteAllEmployees()
    }

    private fun fillDb() {
        viewModel.fillData()
    }
}