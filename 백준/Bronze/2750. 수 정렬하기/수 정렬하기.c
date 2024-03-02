//Insertion Sort
#include<stdio.h>

void insertionSort(int a[], int c)
{
	int n, temp;

	for (int i = 0; i < c; i++) {
		n = a[i];
		for (int j = i - 1; j >= 0; j--) {
			if (n >= a[j]) {
				break;
			}
			else if (n < a[j]) {
				a[j + 1] = a[j];
				a[j] = n;
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

	insertionSort(num, cnt);

	for (int i = 0; i < cnt; i++)
		printf("%d ", num[i]);
}