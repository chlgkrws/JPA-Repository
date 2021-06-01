package jpa.order.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Member extends BaseEntity{

	@Id
	@GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;

	private String name;

	@Embedded					//생략가능
	private Address address;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}

	private Address getAddress() {
		return address;
	}

	private void setAddress(Address address) {
		this.address = address;
	}







}
