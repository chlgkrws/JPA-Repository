package jpa.order.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Delivery extends BaseEntity {

	@Id
	@GeneratedValue
	private Long id;

	@Embedded					//생략가능
	private Address address;

	private DeliveryStatus status;

	@OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
	private Order order;
}
