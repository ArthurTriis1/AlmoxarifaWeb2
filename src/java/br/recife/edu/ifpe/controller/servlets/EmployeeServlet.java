package br.recife.edu.ifpe.controller.servlets;


import br.recife.edu.ifpe.model.classes.Funcionario;
import br.recife.edu.ifpe.model.repositorios.RepositorioFuncionario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EmployeeServlet", urlPatterns = "/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String method = request.getParameter("method");
        String name = request.getParameter("nome");
        String departament = request.getParameter("departamento");

        Funcionario f = new Funcionario(name, departament);
        RepositorioFuncionario repository = RepositorioFuncionario.getCurrentInstance();

        if(method.equals("register")){
            repository.create(f);
            request.setAttribute("resgisterMsg", "'Funcionario " + f.getNome() +" cadastrado com sucesso'");
            request.getRequestDispatcher("employee-list.jsp").forward(request, response);
        }

        else if(method.equals("edit")){
            int code = Integer.parseInt(request.getParameter("codigo"));
            f.setCodigo(code);
            repository.update(f);
            request.setAttribute("resgisterMsg", "'Funcionario " + f.getNome() +" editado com sucesso'");
            request.getRequestDispatcher("employee-list.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String method = "";

        method = request.getParameter("method");

        if(method == null){
            request.getRequestDispatcher("employee-list.jsp").forward(request, response);
        }

        else if(method.equals("register")){
            request.getRequestDispatcher("employee-register.jsp").forward(request, response);
        }

        else if(method.equals("edit")){

            Integer cod;

            try{
                cod = Integer.parseInt(request.getParameter("codigo"));
            }catch (NumberFormatException err){
                cod = null;
            }

            if(cod == null){
                request.getRequestDispatcher("employeee-list.jsp").forward(request, response);
            }

            else{
                Funcionario f = RepositorioFuncionario.getCurrentInstance().read(cod);
                request.setAttribute("selectedEmployee", f);
                request.getRequestDispatcher("employee-register.jsp").forward(request, response);
            }

        }
    }


    @Override
    protected void doDelete(
            HttpServletRequest request,
            HttpServletResponse response){
        int cod = Integer.parseInt(request.getParameter("codigo"));
        Funcionario f = RepositorioFuncionario.getCurrentInstance().read(cod);
        RepositorioFuncionario.getCurrentInstance().delete(f);
    }
}
