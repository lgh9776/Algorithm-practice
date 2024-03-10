#define _CRT_SECURE_NO_WARNINGS
//Merge Sort
#include<stdio.h>
int sorted[1000];

void merge(int a[], int s, int e, int m) // ���ĵ� �� �պ�
{
	int i = s, j = m + 1;
	int left = s;

	while (i <= m && j <= e) { //mid �������� ������ ����, ���� ���� �κ��� ���Ͽ� ���ο� ����Ʈ�� ������ �պ�
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

	//���ʿ� ū�� ���� �� �ֱ� ������ �����ִ� ���Ҹ� ����
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


void mergeSort(int a[], int start, int end) // �κи���Ʈ ���� �� ����
{
	int mid;

	if (start >= end)
		return;

	mid = (start + end) / 2;
	mergeSort(a, start, mid);
	mergeSort(a, mid + 1, end); // ������ �ɰ��� �ĺ��� return�ż� merge�� �����
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