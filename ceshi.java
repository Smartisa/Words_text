package txt����;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Arrays;
public class ceshi {

    /*
     * ��ȡָ��·���µ��ļ�����Ŀ¼��
     */
    public void getFileList() throws IOException {
    	System.out.println("������·��");
    	Scanner scan=new Scanner(System.in);
    	String a=scan.next();
        File file = new File(a);
        
        File[] fileList = file.listFiles();
        
        for (int i1 = 0; i1 < fileList.length; i1++) {
            if (fileList[i1].isFile()) {
                String fileName = fileList[i1].getName();
                if(fileName.indexOf(".txt")!=-1)
                {
                	Word word=new Word();                                      //���ʵ���ͷ
                    Word lian,xin;                                             
                    String str="";
                    String S=a+"\\"+fileName;
                    System.out.println(S);
                    FileReader f=new FileReader(S);                //��ȡӢ���ļ�
                    char[] c=new char[1];                                 //ÿ�ζ�ȡһ����ĸ
                    int b=0;
                    boolean exist=false;                              //�жϵ����Ƿ������  word ����
                    while((b=f.read(c))!=-1)                              //ÿ�ζ�ȡһ����ĸֱ�����
                    {
                        //����ַ�Ϊ  ���С��ո񡢵����š�˫���š����š����  ��Ϊһ�����ʵĽ�������һ�����ʵĿ�ʼ
                        if(String.valueOf(c).equals("\r")||String.valueOf(c).equals("\n")||String.valueOf(c).equals(" ")||String.valueOf(c).equals(",")||String.valueOf(c).equals(".")||String.valueOf(c).equals("\"")||String.valueOf(c).equals("'"))
                        {
                            lian=word;
                            while(lian!=null)            
                            {
                                if(lian.value.equalsIgnoreCase(str))           //��������ڵ������д��ڣ��򵥴ʸ���++
                                {
                                    lian.geshu++;exist=true;break;
                                }
                                else
                                {
                                    lian=lian.next;
                                }
                            }
                            if(exist==false)                        //��������ڣ����ڵ����������
                            {
                                xin=new Word(str,1);
                                xin.next=word.next;
                                word.next=xin;
                                str="";
                            }
                            else
                            {
                                exist=false;
                                str="";
                            }
                        }
                        else                                      //����
                        {
                            str+=String.valueOf(c);
                        }
                    }
                    //   ѭ��10��
                    System.out.println("�����������ѯ��ǰ�������ִ˴����ĵ���");
                	int N=scan.nextInt();
                    for(int i=1;i<=N;i++)                   
                    {
                        xin=new Word("",0);
                        lian=word.next;
                        //�ҵ��������и�������
                        while(lian!=null)
                        {
                            if(lian.geshu>xin.geshu)
                            {
                                xin=lian;
                            }
                            lian=lian.next;
                        }
                        //����������и�������
                        System.out.println("��"+i+"�� :"+xin.value+"������"+xin.geshu);
                        lian=word;
                        //ɾ���������е��ʸ�������
                        while(lian.next!=null)
                        {
                            if(lian.next.value.equalsIgnoreCase(xin.value))
                            {
                                lian.next=lian.next.next;
                                break;
                            }
                            lian=lian.next;
                        }
                    }
                }
                System.out.println("�ļ���" + fileName);                
            }
            
        }
    }
    
    public static void main(String[] args) throws IOException {
        ceshi rf = new ceshi();
        rf.getFileList();
    }
}