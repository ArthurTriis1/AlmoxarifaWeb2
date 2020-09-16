<%@ page import="br.recife.edu.ifpe.model.classes.Produto" %>
<%@ page import="java.util.List" %>
<%@ page import="br.recife.edu.ifpe.model.repositorios.RepositorioProdutos" %>
<%@ page import="br.recife.edu.ifpe.model.repositorios.RepositorioFuncionario" %>
<%@ page import="br.recife.edu.ifpe.model.classes.Funcionario" %>
<%--
  Created by IntelliJ IDEA.
  User: aluno
  Date: 9/13/20
  Time: 4:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Produtos</title>
    <jsp:include page="/shared/head-imports.jsp"/>
</head>
<body onload="M.toast({html: ${requestScope.resgisterMsg} })">
<jsp:include page="/components/header.jsp"/>
<div class="container">
    <div class="row" style="margin: 30px 0;">
        <a
                class="waves-effect waves-light btn col s12 m4 offset-m4"
                href="employee-register.jsp">
            Cadastrar Funcionario
            <i class="material-icons right">add</i>
        </a>
    </div>


    <%
        List<Funcionario> funcionarios = RepositorioFuncionario.getCurrentInstance().readAll();
    %>

    <table class="centered highlight responsive-table">
        <thead>
        <tr>
            <th>Código</th>
            <th>Nome</th>
            <th>Departamento</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <% for (Funcionario funcionario: funcionarios ) { %>
        <tr>
            <td><%= funcionario.getCodigo()%></td>
            <td><%= funcionario.getNome()%></td>
            <td><%= funcionario.getDepartamento()%></td>
            <td>
                <button
                        onclick="deleteEmployee(<%= funcionario.getCodigo()%>)"
                        type="button"
                        class="waves-effect waves-teal btn-flat">
                    <i class="material-icons" style="color: crimson">delete</i>
                </button>
                <a
                        href="EmployeeServlet?method=edit&codigo=<%= funcionario.getCodigo()%>"
                        class="waves-effect waves-teal btn-flat"
                >
                    <i class="material-icons" style="color: cadetblue">create</i>
                </a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
<script>
    if ( window.history.replaceState ) {
        window.history.replaceState( null, null, window.location.href );
    }

    const deleteEmployee = async (id) => {
        const response = await axios.delete("EmployeeServlet?codigo=" + id);
        document.location.href = "EmployeeServlet";
    }
</script>
<jsp:include page="/shared/footer-js.jsp"/>
</body>
</html>
