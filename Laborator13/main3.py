from functional import seq

numbers = [1, 21, 75, 39, 7, 2, 35, 3, 31, 7, 8]

filtered = seq(numbers).filter(lambda x: x >= 5).to_list()
print(f"Dupa filter: {filtered}")

pairs = [(filtered[i], filtered[i+1]) for i in range(0, len(filtered)-1, 2)]
print(f"Dupa zip: {pairs}")

products = seq(pairs).map(lambda pair: pair[0] * pair[1]).to_list()
print(f"Dupa inmultire: {products}")

result = seq(products).sum()
print(f"Suma: {result}")