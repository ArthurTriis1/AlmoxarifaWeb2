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
<body onload="invokeToast()">
<jsp:include page="/components/header.jsp"/>

<div class="container">

    <ifpe:loadItensTag itemType="${ItemType.ESTOQUE}"></ifpe:loadItensTag>

    <h3>Adicionar Produtos ao Lote de <b>Saida</b></h3>

    <table class="centered highlight responsive-table">
        <thead>
        <tr>
            <th>Código</th>
            <th>Nome</th>
            <th>Marca</th>
            <th>Categoria</th>
            <th>Quantidade</th>
            <th>Adicionar</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${estoque.getItens()}">
            <c:if test="${item.getQuantidade() > 0}">
                <tr>
                    <td>${item.getProduto().getCodigo()}</td>
                    <td>${item.getProduto().getNome()}</td>
                    <td>${item.getProduto().getMarca()}</td>
                    <td>${item.getProduto().getCategoria()}</td>
                    <td>${item.getQuantidade()}</td>
                    <td>
                        <button
                                onclick="productInLot(true, ${item.getProduto().getCodigo()})"
                                type="button"
                                class="waves-effect waves-teal btn-flat">
                            <i class="material-icons" style="color: crimson">add</i>
                        </button>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>

    <c:if test="${exitLot != null}">
        <h3>Produtos no Lote</h3>
        <table class="centered highlight responsive-table">
            <thead>
            <tr>
                <th>Item Código</th>
                <th>Produto Código</th>
                <th>Nome</th>
                <th>Marca</th>
                <th>Categoria</th>
                <th>Quantidade</th>
                <th>Ações</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="i" items="${exitLot.itens}">
                <tr>
                    <td>${i.getCodigo()}</td>
                    <td>${i.produto.getCodigo()}</td>
                    <td>${i.produto.getNome()}</td>
                    <td>${i.produto.getMarca()}</td>
                    <td>${i.produto.getCategoria()}</td>
                    <td>${i.quantidade}</td>
                    <td>
                        <button
                                onclick="productInLot(true, ${i.produto.getCodigo()})"
                                type="button"
                                class="waves-effect waves-teal btn-flat">
                            <i class="material-icons" style="color: crimson">add</i>
                        </button>
                        <button
                                onclick="productInLot(false, ${i.produto.getCodigo()})"
                                type="button"
                                class="waves-effect waves-teal btn-flat">
                            <i class="material-icons" style="color: crimson">remove</i>
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="row" style="margin: 30px 0;">
            <button
                    class="waves-effect waves-light btn col s12 m4 offset-m4"
                    onclick="registerLot()">
                Cadastrar Lote
                <i class="material-icons right">add</i>
            </button>
        </div>
    </c:if>
</div>

<script>
    if ( window.history.replaceState ) {
        window.history.replaceState( null, null, window.location.href );
    }

    const productInLot = async (method, id) => {

        const operation = method ? 'add': 'remove'
        await axios.put('ExitLotServlet?method='+ operation + '&codigo=' + id);
        location.reload();
    }

    const registerLot = async () => {
        try{
            const response = await axios.post('ExitLotServlet');
            location.href = 'exit-lot-list.jsp';
        }catch (e) {
            console.log(e)
        }
    }

    const invokeToast = () => {
        if('${sessionScope.inputExitMessage}'){
            M.toast({html: '${sessionScope.inputExitMessage}'})
        }
    }
</script>
<jsp:include page="/shared/footer-js.jsp"/>
<% session.removeAttribute("inputExitMessage"); %>
</body>
</html>
