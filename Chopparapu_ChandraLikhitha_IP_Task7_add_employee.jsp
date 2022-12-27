<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Query Result</title>
</head>
    <body>
    <%@page import="jsp_azure_test1.DataHandler"%>
    <%@page import="java.sql.ResultSet"%>
    <%@page import="java.sql.Array"%>
    <%
    // The handler is the one in charge of establishing the connection.
    DataHandler handler = new DataHandler();

    // Get the attribute values passed from the input form.
    String employee_name = request.getParameter("e_name");
    String employee_address = request.getParameter("e_address");
    String salary = request.getParameter("salary");
   

    /*
     * If the user hasn't filled out all the time, movie name and duration. This is very simple checking.
     */
    if (employee_name.equals("") || employee_address.equals("") || salary.equals("")) {
        response.sendRedirect("add_movie_form.jsp");
    } else {
        int e_salary = Integer.parseInt(salary);
        
        // Now perform the query with the data from the form.
        boolean success = handler.addMovie(employee_name, employee_address,e_salary);
        if (!success) { // Something went wrong
            %>
                <h2>There was a problem inserting the course</h2>
            <%
        } else { // Confirm success to the user
            %>
            <h2>Employee data:</h2>

            <ul>
                <li>Start Time: <%=employee_name%></li>
                <li>Movie Name: <%=employee_address%></li>
                <li>Duration: <%=e_salary%></li>
                
            </ul>

            <h2>Was successfully inserted.</h2>
            
            <a href="get_all_movies.jsp">See all Employees data.</a>
            <%
        }
    }
    %>
    </body>
</html>
