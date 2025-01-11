import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import artgallery.Validate;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class CusLogin extends HttpServlet {
		protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub

		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");

		String user = req.getParameter("email");
		String pass = req.getParameter("password");
		//pw.println(user);
		//pw.println(pass);

		if (Val.checkUser(user, pass)) {
			System.out.println("Successfully");
			RequestDispatcher rd = req.getRequestDispatcher("Main.html");
			rd.forward(req, res);
		}
		else {
			System.out.println("email or Password incorrect");
			RequestDispatcher rd = req.getRequestDispatcher("index.html");
			rd.forward(req, res);

		}
		pw.close();

	}

}




class Val {
	public static boolean checkUser(String user, String pass) {
		boolean st = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/work", "root", "0021");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Newreg");
			while (rs.next()) {
				if (rs.getString(2).equals(user) && rs.getString(4).equals(pass)) {
					st = true;
				} else {
					st = false;
				}
			}
			con.close();
		}

		catch (Exception e) {
			System.out.println(e);
		}
		return st;
	}
}
