package by.yanpo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete extends HttpServlet {

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

            String id = request.getParameter("id");
            String table = request.getParameter("tabl");
            System.out.println(table);

            if (!id.equals("0")) {

                SQL = "delete from "+table+
                        " where id = " + id + ";";
                System.out.println(SQL);

                stat.executeUpdate(SQL);
            }

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