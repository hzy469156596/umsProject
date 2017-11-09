package com.qingshixun.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "t_role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String username;

	private String description;

	@Temporal(TemporalType.DATE)
	private Date createDate;
	
	
	

	@OneToMany(targetEntity = User.class, mappedBy="role", fetch = FetchType.EAGER)
	private Set<User> user = new HashSet<User>();

	@ManyToMany(targetEntity = Jurisdiction.class, fetch = FetchType.EAGER)
	private Set<Jurisdiction> jurisdiction = new HashSet<Jurisdiction>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}

	public Set<Jurisdiction> getJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(Set<Jurisdiction> jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", username=" + username + ", description=" + description + ", createDate="
				+ createDate + "]";
	}

}
