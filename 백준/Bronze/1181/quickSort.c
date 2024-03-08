#define _CRT_SECURE_NO_WARNINGS
//Bubble Sort
#include<stdio.h>
#include<string.h>

void bubbleSort(int a[], int c)
{
	int temp[50];

	for (int i = 0; i < c; i++) {
		for (int j = 0; j + 1 < c; j++) {
			if (strlen(a[j]) > strlen(a[j + 1])) {
				strcpy(temp, a[j]);
				strcpy(a[j], a[j + 1]);
				strcpy(a[j + 1], temp);
			}
			else if (strlen(a[j]) == strlen(a[j + 1])) {
				if(a[j] > a[j + 1]) {
					strcpy(temp, a[j]);
					strcpy(a[j], a[j + 1]);
					strcpy(a[j + 1], temp);
				}
			}
		}
	}
}

int main()
{
	int cnt;
	char word[20000][50];

	scanf("%d", &cnt);
	for (int i = 0; i < cnt; i++)
		scanf("%s", &word[i]);

	bubbleSort(word, cnt);

	for (int i = 0; i < cnt; i++)
		printf("%s\n", word[i]);
}