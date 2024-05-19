T = int(input())

for i in range(1, T + 1):
    N = int(input())
    a = [int(num) for num in input().split()]
    b = [int(num) for num in input().split()]

    a.sort()
    b.sort()
    #print(a)
    #print(b)

    count = 0
    for j in a:
        for k in b:
            if (j - k) <= 3 and (j - k) >= -3:
                count += 1
                b.remove(k)
                #print(b)
                break
        #print(a)
    print("#{} {}".format(i, count))