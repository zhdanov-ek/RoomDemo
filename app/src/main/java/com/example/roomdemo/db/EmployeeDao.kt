package com.example.roomdemo.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
public interface EmployeeDao {

    @Query("SELECT * FROM employee")
    fun getAll(): LiveData<List<Employee>>

    @Query("SELECT * FROM employee WHERE id = :id")
    fun getById(id: Long): LiveData<Employee>

    @Insert
    fun insert(employee: Employee)

    @Update
    fun update(employee: Employee)

    @Delete
    fun delete(employee: Employee)

}