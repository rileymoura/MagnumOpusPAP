/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magnumopusms;

/**
 *
 * @author xdmah
 */
class Cat {
    private int id;
    private String nome_cat;
    public Cat(int id, String nome_cat){
        this.id = id;
        this.nome_cat = nome_cat;

    }

    
    
    public int getId(){
        return id;
    }
    public String getNome_cat(){
        return nome_cat;
    }
    
    
}
