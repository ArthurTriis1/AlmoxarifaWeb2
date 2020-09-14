package br.recife.edu.ifpe.controller.servlets;

import br.recife.edu.ifpe.model.classes.ItemEstoque;
import br.recife.edu.ifpe.model.classes.Produto;
import br.recife.edu.ifpe.model.repositorios.RepositorioEstoque;
import br.recife.edu.ifpe.model.repositorios.RepositorioProdutos;
import org.jetbrains.annotations.NotNull;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "ProductServlet", urlPatterns = "/ProductServlet")
public class ProductServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        int cod = Integer.parseInt(request.getParameter("codigo"));
        String name = request.getParameter("nome");
        String  category = request.getParameter("categoria");
        String  mark = request.getParameter("marca");
        String desc  = request.getParameter("descricao");

        Produto p = new Produto(cod, name, mark, category, desc);
        RepositorioProdutos.getCurrentInstance().create(p);

        ItemEstoque item = new ItemEstoque(p, 0);
        RepositorioEstoque.getCurrentInstance().read().addItem(item);

        request.setAttribute("resgisterMsg", "'Produto " + p.getNome() +" cadastrado com sucesso'");
        request.getRequestDispatcher("product-list.jsp").forward(request, response);
    }

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response) 
            throws javax.servlet.ServletException, IOException {
        request.getRequestDispatcher("product-list.jsp").forward(request, response);
    }


    protected void doDelete(
            HttpServletRequest request,
            HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        int cod = Integer.parseInt(request.getParameter("codigo"));
        Produto p = RepositorioProdutos.getCurrentInstance().read(cod);
        RepositorioProdutos.getCurrentInstance().delete(p);
    }

}
