# Problem 10.51
#Ho: p1 - p2 = 0
#Ha: p1 - p2 != 0 (two tail test)

n1 = 783
x1 = 345
n2 = 896
x2 = 421

phat1 = x1/n1
phat2 = x2/n2

pbar = (x1 + x2)/(n1 + n2)
qbar = 1 - pbar

z = (phat1 - phat2)/sqrt(pbar * qbar * (1/n1 + 1/n2))
pnorm(z)

# 0.1149622 is more than alpha (0.05) so we do not reject Ho