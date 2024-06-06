a = int(input())

total = 0
while True:
    if a <= 0:
        break

    total += (a % 10)
    a = int(a / 10)

print("{}".format(total))
