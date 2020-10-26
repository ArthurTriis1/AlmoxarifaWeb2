/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.recife.edu.ifpe.model.classes;

import br.recife.edu.ifpe.inter.Lote;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoteSaida implements Lote {
    
    private int codigo;
    private List<ItemSaida> itens;
    private Date data;

    private static int codeAcumulator = 0;
            
    /*
    * Neste campo ficarão armazenadas as informações de documentos
    */
    private String descricao;
    
    public LoteSaida(){
        this.itens = new ArrayList<>();
        this.data = new Date();
        this.codigo = codeAcumulator++;
    }



    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public List<ItemSaida> getItens() {
        return itens;
    }

    public void setItens(List<ItemSaida> itens) {
        this.itens = itens;
    }

    public Date getData() {
        return data;
    }

    @Override
    public String getJson() {

        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


        String responseJSON = "{"

                +"\"quantidadeTotal\":"+this.getQuantidadeTotal()+","
                +"\"data\":\""+DateFor.format(this.getData())+"\","
                +"\"entrada\":false,"
                +"\"codigo\":"+this.getCodigo()+","+
                "\"descricao\":\""+this.getDescricao()+
                "\",\"itens\":" +
                "[";

        for(ItemSaida item: this.getItens()){
            responseJSON += item.getJson();
            if(this.getItens().indexOf(item)!=this.getItens().size()-1){
                responseJSON += ",";
            }
        }

        responseJSON += "]}";

        return responseJSON;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public void addItem(ItemSaida i){
        this.itens.add(i);
    }

    public int getQuantidadeTotal(){
        int quant = 0;

        for(ItemSaida i: itens){
            quant += i.getQuantidade();
        }

        return quant;
    }

    @Override
    public int compareTo(Lote o) {
        return getData().compareTo(o.getData());
    }
}
