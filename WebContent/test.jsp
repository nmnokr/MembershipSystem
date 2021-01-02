<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 

<sql:query var="rs" dataSource="jdbc/TestDB">
select musteriisim  from musteribilgileri
</sql:query>

<html>
  <head>
    <title>DB Test</title>
  </head>
  <body>

  <h2>Results</h2>

<c:forEach var="row" items="${rs.rows}">
    Foo ${row.musteriisim}<br/>
    
</c:forEach>

  </body>
</html>