<%--
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
        <div class="container">
                <h4>Cadastro de Produto</h4>
                <form method="post" class="row" action="ProductServlet">
                    <div class="input-field col s6">
                        <input id="codigo" type="number" class="validate" name="codigo"/>
                        <label for="codigo">Código</label>
                    </div>
                    <div class="input-field col s6">
                        <input id="nome" type="text" class="validate" name="nome">
                        <label for="nome">Nome</label>
                    </div>
                    <div class="input-field col s6">
                        <input id="categoria" type="text" class="validate" name="categoria">
                        <label for="categoria">Categoria</label>
                    </div>
                    <div class="input-field col s6">
                        <input id="marca" type="text" class="validate" name="marca">
                        <label for="marca">Marca</label>
                    </div>
                    <div class="input-field col s12">
                        <textarea class="materialize-textarea" name="descricao"></textarea>
                        <label for="marca">Descrição</label>
                    </div>
                    <button
                        class="waves-effect waves-light btn col s4 offset-s4"
                        type="submit">
                        Cadastrar
                        <i class="material-icons right">send</i>
                    </button>
                </form>
        </div>
    </body>
</html>
