#define _CRT_SECURE_NO_WARNINGS
//Quick Sort
#include<stdio.h>

void quickSort(int a[], int start, int end)
{
	int left = start + 1, right = end;
	int temp;

	while (left < right) {
		while (a[left] < a[start] && left < right)
			left++;

		while (a[right] > a[start] && left < right)
			right--;

		if (left >= right) {
			temp = a[start];
			a[start] = a[right - 1];
			a[right - 1] = temp;
			break;
		}
		else {
			temp = a[right];
			a[right] = a[left];
			a[left] = temp;
		}

	}

	quickSort(a, start, right - 1);
	quickSort(a, right + 1, end);
}

int main()
{
	int cnt;
	int num[10];

	scanf("%d", &cnt);
	for (int i = 0; i < cnt; i++)
		scanf("%d", &num[i]);

	quickSort(num, 0, cnt);

	for (int i = 0; i < cnt; i++)
		printf("%d\n", num[i]);
}