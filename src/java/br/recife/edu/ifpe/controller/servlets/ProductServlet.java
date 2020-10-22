package br.recife.edu.ifpe.controller.servlets;

import br.recife.edu.ifpe.model.classes.ItemEstoque;
import br.recife.edu.ifpe.model.classes.Produto;
import br.recife.edu.ifpe.model.repositorios.RepositorioEstoque;
import br.recife.edu.ifpe.model.repositorios.RepositorioProdutos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "ProductServlet", urlPatterns = "/ProductServlet")
public class ProductServlet extends HttpServlet {
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String method = request.getParameter("method");
        String name = request.getParameter("nome");
        String  category = request.getParameter("categoria");
        String  mark = request.getParameter("marca");
        String desc  = request.getParameter("descricao");
        Produto p = new Produto(name, mark, category, desc);
        RepositorioProdutos repository = RepositorioProdutos.getCurrentInstance();

        if(method.equals("register")){
            repository.create(p);
            ItemEstoque item = new ItemEstoque(p, 0);
            RepositorioEstoque.getCurrentInstance().read().addItem(item);
            request.setAttribute("registerMsg", "'Produto " + p.getNome() +" cadastrado com sucesso'");
            request.getRequestDispatcher("product-list.jsp").forward(request, response);
        }

        else if(method.equals("edit")){
            repository.update(p);
            ItemEstoque item = new ItemEstoque(p, 0);
            RepositorioEstoque.getCurrentInstance().read().addItem(item);
            request.setAttribute("registerMsg", "'Produto " + p.getNome() +" editado com sucesso'");
            request.getRequestDispatcher("product-list.jsp").forward(request, response);
        }


    }

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response) 
            throws javax.servlet.ServletException, IOException {

        String method = "";

        method = request.getParameter("method");

        if(method == null){
            request.getRequestDispatcher("product-list.jsp").forward(request, response);
        }

        else if(method.equals("register")){
            request.getRequestDispatcher("product-register.jsp").forward(request, response);
        }

        else if(method.equals("edit")){

            Integer cod;

            try{
                cod = Integer.parseInt(request.getParameter("codigo"));
            }catch (NumberFormatException err){
                cod = null;
            }

            if(cod == null){
                request.getRequestDispatcher("product-list.jsp").forward(request, response);
            }

            else{
                Produto p = RepositorioProdutos.getCurrentInstance().read(cod);
                request.setAttribute("selectedProduct", p);
                request.getRequestDispatcher("product-register.jsp").forward(request, response);
            }

        }




    }


    protected void doDelete(
            HttpServletRequest request,
            HttpServletResponse response){
        int cod = Integer.parseInt(request.getParameter("codigo"));
        Produto p = RepositorioProdutos.getCurrentInstance().read(cod);
        RepositorioProdutos.getCurrentInstance().delete(p);
    }
}
