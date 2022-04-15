import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class newservlet
 */
@WebServlet("/newservlet")
public class newservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String url = "jdbc:mysql://localhost:3306/db1";
	String username = "root";
	String password = "mysql";
    public newservlet() {
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	response.setContentType("text/html");
	PrintWriter out= response.getWriter();
	out.println("<h1>"+"Servlet Program"+"</h1>");
	try
	{
		// To connect mysql jdbc
		Class.forName("com.mysql.jdbc.Driver");	
		//Connection string
		Connection con = DriverManager.getConnection(url,username, password);
		out.println("Connection Successful!");
		
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		Statement st = con.createStatement();
		
		String submit = request.getParameter("SUBMIT");
		String delete = request.getParameter("DELETE");
		String update = request.getParameter("UPDATE");
		String select = request.getParameter("SELECT");
		if(submit==null){
			out.println("Record not inserted");
		}
			else if(submit.equals("Submit"))
			{	
				int i = st.executeUpdate("Insert into form values('"+fname+"','"+lname+"')");
				if(i>0){
					out.println("Record inserted successfully!");
				}
				else{
					out.println("Record not inserted");
				}
			}
			else if(delete.equals("Delete")){
				int i=st.executeUpdate("delete from form where name ='"+fname+"'");
				if(i>0){
					out.println("Record Deleted");
				}
				else{
					out.println("Error in deletion of record");
				}
			}
			else if(update.equals("Update")){
				int i=st.executeUpdate("UPDATE form SET fname ='"+fname+"' WHERE lname = '"+lname+"'");
				if(i>0){
					out.println("Record Updated");
				}
				else{
					out.println("Error in updation of record");
				}
			}
			else if(update.equals("Select")){
				ResultSet i =st.executeQuery("SELECT * FROM form SET fname ='"+fname+"' WHERE lname = '"+lname+"'");
				ResultSetMetaData meta = i.getMetaData();
				int colcount = meta.getColumnCount();
				while(i.next()){
					for(int col=1;col<=colcount;col++)
					{
						Object value = i.getObject(col);
						if(value!=null){
							out.println("\n");
							out.println(value.toString());
						}
					}
				}
			}
	}
	catch(Exception e){
		out.println(e);
	}	
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
