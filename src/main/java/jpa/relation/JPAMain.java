package jpa.relation;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpa.relation.entity.BaseEntity;
import jpa.relation.entity.Item;
import jpa.relation.entity.Movie;


public class JPAMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-application");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.flush();
			em.clear();
			Movie movie = new Movie();
			movie.setDirector("aaaa");
			movie.setActor("bnbbb");
			movie.setName("바람가");
			movie.setPrice(10000);
			movie.setCreatedTime(LocalDateTime.now());

			em.persist(movie);

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}
		em.close();
		emf.close();
	}

}
