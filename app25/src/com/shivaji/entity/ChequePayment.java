package com.shivaji.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="cheque_payment")
@PrimaryKeyJoinColumn(name="TX_ID")
public class ChequePayment extends Payment {
	@Column(name="CHEQUE_NO")
	private int cheque_No;
	@Column(name="ACC_NO")
	private String acc_No;
	
	public int getCheque_No() {
		return cheque_No;
	}
	public void setCheque_No(int cheque_No) {
		this.cheque_No = cheque_No;
	}
	public String getAcc_No() {
		return acc_No;
	}
	public void setAcc_No(String acc_No) {
		this.acc_No = acc_No;
	}
}
