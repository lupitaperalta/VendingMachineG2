/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.vendingmachine.service;

/**
 *
 * @author Group 2
 */
public class VendingMachineNoItemInventoryException extends Exception{
    public VendingMachineNoItemInventoryException(String message){
        super(message);
    }
    public VendingMachineNoItemInventoryException(String message, Throwable cause){
        super(message,cause);
    }
}
