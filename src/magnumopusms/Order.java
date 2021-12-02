/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magnumopusms;

/**
 *
 * @author a100519
 */
public class Order {
    private int id, valor;
    private String nome_cliente, produtos, data, estado;
    public Order(int id, String nome_cliente, String produtos, int valor, String data, String estado){
        this.id = id;
        this.nome_cliente = nome_cliente;
        this.produtos = produtos;
        this.valor = valor;
        this.data = data;
        this.estado = estado;
    }
     public int getId(){
        return id;
    }
    public String getNome_cliente(){
        return nome_cliente;
    }
    public String getProdutos(){
        return produtos;
    }
    public int getValor(){
        return valor;
    }
    public String getData(){
        return data;
    }
    public String getEstado(){
        return estado;
    }
}
