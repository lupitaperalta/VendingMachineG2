package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;

/**
 * This class is a stub implementation of the VendingMachineAuditDao interface.
 * It provides an empty implementation of the writeAuditEntry method.
 * This stub is used during testing to simulate the auditing functionality.
 */
public class VendingMachineAuditDaoStubImpl implements VendingMachineAuditDao {
    @Override
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException {

    }
}
