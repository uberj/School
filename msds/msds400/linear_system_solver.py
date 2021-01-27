import sympy as sym

sym.init_printing()

x, y, z = sym.symbols("x, y, z")


solns = sym.solve(
        [0.2*x + 0.5*y + 0.3*z - 1950,
         0.3*x + 0.4*y + 0.1*z - 1490,
         0.1*x + 0.6*y + 0.4*z - 2160]
, [x, y, z])

print(solns)
