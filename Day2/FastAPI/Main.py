from fastapi import FastAPI 
from Routes import Router

app = FastAPI()
app.include_router(Router)