<%@ page import="br.recife.edu.ifpe.model.DTOs.ItemType" %>
<%@ taglib prefix="ifpe" uri="arthur.andrade.web2.customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Estoque</title>
    <jsp:include page="/shared/head-imports.jsp"/>
</head>
<body onload="M.toast({html: ${requestScope.registerMsg} })">
<jsp:include page="/components/header.jsp"/>

<div class="container">

    <ifpe:loadItensTag itemType="${ItemType.ESTOQUE}"></ifpe:loadItensTag>

    <h3>Estoque</h3>

    <table class="centered highlight responsive-table">
        <thead>
        <tr>
            <th>Código</th>
            <th>Nome</th>
            <th>Marca</th>
            <th>Categoria</th>
            <th>Quantidade</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${estoque.getItens()}">
            <c:if test="${item.getQuantidade() > 0}">
                <tr>
                    <td>${item.getCodigo()}</td>
                    <td>${item.getProduto().getMarca()}</td>
                    <td>${item.getProduto().getNome()}</td>
                    <td>${item.getProduto().getCategoria()}</td>
                    <td>${item.getQuantidade()}</td>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="/shared/footer-js.jsp"/>
</body>
</html>
