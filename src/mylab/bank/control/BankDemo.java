package mylab.bank.control;

import mylab.bank.entity.*;
import mylab.bank.exception.*;

public class BankDemo {
    public static void main(String[] args) {
        Bank bank = new Bank();

        try {
            System.out.println("=== 계좌 생성 ===");
            String ac1 = bank.createSavingsAccount("홍길동", 10000, 3.0);
            String ac2 = bank.createCheckingAccount("김철수", 20000, 5000);
            String ac3 = bank.createSavingsAccount("이영희", 30000, 2.0);
            
            bank.printAllAccounts();

            System.out.println("\n=== 입금/출금 테스트 ===");
            bank.deposit(ac1, 5000);
            System.out.println("5000.0원이 입금되었습니다. 현재 잔액: " + bank.findAccount(ac1).getBalance() + "원");
            bank.withdraw(ac2, 3000);
            System.out.println("3000.0원이 출금되었습니다. 현재 잔액: " + bank.findAccount(ac2).getBalance() + "원");

            System.out.println("\n=== 이자 적용 테스트 ===");
            SavingsAccount sa = (SavingsAccount) bank.findAccount(ac1);
            sa.applyInterest();
            System.out.println("이자 적용 후 잔액: " + sa.getBalance() + "원");

            System.out.println("\n=== 계좌 이체 테스트 ===");
            bank.transfer(ac3, ac2, 5000);
            System.out.println("5000.0원이 " + ac3 + "에서 " + ac2 + "로 송금되었습니다.");

            System.out.println("\n=== 모든 계좌 목록 ===");
            bank.printAllAccounts();

            System.out.println("\n=== 예외 발생 테스트 ===");
            // 1. 한도 초과 테스트
            bank.withdraw(ac2, 6000); 

        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        try {
            // 2. 존재하지 않는 계좌 테스트
            bank.deposit("AC9999", 1000);
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
    }
}