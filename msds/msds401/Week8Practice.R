df = data.frame(
  cat = as.factor(c(
    '1','1','1','1','1','1',
    '2','2','2','2','2',
    '3','3','3','3','3','3'
  )),
  v = c(
    2,1,3,3,2,1,
    5,3,6,4,5,
    3,4,5,5,3,5
  )
)

aov.results = aov(v ~ cat, data=df)
sprintf("critical F is %s", round(qf(0.95, 2, 14), 3))
summary(aov.results)
"The F value (11.07) is much higher than the critical F value (3.739) so we reject the null hypotheses"

TukeyHSD(aov.results)
