<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.tarun.persistent.BooksPersistent"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Books Details</title>
<style type="text/css">
.h {
text-align:center;
}
</style>
</head>
<body>
<h1 class="h">Available Books Details</h1>
<% 
List<BooksPersistent> bk = (List<BooksPersistent>)request.getAttribute("getList");
%>
<h3 class="alert alert-success">Total no of books <%= bk.size() %></h3>
<table class="table table-bordered">
<tr>
<td>Id</td>
<td>Name</td>
<td>Author</td>
<td>Publisher</td>
<td>Price</td>
</tr>
<%
for(BooksPersistent bks : bk) {
%>
<tr>
<td><%= bks.getId()%></td>
<td><%= bks.getName()%></td>
<td><%= bks.getAuthor()%></td>
<td><%= bks.getPublisher()%></td>
<td><%= bks.getPrice()%></td>
</tr>
<%
}
%>
</table>
<br>
<h4>
Insert More Books <a href="index.jsp">Click Here....</a>
</h4>
</body>
</html>