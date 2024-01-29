#include <stdio.h>

int main(void)
{
    char str[5][20] = { '\0' };
    int i, j, cnt = 0;

    for (i = 0; i < 5; i++)
        scanf("%s", str[i]);

    for (i = 0; i < 15; i++) {
        for (j = 0; j < 5; j++) {
            if (str[j][i] == '\0') {
                cnt++;
                continue;
            }
            printf("%c", str[j][i]);
        }
        if (cnt >= 5)
            break;
        cnt = 0;
    }
}