#define _CRT_SECURE_NO_WARNINGS
//Merge Sort
#include<stdio.h>
int sorted[1000];

void merge(int a[], int s, int e, int m) // 정렬된 것 합병
{
	int i = s, j = m + 1;
	int left = s;

	while (i <= m && j <= e) { //mid 기준으로 오른쪽 정렬, 왼쪽 정렬 부분을 비교하여 새로운 리스트에 넣으며 합병
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

	//한쪽에 큰게 몰릴 수 있기 때문에 남아있는 원소를 정리
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


void mergeSort(int a[], int start, int end) // 부분리스트 생성 및 정렬
{
	int mid;

	if (start >= end)
		return;

	mid = (start + end) / 2;
	mergeSort(a, start, mid);
	mergeSort(a, mid + 1, end); // 끝까지 쪼개진 후부터 return돼서 merge가 실행됨
	merge(a, start, end, mid);

	for (int k = start; k <= end; k++)
		a[k] = sorted[k];
}

int main()
{
	int len;
	int num[1000];

	scanf("%d", &len);
	for (int i = 0; i < len; i++)
		scanf("%d", &num[i]);

	mergeSort(num, 0, len - 1);

	for (int i = 0; i < len; i++)
		printf("%d ", num[i]);
}