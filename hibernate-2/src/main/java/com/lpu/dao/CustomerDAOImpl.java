package com.lpu.dao;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.lpu.bean.Customer;
import com.lpu.factory.MyHibernateFactory;

public class CustomerDAOImpl implements CustomerDAO {
	
	private  MyHibernateFactory factory=null;
	private SessionFactory sessionFactory=null;
	private Session session=null;
	
	{
		factory=MyHibernateFactory.getMyHibernateFactory();
		sessionFactory=factory.getSessionFactory();
		session=sessionFactory.openSession();
	}

	@Override
	public Customer createCustomer(Customer customer) {
		
		session.getTransaction().begin();
		session.save(customer);
		session.getTransaction().commit();
		return customer;
	}

	@Override
	public List<Customer> getAllCustomer() {
		
		List<Customer> list=session.createQuery("from Customer",Customer.class).list();
		
		return list;
	}

	@Override
	public Customer getCustomerByID(String customerId) {
		
		return session.get(Customer.class, customerId);
	}

	@Override
	public Customer updateById(Customer customer) {
		session.getTransaction().begin();
		session.update(customer);
		session.getTransaction().commit();
		return customer;
	}

	
	
	

}
