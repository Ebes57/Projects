# -*- coding: utf-8 -*-
"""
Created on Tue Sep 24 12:06:40 2024

@author: Connor Ebert


"""
import pandas as pd
from tqdm import tqdm
import random
import string



#XLSX used to pull information
sag_file = "Database-Fields-n-Tables-Recommended.xlsx"
file_name = "test_names_raw.xlsx"

#opesn health issues sheet
health_issues_sheet = pd.read_excel(sag_file, sheet_name="Health_Issues")
health_issues = health_issues_sheet.values.tolist()

#Gets information from Dod Sheet
dod_dept_file = pd.read_excel(sag_file, sheet_name="DoD_Departments")
dod_dept = dod_dept_file.values.tolist()

#Opens hull sheet
hull_sheet = pd.read_excel(sag_file, sheet_name="HD&N")
hull = hull_sheet.values.tolist()

#Gloabal Variables
#Creates dataframe and gets info to store in global variable
df = pd.read_excel(file_name, sheet_name="Sheet2")
nameList = df.values.tolist()

#opens va claim sheet
va_claim_sheet = pd.read_excel(sag_file, sheet_name="VA_Claim")
va_claim = va_claim_sheet.values.tolist()

state_file = "states.xlsx"
city_file = "city.xlsx"


#opens states xlsx
state = pd.read_excel(state_file)
state = state.values.tolist()  
#opens city xlsx
city = pd.read_excel(city_file)
city = city.values.tolist()  

#Global list used
first_name_list = []
last_name_list = []

#Gets a random hull
def getRandHull(x):
    if(pd.isnull(hull[x][2])):
        val = "NULL"
    else:
        val = hull[x][2]
    return str(val)
#Gets a random hull name
def getName(x):
    if(pd.isnull(hull[x][2])):
        val = "NULL"
    else:
        val = hull[x][2]
    return str(val)


def getSubmarines():
    return hull

#generates random birthday
def getRandDate():
    month = random.randint(1, 12)
    if(month == 2):
        day = random.randint(1, 28)
    elif(month == 4 or month == 6 or month == 9 or month == 11):
        day = random.randint(1, 30)
    else:
        day = random.randint(1,31)
    year = random.randint(1941, 2002)
    date = str(year) + "-" + str(month) + "-" + str(day)
        
    return date




#Gets a random gender
def getRandGender():
    gender = ["male", "female", "other"]
    return gender[random.randint(0,2)]
    

#returns if they have a spouse or not or nothing
def getRandSpouse():
    spouse = ["Yes", "No", "NULL"]
    return spouse[random.randint(0, 2)]

def formatName():
    split_name = []
    for item in nameList:
        split_name.append(item[0].split())
    #after data is split, make sure last name is not null
    x = 0
    while x < len(split_name):
        #if no last name, last name becomes first name
        if(len(split_name[x]) == 1):
            first_name_list.append(split_name[x])
            last_name_list.append(split_name[x])
        #if more than one word for last name, add it to last name
        else:
            first_name_list.append(split_name[x][0])
            last_name_list.append(split_name[x][1:])
        x += 1



def getRandomFirstName(x):
    return str(first_name_list[x]).replace("['","").replace("']","").replace(",", "")

def getRandomLastName(x):
    return str(last_name_list[x]).replace("['","").replace("']","").replace(",", "")

def getRandomCharacter():
    return str(random.choice(string.ascii_letters))

    
def getRandomDoDDept(x):
    return str(dod_dept[x][0])

def getRandomDodName(x):
    return str(dod_dept[x][1])

def getRandomMultiCharacters():
    multiChar = ""
    for x in range(random.randint(2, 3)):
        multiChar += random.choice(string.ascii_uppercase)
    return multiChar

def boolAnswer():
    twoOptions = ["yes", "no"]
    x = random.randint(0,1)
    return twoOptions[x]

def getCategories(x):
    return health_issues[x][0]

def getSubCategory(x):
    if(pd.isnull(health_issues[x][1])):
        return "NULL"
    return health_issues[x][1]

def getHIName(x):
    if(pd.isnull(health_issues[x][2])):
        return "NULL"
    return health_issues[x][2]

def getIssueDiag(x):
    if(pd.isnull(health_issues[x][2])):
        if(pd.isnull(health_issues[x][1])):
            return "Null"
        else:
            return health_issues[x][1]
    else:
        return health_issues[x][2]

def getPhoneNumber():
    #creates phone number
    areaCode = random.randint(100, 555)
    idkWhatItsCalled = random.randint(100, 555)
    lastFour = random.randint(1000, 5555)
    #Puts phone number together
    phoneNum = str(areaCode) + "-" + str(idkWhatItsCalled) + "-" + str(lastFour)
    return phoneNum

def getAddressOne():
    #Get random street name
    streetName = str(random.randint(1, 150)) + " Street"
    
    #add street address
    return str(streetName) + " " + str(city[random.randint(1, 2520)][1])

def getState():
    state_info = str(state[random.randint(0,49)])
    #format list
    state_info = state_info.replace("'","").replace("[","").replace("]","").replace(",","")
    return state_info

def getCity():
    return city[random.randint(0, 2519)][1]

getCity()

def getZip():
    return random.randint(10000, 99999)

def getSubBy():
    sub = ["Me", "Spouse", "Other", "NULL"]
    return sub[random.randint(0,3)]
    
def getLifeStatus():
    life = ["Deceased", "Alive"]
    return life[random.randint(0, 1)]

def getServStatus():
    status = ["active", "retired", "NULL"]
    return status[random.randint(0,1)]

def getClaimStatus():
    status = ["Approved", "Denied", "Pending"]
    return status[random.randint(0,2)]

formatName()

"""
    info.append(str(health_issues[rand_health_issue][0]))
    info.append(str(health_issues[rand_health_issue][1]))
    info.append(str(health_issues[rand_health_issue][2]))

"""