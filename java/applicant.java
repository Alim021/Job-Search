import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AORDER")
public class applicant extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Setting up content type of the response
        response.setContentType("text/html");
        
        // Writing HTML response to the client
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>User Details</title></head><body>");
        out.println("<h2>User Details</h2>");

        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // Establishing database connection (replace with your database details)
            String url = "jdbc:mysql://localhost:3306/work";
            String username = "root";
            String password = "0021";
            conn = DriverManager.getConnection(url, username, password);
            
            // Creating SQL query
            String sql = "SELECT * FROM WebApp";
            pstmt = conn.prepareStatement(sql);
            
            // Executing the query
            rs = pstmt.executeQuery();
            
            // Displaying fetched data
            out.println("<table border=\"1\">");
            out.println("<tr><th>First Name</th><th>Last Name</th><th>Email</th><th>Job Role</th><th>Address</th><th>City</th><th>Pin Code</th><th>Date</th></tr>");
            while (rs.next()) {
                
                out.println("<tr><td>" + rs.getString(1)+ "</td><td>" + rs.getString(2) + "</td><td>" + rs.getString(3)+ "</td><td>" + rs.getString(4) + "</td><td>" + rs.getString(5)+ "</td><td>" + rs.getString(6) + "</td><td>" + rs.getString(7)+ "</td><td>" + rs.getString(8) + "</td></tr>");
            }
            out.println("</table>");
            
            
            
            out.println("</body></html>");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Closing database resources
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}