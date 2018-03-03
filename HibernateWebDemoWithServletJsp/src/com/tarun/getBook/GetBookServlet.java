package com.tarun.getBook;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/GetBook")
public class GetBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			tx = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<BooksPersistent> list = session.createQuery("FROM BooksPersistent").list();
			tx.commit();
			session.close();
			request.setAttribute("getList", list);
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		request.getRequestDispatcher("booksDetails.jsp").forward(request, response);
	}
}
