package jpa.order.jpaMain;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpa.order.domain.Book;


public class JPAMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-application");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {

			Book book = new Book();
			book.setName("JPA");
			book.setAuthor("최학준");

			em.persist(book);
			System.out.println(book.getId());

			tx.commit();
		} catch (Exception e) {
			System.out.println("ROLLBACK!!!!!!!");
			tx.rollback();
		}
		em.close();
		emf.close();
	}
}
