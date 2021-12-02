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
public class Client {
    private int id, num_tel;
    private String nome_client, morada, cod_postal, localidade, cidade;
    public Client(int id, String nome_client, String morada, String cod_postal, String localidade, String cidade, int num_tel){
        this.id = id;
        this.nome_client = nome_client;
        this.morada = morada;
        this.cod_postal = cod_postal;
        this.localidade = localidade;
        this.cidade = cidade;
        this.num_tel = num_tel;
    }
    
    public int getId(){
        return id;
    }
    public String getNome_client(){
        return nome_client;
    }
    public String getMorada(){
        return morada;
    }
    public String getCod_postal(){
        return cod_postal;
    }
    public String getLocalidade(){
        return localidade;
    }
    public String getCidade(){
        return cidade;
    }
    public int getNum_tel(){
        return num_tel;
    }
}
