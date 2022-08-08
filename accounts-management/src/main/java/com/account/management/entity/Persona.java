package com.account.management.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "PERSONA")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Persona {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", length = 6)
	private Integer id;
	
	@Column(name = "name", nullable = false, length = 30)
	private String name;
	
	@Column(name = "gender", length = 10)
	private String gender;
	
	@Column(name = "age", length = 2)
	private String age;
	
	@Column(name = "identification", length = 10, unique = true)
	@Pattern(regexp = "[0-9]{10,10}", message = "La cedula debe ser de 10 digitos")
	@NotBlank(message = "El campo cedula es obligatorio")
	private String identification;
	
	@Column(name = "address", nullable = false, length = 50)
	private String address;
	
	@Column(name = "phone", nullable = false, length = 10)
	@Pattern(regexp = "[0-9]{10,10}", message = "El telefono debe ser de 10 digitos")
	private String phone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", name=" + name + ", gender=" + gender + ", age=" + age + ", identification="
				+ identification + ", address=" + address + ", phone=" + phone + "]";
	}

	public Persona(Integer id, String name, String gender, String age,
			@Pattern(regexp = "[0-9]{10,10}", message = "La cedula debe ser de 10 digitos") @NotBlank(message = "El campo cedula es obligatorio") String identification,
			String address,
			@Pattern(regexp = "[0-9]{10,10}", message = "El telefono debe ser de 10 digitos") String phone) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.identification = identification;
		this.address = address;
		this.phone = phone;
	}

	public Persona() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
