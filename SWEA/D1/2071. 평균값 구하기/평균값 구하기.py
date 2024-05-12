T = int(input())

for i in range(1, T + 1):
    n = list(map(int, input().split()))
    total = 0
    for j in n:
        total += j
    print("#{} {}".format(i, round(total / 10)))
