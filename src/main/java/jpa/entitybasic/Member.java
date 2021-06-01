package jpa.entitybasic;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

//@Entity
@Table(name = "MEMBER")		//매핑할 테이블 명
@SequenceGenerator(
		 name = "MEMBER_SEQ_GENERATOR",
		 sequenceName = "MEMBER_SEQ", //매핑할 데이터베이스 시퀀스 이름
		 initialValue = 1, allocationSize = 1)
public class Member {

	//@Column(name = "MEMBER_ID", updatable = false)	//MEMBER_ID 컬럼 / 업데이트 불가능
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
	private Long id;

	@Column(length = 200 ,nullable = false)	//DDL 생성 시 not null 제약조건이 붙음 / 데이터 길이는 200
	private String name;

	@Column(columnDefinition = "varchar(100) default '설명'")	//컬럼 정보 직접 작성
	private String description;

	@Column
	private LocalDate createDate;

	@Column
	private LocalDateTime createDateTime;

	@Temporal(TemporalType.DATE)
	private Date date;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;

	@Transient
	private String temp;

	@Enumerated(EnumType.ORDINAL)
	RoleType roleTypeOrdinal;

	@Enumerated(EnumType.STRING)
	RoleType roleTypeString;
}
