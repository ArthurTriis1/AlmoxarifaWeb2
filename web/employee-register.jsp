<%@ page import="br.recife.edu.ifpe.model.classes.Funcionario" %><%--
  Created by IntelliJ IDEA.
  User: aluno
  Date: 9/12/20
  Time: 1:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Funcionario</title>
    <jsp:include page="/shared/head-imports.jsp"/>
</head>
<body>
<jsp:include page="/components/header.jsp"/>
<%
    Funcionario funcionario = (Funcionario) request.getAttribute("selectedEmployee");
%>
<div class="container">
    <h4>Cadastro de Produto</h4>
    <form method="post" class="row" action="EmployeeServlet">

        <div class="input-field col s6">
            <input
                    id="codigo"
                    type="number"
                    class="validate"
                    name="codigo"
                    disabled
                    value="<%= funcionario != null ? funcionario.getCodigo() : ""%>"
            />
            <label for="codigo">Código</label>
        </div>

        <div class="input-field col s6">
            <input id="nome" type="text" class="validate" name="nome"
                   value="<%= funcionario != null ? funcionario.getNome() : ""%>"
            >
            <label for="nome">Nome</label>
        </div>

        <div class="input-field col s12">
            <input id="categoria" type="text" class="validate" name="departamento"
                   value="<%= funcionario != null ? funcionario.getDepartamento() : ""%>"
            >
            <label for="categoria">Departamento</label>
        </div>

        <input type="hidden" name="method" value="<%= funcionario != null ? "edit" : "register"%>"/>
        <button
                class="waves-effect waves-light btn col s4 offset-s4"
                type="submit">
            <%= funcionario != null ? "Editar" : "Cadastrar"%>
            <i class="material-icons right">send</i>
        </button>
    </form>
</div>
<jsp:include page="/shared/footer-js.jsp"/>
</body>
</html>
