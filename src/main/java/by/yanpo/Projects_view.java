package by.yanpo;//package main;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class Projects_view extends HttpServlet {

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

        ServletOutputStream out = response.getOutputStream();

        try {

            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            if (!con.isClosed())
                System.out.println("\n***Connection is OK***");

            Statement stat = con.createStatement();

            String Select = "select projects.id, projects.name,  projects.progress, " +
            "users.name as Lider from projects join users on projects.team_lider = users.id;";
            ResultSet ResSet = stat.executeQuery(Select);

            out.println("<html>"+
                    "<head><title>Projects</title>" +
                    "<link rel='stylesheet' href='style.css'>"+
                    "</head>"+
                    "<body>"+
                    "<h1>list of Projects</h1>"+"\n");
            out.println(
               "<table>"+
               "<tr>"+
               "   <th>Name</th>"+
               "   <th>Progress</th>"+
               "   <th>Team lider</th>"+
               "   <th>Edit</th>"+
               "   <th>Delete</th>"+
               "</tr>");

            int id, prog;
            String name, lider;

            while (ResSet.next()){
                id = ResSet.getInt(1);
                name = ResSet.getString(2);
                prog = ResSet.getInt(3);
                lider = ResSet.getString(4);

                out.println(
                    "<tr>"+
                        "<td>"+name+"</td>"+
                        "<td>"+prog+"</td>"+
                        "<td>"+lider+"</td>"+
                        "<td>"+
                            "<a href='edit_proj.jsp?id="+
                            id+"&tabl=projects'>Edit</a>"+"</td>"+
                        "<td>"+
                            "<a href='delete.jsp?id="+
                            id+"&tabl=projects'>" +
                            "Delete</a>"+"</td>"+
                    "</tr>");
            }

            out.println(
                    "<tr><td  colspan='5'>"+
                        "<a href='edit_proj.jsp?id="+
                        0+"&tabl=projects'>" +
                        "Add New</a></td></tr>"+
                    "</table>"+
                    "</body>"+
                    "<html>");

            con.close();
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