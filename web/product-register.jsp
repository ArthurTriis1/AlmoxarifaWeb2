<%@ page import="br.recife.edu.ifpe.model.classes.Produto" %><%--
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
    <title>Cadastro de Produto</title>
    <jsp:include page="/shared/head-imports.jsp"/>
</head>
<body>
<jsp:include page="/components/header.jsp"/>
<%
    Produto produto = (Produto) request.getAttribute("selectedProduct");
%>
<div class="container">
    <h4>Cadastro de Produto</h4>
    <form method="post" class="row" action="ProductServlet">
        <div class="input-field col s6">
            <input
                    id="codigo"
                    type="number"
                    class="validate"
                    name="codigo"
                    readonly="readonly"
                    value="<%= produto != null ? produto.getCodigo() : ""%>"
            />
            <label for="codigo">Código</label>
        </div>
        <div class="input-field col s6">
            <input id="nome" type="text" class="validate" name="nome"
                   value="<%= produto != null ? produto.getNome() : ""%>"
            >
            <label for="nome">Nome</label>
        </div>
        <div class="input-field col s6">
            <input id="categoria" type="text" class="validate" name="categoria"
                   value="<%= produto != null ? produto.getCategoria() : ""%>"
            >
            <label for="categoria">Categoria</label>
        </div>
        <div class="input-field col s6">
            <input id="marca" type="text" class="validate" name="marca"
                   value="<%= produto != null ? produto.getMarca() : ""%>"
            >
            <label for="marca">Marca</label>
        </div>
        <div class="input-field col s12">
            <textarea class="materialize-textarea" name="descricao"><%= produto != null ? produto.getDescricao() : ""%></textarea>
            <label for="marca">Descrição</label>
        </div>
        <input type="hidden" name="method" value="<%= produto != null ? "edit" : "register"%>"/>
        <button
                class="waves-effect waves-light btn col s4 offset-s4"
                type="submit">
            <%= produto != null ? "Editar" : "Cadastrar"%>
            <i class="material-icons right">send</i>
        </button>
    </form>
</div>
<jsp:include page="/shared/footer-js.jsp"/>
</body>
</html>
