import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.pool.OracleDataSource;

class JdbcTest {
	public static void main(String args[]) throws SQLException {

		long start = System.currentTimeMillis();
		OracleDataSource ods = null;
		Connection conn = null;
		
		List<Connection> conns = new ArrayList<Connection>();
		
		Statement stmt = null;
		ResultSet rset = null;

		// Create DataSource and connect to the local database
		ods = new OracleDataSource();
		 ods.setURL("jdbc:oracle:thin:@129.146.75.51:1521/nyerp_phx18t.sub03060456490.phoenixsub.oraclevcn.com ");
		
//		ods.setURL("jdbc:oracle:thin:@129.146.110.2:1521/pdb1.sub03060456491.phoenixsub.oraclevcn.com");
		ods.setUser("NY_ERP");
		ods.setPassword("NY_erp#37455");
		
		conn = ods.getConnection();

		for( int i = 0; i < 20; i ++ ){
			conns.add(ods.getConnection());
		}
		
		System.out.println(conns.size());
		try {
			// Query the employee names
			stmt = conn.createStatement();
			rset = stmt.executeQuery("SELECT 'Hello world' FROM dual");

			// Print the name out
			while (rset.next())
				System.out.println(rset.getString(1));
		}

		// Close the result set, statement, and the connection

		finally {
			if (rset != null)
				rset.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
			
			long end = System.currentTimeMillis();
			
			System.out.println(end - start );
		}
		
		
//		System.out.print
	
	}
}
