package com.shivaji.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="emp1")
public class Employee {
	@Id
	//@SequenceGenerator(name="seqGenerator",sequenceName="my_sequence")
	//@TableGenerator(name="tableGenerator",table="my_table",pkColumnName = "id",pkColumnValue = "10",valueColumnName = "next_hi")
	@GenericGenerator(name="incGenerator", strategy="increment")
	@GeneratedValue(generator="incGenerator")
	@Column(name="ENO")
	private int eno;
	@Column(name="ENAME")
	private String ename;
	@Column(name="ESAL")
	private float esal;
	@Column(name="EADDR")
	private String eaddr;
	
	public int getEno() { 
		return eno;
	}
	public void setEno(int eno) {
		this.eno = eno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public float getEsal() {
		return esal;
	}
	public void setEsal(float esal) {
		this.esal = esal;
	}
	public String getEaddr() {
		return eaddr;
	}
	public void setEaddr(String eaddr) {
		this.eaddr = eaddr;
	}
}
