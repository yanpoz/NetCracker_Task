package by.yanpo;

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
    private static String SQL;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {

            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            if (!con.isClosed())
                System.out.println("\n***Connection is OK***");

            Statement stat = con.createStatement();

            //String SQL;
            String table = request.getParameter("tabl");



            if (table.equals("users")) {
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                String age = request.getParameter("age");
                String email = request.getParameter("email");

                if (id.equals("0")) {
                    SQL = "insert into "+ table +" (name, age, email) values('"+
                            name+"', "+age+", '"+email+"');";
                } else {
                    SQL = "update "+ table +" set " +
                            "name = '"+ name +
                            "', age = "+ age +
                            ", email = '"+ email +
                            "' where id = "+ id +";";
                }
            } else

            if (table.equals("projects")){
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                String progress = request.getParameter("progress");
                String lider = request.getParameter("lider");

                if (id.equals("0")) {
                    SQL = "insert into "+ table +" (name, progress, team_lider) values('"+
                            name+"', "+progress+", '"+lider+"');";
                } else {
                    SQL = "update "+ table +" set " +
                            "name = '"+ name +
                            "', progress = "+ progress +
                            ", team_lider = '"+ lider +
                            "' where id = "+ id +";";
                }
            }
            System.out.println(SQL);
            stat.executeUpdate(SQL);

            con.close();

            response.setStatus(response.SC_MOVED_PERMANENTLY);
            response.setHeader("Location", "index.jsp");
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