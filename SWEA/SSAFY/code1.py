T = int(input())

for i in range(1, T + 1):
    N, K = map(int, input().split())
    li = [int(num) for num in input().split()]

    result = []
    for j in range(0, N):
        for r in range(0, N):
            result.append([li[j], li[r]])

    result.sort()
    print("#{} {}".format(i, result[K - 1][0] + result[K - 1][1]))