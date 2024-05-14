T = int(input())

a = 0
for i in range(1, T + 1):

    if (i % 10) % 3 == 0 and i % 10 != 0:
        print("-", end="")
        a = 1

    if int(i / 10) % 3 == 0 and int(i / 10) != 0:
        print("-", end="")
        a = 1

    if a != 1:
        print("{}".format(i), end="")
    print(" ", end="")
    a = 0