import numpy as np

# y = [18, 23, 13]

D = np.array([
    [1, 2, 3],
    [3, 1, 2],
    [2, 0, 1]
]) # D

Dx = np.array([
    [18, 2, 3],
    [23, 1, 2],
    [13, 0, 1]
]) # D x

Dy = np.array([
    [1, 18, 3],
    [3, 23, 2],
    [2, 13, 1]
]) # D y

Dz = np.array([
    [1, 2, 18],
    [3, 1, 23],
    [2, 0, 13]
]) # D z

print("|D| = ", np.linalg.det(D))
print("|D_x| = ", np.linalg.det(Dx))
print("|D_y| = ", np.linalg.det(Dy))
print("|D_z| = ", np.linalg.det(Dz))
print("x = |D_x|/|D| = ", np.linalg.det(Dx)/np.linalg.det(D))
print("y = |D_y|/|D| = ", np.linalg.det(Dy)/np.linalg.det(D))
print("z = |D_z|/|D| = ", np.linalg.det(Dz)/np.linalg.det(D))
