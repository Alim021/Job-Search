import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/submitApplication")
public class WebInsert extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extracting form data
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String jobRole = request.getParameter("job_role");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String pincode = request.getParameter("pincode");
        String date = request.getParameter("date");
        
        // Inserting form data into the database
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            // Establishing database connection (replace with your database details)
            String url = "jdbc:mysql://localhost:3306/work";
            String username = "root";
            String password = "0021";
            conn = DriverManager.getConnection(url, username, password);
            
            // Creating SQL query
            String sql = "INSERT INTO WebApp (fname, lname, Email, jobrole, address,city, pincode, date) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setString(4, jobRole);
            pstmt.setString(5, address);
            pstmt.setString(6, city);
            pstmt.setString(7, pincode);
            pstmt.setString(8, date);
            
            // Executing the query
            int rowsAffected = pstmt.executeUpdate();
            
            // Sending response to the client
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h2>Application submitted successfully!</h2>");
            out.println("<p>Rows affected: " + rowsAffected + "</p>");
            out.println("</body></html>");
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Sending error response to the client
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h2>Error occurred while submitting application!</h2>");
            out.println("</body></html>");
        } finally {
            // Closing database resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}