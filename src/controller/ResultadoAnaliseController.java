package controller;

import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;

@WebServlet("/resultado-analise")
public class ResultadoAnaliseController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sessao = request.getSession();
        Object resultado = sessao.getAttribute("resultadoAnalise");

        response.setContentType("application/json");

        if (resultado == null) {
            response.getWriter().write("{\"erro\": \"Nenhum resultado encontrado\"}");
            return;
        }

        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(resultado));
    }
}
