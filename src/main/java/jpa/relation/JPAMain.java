package jpa.relation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpa.relation.entity.Member;
import jpa.relation.entity.Team;


public class JPAMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-application");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			Team team = new Team();
			team.setId(150L);
			team.setName("카카오");
			em.persist(team);

			Member m = new Member();
			m.setId(100L);
			m.setName("춘식이");
			m.setTeam(team);
			em.persist(m);

			em.flush();
			em.clear();

			Team findTeam  = em.find(Team.class, 150L);
			Member findMember = em.find(Member.class, 100L);
			List<Member> members =findMember.getTeam().getMembers();

			System.out.println(findTeam.getMembers());
			System.out.println(members);
			System.out.println(findMember.getTeam().getId());

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}
		em.close();
		emf.close();
	}

}
