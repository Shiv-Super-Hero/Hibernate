/*
create or replace procedure getEmps(sal_Range IN float,emps OUT SYS_REFCURSOR)
AS
BEGIN
	open emps for
		select * from emp1 where ESAL < sal_Range;
END getEmps;
/
 */
package com.shivaji.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import oracle.jdbc.OracleTypes;

public class Test {

	public static void main(String[] args) {
		Connection conn = null;
		CallableStatement cst = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","durga");
			
//		The Positional parameters starts from 1 in Jdbc whereas	it starts from 0 in case of Hibernate.
			
			cst = conn.prepareCall("{call getEmps(?,?)}");
			cst.setFloat(1, 5500);
			cst.registerOutParameter(2, OracleTypes.CURSOR);
			cst.execute();
			rs = (ResultSet)cst.getObject(2);
			System.out.println("ENO\tENAME\tESAL\tEADDR");
			System.out.println("---------------------------");
			while(rs.next()) {
				System.out.print(rs.getInt("ENO")+"\t");
				System.out.print(rs.getString("ENAME")+"\t");
				System.out.print(rs.getFloat("ESAL")+"\t");
				System.out.print(rs.getString("EADDR")+"\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				cst.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();			
			}
		}

	}

}
