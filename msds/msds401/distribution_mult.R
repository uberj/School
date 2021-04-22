library('ks')
library("BBmisc")

N = 1000
d1 = normalize(rbeta(N,5,5))
d2 = normalize(rbeta(N,5,2))
pdf1 = kde(d1)
pdf2 = kde(d2)

desc_1 = predict(pdf1, x=seq(-4, 4, 0.01))
desc_2 = predict(pdf2, x=seq(-4, 4, 0.01))

desc_3 = normalize(desc_1 * desc_2)
par(mfrow = c(1, 3))

plot((desc_1))
plot((desc_2))
plot((desc_3))


par(mfrow = c(1, 4))

p1 = normalize(d1 * d1)
plot(kde(p1))
p2 = normalize(p1 * p1)
plot(kde(p2))
p3 = normalize(p2 * p2)
plot(kde(p3))
p4 = normalize(p3 * p3)
plot(kde(p4))

par(mfrow = c(2, 3))
n1 = dnorm(x, mean = 0, sd = 1)
n2 = dnorm(x, mean = 1, sd = 0.5)
plot(n1)
plot(n2)

n3 = n1 * n2
plot(n3)

n4 = n2 * n3
plot(n4)
