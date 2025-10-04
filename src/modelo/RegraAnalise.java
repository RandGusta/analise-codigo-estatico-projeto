package modelo;
import java.util.List;
public interface RegraAnalise {

    List<Ocorrencia> analisar(ArquivoCodigo arquivo);

}
    // pesquisar sobre Exoressão Regular
    // corrigir quando so tem uma aparição
//    public int verificarQuantidadeParametros(String arquivo){
//        int cont = 0;
//        if(arquivo.contains("function")){
//            System.out.println("encontrou FUNCTION");
//            int indiceFunction = arquivo.indexOf("function");
//            String posFunction = arquivo.substring(indiceFunction + "function".length()).trim();
//            int indiceInicioParametros = posFunction.indexOf("(");
//
//            if(indiceInicioParametros != -1){
//                String nomeFuncao = posFunction.substring(0, indiceInicioParametros).trim();
//                System.out.println("O nome da função: " + nomeFuncao);
//
//                int indiceFimParenteses = posFunction.indexOf(")");
//                if(indiceFimParenteses != -1) {
//
//                    String parametros = posFunction.substring(indiceInicioParametros + 1, indiceFimParenteses).trim();
//                    char[] parametrosChar = parametros.toCharArray(); // array de String, split por virgula
//                    for(int i=0; i<parametrosChar.length; i++){
//                        if(String.valueOf(parametrosChar[i]).equals(",")){
//                            cont++;
//                        } else {
//                            cont = -1;
//                        }
//
//                    }
//                }
//            }
//        }
//        return cont+1;
//
//    }
//
//}
