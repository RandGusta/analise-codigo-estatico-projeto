
import os
from flask import Flask, jsonify, request, send_from_directory, abort
from flask_cors import CORS
from werkzeug.utils import secure_filename
from pathlib import Path

BASE_DIR = Path(__file__).resolve().parent
FRONTEND_DIR = BASE_DIR.parent / "frontend"
UPLOADS_DIR = BASE_DIR / "uploads"

UPLOADS_DIR.mkdir(exist_ok=True)

app = Flask(__name__, static_folder=str(FRONTEND_DIR), static_url_path="/")
CORS(app)

# --- armazenamento simples em memória (substituir por DB depois) ---
# Cada projeto: { "id": int, "nome": str }
projects = []
next_project_id = 1

def find_project(pid):
    for p in projects:
        if p["id"] == pid:
            return p
    return None

# ---------- rotas do frontend estático ----------
# Serve index.html e outros arquivos do frontend (já funciona abrindo /)
@app.route("/")
def index():
    return app.send_static_file("index.html")

# rota para servir qualquer arquivo estático do frontend (css, js, html)
@app.route("/<path:filename>")
def frontend_files(filename):
    return send_from_directory(str(FRONTEND_DIR), filename)


# ---------- API simples ----------
@app.route("/api/projetos", methods=["GET"])
def api_list_projetos():
    return jsonify(projects)


@app.route("/api/projetos", methods=["POST"])
def api_create_projeto():
    global next_project_id
    data = request.get_json() or {}
    nome = data.get("nome", "").strip() or f"Projeto {next_project_id}"
    projeto = {"id": next_project_id, "nome": nome}
    projects.append(projeto)
    # criar pasta para arquivos do projeto
    proj_dir = UPLOADS_DIR / str(next_project_id)
    proj_dir.mkdir(parents=True, exist_ok=True)
    next_project_id += 1
    return jsonify(projeto), 201


@app.route("/api/projetos/<int:pid>/arquivos", methods=["GET"])
def api_list_arquivos(pid):
    proj = find_project(pid)
    if not proj:
        abort(404)
    proj_dir = UPLOADS_DIR / str(pid)
    proj_dir.mkdir(parents=True, exist_ok=True)
    files = []
    for f in proj_dir.iterdir():
        if f.is_file():
            files.append({"nome": f.name})
    return jsonify(files)


# Upload de múltiplos arquivos para um projeto (drag & drop / input file)
@app.route("/api/projetos/<int:pid>/upload", methods=["POST"])
def api_upload_arquivos(pid):
    proj = find_project(pid)
    if not proj:
        abort(404)

    proj_dir = UPLOADS_DIR / str(pid)
    proj_dir.mkdir(parents=True, exist_ok=True)

    # aceita 'arquivo' (single) e também múltiplos files em request.files.getlist("arquivos")
    uploaded = []
    if "arquivo" in request.files:
        f = request.files["arquivo"]
        filename = secure_filename(f.filename)
        if filename:
            dest = proj_dir / filename
            f.save(dest)
            uploaded.append(filename)

    # support multiple files
    for f in request.files.getlist("arquivos"):
        filename = secure_filename(f.filename)
        if filename:
            dest = proj_dir / filename
            f.save(dest)
            uploaded.append(filename)

    return jsonify({"uploaded": uploaded})


# endpoint para upload de "projeto" (poderia ser zip) - exemplo simples
@app.route("/api/uploadProjeto", methods=["POST"])
def api_upload_projeto():
    global next_project_id
    # se vier form-data com 'arquivo' -> salva e cria projeto novo
    if "arquivo" not in request.files:
        return jsonify({"error": "nenhum arquivo enviado"}), 400
    f = request.files["arquivo"]
    filename = secure_filename(f.filename)
    # cria novo projeto
    proj = {"id": next_project_id, "nome": filename}
    projects.append(proj)
    proj_dir = UPLOADS_DIR / str(next_project_id)
    proj_dir.mkdir(parents=True, exist_ok=True)
    # salva arquivo dentro da pasta do projeto
    if filename:
        f.save(proj_dir / filename)
    next_project_id += 1
    return jsonify(proj), 201


if __name__ == "__main__":
    # porta 5000 por padrão (mude se quiser)
    app.run(host="0.00.0.0", port=5000, debug=True)
