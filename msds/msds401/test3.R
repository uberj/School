round(pnorm(130, 100, 15, lower.tail = FALSE), 3)


pbinom(529, 522, 205/522)

529 * (205/522)

0.7* 1 + 0.2 *2 + 0.1*3

E = 0.011
q = p = 0.5
z = qnorm(0.02 / 2, 0, 1)
( z^2 * p * q ) / E^2


(z^2)
n = 40
stdev = 96
Conf = 0.90

z_d2 = qnorm(0.05/2, 0, 1)
sigma = 500
E = 110

((z_d2 * sigma) / E)^2


xbar = (67.3 + 65.7)/2 
sdev = ((65.7 - xbar) * 12)/qnorm(0.01/2, 0, 1)
sdev

term = (qnorm(0.05/2, 0, 1, lower.tail=FALSE) * sdev)/12
round(xbar - term, 1)
round(xbar + term, 1)
term

(qnorm(0.10/2, 0, 1, lower.tail = FALSE) * 96)/sqrt(40)
