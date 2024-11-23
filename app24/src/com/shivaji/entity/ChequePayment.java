package com.shivaji.entity;

public class ChequePayment extends Payment {
	private int cheque_No;
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
