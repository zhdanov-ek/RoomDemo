package com.example.roomdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdemo.db.Employee

class EmployeeAdapter(context: Context, private val itemClickListener: EmployeeListClickListener):
    RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {


    private var data: List<Employee>? = null
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    class EmployeeViewHolder(itemView: View, val employeeClickListener: EmployeeListClickListener)
        : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var itemData: Employee? = null
        val name: TextView = itemView.findViewById(R.id.employeeNameTextView)
        val salary: TextView = itemView.findViewById(R.id.employeeSalaryTextView)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            employeeClickListener.onEmployeeClick(itemData)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val itemView = inflater.inflate(R.layout.employee_item, parent, false)
        return EmployeeViewHolder(itemView, itemClickListener)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.itemData = data!![position]
        holder.name.text = data!![position].name
        holder.salary.text = data!![position].salary
    }

    public fun swapData(employees: List<Employee>?) {
        data = employees
        notifyDataSetChanged()
    }
}