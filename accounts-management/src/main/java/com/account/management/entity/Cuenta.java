package com.account.management.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CUENTA")
public class Cuenta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "idAccount", nullable = false, length = 6)
	private Integer idAccount;
	
	@Column(name = "accountNumber", nullable = false, length = 10)
	private String accountNumber;
	
	@Column(name = "accountType", nullable = false, length = 20)
	private String accountType;
	
	@Column(name = "initialBalance", nullable = false, length = 6)
	private Integer initialBalance;
	
	@Column(name = "status", nullable = false, length = 5)
	private String status;
	
	public Integer getIdAccount() {
		return idAccount;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public String getAccountType() {
		return accountType;
	}
	
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	public Integer getInitialBalance() {
		return initialBalance;
	}
	
	public void setInitialBalance(Integer initialBalance) {
		this.initialBalance = initialBalance;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	//@JsonManagedReference
	@ManyToOne
    @JoinColumn(name="id_cliente", nullable=false)
    private Cliente cliente;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@ManyToMany(mappedBy = "cuenta")
	Set<Movimiento> movimiento;
	
	public Cuenta() {

	}

}
