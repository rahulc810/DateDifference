package com.scratch.ds;

import java.util.Arrays;

//[1, 2, 4, 2] [3,5]  => [3,4]
public class Problem1 {

	public static void main(String[] args) {
		//int[] arr1 = { 1, 2, 4, 2 };
		int []arr1 = { 1, 2, 4, 5, 7, 8, 11, 15 };
		int[] arr2 = { 3, 4 };
		
		
		System.out.println(Arrays.asList(solution(arr1, arr2)));
	}

	static Integer[] solution(int[] arr1, int[] arr2) {
		Integer[] ret = new Integer[arr2.length];

		//nlogn
		Arrays.sort(arr1);
		
		for(int i=0;i<arr2.length;i++) {
			ret[i]= modifiedBinarySearch(arr2[i], arr1) +1;
		}
		return ret;
	}
	
	static int modifiedBinarySearch(int key, int[] sortedArr) {
		int start = 0;
		int end = sortedArr.length -1;
		
		int mid;
		
		while(end-start > 1) {
			mid = (end + start) /2;
			
			if (key < sortedArr[mid]) {
				end = mid;
			}else if (key > sortedArr[mid]) {
				start = mid;
			}else {
				return mid;
			}
		}
		
		if (end==start && sortedArr[end]<=key) {
			return end;
		}
		
		if (end-start== 1) {
			if (sortedArr[end] <= key)
				return end;
			if (sortedArr[start] <= key)
				return start;
		}
			
		return -1;
	}

}
