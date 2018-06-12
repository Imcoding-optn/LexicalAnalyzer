import java.util.HashMap;
import java.util.Map;

/**
 * 词法类<br />
 * 可直接修改 {@link Lexer#Lexer()} 进行构造种别码表<br />
 * @author <i>SilenceSoul</i>
 */
public class Lexer {
	private static int syn;//种别码
	private static Map<String,Integer>  keyWord = new HashMap<>();//关键字
	//static Map<String,Integer>  Letter = new HashMap<>();//字母 |字母+数字
	//static Map<String,Integer>  Dight = new HashMap<>();//数字
	private static Map<String,Integer>  OperatorChar = new HashMap<>();//运算符
	private static Map<String,Integer>  OtherChar = new HashMap<>();//其他符号

	/**
	 * 构造种别码表<br />
	 */
	Lexer(){
		keyWord.put("begin", 1);
		keyWord.put("if", 2);
		keyWord.put("then", 3);
		keyWord.put("while", 4); 
		keyWord.put("do", 5);
		keyWord.put("end", 6);

		OperatorChar.put(":=", 18);
		OperatorChar.put("<>", 21);
		OperatorChar.put("<=", 22);
		OperatorChar.put(">=", 24);
		OperatorChar.put("+", 13);
		OperatorChar.put("-", 14);
		OperatorChar.put("*", 15);
		OperatorChar.put("/", 16);
		OperatorChar.put("<", 20);
		OperatorChar.put(">", 23);
		OperatorChar.put("=", 25);

		OtherChar.put(":", 17);
		OtherChar.put(";", 26);
		OtherChar.put("(", 27);
		OtherChar.put(")", 28);
		OtherChar.put("#", 0);
	}

	/**
	 * 根据输入的字符获取关键字或者其它字符的种别码<br />
	 * @param word  输入的字符
	 * @return 匹配成功返回种别码,失败返回-1,表示需要进一步进行处理
	 */
	public int getSyn(String word){
		word=word.trim();//传进来的字符串可能还有空格
		if(!word.equals("") &&word != null){//输入不能为空
			
			/*  ================ 1 ==================  */
			//若为变量 开始不能是数字
			if(  (word.charAt(0) >= 'a' && word.charAt(0) <= 'z') || ( word.charAt(0) >= 'A' && word.charAt(0) <= 'Z') ){
				if( keyWord.get(word) != null){//查找关键字表
					//System.out.println("该关键字的种别码  "+keyWord.get(word));
					return keyWord.get(word);
				}else {//不是关键字   可能是纯字母 也可能是字母加数字
					//下面判断字符串内的每个字符 是否是字母 或者数字(变量名) 
					int flag = 0;
					for(char s:word.toCharArray()){//遍历字符串内的每一个字符
						if( (s >='a'&&s<= 'z')||(s>='A'&&s<='Z')||(s>='0'&&s<='9')  ){
							flag++;
						}else break;//只要有一位不是就退出
					}
					if(flag == word.length()) return 10;//System.out.println("字母(变量)的种别码  "+10);
					//else System.err.println(word +" 非法字符");//非法字符的处理。。。。以后再说
				}
			/*  ================ 2 ==================  */
			}else if(OperatorChar.get(word) != null){//查找操作符表
				//System.out.println("该操作符的种别码   "+OperatorChar.get(word));
				return OperatorChar.get(word);
			/*  ================ 3 ==================  */
			}else if(OtherChar.get(word) != null){//查找其它字符表
				//System.out.println("其它字符的种别码  "+OtherChar.get(word));
				return OtherChar.get(word);
			/*  ================ 4 ==================  */
			}else if(isNumeric(word)){//判断是不是数字
				//System.out.println("数字的种别码为  "+11);
				return 11;
			}
		}else {
			System.err.println("空字符！！！");
			//System.exit(0);
		}
		
		//不能正确匹配的字符返回-1  需要进一步进行判断
		return -1;
	}

	
	/**
	 * 判断输入的字符串是不是数字<br />
	 * @param String  输入的字符
	 * @return boolean
	 */
	public static boolean isNumeric(String str){  
		for (int i = 0; i < str.length(); i++){  
			if (!Character.isDigit(str.charAt(i))){  
				return false;  
			}  
		}  
		return true;  
	}
	
	/**
	 * 判断输入的字符是不是数字<br />
	 * @param Char 输入的字符
	 * @return 是数字的返回TRUE，否则返回FALSE
	 */
	public static boolean isNumeric(char aChar){  
		if ( !Character.isDigit(aChar) ){  
			return false;  
		}  
		return true;  
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