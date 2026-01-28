/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author admin
 */
class Employee extends Person{
    
    private int employeeId;
    
    private String password;
    
    private static final String PASSWORD = "test";
    
    private static final int EMPLOYEEID = 123;

    public Employee(int employeeId, String password, String name) {
        super(name);
        this.employeeId = employeeId;
        this.password = password;
    }

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * Get the value of employeeId
     *
     * @return the value of employeeId
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * Set the value of employeeId
     *
     * @param employeeId new value of employeeId
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (this.employeeId != other.employeeId) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }

    
    
    public boolean login(int employeeId, String password){
    //DO WHILE (e!= null && e!= login(employee.getID,password) )
    Scanner sc = new Scanner (System.in);
    boolean valid;
        
    do {
        System.out.println("Introduzaca el nombre de empleado:");
        String eName = sc.nextLine();
        System.out.println("Introduzaca el Id de empleado:");
        int eId = sc.nextInt();
        System.out.println("Introduzaca el password de empleado:");
        String ePassword = sc.nextLine();
        
        Employee e = new Employee(eId, ePassword,eName);
    } while (e != null && !e.login());
    
        System.out.println("Empleado validado");
        valid = true;
        
        return valid;
    }
}
