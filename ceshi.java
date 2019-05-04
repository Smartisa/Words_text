package txt读入;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Arrays;
public class ceshi {

    /*
     * 读取指定路径下的文件名和目录名
     */
    public void getFileList() throws IOException {
    	System.out.println("请输入路径");
    	Scanner scan=new Scanner(System.in);
    	String a=scan.next();
        File file = new File(a);
        
        File[] fileList = file.listFiles();
        
        for (int i1 = 0; i1 < fileList.length; i1++) {
            if (fileList[i1].isFile()) {
                String fileName = fileList[i1].getName();
                if(fileName.indexOf(".txt")!=-1)
                {
                	Word word=new Word();                                      //单词的链头
                    Word lian,xin;                                             
                    String str="";
                    String S=a+"\\"+fileName;
                    System.out.println(S);
                    FileReader f=new FileReader(S);                //读取英文文件
                    char[] c=new char[1];                                 //每次读取一个字母
                    int b=0;
                    boolean exist=false;                              //判断单词是否存在于  word 链中
                    while((b=f.read(c))!=-1)                              //每次读取一个字母直到最后
                    {
                        //如果字符为  换行、空格、单引号、双引号、逗号、句号  则为一个单词的结束及另一个单词的开始
                        if(String.valueOf(c).equals("\r")||String.valueOf(c).equals("\n")||String.valueOf(c).equals(" ")||String.valueOf(c).equals(",")||String.valueOf(c).equals(".")||String.valueOf(c).equals("\"")||String.valueOf(c).equals("'"))
                        {
                            lian=word;
                            while(lian!=null)            
                            {
                                if(lian.value.equalsIgnoreCase(str))           //如果单词在单词链中存在，则单词个数++
                                {
                                    lian.geshu++;exist=true;break;
                                }
                                else
                                {
                                    lian=lian.next;
                                }
                            }
                            if(exist==false)                        //如果不存在，则在单词链中添加
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
                        else                                      //单词
                        {
                            str+=String.valueOf(c);
                        }
                    }
                    //   循环10次
                    System.out.println("请输入您想查询的前几个出现此处最多的单词");
                	int N=scan.nextInt();
                    for(int i=1;i<=N;i++)                   
                    {
                        xin=new Word("",0);
                        lian=word.next;
                        //找到单词链中个数最多的
                        while(lian!=null)
                        {
                            if(lian.geshu>xin.geshu)
                            {
                                xin=lian;
                            }
                            lian=lian.next;
                        }
                        //输出单词链中个数最多的
                        System.out.println("第"+i+"个 :"+xin.value+"个数："+xin.geshu);
                        lian=word;
                        //删除单词链中单词个数最多的
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
                System.out.println("文件：" + fileName);                
            }
            
        }
    }
    
    public static void main(String[] args) throws IOException {
        ceshi rf = new ceshi();
        rf.getFileList();
    }
}