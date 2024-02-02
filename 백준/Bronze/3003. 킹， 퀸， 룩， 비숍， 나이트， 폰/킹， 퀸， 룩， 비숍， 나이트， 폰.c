#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

int main(void)
{
	int n[6] = { 1, 1, 2, 2, 2, 8 };
	int cnt = 0;

	for (int i = 0; i < 6; i++) {
		scanf("%d", &cnt);
		printf("%d ", n[i] - cnt);
	}
}