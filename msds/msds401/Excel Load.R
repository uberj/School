install.packages("tidyverse")
library(readxl)
BLACK_DATABASES = "./data/Databases_in_Excel.xlsx"
excel_sheets(BLACK_DATABASES)
finance_data = read_excel(BLACK_DATABASES, sheet = "Financial")
