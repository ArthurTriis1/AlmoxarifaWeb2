package br.recife.edu.ifpe.inter;

import java.util.Date;

public interface Lote extends Comparable<Lote> {
    public Date getData();
    public String getJson();
}
