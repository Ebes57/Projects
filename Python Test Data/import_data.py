# -*- coding: utf-8 -*-
"""
Created on Fri Sep 20 16:43:50 2024

@author: Connor Ebert

    *****INSTALL BEFORE YOU RUN THE PROGRAM*****
    pip install mysql
    pip install mysql-connector-python
    pip install tqdm
    pip install pandas
"""

import mysql.connector
import pandas as pd
from tqdm import tqdm
import decimal
import random
import dbre


#How many entries will be in the database
entries = 100

#login to your database
mydb = mysql.connector.connect(
    host="localhost",
    user="root",
    password="1234567890"
    )

#Cursor is what executes the sql code
mycursor = mydb.cursor()


#Gets insert commands from list to make it easier to read
def getSQLCommands():
    sqlInsertList = []
    sql = open("sql_insert_test.txt", "r")
    for x in sql:
        sqlInsertList.append(x)
    return sqlInsertList

#Function to be used for inserting items into db
def insertSQL(sql, val):
    mycursor.execute(sql, val)
    
#Function to run sql that doesn't need values
def runSQL(sql):
    mycursor.execute(sql)



#Drops Schema
def dropDB():
    sql = "DROP SCHEMA sag_db;"
    runSQL(sql)
        
#Creates Schema
def createDatabase():
    sql = open("test_db.txt", "r")
    sqlList = ""
    for x in sql:
        sqlList += x
    formatSQLList = []
    sqlList = sqlList.split(";")
    #print(sqlList)
    for x in sqlList:
        if(x == "NULL" or x ==""):
            continue
        else:
            x = " " + x + ";"
            formatSQLList.append(x.replace("\n"," "))
    for x in formatSQLList:
        runSQL(x)

#insert sub into db
def insertSub(sql):
    subList = dbre.getSubmarines()
    for i in subList:
        if(pd.isnull(i[3])):
            i[3] = "Null"
        val = (
            i[2],
            i[3]
            )
        insertSQL(sql, val)
        

def insertRandomHealthIssueHippa(sql):
    x = random.randint(0, 72)
    year = random.randint(1940, 2000)
    val =(
        dbre.getCategories(x),
        dbre.getHIName(x),
        dbre.getRandDate(),
        year,
        dbre.boolAnswer(),
        dbre.getClaimStatus(),
        dbre.boolAnswer(),
        decimal.Decimal(random.randrange(1,100))/100
        )
    
            
    insertSQL(sql, val)
        

#Creates random personal Data Entries
def insertPersonalData(sql):
    x = random.randint(0, 2327)
    val = (
        dbre.getRandomFirstName(x),
        dbre.getRandomCharacter(),
        dbre.getRandomLastName(x),
        dbre.getRandDate(), 
        dbre.getRandGender(), 
        dbre.getRandSpouse()
           )
    
            
    insertSQL(sql, val)
        
    
def insertUSNRates(sql):
    val = (
        dbre.getRandomMultiCharacters(),
        dbre.getRandomMultiCharacters()
           )
    
            
    insertSQL(sql, val)
        
    
#Creates random member DoD information
def insertMemberDoD(sql):
    x = random.randint(0, 4)
    val = (
        dbre.getRandomDoDDept(x),
        dbre.getRandomDodName(x),
        dbre.getRandomCharacter()
        )
    
            
    insertSQL(sql, val)
        

#Add memberboat 
def insertMemberBoats(sql):
    x = random.randint(0,720)
    val = (
        dbre.getRandHull(x),
        dbre.getRandDate(),
        dbre.getRandDate()
        ) 
    
    
    insertSQL(sql, val)
        
    
        
def insertAccidents(sql):
    x = random.randint(0, 72)
    randHull = random.randint(0,720)
    val = (
        dbre.getRandHull(randHull),
        dbre.getCategories(x),
        dbre.getRandomMultiCharacters(),
        dbre.getRandDate()
        
        )
    
            
    insertSQL(sql, val)
        
    
def insertHealthIssuesCat(sql):
    for i in range(72):
        val = (
            dbre.getCategories(i),
            dbre.getIssueDiag(i)
            )
        
               
        insertSQL(sql, val)
            

def insertPhoneNumber(sql):
    val = (
        dbre.getPhoneNumber(),
        dbre.getPhoneNumber()
        )
    
           
    insertSQL(sql, val)
        
    
def insertPersonalAddress(sql):
    val = (
        dbre.getAddressOne(),
        dbre.getAddressOne(),
        dbre.getAddressOne(),
        dbre.getCity(),
        dbre.getState(),
        dbre.getZip(),
        dbre.getRandSpouse()
        )
    insertSQL(sql, val)
        

def insertMiscInfo(sql,pk):
    val = (
        pk,
        dbre.getSubBy(),
        dbre.getLifeStatus(),
        dbre.boolAnswer(),
        pk,
        dbre.getServStatus(),
        dbre.boolAnswer(),
        dbre.boolAnswer(),
        dbre.boolAnswer(),
        dbre.boolAnswer()
        )
    
    
    
    insertSQL(sql, val)
        
    
def insertServiceClaimStatus(sql):

    insertSQL(sql, dbre.getClaimStatus().split())

def insertSubSerStatus(sql, pk):
    val = (
        pk,
        dbre.getRandomMultiCharacters(),
        dbre.getRandomMultiCharacters()
        )
    
    
    insertSQL(sql, val)
        

def loop():
    #Primary key
    pk = 1
    sqlList = getSQLCommands()
    
    print("Filling Personal Data Tabl...")
    for i in tqdm(range(entries)):
        insertPersonalData(sqlList[0])
    mydb.commit()
          
    print("Filling Member DoD Table...")
    for i in tqdm(range(entries)):
        insertMemberDoD(sqlList[1])
    mydb.commit()
    
    print("Filling USN Rates Table...")
    for i in tqdm(range(entries)):
        insertUSNRates(sqlList[2])
    mydb.commit()
    print("Filling Accidents Table...")
    for i in tqdm(range(entries)):
        insertAccidents(sqlList[3])
    mydb.commit()
        
    print("Filling Phone Number Table...")
    for i in tqdm(range(entries)):
        insertPhoneNumber(sqlList[5])
    mydb.commit()
    
    print("Filling Personal Address Table...")
    for i in tqdm(range(entries)):
        insertPersonalAddress(sqlList[6])
    mydb.commit()
    
    print("Filling Health Issues (Hipaa) Table...")
    for i in tqdm(range(entries)):
        insertRandomHealthIssueHippa(sqlList[8])
    mydb.commit()
    print("Filing Member Boats Table...")
    for i in tqdm(range(entries)):
        insertMemberBoats(sqlList[9])
    mydb.commit()
    print("Filling Sub Service Status Table...")
    for i in tqdm(range(entries)):
        insertSubSerStatus(sqlList[10], pk)
        pk += 1
    mydb.commit()
    print("Filling Service Claim Status Table...")
    for i in tqdm(range(entries)):
        insertServiceClaimStatus(sqlList[12])
    mydb.commit()
    pk = 1
    print("Filling Misc Info Table...")
    for i in tqdm(range(entries)):
        insertMiscInfo(sqlList[7],pk)
        pk += 1
    mydb.commit()
       
def getCount():
    sql = open("sqlCount.txt", "r")
    #print(sql)
    sqlList = []
    for x in sql:
        sqlList.append(x)
    #print(sqlList)
    mycursor.execute(sqlList[0])
    """
    for x in sqlList:
        mycursor.execute(x)
    results = mycursor.fetchall()
    print(results)
    for x in results:
        print(x)
    """
#getCount()
def main():
    pk = 1
    sqlList = getSQLCommands()
    #print("HICAt")
    print("Starting...")
    createDatabase()
    print("Filling Health Issues Category table...")
    insertHealthIssuesCat(sqlList[4])
    print("Filling Submarines Table...")
    insertSub(sqlList[11])
    mydb.commit()
    loop()
    print("\nDONE!")
    
    getCount()

main()
