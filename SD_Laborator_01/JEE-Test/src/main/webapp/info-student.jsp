<%@ page import="beans.StudentBean" %>
<html xmlns:jsp="http://java.sun.com/JSP/Page">
<head>
    <title>Informatii student</title>
    <meta charset="UTF-8"/>
</head>
<body>
<h3>Informatii student</h3>

<!-- populare bean cu informatii din cererea HTTP -->
<jsp:useBean id="lista" class="beans.RootBean" scope="request"/>
<!-- folosirea bean-ului pentru afisarea informatiilor -->
<p>Urmatoarele informatii au fost introduse:</p>
<%
    if (lista == null) {
        out.print("Nu sunt studenti");
    } else {
        for (StudentBean bean : lista.getList()) { %>
<ul type="bullet">
    <li>Nume:
        <%= bean.getNume() %>
    </li>
    <li>Prenume:
        <%= bean.getPrenume() %>
    </li>
    <li>Varsta:
        <%= bean.getVarsta() %>
    </li>
    <li>Anul nasterii: <%= bean.getAnNastere() %>
    </li>
</ul>
<%
        }
    }
%>

</body>
</html>