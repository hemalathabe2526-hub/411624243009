from urllib.parse import quote_plus
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker, declarative_base

# password = quote_plus("Hema_Latha@25726")
DATABASE_URL = "mysql+pymysql://root:Hema_Latha%4025726@localhost:3306/school_demoDB"
engine = create_engine(DATABASE_URL)

SessionLocal = sessionmaker(bind=engine)

Base = declarative_base()