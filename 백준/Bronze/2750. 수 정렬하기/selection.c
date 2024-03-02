//Selection Sort
#include<stdio.h>

void selectionSort(int a[], int c)
{
	int min = a[0];
	int minIdx = 0;

	for (int i = 0; i < c;) {
		for (int j = i + 1; j < c; j++) {
			if (min > a[j]) {
				min = a[j];
				minIdx = j;
			}
		}
		a[minIdx] = a[i];
		a[i] = min;

		min = a[++i];
		minIdx = i;
	}
}

int main()
{
	int cnt;
	int num[1000];

	scanf("%d", &cnt);
	for (int i = 0; i < cnt; i++)
		scanf("%d", &num[i]);

	selectionSort(num, cnt);

	for (int i = 0; i < cnt; i++)
		printf("%d ", num[i]);
}
