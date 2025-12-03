<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Detalhes do Projeto</title>
    <link rel="stylesheet" href="estilo.css">
</head>
<body>

    <h1>Projeto: ${projetoDetalhe.nomeProjeto}</h1>

    <h3>Arquivos para análise:</h3>

   <form action="${pageContext.request.contextPath}/analisar-arquivos" method="post">

        <input type="hidden" name="idProjeto" value="${projetoDetalhe.id}">

        <table class="tabela-if">
            <tr>
                <th>Selecionar</th> <th>Nome do Arquivo</th>
                <th>Linguagem</th>
                <th>Ações</th>
            </tr>

            <c:forEach items="${projetoDetalhe.arquivos}" var="arq">
                <tr>
                    <td>
                        <input type="checkbox" name="arquivosSelecionados" value="${arq.id}">
                    </td>

                    <td>${arq.nome}</td>
                    <td>${arq.tipoLinguagem}</td>
                    <td>
                        <button type="button" class="btn-if">Ver Código</button>
                        <a href="deletar-arquivo?id=${arq.id}" class="btn-if btn-danger">Remover</a>
                    </td>
                </tr>
            </c:forEach>

            <c:if test="${empty projetoDetalhe.arquivos}">
                <tr><td colspan="4">Nenhum arquivo neste projeto.</td></tr>
            </c:if>
        </table>

        <br>

        <button type="submit" class="btn-if" style="font-size: 1.2em; padding: 10px 20px;">
             Analisar Selecionados
        </button>

    </form>
    <br><br>
    <a href="meus-projetos" class="link-if">Voltar para a Lista de Projetos</a>

</body>
</html>