package jpa.order.jpaMain;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpa.order.domain.Member;


public class JPAMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-application");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {

			/* 기본 조회 JPQL
			List<Member> result = em.createQuery(
					"select m From Member m where m.name like '%Kim%'",Member.class
					).getResultList();
			*/

			


			tx.commit();
		} catch (Exception e) {
			System.out.println("ROLLBACK!!!!!!!");
			tx.rollback();
		}
		em.close();
		emf.close();
	}
}
