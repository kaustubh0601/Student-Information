import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/Student")
public class Student extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Student() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String roll_no = request.getParameter("roll_no");
        String division = request.getParameter("division");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        
        

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBMS", "root", "Pass@123");

            // Use explicit column names in the INSERT statement
            PreparedStatement ps = con.prepareStatement("INSERT INTO Student_info (name,roll_no, division, phone, email, address) VALUES (?, ?, ?, ?, ?, ?)");

            ps.setString(1, name);
            ps.setString(2, roll_no);
            ps.setString(3, division);
            ps.setString(4, address);
            ps.setString(5, phone);
            ps.setString(6, email);
            
            int i = ps.executeUpdate();

            if (i > 0) {
                out.print("You are successfully registered.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}