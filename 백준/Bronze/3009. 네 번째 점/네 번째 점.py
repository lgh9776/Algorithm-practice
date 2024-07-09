result = []

a1, b1 = map(int, input().split())

a2, b2 = map(int, input().split())

a3, b3 = map(int, input().split())


if a1 == a2:
	result.append(a3)
elif a1 == a3:
	result.append(a2)
else:
	result.append(a1)


if b1 == b2:
	result.append(b3)
elif b1 == b3:
	result.append(b2)
else:
	result.append(b1)
	
print(result[0], result[1])