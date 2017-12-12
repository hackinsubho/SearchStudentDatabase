package org.jspiders.searchApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/search")
public class SearchServlet extends GenericServlet {
	PrintWriter pw;
	PreparedStatement ps;
	Connection con;

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		pw = res.getWriter();
		String stream = req.getParameter("st");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "pba206");
			String qry = "select * from db.studentinfo where stream=?";
			ps = con.prepareStatement(qry);
			ps.setString(1, stream);
			ResultSet rs = ps.executeQuery();
			
				
				while (rs.next()) {
					
					String out="<html>" + 
							"<body>" + 
							"<table border=\"1px\">" + 
							"<tr padding=\"3px\">" + 
							"<td>"+rs.getInt(1)+"</td>" + 
							"<td>"+rs.getString(2)+"</td>" + 
							"<td>"+rs.getDouble(3)+"</td>" + 
							"</tr>" + 
							"</table>" + 
							"</body>" + 
							"</html>";
					pw.println(out);
				
				}
				
			
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

	}

}
