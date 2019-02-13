
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;


public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String url="jdbc:mysql://localhost:3306/servlet";
	String username="root";
	String password="mysql";

    /**
     * Default constructor. 
     */
    public Servlet() {
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h1>Hurr aayyy</h1>");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try {
			String user=request.getParameter("username");
			String pwd = request.getParameter("password");
			getClass().forName("com.mysql.jdbc.Driver");
			java.sql.Connection conn = DriverManager.getConnection(url,username,password);
			out.println("connected successful");
			java.sql.Statement st = conn.createStatement();
			String querry = "update Form set Password=? where Username=?";
			String sel = "select *from Form;";
			
			PreparedStatement pst = (PreparedStatement) conn.prepareStatement(querry);
			
			ResultSet rs = pst.executeQuery(sel);
			
			String input=request.getParameter("action");
			pst.setString(1,user);
			pst.setString(2, pwd);
			if(input.equals("insert")) {
			int i=st.executeUpdate("insert into Form values('"+user+"','"+pwd+"')");
			
			if(i>0) {
				out.println("Inserted successfl");
			}
			else
				out.println("Not inserted");
			}
			if(input.equals("delete")) {
				int i=st.executeUpdate("delete from Form where username ='"+user+"'");
				if(i>0)
					out.println("Deleted");
				else
					out.println("not deleted");
			}
			if(input.equals("update")) {
				int i=pst.executeUpdate();
				if(i>0)
					out.println("Updated");
				else
					out.println("Not updated");
			}
			if(input.equals("select")) {
				while(rs.next()) {
					String name = rs.getString("Username");
					out.println(name);
				}
			}
		}catch(Exception e) {
			out.println(e);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServxletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

