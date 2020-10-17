package br.recife.edu.ifpe.customtags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class HelloTag extends SimpleTagSupport {

    public void doTag() throws JspException, IOException {
        super.doTag();
        getJspContext().getOut().write("<h1>Helloooooo</h1");
    }
}
