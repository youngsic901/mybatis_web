<%@ page import="java.util.ArrayList" %>
<%@ page import="pack.business.SangpumDto" %>
<%@ page import="java.sql.SQLException" %>
<%@ page
        contentType="text/html;charset=UTF-8"
        pageEncoding="UTF-8"
%>
<jsp:useBean id="processDao" class="pack.business.ProcessDao"/>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Title</title>
</head>
<body>
<h3>* 상품정보(MyBatis) *</h3>
<a href="ins.html">상품 추가</a>
<br>
<%
    try {
        ArrayList<SangpumDto> slist = (ArrayList<SangpumDto>)processDao.selectDataAll();
%>
<table border="1">
    <tr>
        <th>코드</th><th>품명</th><th>수량</th><th>단가</th>
    </tr>
    <c:forEach var="s" items="<%=slist%>">
        <tr>
            <td>${s.code}</td>
            <td>${s.sang}</td>
            <td>${s.su}</td>
            <td>${s.dan}</td>
        </tr>
    </c:forEach>
<%
    } catch (SQLException e) {
        System.out.println("list.jsp err" + e);
    }
%>
</table>
</body>
</html>
