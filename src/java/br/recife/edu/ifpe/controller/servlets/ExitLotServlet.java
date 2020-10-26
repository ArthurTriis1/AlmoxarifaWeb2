package br.recife.edu.ifpe.controller.servlets;

import br.recife.edu.ifpe.model.classes.*;
import br.recife.edu.ifpe.model.repositorios.RepositorioEstoque;
import br.recife.edu.ifpe.model.repositorios.RepositorioLoteEntrada;
import br.recife.edu.ifpe.model.repositorios.RepositorioLoteSaida;
import br.recife.edu.ifpe.model.repositorios.RepositorioProdutos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ExitLotServlet", urlPatterns = "/ExitLotServlet")
public class ExitLotServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        LoteSaida exitInput = (LoteSaida) session.getAttribute("exitLot");
        session.removeAttribute("inputExitMessage");

        Estoque estoque = RepositorioEstoque.getCurrentInstance().read();

        for(ItemSaida i: exitInput.getItens()){
            for(ItemEstoque ie: estoque.getItens()){
                if(i.getProduto().getCodigo() == ie.getProduto().getCodigo()){
                    ie.removeQuantidade(i.getQuantidade());
                    break;
                }
            }
        }

        RepositorioLoteSaida.getCurrentInstance().create(exitInput);
        session.removeAttribute("exitLot");
        session.setAttribute("inputExitMessage", "Lote de saida removido com sucesso");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codigo = Integer.parseInt(request.getParameter("codigo"));

        LoteSaida loteSaida= RepositorioLoteSaida.getCurrentInstance().read(codigo);

        String responseJSON = loteSaida.getJson();

        response.setContentType("text/plain");

        try(PrintWriter out = response.getWriter()){
            out.println(responseJSON);
        }
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int code = Integer.parseInt(req.getParameter("codigo"));
        String method = req.getParameter("method");

        HttpSession session = req.getSession();

        LoteSaida exitLot = (LoteSaida) session.getAttribute("exitLot");

        session.removeAttribute("inputExitMessage");

        RepositorioEstoque re = RepositorioEstoque.getCurrentInstance();


        if(exitLot == null){
            exitLot = new LoteSaida();
            session.setAttribute("exitLot", exitLot);
        }

        if(method.equals("add")){
            boolean flagControl = false;

            for(ItemSaida i: exitLot.getItens()){
                if(i.getProduto().getCodigo() == code){
                    if(i.getQuantidade() >= re.read(i.getProduto().getCodigo()).getQuantidade() ){
                        session.setAttribute("inputExitMessage", "Você não pode tirar mais do que você tem" +
                                ", seu imbecil!");
                        return;
                    }else{
                        i.setQuantidade(i.getQuantidade() + 1);
                        session.setAttribute("inputExitMessage", "O produto " + i.getProduto().getNome() + "foi inserido no lote");
                        flagControl = true;
                    }
                }
            }

            if(!flagControl){

                Produto p = RepositorioProdutos.getCurrentInstance().read(code);

                ItemSaida item = new ItemSaida(p, 1);

                exitLot.addItem(item);

                session.setAttribute("inputExitMessage", "O produto " + p.getNome() + "foi inserido no lote");
            }
        }else if(method.equals("remove")){
            for(ItemSaida i: exitLot.getItens()){
                if(i.getProduto().getCodigo() == code){
                    if(i.getQuantidade() == 1){
                        exitLot.getItens().remove(i);
                        if(exitLot.getItens().size() <= 0){
                            session.removeAttribute("exitLot");
                        }
                        break;
                    }
                    i.setQuantidade(i.getQuantidade() - 1);
                    session.setAttribute("inputExitMessage", "O produto " + i.getProduto().getNome() + "foi removido do lote");
                    break;
                }
            }
        }
    }
}
