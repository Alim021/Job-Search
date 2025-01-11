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

@WebServlet("/RegisterServlet")
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String fullname = request.getParameter("fullname");
        String emailid = request.getParameter("email");
        String mobileno = request.getParameter("mobileno");
      
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (password.equals(confirmPassword)) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/work", "root", "0021");

                String query = "INSERT INTO Newreg (fullname,email ,mobilno ,password) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, fullname);
                ps.setString(2, emailid);
                ps.setString(3, mobileno);
                ps.setString(4, password);

                int i = ps.executeUpdate();
                if (i > 0) 
                {
                	System.out.println("Successfully");
        			RequestDispatcher rd = request.getRequestDispatcher("hm.html");
        			rd.forward(request, response);
        		}
        		else {
        			System.out.println("Username or Password incorrect");
        			RequestDispatcher rd = request.getRequestDispatcher("Register.html");
        			rd.forward(request, response);
                }
               
                con.close();
            } 
            catch (Exception e)
            {
                out.println(e);
            }
        } 
        else {
            out.print("Passwords do not match!");
        }

        
       }
    }
