<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Relatório de Análise</title>
    <link rel="stylesheet" href="estilo.css">
</head>
<body>

    <h1>Relatório de Ocorrências</h1>

    <div style="margin-bottom: 20px;">
        <a href="projeto-especifico?id=${idProjeto}" class="link-if">
            &larr; Voltar para o Projeto
        </a>
    </div>

    <table class="tabela-if">
        <thead>
            <tr>
                <th>Arquivo</th>
                <th>Linha</th>
                <th>Tipo do Problema</th>
                <th>Descrição</th>
                <th>Função (Opcional)</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listaOcorrencias}" var="oc">
                <tr>
                    <td>${oc.nomeArquivo.nome}</td>

                    <td style="text-align: center; font-weight: bold;">${oc.linha}</td>

                    <td style="color: #aa0000;">${oc.tipoProblema}</td>

                    <td>${oc.descricao}</td>

                    <td>${oc.nomeFuncao != null ? oc.nomeFuncao : '-'}</td>
                </tr>
            </c:forEach>

            <c:if test="${empty listaOcorrencias}">
                <tr>
                    <td colspan="5" style="text-align: center; padding: 20px; color: green;">
                        <h3>Parabéns! Nenhum problema encontrado. ✅</h3>
                        <p>Seu código está seguindo as boas práticas configuradas.</p>
                    </td>
                </tr>
            </c:if>
        </tbody>
    </table>

</body>
</html>