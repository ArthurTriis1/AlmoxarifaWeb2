package br.recife.edu.ifpe.model.DTOs;

public enum ItemType {

    PRODUTO(1),
    FUNCIONARIO(2),
    LOTEENTRADA(3),
    LOTESAIDA(4),
    ESTOQUE(5);

    private final int value;
    ItemType(int optionValue){
        value = optionValue;
    }
    public int getValor(){
        return value;
    }
}
