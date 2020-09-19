def build(pat,m):
    nfa = [0 for _ in range(m)]
    j = 0 # virtual state run through nfa
    for i in range(1,m):
        nfa[i] = j
        while j > 0 and pat[i] != pat[j]:
            j = nfa[j]
        if pat[i] == pat[j]:
            j += 1
    return nfa

pattern = "ABCDABD"
print(build(pattern, len(pattern)))