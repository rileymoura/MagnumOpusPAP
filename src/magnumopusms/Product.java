/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magnumopusms;

/**
 *
 * @author A100519
 */
class Product {
    private float iva, preco, preco_total;
    private int id, quant_disp;
    private String nome_produto, nome_categoria, nome_subcategoria;
    public Product(int id, String nome_produto, String nome_categoria, String nome_subcategoria, int quant_disp, float preco, float preco_total, float iva){
        this.id = id;
        this.nome_produto = nome_produto;
        this.nome_categoria = nome_categoria;
        this.nome_subcategoria = nome_subcategoria;
        this.quant_disp = quant_disp;
        this.preco = preco;
        this.preco_total = preco_total;
        this.iva = iva;
    }
    
    public int getId(){
        return id;
    }
    public String getNome_produto(){
        return nome_produto;
    }
    public String getNome_categoria(){
        return nome_categoria;
    }
    public String getNome_subcategoria(){
        return nome_subcategoria;
    }
    public int getQuant_disp(){
        return quant_disp;
    }
    public float getPreco(){
        return preco;
    }
    public float getPrecoTotal(){
        return preco_total;
    }
    public float getIVA(){
        return iva;
    }
}
