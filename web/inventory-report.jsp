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
    <title>Historico de lote</title>
    <jsp:include page="/shared/head-imports.jsp"/>
</head>
<body onload="invokeToast()">
<jsp:include page="/components/header.jsp"/>

<div class="container">


    <ifpe:loadItensTag itemType="${ItemType.INVENTARIO}"></ifpe:loadItensTag>



    <h3>Historico de Lotes de Entrada</h3>
    <ul class="collapsible" id="listLots">

    </ul>

</div>
<script>



    const inventario = ${inventario};
    const finalHtml = inventario.reduce((html, lote) =>
       html +=
           "<li>" +
                "<div class=\"collapsible-header\">" +
                    "<i class=\"material-icons\">inbox</i> Lote " +
                        lote.codigo + " de " +
                        (lote.entrada ? "entrada" : "saida") +
                        " | Data: " +
                        lote.data +
                    "<span class=\"badge\">" +
                    lote.quantidadeTotal + " itens " +
                    "</span></div>" +
            "    <div class=\"collapsible-body\">" +
                   `<table class="centered highlight responsive-table">
                        <thead>
                            <tr>
                                <th>Código</th>
                                <th>Nome</th>
                                <th>Quantidade</th>
                            </tr>
                        </thead>
                        <tbody id="bodyTableLot">` +
                            lote.itens.reduce((html, lote) =>
                                html += "<tr><td>" + lote.codigo + "</td><td>" + lote.nomeProduto + "</td><td>" + lote.quantidade + "</td></tr>", ""
                            ) +
                        `</tbody>
                    </table>
                </div>
            </li>`
    , "");

    document.getElementById("listLots").innerHTML = finalHtml;



    document.addEventListener('DOMContentLoaded', function() {
        var elems = document.querySelectorAll('.collapsible');
        var instances = M.Collapsible.init(elems, {
            accordion: false
        });
    });
</script>



<jsp:include page="/shared/footer-js.jsp"/>
</body>
</html>
