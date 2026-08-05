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

    """post logic"""
    new_user = User(**user.dict())
    db.add(new_user)
    db.commit()
    db.close()
    return {"message": "Inserted Successfully"}

@app.get("/users")
def get_users():
    db = SessionLocal()

    """get logic"""
    users = db.query(User).all()
    db.close()
    return users