package com.lpu.demo.repo;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lpu.demo.bean.Account;

@Repository("accountRepository")
public class AccountRepositoryImpl implements AccountRepository {
	
	private JdbcTemplate jdbcTemplate;
	private Logger logger=Logger.getLogger("AccountRepositoryImpl");
	@Autowired
	public AccountRepositoryImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}


	@Override
	public Account createAccount(Account account) {
		
		
		String query="insert into account(accountNumber,accountType,balance) values('"
				+account.getAccountNumber()+"','"
				+account.getAccountType()+"',"+account.getInitialBalance()+")";
		
		jdbcTemplate.update(query);
		
		
		return account;
	}


	@Override
	public Account updateAccount(Account account) {
		String query = "update account set accountType='"+account.getAccountType()+"', balance="+account.getInitialBalance()+" where accountNumber='"+account.getAccountNumber()+"'";
		jdbcTemplate.update(query);
		return account;
	}


	@Override
	public Account deleteAccount(Account account) {
		String query = "delete from account where accountNumber='"+account.getAccountNumber()+"'";
		jdbcTemplate.update(query);
		return account;
	}
	

}
