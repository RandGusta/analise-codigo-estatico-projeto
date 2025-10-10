package regra;
import modelo.ArquivoCodigo;
import modelo.Ocorrencia;

import java.util.List;
public interface RegraAnalise {

   List<Ocorrencia> aplicar(ArquivoCodigo arquivoCodigo);

}
