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
public class SubCat {
    private int id, cod_cat;
    private String nome_subcat;
    public SubCat(int id, int cod_cat, String nome_subcat){
        this.id = id;
        this.cod_cat = cod_cat;
        this.nome_subcat = nome_subcat;
    }

    public int getId(){
        return id;
    }
    public int getCod_cat(){
        return cod_cat;
    }
    public String getNome_subcat(){
        return nome_subcat;
    }
    
}