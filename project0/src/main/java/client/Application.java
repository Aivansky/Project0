package client;

import java.util.Map;
import java.util.Scanner;
import org.apache.log4j.Logger;

import dao.Methods;
import transactions.util.Transactions;

public class Application 
{
	private static final Logger log = Logger.getLogger(Application.class);
	  
	private static Transactions user = null;
	private static Transactions user2 = null;
	
	private static Scanner scan = new Scanner(System.in);
	
	private static Methods dao = Methods.getter();

	public static void main(String[] args) throws Exception 
	{
        
			log.info("Logging is now fully operational");
			while (true) {
			    
			System.out.println("Main Menu:");
			System.out.println("Press 1 to login");
			System.out.println("Press 2 to create a user");
			System.out.println("Press 0 to exit");
			int menu = scan.nextInt();
			scan.nextLine();

			switch (menu) {
			case 1:
				login();
				break;
			case 2:
				createAccount();
				break;
			case 0:
				return;
			}
		}
	}

	public static void login() 
	{
			System.out.println("Enter name: ");
			String userName = scan.nextLine();
			user = dao.getUser(userName);
			if (user == null) {
				System.out.println("No user found");
			}
			System.out.println("Enter password: ");
			String password = scan.nextLine();
			if (!user.password.equals(password)) {
				System.out.println("Invalid password for : " + userName);
			}
		System.out.println(user.name+ " account is now logged in");
		log.info("User Logged in");
		CustomerMenu();
	}
	
	public static void createAccount() 
	{
		System.out.println("Create a account");
		boolean admin = false;
		String name = null;
			System.out.println("Enter a new user name: ");
			name = scan.nextLine();
		System.out.println("Enter a password: ");
		String password = scan.nextLine();
		user = new Transactions(name, password, 0, admin); 
		dao.insertUser(user);
		System.out.println("Customer account created with 0 balance, username: " + user.name);
		log.info("Customer Created");
	}

	public static void CustomerMenu() 
	{
		while (true) 
		{
			System.out.println("Customer Menu:");
			System.out.println("Press 1 to view balance");
			System.out.println("Press 2 to deposit");
			System.out.println("Press 3 to withdraw");
			System.out.println("Press 4 to transfer between accounts");
		
			if (user.admin) 
			{
				System.out.println("Employee Menu:");
				System.out.println("Press 5 to grant admin to user account");
				System.out.println("Press 6 to view all accounts");
				System.out.println("Press 7 to view all transactions");
				
			}
			System.out.println("Press 0 to sign out and exit to main menu : " + user.name);

			int menu = scan.nextInt();
			scan.nextLine();
			
			switch (menu) {
			case 1:
				viewBalance();
				break;
			case 2:
				deposit();
				break;
			case 3:
				withdraw();
				break;
			case 4:
				transfer();
				break;
			case 5:
				adminUser();
			case 6:
				viewAll();
			case 7:
				viewTransactions();
			case 0:
				System.out.println("Logging out: "+user.name);
				user = null;
				return;
			}
		}
	}
	
	
	public static void viewTransactions(){
		user.printTransactions();
	}

	
	public static void viewBalance() {
		System.out.println(user.name + " your balance is $" + user.balance);
	}
					

	public static void deposit() 
	{
		System.out.println("Enter money to deposit: ");
		float deposit = scan.nextFloat();

		user.AccDeposit(deposit);
		dao.refreshUser(user);

		System.out.println("your new balance is $" + user.balance);
		 log.info("Amount Deposited");
	}
	
	public static void withdraw() 
	{
		System.out.println("Enter the amount to withdraw: ");
		float withdrawal = scan.nextFloat();

		user.AccWithdraw(withdrawal);
		dao.refreshUser(user);

		System.out.println(user.name + " your new balance is $" + user.balance);
		 log.info("Amount Withdrawn");
	}

	private static void transfer() 
	{
		System.out.println(user.name +  " your balance is $" + user.balance);
		System.out.println("Enter transfer money ");
		float withdrawal = scan.nextFloat();

		user.AccWithdraw(withdrawal);
		dao.refreshUser(user);
		System.out.println("enter account name you wish to transfer to");
		String name = scan.next();
		user2 = dao.getUser(name);
		user2.AccDeposit(withdrawal);
		dao.refreshUser(user2);
		
		System.out.println(user.name +  " your balance is now $"  + user.balance);
		System.out.println(user2.name + " your balance is now $" + user2.balance );
		 log.info("Amount transfered");
	}
	
	private static void adminUser() 
	{
		System.out.println("Approve users");

		Map<String, Transactions> getallmap = dao.getAll();
		viewAll();

		System.out.println("Enter the name of the user to approve: ");
		String scan2 = scan.nextLine();

		Transactions user = getallmap.get(scan2); 
		user.admin = true;
		dao.refreshUser(user);
		System.out.println(user.name + " approved");
		 log.info("User promoted to administrator");
	}
	
	private static void viewAll() 
	{
		Map<String, Transactions> getallmap = dao.getAll();
		System.out.println("Users in the system:");
		for (String name : getallmap.keySet()) 
		{
			Transactions usersAll = getallmap.get(name);
			System.out.println
			(name + " pw: " + usersAll.password + " balance: " + usersAll.balance + " admin: " + usersAll.admin );
		}
		 log.info("List of users");
	}
	
}
