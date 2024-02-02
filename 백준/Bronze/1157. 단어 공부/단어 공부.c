#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <string.h>

int main(void)
{
	char word[1000000];
	int cnt[26] = { 0 };
	int max = 0, isSame = 0;

	scanf("%s", word);

	int len = strlen(word);
	for (int i = 0; i < len; i++) {
		if (word[i] >= 'A' && word[i] <= 'Z')
			cnt[word[i] - 'A']++;
		else if (word[i] >= 'a' && word[i] <= 'z')
			cnt[word[i] - 'a']++;
	}

	for (int i = 0; i < 26; i++) {
		if (cnt[max] < cnt[i]) {
			max = i;
			isSame = 0;
		}
		else if (cnt[max] == cnt[i] && cnt[max] != 0 && max != i)
			isSame = 1;
	}

	if (isSame == 1)
		printf("?");
	else
		printf("%c", 'A' + max);
}