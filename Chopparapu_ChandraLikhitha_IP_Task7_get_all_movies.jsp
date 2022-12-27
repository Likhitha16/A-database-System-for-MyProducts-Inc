<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
        <title>Movie Nights</title>
    </head>
    <body>
        <%@page import="jsp_azure_test1.DataHandler"%>
        <%@page import="java.sql.ResultSet"%>
        <%
            // We instantiate the data handler here, and get all the movies from the database
            final DataHandler handler = new DataHandler();
            final ResultSet movies = handler.getAllMovies();
        %>
        <!-- The table for displaying all the movie records -->
        <table cellspacing="2" cellpadding="2" border="1">
            <tr> <!-- The table headers row -->
              <td align="center">
                <h4>Employee_name</h4>
              </td>
              <td align="center">
                <h4>Employee_Address</h4>
              </td>
              <td align="center">
                <h4>Salary</h4>
              </td>
            </tr>
            <%
               while(movies.next()) { // For each movie_night record returned...
                   // Extract the attribute values for every row returned
                   final String employee_name = movies.getString("e_name");
                   final String employee_address = movies.getString("e_address");
                   final String  salary = movies.getString("salary");
                  
                   
                   out.println("<tr>"); // Start printing out the new table row
                   out.println( // Print each attribute value
                        "<td align=\"center\">" + employee_name +
                        "</td><td align=\"center\"> " + employee_address +
                        "</td><td align=\"center\"> " + salary +
                        "</td>");
                   out.println("</tr>");
               }
               %>
          </table>
    </body>
</html>
