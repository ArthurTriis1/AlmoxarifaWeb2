/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.recife.edu.ifpe.model.classes;

public class ItemEntrada {
    
    private int codigo;
    private Produto produto;
    private int quantidade;
    private static int codeAcumulator = 0;

    public ItemEntrada(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.codigo = codeAcumulator++;
    }

    public ItemEntrada(int codigo, Produto produto, int quantidade) {
        this.codigo = codigo;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getJson(){
        return "{\"codigo\":"+this.getCodigo()+",\"nomeProduto\":\""+this.getProduto().getNome()+"\""
                + ",\"quantidade\":"+this.getQuantidade()+"}";
    }
    
    
}
