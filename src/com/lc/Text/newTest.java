package com.lc.Text;

public class newTest {
    public void quickSort(int left, int right, int[] a) {
        if (left > right) {
            return;
        }
        int mark = getModel(left, right, a);
        quickSort(left, mark - 1, a);
        quickSort(mark + 1, right, a);
    }

    public int getModel(int left, int right, int[] a) {
        int period = a[left];
        int mark = left;
        for (int i = left; i <= right; i++) {
            if (a[i] < period) {
                mark++;
                temp(a, i, mark);
            }
        }
        a[left] = a[mark];
        a[mark] = period;
        return mark;
    }

    public void temp(int[] a, int ont, int second) {
        int temp = a[ont];
        a[ont] = a[second];
        a[second] = temp;
    }

    public static void main(String[] args) {
        int a[] = {1421, 22, 314, 456, 51, 73, 1, 4, 21, 15, 99, 1, 2, 51, 7, -1};
        new newTest().quickSort(0, a.length - 1, a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
