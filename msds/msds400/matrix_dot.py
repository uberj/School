import numpy as np

A = np.array([
    [80, 40, 120],
    [60, 30, 150],
])

B = np.array([
    [1/2, 1/4, 1/4],
    [1/5, 1/5, 3/5],
])

print("A")
print(A)
print("B")
print(np.transpose(B))
print("A dot B")
print(np.dot(A, np.transpose(B)))
