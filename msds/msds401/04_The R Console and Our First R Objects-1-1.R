#!
5 + 2

seq(from = 0, to = 100, by = 2)

10 - 3   # subtraction
10 * 3   # multiplication
10 ^ 3   # exponentiation
10 / 3   # division
10 %% 3  # modulo
10 %/% 3 # integer division

5 > 2   # 5 greater than 2?
5 < 2   # 5 less than 2?
5 == 2  # 5 equal to 2?
5 != 2  # 5 not equal to 2?
5 <= 2  # 5 less than or equal 2?
5 >= 2  # 5 greater than or equal to 2?


x <- 5
x

x - 2
x ^ 2
x > 2

y <- 2

x != x      # again, NOT EQUAL
x & y == 5  # AND
x | y == 5  # OR

isTRUE(x == x)
identical(x, y)
xor(T, F)   #exclusive-or
xor(T, T)

our_vector <- c(2, 4, 6, 2, 4, 7, 3, 5, 7, 7, 2, 5, 6)

our_character_vector <- c("that", "is", "not", "a", "convenient", "name",
	"for", "a", "vector", "less", "characters", "please")

str(our_vector)
our_vector

our_vector * 2

our_vector && our_vector
our_vector & our_vector

some_other_vector <- rnorm(13, mean = mean(our_vector), sd = sd(our_vector))
some_other_vector

mean(our_vector)
sd(our_vector)
some_other_vector <- rnorm(13, mean = 4.615385, sd = 1.938146)

our_vector / some_other_vector

name <- c("Adam", "Betty", "Clark", "Denise", "Everett",
	"Francine", "Geraldo", "Harley")
age <- c(25, 65, 45, 35, 29, 55, 33, 23)
complete <- c(T, F, T, T, F, T, F, F)

our_dataframe <- data.frame(name, age, complete)
str(our_dataframe)

our_dataframe






