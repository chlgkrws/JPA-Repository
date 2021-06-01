package jpa.relation.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

//@Entity
@Inheritance(strategy = InheritanceType.JOINED)		//조인전략
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)	//단일테이블 전략
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) //구현클래스 테이블 전략
@DiscriminatorColumn		//조인전략 시 사용할 DType
@Table(name="super_item")
public abstract class Item extends BaseEntity{

	@Id @GeneratedValue
	@Column
	private Long id;

	private String name;
	private int price;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}


}
