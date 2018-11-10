package by.yanpo;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class Edit extends HttpServlet {

    private static final String URL = "jdbc:mysql://localhost:3306/schem_devcolibri"+
            "?verifyServerCertificate=false"+
            "&useSSL=false"+
            "&requireSSL=false"+
            "&useLegacyDatetimeCode=false"+
            "&amp"+
            "&serverTimezone=UTC";

    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {


        try {

            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            if (!con.isClosed())
                System.out.println("\n***Connection is OK***");

            Statement stat = con.createStatement();

            int id, age;
            String name;
            String email;
            if (!request.getParameter("id").equals("0")) {

                id = Integer.parseInt(request.getParameter("id"));
                name = request.getParameter("name");
                age = Integer.parseInt(request.getParameter("age"));
                email = request.getParameter("email");

                String Update = "update users set " +
                        "name = '"+ name +
                        "', age = "+ age +
                        ", email = '"+ email +
                        "' where id = "+ id +";";
                stat.executeUpdate(Update);
            }

            con.close();

            response.setStatus(response.SC_MOVED_PERMANENTLY);
            response.setHeader("Location", "servlet?id=0");
        }
        catch (SQLException e) {
            System.err.println();
            System.err.println("Error code: "+e.getErrorCode());
            System.err.println("SQLState: "+e.getSQLState());
            e.printStackTrace();
        }
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doGet(request, response);
    }
}