package br.recife.edu.ifpe.customtags;

import br.recife.edu.ifpe.model.DTOs.ItemType;
import br.recife.edu.ifpe.model.classes.Funcionario;
import br.recife.edu.ifpe.model.classes.Produto;
import br.recife.edu.ifpe.model.repositorios.RepositorioFuncionario;
import br.recife.edu.ifpe.model.repositorios.RepositorioProdutos;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
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
        }
    }
}
