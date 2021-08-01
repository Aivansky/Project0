package transactions.util;

import java.util.ArrayList;

public class Transactions
{
	public static ArrayList<Integer> deposits = new ArrayList<Integer>();
	public static ArrayList<Integer> withdraws = new ArrayList<Integer>();
	
	public String name;
	public String password;
	public float balance;
	public boolean admin;
	public boolean approved;
	
	public void AccDeposit(float deposit) 
	{
		balance += deposit;
		  deposits.add((int) deposit);
	}

	public void AccWithdraw(float withdrawal) 
	{
		if(balance<=0) {
			System.out.println("Sorry there is no more money to withdraw");
			return;
		}
		else if(withdrawal> balance) {
			System.out.println("Sorry you are withdrawing more money than what is stored");
			return;
		}
		balance -= withdrawal;
		  withdraws.add((int) withdrawal);
	}
	
	public void printTransactions() {
		  for(Integer s : deposits) {
		        System.out.println("$"+s+" was deposited");
		    }
		  for(Integer s : withdraws) {
		        System.out.println("$"+s+" was withdrawn");
		    }
	}
	

	public Transactions(String name, String password, float balance, boolean admin) {
	super();
	this.name = name;
	this.password = password;
	this.balance = balance;
	this.admin = admin;
	}

	
}





