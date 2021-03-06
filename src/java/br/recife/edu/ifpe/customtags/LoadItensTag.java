package br.recife.edu.ifpe.customtags;

import br.recife.edu.ifpe.inter.Lote;
import br.recife.edu.ifpe.model.DTOs.ItemType;
import br.recife.edu.ifpe.model.classes.*;
import br.recife.edu.ifpe.model.repositorios.*;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoadItensTag extends SimpleTagSupport {

    ItemType itemType;

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    @Override
    public void doTag() throws JspException, IOException {
        super.doTag();

        switch (this.itemType){
            case FUNCIONARIO:
                List<Funcionario> funcionarios = RepositorioFuncionario.getCurrentInstance().readAll();
                getJspContext().setAttribute("funcionarios", funcionarios);
                break;

            case PRODUTO:
                List<Produto> produtos = RepositorioProdutos.getCurrentInstance().readAll();
                getJspContext().setAttribute("produtos", produtos, PageContext.PAGE_SCOPE);
                break;

            case LOTEENTRADA:
                List<LoteEntrada> loteEntrada = RepositorioLoteEntrada.getCurrentInstance().readAll();
                getJspContext().setAttribute("lotes", loteEntrada, PageContext.PAGE_SCOPE);
                break;

            case LOTESAIDA:
                List<LoteSaida> loteSaida = RepositorioLoteSaida.getCurrentInstance().readAll();
                getJspContext().setAttribute("loteSaida", loteSaida, PageContext.PAGE_SCOPE);
                break;

            case ESTOQUE:
                Estoque e = RepositorioEstoque.getCurrentInstance().read();
                getJspContext().setAttribute("estoque", e, PageContext.PAGE_SCOPE);
                break;

            case INVENTARIO:
                List<Lote> lots = new ArrayList<Lote>();
                lots.addAll(RepositorioLoteEntrada.getCurrentInstance().readAll());
                lots.addAll(RepositorioLoteSaida.getCurrentInstance().readAll());
                Collections.sort(lots);
                String responseJSON = "[";
                for(Lote item: lots){
                    responseJSON += item.getJson();
                    if(lots.indexOf(item)!=lots.size()-1){
                        responseJSON += ",";
                    }
                }
                responseJSON += "]";
                getJspContext().setAttribute("inventario", responseJSON, PageContext.PAGE_SCOPE);
                break;

        }
    }
}
