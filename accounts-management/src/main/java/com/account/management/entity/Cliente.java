package com.account.management.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "CLIENTE")
public class Cliente extends Persona {
	
	@Column(name = "clientId", length = 6)
	private String clientId;
	
	@Column(name = "password", nullable = false, length = 4)
	private String password;
	
	@Column(name = "status", nullable = false, length = 5)
	private String status;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	//@JsonBackReference
	@OneToMany(mappedBy="cliente")
	private Set<Cuenta> cuentas;

	public Cliente() {

	}

}
