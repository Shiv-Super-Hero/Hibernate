/*
create or replace function getAVG(no1 IN number,no2 IN number)return float
AS
	sal1 float;
	sal2 float;
BEGIN
	select ESAL into sal1 from emp1 where ENO = no1;
	select ESAL into sal2 from emp1 where ENO = no2;
	return(sal1+sal2)/2;
END getAVG;
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
			cst = conn.prepareCall("{? = call getAVG(?,?)}");
			cst.setInt(2, 111);
			cst.setInt(3, 222);
			cst.registerOutParameter(1, Types.FLOAT);
			cst.execute();
			System.out.println("111 and 222 Employees AVG Salary  : "+cst.getFloat(1));
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
