/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import main.Payable;
import model.Amount;


/**
 *
 * @author Usuario
 */
public class Client extends Person implements Payable{
    
    private int memberId;
    
    private Amount balance;
    
    private static final int MEMBERID = 456;
    
    private static final Amount BALANCE = new Amount (50.00);

    public Client(String name) {
        super(name);
        this.memberId = MEMBERID;
        this.balance = BALANCE;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Amount getBalance() {
        return balance;
    }

    public void setBalance(Amount balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean pay(Amount amount) {
        boolean valid = false;
        
        if (balance.getValue()>= amount.getValue()) {
            balance.setValue(balance.getValue()-amount.getValue());
            valid = true;        
        } else {
            balance.setValue(balance.getValue()-amount.getValue());
        }
        
        return valid;
    }

    
}
