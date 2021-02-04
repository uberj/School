from pulp import LpVariable, LpProblem, LpMaximize, LpMinimize, LpStatus, value

# Declare variables
x = LpVariable('x', 0, None) # x >= 0
y = LpVariable('y', 0, None) # y >= 0

# define the problem
prob = LpProblem("problem", LpMaximize)

# defines the constraints

prob += x + 3*y <= 10
prob += 3*x + y <= 6

# defines the objective function to minimize
prob += 3*x + 2*y

# solve the problem
status = prob.solve()
LpStatus[status]

print(f"x: {value(x)}")
print(f"y: {value(y)}")
print(f"optimum: {5*value(x) + 2*value(y)}")
