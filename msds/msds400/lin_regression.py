import matplotlib.pyplot as plt
import numpy as np
from scipy import stats

x = np.array([0, 1, 2, 3 ,4, 5])
y = np.array([0, 1, 2, 3, 4, 5])

plt.scatter(x, y, marker='o', color='r')


slope, intercept, r_value, p_value, std_err = stats.linregress(x, y)

print(f"slope: {round(slope, 3)}")
print(f'y-intercept: {round(intercept, 3)}')
print(f'correlation coefficient: {round(r_value, 4)}')
