/**
 * 人民币转换为大写
 * @author Jeby Sun
 * 2016-1-5 下午12:37:49
 */
public class MoneyConvert {
	
	private static String[] num = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
	private static String[] level = {"", "拾", "佰", "仟", "万", "亿"};
	private static String[] unit = {"圆", "角", "分"};

	/**
	 * @param args
	 * @author Jeby Sun
	 */
	public static void main(String[] args) {
//		convert(302008);
		convertStr(2525_2362_10842L);
	}
	
	
	public static String convert(float n) {
		String result = null;
		float a = n*100;
		System.out.println(a);
		for (float i=1.0F; i<10000000.0F; i*=10.0F) {
			float temp = n%i;
			n -= temp;
			System.out.println(temp);
		}
		return result;
	}
	
	public static String convert(int n) {
		System.out.println(n);
		String result = "";
		for (int i=0; i<10; i++) {
			int t = (int)Math.pow(10, i+1);
			if (t<=n*10) {
				int temp = n%t;
				n = n - temp;
				int in = temp/(t/10);
				if (in==0) {
					if (!result.startsWith("零")) {
						result = num[in] + result;
					}
				} else {
					result = num[in]+level[i] + result;
				}
			}
		}
		System.out.println(result);
		return result;
	}
	
	public static String convertStr(long n) {
		System.out.println(n);
		String strN = String.valueOf(n);
		String result = "";
		for (int i=strN.length()-1; i>=0; i--) {
			int inde = Integer.parseInt(strN.charAt(i)+"");
			result = (num[inde] + level[strN.length()-1-i])+ result;
		}
		
		System.out.println(result);
		return result;
	}
	

}
