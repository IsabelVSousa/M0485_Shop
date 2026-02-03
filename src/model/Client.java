/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Client extends Person{
    
    private int memberId;
    
    private Amount balance;
    
    private static final int MEMBER_ID = 456;
    
    private static final Amount BALANCE = new Amount (50.00);

    public Client(int memberId, String name) {
        super(name);
        this.memberId = memberId;
    }

}
