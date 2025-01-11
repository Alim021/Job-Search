import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Detail")
public class Detail extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String fullname = request.getParameter("fullname");
        String exprince = request.getParameter("exprince");
        
        
        String mobileno = request.getParameter("mobileno");
        String location = request.getParameter("location");
        String email = request.getParameter("email");

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/work", "root", "0021");

                String query = "INSERT INTO basic_details (username,exprince,location,emailid ,mobileno) VALUES (?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, fullname);
                ps.setString(2, exprince);
                ps.setString(3, location);
                ps.setString(4, email);
                ps.setString(5,mobileno);

                int i = ps.executeUpdate();
                if (i > 0) 
                {
                	System.out.println("Successfully");
        			RequestDispatcher rd = request.getRequestDispatcher("Main.html");
        			rd.forward(request, response);
        		}
        		
               // {
               //     out.print("Registration successful!");
               // } 
               // else 
               // {
               //     out.print("Registration failed!");
               // }

                con.close();
            } catch (Exception e) {
                out.println(e);
            }
       
        out.close();
    }
}