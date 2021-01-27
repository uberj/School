import numpy as np
from scipy import linalg

brand_stats = [
        [1, 2, 3], # Bag 1
        [3, 1, 2], # Bad 2
        [2, 0, 1], # Bad 2
]

ideal_quants = [18, 23, 13]

# X = number of each brand's bags

# brand_stats * X = ideal_quants
# X = inv(brand_stats) * ideal_quants


X = np.dot(linalg.inv(brand_stats), ideal_quants)
print(X) #  [5. 2. 3.]

# Check 18 = 5*1 + 2*2 + 3*3
# Check 23 = 5*3 + 2*1 + 3*2
# Check 13 = 5*2 + 2*0 + 3*1

