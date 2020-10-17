<%@ page import="br.recife.edu.ifpe.model.classes.Produto" %>
<%@ page import="java.util.List" %>
<%@ page import="br.recife.edu.ifpe.model.repositorios.RepositorioProdutos" %>
<%@ taglib prefix="ifpe" uri="arthur.andrade.web2.customtags" %>
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
<ifpe:helloWorld/>
<div class="container">
    <div class="row" style="margin: 30px 0;">
        <a
                class="waves-effect waves-light btn col s12 m4 offset-m4"
                href="product-register.jsp">
            Cadastrar Produto
            <i class="material-icons right">add</i>
        </a>
    </div>

    <%
        List<Produto> produtos = RepositorioProdutos.getCurrentInstance().readAll();
    %>

    <table class="centered highlight responsive-table">
        <thead>
        <tr>
            <th>Código</th>
            <th>Nome</th>
            <th>Marca</th>
            <th>Categoria</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <% for (Produto produto: produtos ) { %>
        <tr>
            <td><%= produto.getCodigo()%></td>
            <td><%= produto.getNome()%></td>
            <td><%= produto.getMarca()%></td>
            <td><%= produto.getCategoria()%></td>
            <td>
                <button
                        onclick="deleteProduct(<%= produto.getCodigo()%>)"
                        type="button"
                        class="waves-effect waves-teal btn-flat">
                    <i class="material-icons" style="color: crimson">delete</i>
                </button>
                <a
                        href="ProductServlet?method=edit&codigo=<%= produto.getCodigo()%>"
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

    const deleteProduct = async (id) => {
        const response = await axios.delete("ProductServlet?codigo=" + id);
        document.location.href = "ProductServlet";
    }
</script>
<jsp:include page="/shared/footer-js.jsp"/>
</body>
</html>
