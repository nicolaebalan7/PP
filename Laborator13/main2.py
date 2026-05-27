import more_itertools

text = "This sentence has words of various lengths in it, both short ones and long ones"
words = text.split()

key_func = lambda word: word[0]      # prima litera
value_func = lambda word: word       # cuvantul ca valoare

result = more_itertools.map_reduce(words, key_func, value_func)

for letter, words_list in sorted(result.items()):
    print(f"{letter}: {sorted(words_list)}")