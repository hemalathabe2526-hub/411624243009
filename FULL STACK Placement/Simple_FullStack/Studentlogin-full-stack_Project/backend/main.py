from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware

from database import Base, engine, SessionLocal
from models import User
from schemas import UserCreate

Base.metadata.create_all(bind=engine)

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_methods=["*"],
    allow_headers=["*"],
)

"""post and get user"""
@app.post("/login")
def login(user: UserCreate):
    db = SessionLocal()
    new_user = User(username=user.username, password=user.password)
    
    """post logic"""
    
    db.add(new_user)
    db.commit()
    return {"message": "Inserted Successfully"}

@app.get("/users")
def get_users():
    db = SessionLocal()
    users = db.query(User).all()

    """get logic"""
    
    return users

"""update user"""

@app.put("/users/{user_id}")
def update_user(user_id: int, user: UserCreate):
    db = SessionLocal()
    existing_user = db.query(User).filter(User.id == user_id).first()
    if existing_user is None:
        return {"message": "User not found"}

    """update logic"""
    existing_user.username = user.username
    existing_user.password = user.password

    db.commit()

    return {"message": "User Updated Successfully"}

"""delete user"""

@app.delete("/users/{user_id}")
def delete_user(user_id: int):
    db = SessionLocal()

    existing_user = db.query(User).filter(User.id == user_id).first()
    if existing_user is None:
        return {"message": "User not found"}

    """delete logic"""
    db.delete(existing_user)
    db.commit()

    return {"message": "User Deleted Successfully"}