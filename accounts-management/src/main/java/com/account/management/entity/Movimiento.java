package com.account.management.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.JoinColumn;

@Entity
@Table(name = "MOVIMIENTO")
public class Movimiento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "idMovement", nullable = false, length = 6)
	private Integer idMovement;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	@Column(name = "date", nullable = false, length = 19)
	private Date date;
	
	@Column(name = "movementType", nullable = false, length = 20)
	private String movementType;
	
	@Column(name = "value", nullable = false, length = 6)
	private Integer value;
	
	@Column(name = "balance", nullable = false, length = 6)
	private Integer balance;
	
	public Integer getIdMovement() {
		return idMovement;
	}
	
	public void setIdMovement(Integer idMovement) {
		this.idMovement = idMovement;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getMovementType() {
		return movementType;
	}
	
	public void setMovementType(String movementType) {
		this.movementType = movementType;
	}
	
	public Integer getValue() {
		return value;
	}
	
	public void setValue(Integer value) {
		this.value = value;
	}
	
	public Integer getBalance() {
		return balance;
	}
	
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	
	@ManyToMany
	@JoinTable(
			  name = "cuenta_movimiento", 
			  joinColumns = @JoinColumn(name = "id_movement"),
			  inverseJoinColumns = @JoinColumn(name = "id_account"))
	Set<Cuenta> cuenta;
	
	public Set<Cuenta> getCuenta() {
		return cuenta;
	}

	public void setCuenta(Set<Cuenta> cuenta) {
		this.cuenta = cuenta;
	}

	public Movimiento() {

	}

}
