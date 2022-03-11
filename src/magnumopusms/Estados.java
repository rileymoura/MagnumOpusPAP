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
class Estados {
    private int id;
    private String estado;
    public Estados(int id, String estado){
        this.id = id;
        this.estado = estado;

    }

    
    
    public int getId(){
        return id;
    }
    public String getEstado(){
        return estado;
    }
    
    
}
