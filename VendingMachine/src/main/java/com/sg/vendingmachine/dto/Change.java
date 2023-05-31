/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.vendingmachine.dto;
import java.math.BigDecimal;

/**
 *
 * @author Group 2
 */
public class Change {
    private int quarters;
    private int dimes;
    private int nickels;
    private int pennies;
    public Change(BigDecimal amount){
        this.quarters=amount.divide(new BigDecimal("25")).intValue();
        amount=amount.remainder(new BigDecimal("25"));
        this.dimes=amount.divide(new BigDecimal("10")).intValue();
        amount=amount.remainder(new BigDecimal("10"));
        this.nickels=amount.divide(new BigDecimal("5")).intValue();
        this.pennies=amount.remainder(new BigDecimal("5")).intValue();
    }
    public int getQuarters(){
        return quarters;
    }
    public int getDimes(){
        return dimes;
    }
    public int getNickels(){
        return nickels;
    }
    public int getPennies(){
        return pennies;
    }
    
}
