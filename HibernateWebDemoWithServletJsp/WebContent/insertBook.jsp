<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert Book</title>
<style type="text/css">
.h {
text-align:center;
}
</style>
</head>
<body>
<h1 class="h">Insert Book</h1>

<form action="InsertBook" method="post">
<fieldset>
<legend><h2>Enter Book Details....</h2></legend>
<table>
<tr>
<td><label for="one">Book Name ::</label></td>
<td><input type="text" id="one" name="bkname"/></td>
</tr>
<tr>
<td><label for="two">Book Author ::</label></td>
<td><input type="text" id="two" name="bkauthor"/></td>
</tr>
<tr>
<td><label for="three">Book Publisher ::</label></td>
<td><input type="text" id="three" name="bkpublisher"/></td>
</tr>
<tr>
<td><label for="four">Book Price ::</label></td>
<td><input type="text" id="four" name="bkprice"/></td>
</tr>
<tr><input type="submit" value="Save"/></tr>
</table>
</fieldset>
</form>
</body>
</html>