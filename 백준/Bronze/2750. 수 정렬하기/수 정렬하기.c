//Bubble Sort
#include<stdio.h>

void bubbleSort(int a[], int c)
{
	int temp;

	for (int i = 0; i < c; i++) {
		for (int j = 0; j + 1 < c; j++) {
			if (a[j] > a[j + 1]) {
				temp = a[j];
				a[j] = a[j + 1];
				a[j + 1] = temp;
			}
		}
	}
}

int main()
{
	int cnt;
	int num[1000];

	scanf("%d", &cnt);
	for (int i = 0; i < cnt; i++)
		scanf("%d", &num[i]);

	bubbleSort(num, cnt);

	for (int i = 0; i < cnt; i++)
		printf("%d ", num[i]);
}