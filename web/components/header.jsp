<%--
  Created by IntelliJ IDEA.
  User: aluno
  Date: 9/13/20
  Time: 2:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="nav-extended">
    <div class="nav-wrapper">
        <a href="/" class="brand-logo">Almoxarifado</a>
        <a href="#" data-target="mobile-demo" class="sidenav-trigger"><i class="material-icons">menu</i></a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a href="ProductServlet">Produtos</a></li>
            <li><a href="EmployeeServlet">Funcionarios</a></li>
            <li><a href="input-lot-register.jsp">Registro Lote</a></li>
        </ul>
    </div>
</nav>

<ul class="sidenav" id="mobile-demo">
    <li><a href="ProductServlet">Produtos</a></li>
    <li><a href="EmployeeServlet">Funcionarios</a></li>
</ul>
