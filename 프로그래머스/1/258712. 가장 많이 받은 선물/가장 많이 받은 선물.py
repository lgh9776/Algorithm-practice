def solution(friends, gifts):
    #표 생성을 위한 2차원 배열 초기화
    send = []
    for i in friends:
        temp = []
        for j in friends:
            temp.append(0)
        send.append(temp)

    #선물 주고받은 표 생성
    for i in gifts:
        temp = i.split(' ')

        j = friends.index(temp[0]) #준 사람의 배열 인덱스 찾기
        k = friends.index(temp[1]) #받은 사람의 배열 인덱스 찾기 -> 2차원 배열

        send[j][k] += 1

    #다음 달에 가장 많은 선물을 받을 친구 찾기
    li = []
    for i in range(0, len(friends)):
        li.append(0)

    for i in range(0, len(friends)):
        for j in range(0, len(friends)):
            if i == j: #본인에게 선물x
                continue

            if send[i][j] - send[j][i] > 0:
                li[i] += 1
            elif send[i][j] - send[j][i] == 0: #선물지수 계산 필요
                a, b, c, d = 0, 0, 0, 0
                for k in range(0, len(send[i])):
                    a += send[i][k]
                    b += send[k][i]
                    c += send[j][k]
                    d += send[k][j]
                if a - b > c - d:
                    li[i] += 1

    answer = max(li)
    
    return answer