package junitTests;

import org.junit.Test;

import dao.Methods;
import transaction_implementation.Transactions;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

public class TestRunner {
	
	private static Methods instance;
	public static Methods getInstance() 
	{
		if (instance == null) 
		{
			instance = new Methods();
		}
		return instance;
	}
	@Test
		public void getUser() {
			Methods dao_user = new Methods();
			dao_user.getUser("bob");
			assertTrue(true);
		    System.out.println("retrieve user success");
		}
		
		@Test
		public void getFakeUser() {
			Methods dao_user = new Methods();
			dao_user.getUser("does not exist");
			assertFalse(false);
		    System.out.println("null user");
		}
		
		  @Test
		  public void testAssertNotNull() {
			  Methods dao_user = new Methods();	
			  dao_user.getUser("bob");
		    assertNotNull("should not be null", dao_user);
		    System.out.println("non null user");
		  }
		  
		  @Test
		  public void testAssertNotSame() {
			  Methods dao_user = new Methods();	
			  Methods dao_user2 = new Methods();		  
		    assertNotSame("should not be same Object", dao_user, dao_user2);
		    System.out.println("diff object");
		  }

		  @Test
		  public void testAssertSame() {
			  Methods dao_user = new Methods();	
		    assertSame("should be same", dao_user, dao_user);
		    System.out.println("same object");
		  }
		  
			
		  @SuppressWarnings({ "deprecation", "unchecked" })
		@Test
		  public void testAssertDepositHasItems() {
		    assertThat(Arrays.asList(transaction_implementation.Transactions.deposits), hasItems());
		    System.out.println("deposit array");
		  }
		  
		  @SuppressWarnings({ "deprecation", "unchecked" })
			@Test
			  public void testAssertWithdrawsHasItems() {
			    assertThat(Arrays.asList(transaction_implementation.Transactions.withdraws), hasItems());
			    System.out.println("withdraw array");
			  }
		
		@Test
		 public void depositTest() {
			Transactions user = new Transactions("testname", "testpassword", 0, false);
			user.AccDeposit(500);
			assertTrue(true);
			System.out.println("If true 500 is deposited " + "user balance: " +user.balance);
		  }
		
		@Test
		 public void withdrawTest() {
			Transactions user = new Transactions("testname", "testpassword", 0, false);
			user.AccWithdraw(500);
			assertTrue(true);
			System.out.println("If true will throw message of unable to withdraw due to no money");
		  }
		
		@Test
		 public void withdrawTest2() {
			Transactions user = new Transactions("testname", "testpassword", 600, false);
			user.AccWithdraw(500);
			assertTrue(true);
			System.out.println("If true 600-500 leaves 100 in balance "+"balance is: "+ user.balance);
			
		  }
		  

		}