/*
 create or replace procedure getSal(no IN number , sal OUT float)
 AS
 BEGIN
 	select esal into sal from emp1 where ENO = no;
 END getSal;
 /
 */

package com.shivaji.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class Test {

	public static void main(String[] args) {
		Connection conn = null;
		CallableStatement cst = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","durga");
			cst = conn.prepareCall("{call getSal(?,?)}");
			cst.setInt(1, 111);
			cst.registerOutParameter(2,Types.FLOAT);
			cst.execute();
			System.out.println("111 Employee Salary  : "+cst.getFloat(2));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				cst.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
