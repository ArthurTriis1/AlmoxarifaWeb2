package br.recife.edu.ifpe.controller.servlets;

import br.recife.edu.ifpe.model.classes.*;
import br.recife.edu.ifpe.model.repositorios.RepositorioEstoque;
import br.recife.edu.ifpe.model.repositorios.RepositorioLoteEntrada;
import br.recife.edu.ifpe.model.repositorios.RepositorioProdutos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "InputLotServlet", urlPatterns = "/InputLotServlet")
public class InputLotServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int codigo = Integer.parseInt(request.getParameter("codigo"));

        LoteEntrada loteEntrada = RepositorioLoteEntrada.getCurrentInstance().read(codigo);

        String responseJSON = "{"
                +"\"codigo\":"+loteEntrada.getCodigo()+","+
                "\"descricao\":\""+loteEntrada.getDescricao()+
                "\",\"itens\":" +
                "[";

        for(ItemEntrada item: loteEntrada.getItens()){
            responseJSON += "{\"codigo\":"+item.getCodigo()+",\"nomeProduto\":\""+item.getProduto().getNome()+"\""
                    + ",\"quantidade\":"+item.getQuantidade()+"}";
            if(loteEntrada.getItens().indexOf(item)!=loteEntrada.getItens().size()-1){
                responseJSON += ",";
            }
        }

        responseJSON += "]}";

        response.setContentType("text/plain");

        try(PrintWriter out = response.getWriter()){
            out.println(responseJSON);
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        LoteEntrada inputLot = (LoteEntrada) session.getAttribute("inputLot");
        session.removeAttribute("inputRegisterMsg");


        for(ItemEntrada i: inputLot.getItens()){
            if(i.getQuantidade() > 10 ){
                session.setAttribute("inputRegisterMsg", "Você não pode inserir mais de 10 " +
                        i.getProduto().getNome() + "s no lote, seu imbecil!");

                resp.sendError(403);
                return;
            }
        }

        Estoque estoque = RepositorioEstoque.getCurrentInstance().read();

        for(ItemEntrada i: inputLot.getItens()){
            for(ItemEstoque ie: estoque.getItens()){
                if(i.getProduto().getCodigo() == ie.getProduto().getCodigo()){
                    ie.addQuantidade(i.getQuantidade());
                    break;
                }
            }
        }

        RepositorioLoteEntrada.getCurrentInstance().create(inputLot);
        session.removeAttribute("inputLot");
        session.setAttribute("inputRegisterMsg", "Lote de entrada inserido com sucesso");
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int code = Integer.parseInt(request.getParameter("codigo"));
        String method = request.getParameter("method");

        HttpSession session = request.getSession();

        LoteEntrada inputLot = (LoteEntrada) session.getAttribute("inputLot");

        session.removeAttribute("inputRegisterMsg");

        if(inputLot == null){
            inputLot = new LoteEntrada();
            session.setAttribute("inputLot", inputLot);
        }

        if(method.equals("add")){
            boolean flagControl = false;

            for(ItemEntrada i: inputLot.getItens()){
                if(i.getProduto().getCodigo() == code){
                    i.setQuantidade(i.getQuantidade() + 1);
                    session.setAttribute("inputRegisterMsg", "O produto " + i.getProduto().getNome() + "foi inserido no lote");
                    flagControl = true;
                }
            }

            if(!flagControl){

                Produto p = RepositorioProdutos.getCurrentInstance().read(code);

                ItemEntrada item = new ItemEntrada(p, 1);

                inputLot.addItem(item);

                session.setAttribute("inputRegisterMsg", "O produto " + p.getNome() + "foi inserido no lote");
            }
        }else if(method.equals("remove")){
            for(ItemEntrada i: inputLot.getItens()){
                if(i.getProduto().getCodigo() == code){
                    if(i.getQuantidade() == 1){
                        inputLot.getItens().remove(i);
                        if(inputLot.getItens().size() <= 0){
                            session.removeAttribute("inputLot");
                        }
                        break;
                    }
                    i.setQuantidade(i.getQuantidade() - 1);
                    session.setAttribute("inputRegisterMsg", "O produto " + i.getProduto().getNome() + "foi removido do lote");
                    break;
                }
            }
        }


    }


}
