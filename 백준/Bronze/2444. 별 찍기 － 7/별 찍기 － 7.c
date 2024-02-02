#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

int main(void)
{
	int i, j, n;

	scanf("%d", &n);

	for (i = 0; i < n; i++) {
		for (j = 0; j < n - 1 - i; j++)
			printf(" ");

		for (j = 0; j < 2 * i + 1; j++)
			printf("*");

		printf("\n");
	}

	for (i = n - 1; i > 0; i--) {
		for (j = n - i; j > 0; j--)
			printf(" ");

		for (j = 0; j < 2 * i - 1; j++)
			printf("*");
		
		printf("\n");
	}
}