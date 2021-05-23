package jpa.order.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity{

	private String createBy;
	private LocalDateTime createTime;
	private String updateBy;
	private LocalDateTime updateTime;
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public LocalDateTime getCreateTime() {
		return createTime;
	}
	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public LocalDateTime getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}


}
