/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.vendingmachine.service;

/**
 *
 * @author Group 2
 */
public class VendingMachineDuplicateIdException extends Exception {
    public VendingMachineDuplicateIdException(String message) {
        super(message);
    }

    public VendingMachineDuplicateIdException(String message, Throwable cause) {
        super(message, cause);
    } 
}
