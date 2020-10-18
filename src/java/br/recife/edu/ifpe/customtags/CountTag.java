package br.recife.edu.ifpe.customtags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;

public class CountTag extends SimpleTagSupport {

    public List listCount;
    public String itemName;

    public void setListCount(List listCount) {
        this.listCount = listCount;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void doTag() throws JspException, IOException {
        super.doTag();
        getJspContext().getOut().write("<h4>Contagem de " + this.itemName + ": "+ this.listCount.size() + "</h4><br>");
    }
}
