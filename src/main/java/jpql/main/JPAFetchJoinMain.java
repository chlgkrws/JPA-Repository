package jpql.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpql.Address;
import jpql.Member;
import jpql.MemberType;
import jpql.Team;

public class JPAFetchJoinMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-application");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {

			Team teamA = new Team();
			teamA.setName("팀A");
			em.persist(teamA);

			Team teamB = new Team();
			teamB.setName("팀B");
			em.persist(teamB);

			Member member1 = new Member();
			member1.setUsername("회원1");
			member1.setTeam(teamA);
			em.persist(member1);

			Member member2 = new Member();
			member2.setUsername("회원2");
			member2.setTeam(teamA);
			em.persist(member2);

			Member member3 = new Member();
			member3.setUsername("회원3");
			member3.setTeam(teamB);
			em.persist(member3);

			em.flush();
			em.clear();

			//기본 조인
			String query = "select m from Member m join m.team";

			List<Member> result1 = em.createQuery(query, Member.class)
					.getResultList();

			for(Member m : result1) {
				System.out.println(m.getTeam());
			}


			//엔티티 패치 조인
			query = "select m from Member m join fetch m.team";

			List<Member> result2 = em.createQuery(query, Member.class)
					.getResultList();

			for(Member m : result2) {
				System.out.println(m.getTeam());
			}


			//컬렉션  조인
			String colQuery = "select t from Team t join t.members m";

			List<Team> result3 = em.createQuery(colQuery, Team.class)
					.getResultList();

			for(Team t : result3) {
				System.out.println( t.getName()+ " | "+ t.getMembers().size());
			}


			//컬렉션 패치 조인
			colQuery = "select distinct t from Team t join fetch t.members m";

			List<Team> result4 = em.createQuery(colQuery, Team.class)
					.getResultList();

			for(Team t : result4) {
				System.out.println( t.getName()+ " | "+ t.getMembers().size());
			}



			//엔티티 직접 사용
			String entityQuery = "select m from Member m where m = :member";

			Member result5 = em.createQuery(entityQuery, Member.class)
					.setParameter("member", member1)
					.getSingleResult();

			System.out.println(result5);


			//엔티티 직접 사용 외래 키 값
			String fkQuery = "select m from Member m where m.team = :team ";

			List<Member> result6 = em.createQuery(fkQuery, Member.class)
					.setParameter("team", teamA)
					.getResultList();

			System.out.println(result6);


			//네임드 쿼리
			em.createNamedQuery("Member.findByUsername")
				.setParameter("username", "회원 1")
				.getResultList();


			//벌크 연산(flush 호출 - DB와 직접 컨택)
			int resultCount = em.createQuery("update Member m set m.age = 20")
				.executeUpdate();

			System.out.println(resultCount);

			//em.clear()가 없으면 아래 age는 아직 0살
			Member findMember = em.find(Member.class, member1.getId());
			System.out.println(findMember.getAge());

			tx.commit();
		} catch (Exception e) {
			System.out.println("############# ROLL_BACK ############");
			e.printStackTrace();
			tx.rollback();
		}
		em.close();
		emf.close();
	}

}
