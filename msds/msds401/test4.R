qnorm(0.042, lower.tail=FALSE)
0.042/2

x = c(2,    5,    8,    10 , 12)
y = c(7,  11 , 13 ,  20,  24)

df = data.frame(x=x, y=y)
lm(y ~ x, df)

qf(0.95, 3, 16)

#Source	    df	  SS	    MS-SS/df	F-statistic
#Treatment	5	    22.2	 	4.44 
#Error	    26	 	104        4	      
#Total	    31	 	126.2

4.44/4


# Ho: xbar = mu
# Ha: xbar > mu

mu = 5423
sigma = 979

n = 352
xbar = 5516

t = (xbar - mu)/(sigma/sqrt(n))

pt(t, n - 1, lower.tail=FALSE)

n1 = 100
x1 = 39
n2 = 100
x2 = 49
p1hat = x1/n1
p2hat = x2/n2
pbar = (x1 + x2) / (n1 + n2)
qbar = 1 - pbar

z = (p2hat - p1hat) / sqrt((pbar * qbar)*(1/n1 + 1/n2))
z
pnorm(z)
qnorm(0.1/2)


x1bar = 19.4
x2bar = 15.1

n1 = 35
n2 = 40

s1 = 1.4 
s2 = 1.3

term = (sqrt((s1^2*(n1 - 1) + s2^2*(n2 - 1))/(n1 + n2 - 2)) * sqrt(1/n1 + 1/n2))
t = qt(0.05/2, n1 + n2 - 2)
t
t * term
round(x1bar - x2bar - t * term, 1)
round(x1bar - x2bar + t * term, 1)

n1 = 400
n2 = 500

x1 = 78
x2 = 92

p1hat = x1/n1
p2hat = x2/n2
q1hat = 1 - p1hat
q2hat = 1 - p2hat

term = qnorm(0.10/2) * sqrt((p1hat * q1hat)/n1 + (p2hat * q2hat)/n2)

round(p1hat - p2hat + term, 4)
round(p1hat - p2hat - term, 4)
