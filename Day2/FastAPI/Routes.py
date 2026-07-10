from fastapi import APIRouter

Router = APIRouter()

@Router.get("/users")
def get_data():
    return {"message": "Hello from the Router!"}

@Router.post("/users/post")
def post_data(name: str, data: dict):
    return {"message": "Data received!", "data": data + name}

@Router.put("/users/put")
def put_data(name: str, age: int, data: dict):
    return {"message": "Data updated!", "data": data + name + str(age)}


@Router.delete("/users/delete")
def delete_data(data: dict):
    return {"message": "Data deleted!", "data": data}

@Router.patch("/users/patch")
def patch_data(name: str, age: int, data: dict):
    return f"Patch Data: {name}, {age}, {data}"