package regra.javaScript;

import modelo.ArquivoCodigo;
import modelo.Ocorrencia;
import regra.RegraAnalise;

import java.util.List;

public abstract class RegraAnaliseJS implements RegraAnalise {
    @Override
    public abstract List<Ocorrencia> aplicar(ArquivoCodigo arquivoCodigo);
}
