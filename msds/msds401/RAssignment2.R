p = 0.05
n = 100
lam = p * n
round(dpois(0, lam), 3)

round(pbinom(5, n, p), 3)
round(dbinom(0, n, p), 3)

library(ggplot2)
library(dplyr)
xs = seq(0, 10)
df = data_frame(events=xs, binom = dbinom(xs, n, p), pois=dpois(xs, n * p))
p1 = ggplot(df, aes(x=events, y=binom)) + geom_bar(stat="identity", width=0.5, fill="steelblue") + labs(title="Binomial | 100 trials with 0.05% chance of succeess", x="Number of successes", y = "Probabilty") + theme_minimal()
p2 = ggplot(df, aes(x=events, y=pois)) + geom_bar(stat="identity", width=0.5, fill="steelblue") + labs(title="Poisson | 100 trials lambda is 5", x="Number of successes", y = "Probabilty") + theme_minimal()
grid.arrange(p1, p2)


p = 0.25
q = 1 - p
n = 100
n * p > 5
n * q > 5
mu = n * p
s = sqrt(n * p * q)

round(pnorm(25.5, mu, s) - pnorm(24.5, mu, s), 3)
round(dbinom(20, n, p), 3)
round(pbinom(19, n, p),4)


es = seq(0, 6)
ps = c(0.214, 0.230, 0.240, 0.181, 0.1305, 0.0025, 0.002)
mu = sum(es * ps)
s = sqrt(sum(ps * (es - mu)^2))
round(s,3)

df = data_frame(x=es, cps=cumsum(ps))
ggplot(df) + geom_step(aes(x=x, y=cps)) +
  geom_vline(xintercept = 3, color='red') + 
  labs(title='Cumlative distribution (red line is the median)', x='Event', y='CDF')
ggplot(df, aes(x=es, y=cps)) + geom_bar(stat="identity", width=0.5, fill="steelblue") +
  geom_vline(xintercept = 3, color='red') + 
  labs(title='Cumlative distribution (red line is the median)', x='Event', y='CDF')

data("faithful")

round(nrow(faithful[(faithful$eruptions > 2) & (faithful$eruptions < 3), ]) / nrow(faithful), 3)

b = faithful[faithful$waiting > 70, ]

round(nrow(b[b$eruptions < 3.5,])/nrow(b), 3)

ggplot(faithful, aes(x=waiting, y=eruptions, colour = waiting > 70  & eruptions < 3)) + geom_point() +
  geom_hline(yintercept = 3) +
  geom_vline(xintercept = 70) +
  labs(title='Eruptions duration vs Waiting time', x='Waiting (min)', y='Eruptions (min)')

data("ChickWeight")

df = ChickWeight[(ChickWeight$Time == 21) & (ChickWeight$Diet == 1 | ChickWeight$Diet == 3),]
t.test(df[df$Diet == 1,]$weight, df[df$Diet == 3,]$weight, mu = 0, diff, alternative = c("two.sided"), var.equal = TRUE, conf.level = 0.95, paired = FALSE)

ggplot(df, aes(x=weight, y=Diet)) + geom_boxplot() +
  geom_boxplot(color="red", fill="orange", alpha=0.2) +
  theme_minimal() +
  labs(title='Weight distribution | Diets 1 and 3', x='Weight', y='Diet #')

df = ChickWeight[(ChickWeight$Diet == 3),]
w20 = df[df$Time == 20,]$weight
w21 = df[df$Time == 21,]$weight
df2 = data.frame(w20=w20, w21=w21)
ggplot(df2, aes(x=w20, y=w21)) + geom_point() + geom_abline(slope=1, intercept=0, color='blue') +
  theme_minimal() +
  

diff = w21 - w20
xbar = mean(diff)
mu = 0
s = sd(diff)
n = length(w20)
t = -(xbar - mu) / (s/sqrt(n))
ndf = n - 1
alpha = 0.05
t.critical = pt(alpha, ndf)
lower_ci = mu - t * (s / sqrt(n))

list("t statistic" = t,
     "p-value" = dt(t, ndf),
     "critical t" = t.critical,
     "conf interval" = list(lower = lower_ci, upper = "Inf"))

t.test(w20, w21, alternative = c('two.sided'), paired=TRUE)

data("warpbreaks")
warpbreaks
mbreaks = median(warpbreaks$breaks)
ggplot(warpbreaks) +
  geom_histogram(aes(x=breaks)) + theme_bw() +
  geom_vline(xintercept = mbreaks, color='blue') +
  geom_text(aes(x=mbreaks - 4, label=paste0("Blue line \nis median"), y=7.5)) +
  labs(title='Number of breaks per loom', x='# of breaks', y='Occurances')

make_mean_breaks = function (v) {
  if (v < mbreaks) {
    r = "below"
  } else {
    r = "above"
  }
  r
}

warpbreaks$median_breaks = apply(array(warpbreaks$breaks), 1,  make_mean_breaks)
warpbreaks
mbreaks
chisq.test(
  warpbreaks[warpbreaks$median_breaks == "above", ]$tension,
  warpbreaks[warpbreaks$median_breaks == "below", ]$tension
)

library(tidyverse)
df = warpbreaks %>% select("median_breaks", "tension")
chisq.test(table(df))
