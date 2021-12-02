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
class Func {
    private int id;
    private String nome_func, username, password;
    public Func(int id, String nome_func, String username, String password){
        this.id = id;
        this.nome_func = nome_func;
        this.username = username;
        this.password = password;
    }
    
    public int getId(){
        return id;
    }
    public String getNome_func(){
        return nome_func;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
}
