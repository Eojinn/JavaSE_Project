package mylab.bank.entity;

import mylab.bank.exception.*;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Account> accounts;
    private int nextAccountNumber;

    public Bank() {
        this.accounts = new ArrayList<>();
        this.nextAccountNumber = 1000;
    }

    public String createSavingsAccount(String ownerName, double balance, double interestRate) {
        String accNum = "AC" + nextAccountNumber++;
        SavingsAccount acc = new SavingsAccount(accNum, ownerName, balance, interestRate);
        accounts.add(acc);
        return accNum;
    }

    public String createCheckingAccount(String ownerName, double balance, double limit) {
        String accNum = "AC" + nextAccountNumber++;
        CheckingAccount acc = new CheckingAccount(accNum, ownerName, balance, limit);
        accounts.add(acc);
        return accNum;
    }

    public Account findAccount(String accountNumber) throws AccountNotFoundException {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(accountNumber)) return acc;
        }
        throw new AccountNotFoundException("계좌번호 " + accountNumber + "에 해당하는 계좌를 찾을 수 없습니다.");
    }

    public void deposit(String accountNumber, double amount) throws AccountNotFoundException {
        findAccount(accountNumber).deposit(amount);
    }

    public void withdraw(String accountNumber, double amount) throws AccountNotFoundException, InsufficientBalanceException {
        findAccount(accountNumber).withdraw(amount);
    }

    public void transfer(String from, String to, double amount) throws AccountNotFoundException, InsufficientBalanceException {
        Account fromAcc = findAccount(from);
        Account toAcc = findAccount(to);
        fromAcc.withdraw(amount);
        toAcc.deposit(amount);
    }

    public void printAllAccounts() {
        for (Account acc : accounts) {
            System.out.println(acc);
        }
    }
}