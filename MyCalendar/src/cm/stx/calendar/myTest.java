package cm.stx.calendar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class myTest {

	public static void main(String[] args) {
		String str = "2019-05-20 00:52:30.345" ;  
        // ׼����һ��ģ�壬���ַ�������ȡ����������  
        String str1 = "yyyy-MM-dd HH:mm:ss.SSS" ;  
        // ׼���ڶ���ģ�壬����ȡ����������ֱ�Ϊָ���ĸ�ʽ  
        String str2 = "yyyy��MM��dd�� HHʱmm��ss��SSS����" ;  
        SimpleDateFormat df1 = new SimpleDateFormat(str1) ;         
        SimpleDateFormat df2 = new SimpleDateFormat(str2) ;        
        Date d = null ;  
        try{  
        	//���ַ�������ȡ���ڸ�ʽ
            d = df1.parse(str) ;   
        }catch(Exception e){            
            e.printStackTrace() ;       
        }  
        System.out.println(d);
        System.out.println(df1.format(d));
        System.out.println(df2.format(d)) ;    
	}
}
