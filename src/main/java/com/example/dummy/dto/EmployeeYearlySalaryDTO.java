package com.example.dummy.dto;

import java.io.File;

public class EmployeeYearlySalaryDTO {
    private Long id;
    private String employee_name;
    private Double employee_salary;
    private int employee_age;
    private File profile_image;

    private Double employee_yearly_salary;

    public EmployeeYearlySalaryDTO() {}

    public EmployeeYearlySalaryDTO(Long id, String employee_name, Double employee_salary, int employee_age, File profile_image, Double employee_yearly_salary) {
        this.id = id;
        this.employee_name = employee_name;
        this.employee_salary = employee_salary;
        this.employee_age = employee_age;
        this.profile_image = profile_image;
        this.employee_yearly_salary = employee_yearly_salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public Double getEmployee_salary() {
        return employee_salary;
    }

    public void setEmployee_salary(Double employee_salary) {
        this.employee_salary = employee_salary;
    }

    public int getEmployee_age() {
        return employee_age;
    }

    public void setEmployee_age(int employee_age) {
        this.employee_age = employee_age;
    }

    public File getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(File profile_image) {
        this.profile_image = profile_image;
    }

    public Double getEmployee_yearly_salary() {
        return employee_yearly_salary;
    }

    public void setEmployee_yearly_salary(Double employee_yearly_salary) {
        this.employee_yearly_salary = employee_yearly_salary;
    }
}
