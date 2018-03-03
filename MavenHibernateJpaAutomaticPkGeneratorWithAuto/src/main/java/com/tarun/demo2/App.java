package com.tarun.demo2;

import java.util.List;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tarun.demo2.connection.HibernateUtil;
import com.tarun.demo2.persistent.OrderPersistent;

public class App 
{
	public void addOrder(String n, double pay, boolean conf) {
    	Session session = null;
    	Transaction tx = null;
    	
    	OrderPersistent op = new OrderPersistent();
		op.setName(n);
		op.setPayment(pay);
		op.setConfirm(conf);
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			tx = session.beginTransaction();
			session.save(op);
			tx.commit();
		}
		catch(HibernateException he) {
			if(tx != null) {
				tx.rollback();
			}
			he.printStackTrace();
		}
		finally {
			// close connection
			session.close();
		}
    }
	
	public void updateOrder(int i, String n, double pay, boolean conf) {
		Session session = null;
		Transaction tx = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			tx = session.beginTransaction();
			OrderPersistent op = session.get(OrderPersistent.class, i);
			op.setName(n);
			op.setPayment(pay);
			op.setConfirm(conf);
			session.update(op);
			tx.commit();
		}
		catch(HibernateException he) {
			if(tx != null) {
				tx.rollback();
			}
			he.printStackTrace();
		}
		finally {
			// close connection
			session.close();
		}
	}
	
	public void deleteOrder(int id) {
		Session session = null;
		Transaction tx = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try{
			tx = session.beginTransaction();
			OrderPersistent op = session.get(OrderPersistent.class, id);
			session.delete(op);
			tx.commit();
		}
		catch(HibernateException he) {
			if(tx != null) {
				tx.rollback();
			}
			he.printStackTrace();
		}
		finally {
			// close connection
			session.close();
		}
	}
	
	public void showOrder() {
		Session session = null;
		Transaction tx = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			tx = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<OrderPersistent> list = (List<OrderPersistent>) session.createQuery("FROM OrderPersistent").list();
			for(OrderPersistent op : list) {
				System.out.println("Id Is :: "+op.getId());
				System.out.println("Name Is :: "+op.getName());
				System.out.println("Product Is :: "+op.getPayment());
				System.out.println("Address Is :: "+op.getConfirm());
				System.out.println("");
			}
			tx.commit();
		}
		catch(HibernateException he) {
			if(tx != null) {
				tx.rollback();
			}
			he.printStackTrace();
		}
		finally {
			// close connection
			session.close();
		}
	}
	
	public void showOrderById(int ids) {
		Session session = null;
		Transaction tx = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			tx = session.beginTransaction();
			OrderPersistent op = session.get(OrderPersistent.class, ids);
			if(op != null) {
				System.out.println(op);
			}
			else {
				System.out.println("Unable To Search Records");
			}
			tx.commit();
		}
		catch(HibernateException he) {
			if(tx != null) {
				tx.rollback();
			}
			he.printStackTrace();
		}
		finally {
			// close connection
			session.close();
		}
	}
	
	@SuppressWarnings({ "unused", "resource" })
	public static void main( String[] args ) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("1. Insert Record");
    		System.out.println("2. Update Record");
    		System.out.println("3. Delete Record");
    		System.out.println("4. Show Record");
    		System.out.println("5. Search Record By Id");
    		System.out.println("6. Exit");
    		System.out.println("Enter The Choice");
    		int choice = sc.nextInt();
    		if(choice == 1) {
    			System.out.println("Enter Name :: ");
    			String nm = sc.next();
    			
    			System.out.println("Enter Payment :: ");
    			double pay = sc.nextDouble();
    			
    			System.out.println("Enter Confirmation :: ");
    			boolean con = sc.nextBoolean();
    			
    			App addition = new App();
    			addition.addOrder(nm, pay, con);
    			if(addition != null) {
    				System.out.println("Orders Inserted Successfully");
    			}
    			else {
    				System.out.println("Unable To Insert Order");
    			}
    		}
    		else if(choice == 2) {
    			System.out.println("Enter Id :: ");
    			int i = sc.nextInt();
    			
    			System.out.println("Enter Name :: ");
    			String nm = sc.next();
    			
    			System.out.println("Enter Payment :: ");
    			double pay = sc.nextDouble();
    			
    			System.out.println("Enter Confirmation :: ");
    			boolean con = sc.nextBoolean();
    			
    			App updt = new App();
    			updt.updateOrder(i, nm, pay, con);
    			if(updt != null) {
    				System.out.println("Order Updated Successfully");
    			}
    			else {
    				System.out.println("Unable To Update Order");
    			}
    		}
    		else if(choice == 3) {
    			System.out.println("Enter Id :: ");
    			int i = sc.nextInt();
    			
    			App delet = new App();
    			delet.deleteOrder(i);
    			if(delet != null) {
    				System.out.println("Order Deleted Successfully");
    			}
    			else {
    				System.out.println("Unable To Delete Order");
    			}
    		}
    		else if(choice == 4) {
    			App show = new App();
    			show.showOrder();
    			if(show != null) {
    				System.out.println(show);
    			}
    			else {
    				System.out.println("Unable To Show Orders");
    			}
    		}
    		else if(choice == 5) {
    			System.out.println("Enter Id :: ");
    			int i = sc.nextInt();
    			
    			App search = new App();
    			search.showOrderById(i);
    		}
    		else {
    			System.out.println("Good Byeeee....");
    			return;
    		}
		}
	}
}
