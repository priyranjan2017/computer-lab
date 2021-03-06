import java.sql.*;
public class insertRSet
{
	public static void main(String args[])
	{
		String url="jdbc:odbc:Customer_Info";
		Statement st;
		ResultSet rs;
		Connection Db;
		boolean haveRecords;
		String name,set;
		int age;
		double sal=0;
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Db=DriverManager.getConnection(url);
	
			try
			{
				String query="SELECT C_NAME,C_age,C_SAL FROM Customer";					// Query to SELECT 3 COLOUMNS RECORD
				st=Db.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);	// TO create the query statement
				rs=st.executeQuery(query);								// execute the query 
				haveRecords=rs.next();
				if(!haveRecords)
					System.out.println("NO RECORDS ARE RETURNED");
				else
				{
					try
					{
						do
						{
							name=rs.getString(1);						// Display the result set
							age=rs.getInt(2);						//Primary Key coloumn is coloumn zero
							sal=rs.getInt(3);			 
							set=name +" "+age + " "+sal;		
							System.out.print(set + "\n\n");					// printing the result set
						}while(rs.next());

						rs.updateString("C_NAME","shyam");					// giving the information of new row
						rs.updateInt("C_age",40);			
						rs.updateDouble("C_SAL",4877);			
						rs.insertRow();								// inserting the new row
					}
					catch(SQLException error)
					{
						System.out.println("DATA DISPLAY ERROR" + error);
					}
				}
				st.close();							//to close the the statement
				Db.close();							// to close the connection
			}

			catch(SQLException e)							// Class to catch the SQL exception
			{									// which may be thrown by 2nd try due to 
				System.out.print("ERROR ! QUERY NOT EXECUTED "+e);		// the statement executeUpdate
			}	

								
		}
		catch(ClassNotFoundException e)							
		{										// Class to catch the SQL exception
			System.out.print("UNABLE TO LOAD JDBC-ODBC BRIDGE" + e);		//which may be thrown by 1st try due to 
			System.exit(1);								//the statement Class.forName
		}


		catch(SQLException e)
		{										//Class to catch the SQL exception
			System.out.print("UNABLE TO CONNECT" + e);				//which may be thrown by 1st try due to
			System.exit(2);								//the statement Driver.Manager
		}
	}
}
