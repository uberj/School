import numpy as np
from scipy import linalg

A = np.array([
    [1, 0, 1],
    [2, -2, -1],
    [3, 0, 0],
])

print("A")
print(A)
print("inverse(A)")
print(linalg.inv(A))
