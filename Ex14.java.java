/**
 * Answers to Maman 14 2019a
 * @author Maytal Twig
 * version 12/01/19
 */
public class Ex14{
	/**
	 ******************************************************   Question 1   *******************************************************
	 * The main method shows what each player chooses and sums up the amount-and Amir always wins.
	 * @param a-Array with K coins
	 * Time Complexity: O(n)
	 * Space complexity: O(1)
	 */
	public static void win (int[]a){
		int amirSum=0;
		int tamarSum=0;
		int first=0, last=a.length-1; //The coins that they can choose from
		int[] array=a;
		while (last>first){
			boolean amirChoose=(amirChoose(array));
			for (int j=0;j<array.length;j++){
				int currentScore=0;// Represented the value of the selected coin
				if(j%2==0) //Amir will start the game and play in 2 turns (double index)
				{
					currentScore=array[amirMove(amirChoose,first, last)];
					System.out.println ("Amir took "+currentScore);
					amirSum=amirSum+currentScore;
				}
				else {
					currentScore=tamarMove(array[first],array[last]);
					System.out.println ("Tamar took "+currentScore);
					tamarSum=tamarSum+currentScore;
				}
				if(currentScore==array[last]) //remove the coin that the player choose (by index)
					last--;
				else {
					first++;
				}
			}
		}
		System.out.println ("Final Score:");
		System.out.println ("Amir total "+amirSum);
		System.out.println ("Tamar total "+tamarSum);
	}

	/**
	 * The method to win - at the beginning of the game Amir checks the
	 * amount of the coins in the even index and in the odd index.
	 * @param The array at the beginning
	 * @return true- if the sum of even index is greater
	 * @return false-  if the sum of odd index is greater
	 */
	private static boolean amirChoose(int[]a) {
		int sumeven=0;
		int sumodd=0;
		for (int i=0;i<=a.length-1;i++){
			if(i%2==0)
				sumeven=sumeven+a[i];
			else
				sumodd=sumodd+a[i];
		}
		if (sumeven>=sumodd)
			return true;
		else{
			return false;
		}
	}

	/**
	 * Amir get the possible coins according to their index, And knows what to choose thanks to amirChoose
	 * @param amirChoose
	 * @param first
	 * @param last
	 * @return The selected coin
	 */
	private static int amirMove(boolean amirChoose, int first,int last){		
		if (amirChoose==true)
			if (first%2==0)
				return first;
			else {
				return last;
			}
		else {
			if (first%2!=0)
				return first;
			else {
				return last;
			}

		}
	}
	/**
	 * Tamar get the possible coins according to their values, And choose the greater
	 * @param first
	 * @param last
	 * @return The selected coin
	 */
	private static int tamarMove(int first,int last){
		if (first<=last)
			return last;
		else 
			return first;
	}

	/**
	 ******************************************************   Question 2   *******************************************************
	 * *********** Section  A *************
	 * The method 'what' checks if there is in the array 'a' consecutive numbers that Their
	 * sum is equal to the number 'num'.
	 * Meaning of the value returned from the what method:
	 * true - there consecutive numbers that Their sum is equal to "num".
	 * input: the index of the consecutive numbers that their sum is equal to "num" +true.
	 * false - there is no consecutive numbers that their sum is equal to "num".
	 * input: no +false.
	 * 
	 * 
	 * *********** Section  B *************
	 * 
	 * 	 * Time Complexity: O(n^2)
	 *	 * Space complexity: O(1)
	 *
	 *
	 * *********** Section  C *************
	 */
	/**
	 * The method 'what' checks if there is in the array 'a' consecutive numbers that their
	 * sum is equal to the number 'num'.
	 * @param array and number
	 * @return true- there are consecutive numbers that their sum is equal to num
	 * @return false - there are no consecutive numbers that their sum is equal to num
	 */
	public static boolean what(int []a,int num){
		int curSum=a[0]; //the sum, begin to calculate from the first cell
		int first = 0; //Represented the first index of the consecutive numbers
		for (int i = 1; i <= a.length; i++) { //Represented the last index of the consecutive numbers
			while (curSum>num&&first<i-1) { //If the sum is greater than the number and still at the array "border" 
				curSum = curSum-a[first]; //will continue counting from the next cell
				first++;
			} 
			if (curSum==num){ 
				System.out.println("Sum found between indexes "+first+" and "+(i-1)); 
				return true; 
			} 
			if (i < a.length) //continue counting the consecutive numbers
				curSum = curSum + a[i]; 
		} 
		System.out.println("No subarray found"); 
		return false; 
	} 

	/**
	 * * *********** Section  D *************
	 * 
	 * 	 * Time Complexity:  O(n) - Linear search
	 * Best case - the first number equals the number
	 * The algorithm will perform 6 operations,O(1).
	 * Worst case - no consecutive amounts equal to the number
	 * The algorithm will perform n operations, O(n)
	 * "Pass" the array once until it reaches the last cell, change the first index (first) without "damaging" the loop.
	 * Space complexity: O(1)- two new variables were created
	 */
	/**
	 *********** Question 3 *************
	 * A method that get a 2D array and an integer num,
	 * @return the length of the longest slope.
	 */

	public static int longestSlope(int[][] mat, int num) {		
		return longestSlope(mat, 0, 0, 0, num);
	}		

	private static int longestSlope(int[][] mat, int x, int y, int max, int num) {
		if (x==mat.length)//End of array
			return max;
		if (y==mat[x].length)//End of line
			return longestSlope(mat, x+1, 0, max, num);
		max=Math.max(max, routh(mat, x, y, num));
		return longestSlope(mat,x, y+1, max, num);
	}

	private static int border(int[][] mat, int x, int y) {//Stopping conditions
		if (x>=0&&y>=0&&x<=mat.length-1&&y<=mat[x].length-1&&mat[x][y]>0)
			return mat[x][y];
		return 0;
	}

	private static int routh(int[][] mat, int x, int y, int num) {
		int curRoute=1;
		if (border(mat,x,y)<=0)
			return 0;
		int cell=mat[x][y]-num;//will check the difference
		if (border(mat, x, y-1)==cell) 
			return curRoute+routh(mat, x, y-1, num);
		if (border(mat, x, y+1)==cell) 
			return curRoute+routh(mat, x, y+1, num);
		if (border(mat, x-1, y)==cell) 
			return curRoute+routh(mat, x-1, y, num);
		if (border(mat, x+1, y)==cell) 
			return curRoute+routh(mat, x+1, y,  num);
		return curRoute;
	}


	/**
	 *********** Question 4 *************
	 * A method that gets
	 * @param size of an array
	 * @param maximum number
	 * @return the number of options that can be filled in the numbers from 1 to the maximum so that they are arranged in a non-descending order.
	 */

	public static int howManySorted(int n,int max){
		int []array=new int[n];
		return howManySorted(array,0,1, max);
	}
	private static int howManySorted(int []array,int n,int cur, int max){	

		if (array.length==1)//Stopping condition
			return max;
		if (n==array.length)//The array is "full"
			return 1;
		if (cur>max)//Greater than the maximum
			return 0;
		if (n==0) //For the first
			array[0]=cur;
		else if (array[n]<=array[n-1]) //non-descending order
			array[n]=cur;
		return howManySorted(array, n, cur+1, max)+howManySorted(array, n+1, cur, max);//Increases the array length and the number
	}
}
