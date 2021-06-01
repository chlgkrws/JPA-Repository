package jpa.basic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpa.basic.entity.Member;

/**
 * JPA 기본(조회, 삽입, 수정, 삭제)
 * @author cgw981 2021.05.05
 */
public class JpaBasic {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-application");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			// 삽입
			Member m = new Member();
			m.setId(100L);
			m.setName("춘식이");

			// 조회
			Member m2 = em.find(Member.class, 100L);

			// 수정
			m2.setName("수정된 춘식이");

			// 삭제
			em.remove(m2);

			// JPQL
			List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();
			System.out.println(result.get(0).getName());


			Member member1 = em.find(Member.class, 10);

			member1.setName("수정된 춘식");

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}
		em.close();
		emf.close();
	}
}
