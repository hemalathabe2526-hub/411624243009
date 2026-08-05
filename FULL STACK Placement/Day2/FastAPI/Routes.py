from fastapi import APIRouter, Depends
from FastAPI.DataBase import get_db
from sqlalchemy.orm import Session
from FastAPI.Models import User

Router = APIRouter()

@Router.get("/users")
def get_data(db: Session = Depends(get_db)):
    users = db.query(User).all()
    return {"users": users}
    # return {"message": "Hello from the Router!"}

@Router.post("/users/post")
def post_data(id: int, db: Session = Depends(get_db), name: str = None, data: dict = None):
    user = User(id=id, name=name)
    db.add(user)
    db.commit()
    db.refresh(user)
    return {"message": "User created!", "user": user}
    # return {"message": "Data received!", "data": data + name}

@Router.put("/users/put")
def put_data(id: int, name: str, age: int, db: Session = Depends(get_db), data: dict = None):
    user = db.query(User).filter(User.id == id).first()
    if user is None:
        return {"message": "User not found!"}
    user.name = name
    db.commit()
    return {"message": "User updated!", "user": user}
    # return {"message": "Data updated!", "data": data + name + str(age)}


@Router.delete("/users/delete")
def delete_data(data: dict):
    return {"message": "Data deleted!", "data": data}

@Router.patch("/users/patch")
def patch_data(name: str, age: int, data: dict):
    return f"Patch Data: {name}, {age}, {data}"