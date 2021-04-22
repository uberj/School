# P(C ^ S) = 0.0125
# P(S) = 0.125
# P(C|S) = P(C^S)/P(S)
# question 1
0.0125/0.125


# Question 2
n = 76
p  =  0.7
q = 1 - p

mu = n * p
sd = sqrt(n * p * q)

x <- seq(40, 65,by = 1)

# Create the binomial distribution.
y <- dbinom(x,n,p)
plot(x,y)

curve(dnorm(x, mu, sd), add=TRUE, col="orange",lwd=2)
curve(dnorm(x, mu+.5, sd), add=TRUE, col="red",lwd=2)
curve(dnorm(x, mu-.5, sd), add=TRUE, col="blue",lwd=2)

x = 50
round(pnorm(x+0.5, mu, sd) - pnorm(x-0.5, mu, sd), 4)

# Question 3
d = c(1.3,2.2,2.7,3.1,3.3,3.7)
help("quantile")
round(quantile(d, 0.33, type=7), 2)

# Question 5
xs = seq(0,3)
ps = c(0.749,	0.225,	0.024,	0.002)

# Mean or expected value of discrete distribution
mu = round(sum(xs * ps), 2)

# Variance
thevar = round(sum(((xs - mu) ^ 2) * ps), 2)

mu
thevar

# Question 7 -- Super unsure on this one
# Q: Once you calculate the z formula for a sample mean, are you supposed to use the standard normal distro? (mu=0, sd=1)
# A: Yes. z-scores are defined on the standard normal distro
mu = 8.4
sd = 1.8
sample_mu = 8.7
n = 36
sample_sd = sd / sqrt(n)

z = (sample_mu - mu)/sample_sd
1 - pnorm(z, 0, 1)

# question 8

# 40 1s
# 10 100s
# 50 bills total

prob = (40/50) * (39/49) * (38/48)
round(prob,4)


# question 9

mu = 1050
sd = 225
round(qnorm(0.45, mu, sd), 1)

# Question 10
# three different drinks
# five trials
# each trial, someone picks one

3*((1/3)^5)
