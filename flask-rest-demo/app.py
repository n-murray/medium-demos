from flask import Flask, jsonify, request
from todoRepository import TodoRepository


app = Flask(__name__)
repo = TodoRepository()


@app.route('/', methods=['GET'])
def get_todos():
    todos = repo.get_all()
    response = jsonify(todos)
    response.status_code = 200
    return response


@app.route('/<string:todo_id>', methods=['GET'])
def get_todo(todo_id):
    todo = repo.get_id(todo_id)
    response = jsonify(todo)
    response.status_code = 200
    return response


@app.route('/', methods=['POST'])
def save_todo():
    todo = request.get_json()
    new_todo = repo.save(todo)
    response = jsonify(repo.get_id(new_todo))
    response.status_code = 201
    return response


@app.route('/', methods=['PUT'])
def update_todo():
    body = request.get_json()
    todo_id = body["_id"]
    updated = repo.update(body)
    if updated >= 1:
        response = jsonify(repo.get_id(todo_id))
        response.status_code = 200
    else:
        response = jsonify({"status_code": 404})
    return response


@app.route('/<string:todo_id>', methods=['DELETE'])
def delete_todo(todo_id):
    deleted = repo.delete(todo_id)
    if deleted >= 1:
        response = jsonify({"status_code": 200})
    else:
        response = jsonify({"status_code": 404})
    return response


if __name__ == '__main__':
    app.run()
