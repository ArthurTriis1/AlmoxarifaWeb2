<%@ page import="br.recife.edu.ifpe.model.DTOs.ItemType" %>
<%@ taglib prefix="ifpe" uri="arthur.andrade.web2.customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title>Lista Lote de Saida</title>
    <jsp:include page="/shared/head-imports.jsp"/>
</head>
<body onload="invokeToast()">
<jsp:include page="/components/header.jsp"/>

<div class="container">


    <ifpe:loadItensTag itemType="${ItemType.LOTESAIDA}"></ifpe:loadItensTag>



    <h3>Historico de Lotes de Saída</h3>
    <table class="centered highlight responsive-table">
        <thead>
        <tr>
            <th>Data</th>
            <th>Código</th>
            <th>Quantidade Total</th>
            <th>Detalhes</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="lote" items="${loteSaida}">
            <tr>
                <td><fmt:formatDate value="${lote.getData()}" type="date"/></td>
                <td>${lote.getCodigo()}</td>
                <td>${lote.getQuantidadeTotal()}</td>
                <td>
                    <button
                            onclick="loadItens(${lote.getCodigo()})"
                            type="button"
                            class="waves-effect waves-teal btn-flat">
                        <i class="material-icons" style="color: #72dea8">description</i>
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

<!-- Modal Structure -->
<div id="modal1" class="modal">
    <div class="modal-content">
        <h4 id="lotName"></h4>
        <p id="lotDescription"></p>
        <table class="centered highlight responsive-table">
            <thead>
            <tr>
                <th>Código</th>
                <th>Nome</th>
                <th>Quantidade</th>
            </tr>
            </thead>
            <tbody id="bodyTableLot">
            </tbody>
        </table>
    </div>
    <div class="modal-footer">
        <a href="#!" class="modal-close waves-effect waves-green btn-flat">Fechar</a>
    </div>
</div>


<script>
    if ( window.history.replaceState ) {
        window.history.replaceState( null, null, window.location.href );
    }

    const invokeToast = () => {
        if('${sessionScope.inputRegisterMsg}'){
            M.toast({html: '${sessionScope.inputRegisterMsg}'})
        }
    }

    const loadItens = async (code) => {
        const { data: valueResponse } = await axios.get("ExitLotServlet?codigo="+code)
        const finalHtml = valueResponse.itens.reduce((html, lote) =>
            html + "<tr><td>" + lote.codigo + "</td><td>" + lote.nomeProduto + "</td><td>" + lote.quantidade + "</td></tr>", ""
        )
        document.getElementById("lotName").innerText = "Lote " + valueResponse.codigo;
        document.getElementById("lotDescription").innerText = "Descrição: " + valueResponse.descricao;
        document.getElementById("bodyTableLot").innerHTML = finalHtml;
        const elem = document.getElementById('modal1');
        const instance = M.Modal.init(elem, {dismissible: false});
        instance.open();
    }


    document.addEventListener('DOMContentLoaded', function() {
        var elems = document.querySelectorAll('.modal');
        var instances = M.Modal.init(elems, {
            opacity: 0.5
        });
    });



</script>
<jsp:include page="/shared/footer-js.jsp"/>
<% session.removeAttribute("inputExitMessage"); %>
</body>
</html>
