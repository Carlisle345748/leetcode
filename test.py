import numpy as np
a = np.array([1,2,3,4,5])
a = np.array([[i, 0] for i in a]).flatten()[:-1]
print(a)
