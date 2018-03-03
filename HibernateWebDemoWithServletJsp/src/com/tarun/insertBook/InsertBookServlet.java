package com.tarun.insertBook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tarun.connection.HibernateUtil;
import com.tarun.persistent.BooksPersistent;

@WebServlet("/InsertBook")
public class InsertBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		PrintWriter out = response.getWriter();
		String name = request.getParameter("bkname");
		String author = request.getParameter("bkauthor");
		String publisher = request.getParameter("bkpublisher");
		String price = request.getParameter("bkprice");
		
		Session session = null;
		Transaction tx = null;
		
		BooksPersistent bp = new BooksPersistent();
		bp.setName(name);
		bp.setAuthor(author);
		bp.setPublisher(publisher);
		bp.setPrice(price);
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			tx = session.beginTransaction();
			session.save(bp);
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
		response.sendRedirect("GetBook");
	}
}
