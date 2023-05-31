package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.service.VendingMachinePersistenceException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class VendingMachineAuditDaoFileImpl  implements VendingMachineAuditDao {

    public static final String AUDIT_FILE = "audit.txt";
    @Override
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE), true);
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Could not load data");
        }

        LocalDateTime timeStamp = LocalDateTime.now();
        out.println(timeStamp.toString() + " : " + entry);
        out.flush();
    }
}
