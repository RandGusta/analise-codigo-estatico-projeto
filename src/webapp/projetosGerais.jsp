<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Meus Projetos</title>
    <link rel="stylesheet" href="estilo.css">
</head>
<body>

    <h1>Meus projetos!</h1>
    <h3>Usuário Logado: [${usuarioLogado.nome}]</h3>
    <table class="tabela-if" id="tabela-projetos">
        <tr>
            <th>N°</th>
            <th>Nome do projeto</th>
            <th>Arquivos</th>
            <th>Ação</th>
        </tr>

        <c:forEach items="${listaProjetos}" var="p" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td>${p.nomeProjeto}</td>
                <td>${p.arquivos.size()} un.</td>
                <td>
                    <a href="projeto-especifico?id=${p.id}" class="btn-if">Editar</a>
                   <a href="deletar-projeto?id=${p.id}" class="btn-if btn-danger"
                          onclick="return confirm('Tem certeza que deseja apagar este projeto?');">
                          Deletar
                       </a>
                </td>
            </tr>
        </c:forEach>

        <c:if test="${empty listaProjetos}">
            <tr><td colspan="4">Nenhum projeto encontrado.</td></tr>
        </c:if>
    </table>

    <br>
    <a href="home.html" class="link-if">Voltar para página inicial</a>
    <br><br>

    <div style="border: 1px solid #ccc; padding: 20px; border-radius: 8px; max-width: 600px;">
        <h3>Adicionar Novo Projeto</h3>

        <form id="form-novo-projeto" action="${pageContext.request.contextPath}/upload-projeto" method="post" enctype="multipart/form-data">

            <div style="margin-bottom: 15px;">
                <label for="campo-nome"><strong>Nome do Projeto:</strong></label><br>
                <input type="text" id="campo-nome" name="nomeProjeto" required placeholder="Digite um nome..." style="padding: 5px; width: 100%;">
            </div>

            <div style="margin-bottom: 15px;">
                <label for="campo-files"><strong>Pasta do Código Fonte:</strong></label><br>
                <input type="file" id="campo-files" name="files" webkitdirectory directory multiple required>
            </div>

            <button type="submit" class="btn-if">Salvar Projeto</button>

        </form>
    </div>

    </body>
</html>