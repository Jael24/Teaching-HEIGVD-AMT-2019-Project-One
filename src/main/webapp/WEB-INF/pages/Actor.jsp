<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actor JSP Page</title>
    </head>
    <body>
        <h1>Actors</h1>
        <table>
            <c:forEach items="${actors}" var="actor">
                <tr>
                    <td>${actor.idActor}</td>
                    <td>${actor.fullName}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
