

import java.util.ArrayList;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {

		System.out.print("请输入：");
		Scanner input = new Scanner( System.in );
		String inputStr = input.nextLine();


		ArrayList<String> TwoTuples = new CharHandling().matchString(inputStr)
													.getTwoTuples();
		//遍历所构造的二元组
		for(String s : TwoTuples){
			System.out.println(s);
		}

		
	}

}
