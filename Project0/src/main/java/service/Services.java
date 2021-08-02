package service;

import java.util.Map;

import transaction_implementation.Transactions;

public interface Services 
{	
	public boolean insertUser(Transactions user);

	public Transactions getUser(String name);

	public boolean refreshUser(Transactions user);

	 public abstract Map<String, Transactions> getAll();
	 
	 

}
