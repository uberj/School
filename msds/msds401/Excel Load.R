install.packages("tidyverse")
library(readxl)
BLACK_DATABASES = "Data/black/Databases_in_Excel.xlsx"
excel_sheets(BLACK_DATABASES)
finance_data = read_excel(BLACK_DATABASES, sheet = "Financial")
