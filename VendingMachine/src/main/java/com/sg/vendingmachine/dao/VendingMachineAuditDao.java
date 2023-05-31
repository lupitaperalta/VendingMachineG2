package com.sg.vendingmachine.dao;

public interface VendingMachineAuditDao {
    //TODO fix exception
    public void writeAuditEntry(String entry) throws Exception;
}
