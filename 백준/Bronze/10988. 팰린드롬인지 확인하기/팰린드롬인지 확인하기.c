#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <string.h>

int main(void)
{
	char word[100] = { '0' };
	int temp = 1;

	scanf("%s", word);

	int len = strlen(word);
	for (int i = 0; i < len / 2; i++) {
		if (word[i] != word[len - i - 1]) {
			temp = 0;
			break;
		}
	}
	printf("%d", temp);
}