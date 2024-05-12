T = int(input())

for i in range(1, T + 1):
    n = list(map(int, input().split()))
    result = 0
    for j in n :
        if j % 2 == 1 :
            result += j
    print("#{} {}".format(i, result))