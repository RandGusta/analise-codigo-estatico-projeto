
package modelo;

import enums.TipoLinguagem;

public class ArquivoCodigo {
    private String nome;
    private TipoLinguagem tipoLinguagem;

    public ArquivoCodigo(String nome, TipoLinguagem tipoLinguagem){
        this.nome = nome;
        this.tipoLinguagem = tipoLinguagem;

    }

    private boolean verificarTipoCodigo(){
        if(this.tipoLinguagem == TipoLinguagem.JAVASCRIPT){
            System.out.println("Linguagem suportada");
            return true;
        } else {
            System.out.println("Linguagem não suportada");
            return false;
        }
    }

}
