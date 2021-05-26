package jpql.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpql.Address;
import jpql.Member;
import jpql.Team;

public class JPAMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-application");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			Member member = new Member();
			member.setUsername("member1");
			em.persist(member);

			//메서드 체인이 가능하도록 API가 설계되어 있음
			Member singleResult = em.createQuery(
					"select m from Member m where m.username = :username", Member.class)
					.setParameter("username", "member1")
					.getSingleResult();

			//영속성 컨텍스트에 관리가 되는지 확인해보기
			System.out.println(singleResult.getUsername());

			List<Team> result = em.createQuery("Select t FROM Member m join m.team t",Team.class).getResultList();


			//임베디드 타입 프로젝션
			em.createQuery("SELECT o.address FROM Order o", Address.class).getResultList();


			//Paging
			List<Member> pagingList =  em.createQuery("SELECT m FROM Member m order by m.age desc", Member.class)
						.setFirstResult(10)
						.setMaxResults(10)
						.getResultList();


			//조인
			Team team = new Team();
			team.setName("teamA");
			em.persist(team);

			member.setTeam(team);

			String innerJoin = "select m from Member m inner join m.team t";
			List<Member> join = em.createQuery(innerJoin, Member.class).getResultList();

			String leftJoin = "select m from Member m left join m.team t";
			List<Member> Join2 = em.createQuery(leftJoin, Member.class).getResultList();

			String leftJoinWithOn = "select m from Member m left join m.team t on t.name = 'teamA'";
			List<Member> Join3 = em.createQuery(leftJoinWithOn, Member.class).getResultList();


			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}
		em.close();
		emf.close();
	}

}
