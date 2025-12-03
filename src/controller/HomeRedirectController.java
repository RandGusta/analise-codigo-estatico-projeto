package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// A aspa vazia "" significa: "Atenda a raiz do site (localhost:8081/)"
@WebServlet("")
public class HomeRedirectController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Chegou na raiz? Manda pro index.html sem perguntar!
        response.sendRedirect("index.html");
    }
}