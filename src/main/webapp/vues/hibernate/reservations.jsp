<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>reservations</title>
<style>
table {
  width: 100%;
  border-collapse: collapse;
}
th, td {
  padding: 8px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}
</style>
</head>
<body>
    <h2>Liste des reservations</h2>
    <table>
        <tr>
            <th>Reserver par :</th>
            <th>Date et heure</th>
            <th>type installation</th>
            <th>Durée en heure</th>
            
        </tr>
        <c:forEach items="${requestScope.reservations}" var="re">
            <tr>
                <td><c:out value="${re[0]} ${re[1]} " /></td> 
                <td><c:out value="${re[2]}" /></td>
                <td><c:out value="${re[4]}" /></td>
                <td><c:out value="${re[3]} h" /></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>