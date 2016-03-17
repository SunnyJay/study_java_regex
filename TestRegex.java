import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex {

	public static void main(String[] args) 
	{
		fun5();
	}
	
	
	public static void fun1()
	{
		boolean ret = false;
		ret = Pattern.matches("\\d+","2223");//����true 
		System.out.println(ret);
		
		ret = Pattern.matches("\\d+","2223aa");//����false,��Ҫƥ�䵽�����ַ������ܷ���true,����aa����ƥ�䵽 
		System.out.println(ret);
		
		ret = Pattern.matches("\\d+","22bb23");//����false,��Ҫƥ�䵽�����ַ������ܷ���true,����bb����ƥ�䵽 
		System.out.println(ret);
		
		Pattern p = Pattern.compile("\\w+");
	}
	
	public static void fun2()
	{
		boolean ret = false;
		
		Pattern p = Pattern.compile("\\d+"); 
		Matcher m = p.matcher("22bb23"); 
		ret = m.matches(); //����false,��Ϊbb���ܱ�\d+ƥ��,���������ַ���ƥ��δ�ɹ�. 
		System.out.println(ret);
		
		Matcher m2 = p.matcher("2223"); 
		ret = m2.matches(); //����true,��Ϊ\d+ƥ�䵽�������ַ���
		System.out.println(ret);
	}
	
	public static void fun3()
	{
		boolean ret = false;
		
		Pattern p = Pattern.compile("\\d+"); 
		Matcher m = p.matcher("22bb23"); 
		ret = m.lookingAt(); //����true,��Ϊ\d+ƥ�䵽��ǰ���22 
		System.out.println(ret);
		
		Matcher m2 = p.matcher("aa2223"); 
		ret = m2.lookingAt(); //����false,��Ϊ\d+����ƥ��ǰ���aa
		System.out.println(ret);
	}
	
	public static void fun4()
	{
		boolean ret = false;
		
		Pattern p = Pattern.compile("\\d+"); 
		
		Matcher m = p.matcher("22bb23"); 
		ret = m.find(); //����true 
		System.out.println(ret);
		
		Matcher m2 = p.matcher("aa2223"); 
		ret = m2.find(); //����true 
		System.out.println(ret);
		
		Matcher m3 = p.matcher("aa2223bb"); 
		ret = m3.find(); //����true 
		System.out.println(ret);
		
		Matcher m4 = p.matcher("aabb"); 
		ret = m4.find(); //����false
		System.out.println(ret);
	}
	
	public static void fun5()
	{
		String xx = "^a";
		String vv = "abcdef";
		System.out.println(vv.matches(xx));
		
		
		int retInt = 0;
		String retStr = null;
		
		Pattern p = Pattern.compile("\\d+"); 

		Matcher m = p.matcher("aaa2223bb"); 
		m.find(); //ƥ��2223 
		
		retInt = m.start(); //����3 
		System.out.println(retInt);
		
		retInt = m.end(); //����7,���ص���2223��������� 
		System.out.println(retInt);
		
		retStr = m.group(); //����2223 
		System.out.println(retStr);

		Matcher m2 = p.matcher("2223bb"); 
		m2.lookingAt(); //ƥ��2223 
		retInt = m2.start(); //����0,����lookingAt()ֻ��ƥ��ǰ����ַ���,���Ե�ʹ��lookingAt()ƥ��ʱ,start()�������Ƿ���0 
		System.out.println(retInt);
		
		retInt = m2.end(); //����4 
		System.out.println(retInt);
		
		retStr = m2.group(); //����2223 
		System.out.println(retStr);
		
		System.out.println("*********************************");
		Matcher m3 = p.matcher("2223bb"); 
		m3.matches();   //ƥ�������ַ��� 
		m3.start();   //����0,ԭ�����Ŵ��Ҳ����� 
		m3.end();   //����6,ԭ�����Ŵ��Ҳ�����,��Ϊmatches()��Ҫƥ�������ַ��� 
		m3.group();   //����2223bb

	}
}
