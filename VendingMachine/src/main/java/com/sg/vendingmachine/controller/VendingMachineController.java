/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.ui.VendingMachineView;

/**
 *
 * @author Korinna
 */
public class VendingMachineController {
    
    private VendingMachineView view;
    priavte VendingMachineServiceLayer service;
    
    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service){
        this.view = view;
        this.service =service;
    }
    
    public void run() throws VendingMachinePersistenceException{
        
    } 
    
    
    
}
