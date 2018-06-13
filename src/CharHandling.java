import java.util.ArrayList;

/**
 * 字符串处理<br />
 * @author <i>SilenceSoul</i>
 */
public class CharHandling {
	private static ArrayList<String> TwoTuples = new ArrayList<>();//最终的二元组

	/**
	 * 将传入的字符串根据空格进行分割<br />
	 * @param str 需要进行分割的字符串
	 * @return 分割后的字符串数组
	 */
	public String[] splitString(String str) {
		return str.split(" ");
	}

	/**
	 * 粗略的匹配字符串<br />
	 * 首先直接通过分割后的字符串(通过空格进行分割)尝试获取种别码{@link Lexer#getSyn(String)} ，
	 * 若返回-1 表示需要进一步分割处理<br />
	 * @param inputStr 用户输入的字符串
	 */
	public  CharHandling matchString(String inputStr){
		Lexer lexer = new Lexer();
		String[] splistedString = this.splitString(inputStr);

		for(int i = 0;i < splistedString.length;i++){//遍历分割后的字符串
			if( lexer.getSyn(splistedString[i]) == -1 ){//不能直接获识别，需要进一步分割
				//通过贪心算法 进行进一步匹配
				this.greedyMatch(splistedString[i]);
			}else{//识别出来了,开始构造二元组
				//种别码
				int Syn = lexer.getSyn(splistedString[i]);
				//字符本身
				String TokenOrSum = splistedString[i];
				CharHandling.createTwoTuples(Syn, TokenOrSum);
			}
		}//end for

		return this;
	}

	/**
	 * <b>贪婪匹配</b><br />
	 * 传入粗略进行匹配时失败的字符串,对字符串分割，尽最大能力匹配最长的字符串,匹配成功构造二元组，失败继续调用此方法(递归)
	 * 进行匹配,直到传入的字符串全部匹配完<br />
	 * @param str 需要进行进一步匹配的字符串
	 */
	public void greedyMatch(String str){

		// 例如  x:=9: 对9匹配后会对  : 进行匹配 ，因为 : 能匹配成功，所以会continue 这时将退出for循环，而不会进入匹配不成功的else内，也就不会构造二元组
		String lastChar="";

		//为了获取到种别码所以搞个对象
		Lexer lexer = new Lexer();
		String newString = str;
		//System.out.println(newString+"  这一个不能直接识别");
		for(int j=1;j<= newString.length();j++){//对分割的字符串一个一个字符进行匹配

			String spString = newString.substring(0,j);//从0位置开始分割字符串

			if( lexer.getSyn(spString)  != -1){
				//System.out.println(spString+"  匹配成功");
				lastChar += spString;//当匹配成功了但是退出for循环时，能将这个字符串进行保存，在for循环外面进行构造二元组
				continue;
			}else {
				//String spOK;//匹配成功的前n个字符所组成的字符串
				String spOK = spString.substring(0,j-1);//第0位到j-1位是匹配成功的

				String spFailed = newString.substring(newString.indexOf(spOK)+spOK.length());
				//对spNewToken 前j-1位匹配成功的进行构造二元组
				CharHandling.createTwoTuples(lexer.getSyn(spOK), spOK);//+"  GreedMath内的"

				//从匹配成功位置到结束 截取字符串  继续分割匹配
				this.greedyMatch(spFailed);
				
				//上面的匹配好了就立刻结束，免得跑到外面的循环去了
				return;//这里结束和for外面结束是不一样的。。。。。
			}
		}//end for

		//对退出for循环的字符串构造二元组，这里不加判断也可以
		if(lexer.getSyn(lastChar) != -1){
			CharHandling.createTwoTuples(lexer.getSyn(lastChar), lastChar);//+"  GreedMath内的"
		}
	}//end method



	/**
	 * 构造最终的二元组<br />
	 * @param Syn 种别码
	 * @param TokenOrSum 单词自身
	 */
	public static void createTwoTuples(int Syn,String TokenOrSum){
		//System.out.println("("+Syn+","+TokenOrSum+")");
		String two = "("+Syn+","+TokenOrSum+")";
		TwoTuples.add(two);
	}

	/**
	 * 获取构造好的二元组集合<br />
	 * @return 所有二元组的集合
	 */
	public ArrayList<String> getTwoTuples(){
		return TwoTuples;
	}

}
/**
*　　　　　　　　┏┓　　　┏┓+ +
*　　　　　　　┏┛┻━━━┛┻┓ + +
*　　　　　　　┃　　　　　　　┃ 　
*　　　　　　　┃　　　━　　　┃ ++ + + +
*　　　　　　 ████━████ ┃+
*　　　　　　　┃　　　　　　　┃ +
*　　　　　　　┃　　　┻　　　┃
*　　　　　　　┃　　　　　　　┃ + +
*　　　　　　　┗━┓　　　┏━┛
*　　　　　　　　　┃　　　┃　　　　　　　　　　　
*　　　　　　　　　┃　　　┃ + + + +
*　　　　　　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting　　　　　　　
*　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug　　
*　　　　　　　　　┃　　　┃
*　　　　　　　　　┃　　　┃　　+　　　　　　　　　
*　　　　　　　　　┃　 　　┗━━━┓ + +
*　　　　　　　　　┃ 　　　　　　　┣┓
*　　　　　　　　　┃ 　　　　　　　┏┛
*　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
*　　　　　　　　　　┃┫┫　┃┫┫
*　　　　　　　　　　┗┻┛　┗┻┛+ + + +
*
*#                      d*##$.
# zP"""""$e.           $"    $o
#4$       '$          $"      $
#'$        '$        J$       $F
# 'b        $k       $>       $
#  $k        $r     J$       d$
#  '$         $     $"       $~
#   '$        "$   '$E       $
#    $         $L   $"      $F ...
#     $.       4B   $      $$$*"""*b
#     '$        $.  $$     $$      $F
#      "$       R$  $F     $"      $
#       $k      ?$ u*     dF      .$
#       ^$.      $$"     z$      u$$$$e
#        #$b             $E.dW@e$"    ?$
#         #$           .o$$# d$$$$c    ?F
#          $      .d$$#" . zo$>   #$r .uF
#          $L .u$*"      $&$$$k   .$$d$$F
#           $$"            ""^"$$$P"$P9$
#          JP              .o$$$$u:$P $$
#          $          ..ue$"      ""  $"
#         d$          $F              $
#         $$     ....udE             4B
#          #$    """"` $r            @$
#           ^$L        '$            $F
#             RN        4N           $
#              *$b                  d$
#               $$k                 $F
#               $$b                $F
#                 $""               $F
#                 '$                $
#                  $L               $
#                  '$               $
#                   $               $
#
*
*/