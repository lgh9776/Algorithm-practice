//Merge Sort
#include<stdio.h>
int sorted[10000000];

void merge(int a[], int s, int e, int m)
{
	int i = s, j = m + 1;
	int left = s;

	while (i <= m && j <= e) {
		if (a[i] < a[j]) {
			sorted[left] = a[i];
			left++;
			i++;
		}
		else {
			sorted[left] = a[j];
			left++;
			j++;
		}
	}

	if (i <= m) {
		while (i <= m) {
			sorted[left] = a[i];
			left++;
			i++;
		}
	}
	else if (j <= e) {
		while (j <= e) {
			sorted[left] = a[j];
			left++;
			j++;
		}
	}

}


void mergeSort(int a[], int start, int end)
{
	int mid;

	if (start >= end)
		return;

	mid = (start + end) / 2;
	mergeSort(a, start, mid);
	mergeSort(a, mid + 1, end);
	merge(a, start, end, mid);

	for (int k = start; k <= end; k++)
		a[k] = sorted[k];
}

int main()
{
	int len;
	int num[10000000];

	scanf("%d", &len);
	for (int i = 0; i < len; i++)
		scanf("%d", &num[i]);

	mergeSort(num, 0, len - 1);

	for (int i = 0; i < len; i++)
		printf("%d ", num[i]);
}