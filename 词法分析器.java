import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.*;

public class exp1_test {

    public static void main(String[] args) throws Exception {
        LinkedHashMap<Integer,String> map = new LinkedHashMap<Integer, String>();
        int index = 0;
        int ind = 0;

        //读取需要处理的源文件
        String str = fileRead();
        if(str.contains("\\ ")){
            int in = str.indexOf("\\ ");
            StringBuilder sn = new StringBuilder(str);
            sn.replace(in,in+2,"");
            str = ""+sn;
        }
        str = shortStr(str);
        String sout = str.concat("#");
        sout = sout.replace(" #","#");
        //生成预处理txt
        PrintStream ps = new PrintStream("/Users/uu/Desktop/编译原理/预处理.txt");
        System.setOut(ps);
        System.out.println(sout);


        String strn = str;
        StringBuilder sb = new StringBuilder(str);
        if(str.contains("IF")){
            ind = strn.indexOf("IF");
            index = str.indexOf("IF");
            map.put(index,"IF");
            strn = ""+sb.replace(ind,ind+2," ");
            while(true){
                index = str.indexOf("IF",index+1);
                if (index<0) break;
                map.put(index,"IF");
            }
            while(true){
                ind = strn.indexOf("IF",ind+1);
                if(ind<0) break;
                strn = ""+sb.replace(ind,ind+2," ");
            }
        }
        if(str.contains("ELSE")){
            ind = strn.indexOf("ELSE");
            index = str.indexOf("ELSE");
            map.put(index,"ELSE");
            strn = ""+sb.replace(ind,ind+4," ");
            while(true){
                index = str.indexOf("ELSE",index+1);
                if (index<0) break;
                map.put(index,"ELSE");
            }
            while(true){
                ind = strn.indexOf("ELSE",ind+1);
                if(ind<0) break;
                strn = ""+sb.replace(ind,ind+4," ");
            }
        }
        if(str.contains("THEN")){
            ind = strn.indexOf("THEN");
            index = str.indexOf("THEN");
            map.put(index,"THEN");
            strn = ""+sb.replace(ind,ind+4," ");
            while(true){
                index = str.indexOf("THEN",index+1);
                if (index<0) break;
                map.put(index,"THEN");
            }
            while(true){
                ind = strn.indexOf("THEN",ind+1);
                if(ind<0) break;
                strn = ""+sb.replace(ind,ind+4," ");
            }
        }
        if(str.contains("GOTO")){
            ind = strn.indexOf("GOTO");
            index = str.indexOf("GOTO");
            map.put(index,"GOTO");
            strn = ""+sb.replace(ind,ind+4," ");
            int count = 0;
            String st = null;
            String st_2 = null;
            while(true){
                char[] s = new char[10];
                int k=0;
                for (int j=index+5;str.charAt(j)!=' ';j++){
                    s[k] = str.charAt(j);
                    k++;
                }
                char[] sx = new char[k+1];
                char[] mx = new char[k];
                k=0;
                for (int j=index+5;str.charAt(j)!=' ';j++){
                    sx[k] = str.charAt(j);
                    k++;
                }
                k=0;
                for (int j=index+5;str.charAt(j)!=' ';j++){
                    mx[k] = str.charAt(j);
                    k++;
                }
                sx[k]='?';
                st=new String(sx);
                st_2 = new String(mx);
                map.put(index+5,st);
                index = str.indexOf("GOTO",index+1);
                if (index<0) break;
                map.put(index,"GOTO");
            }
            strn = ""+sb.replace(ind+2,ind+2+st_2.length(),"");
            while(true){
                ind = strn.indexOf("GOTO",ind+1);
                if(ind<0) break;
                strn = ""+sb.replace(ind,ind+4," ");
                strn = ""+sb.replace(ind+5,ind+5+st_2.length(),"");

            }
        }
        if(str.contains("(")){
            ind = strn.indexOf("(");
            index = str.indexOf("(");
            map.put(index,"(");
            strn = ""+sb.replace(ind,ind+1," ");
            while(true){
                index = str.indexOf("(",index+1);
                if (index<0) break;
                map.put(index,"(");
            }
            while(true){
                ind = strn.indexOf("(",ind+1);
                if(ind<0) break;
                strn = ""+sb.replace(ind,ind+1," ");
            }
        }
        if(str.contains(")")){
            ind = strn.indexOf(")");
            index = str.indexOf(")");
            map.put(index,")");
            strn = ""+sb.replace(ind,ind+1," ");
            while(true){
                index = str.indexOf(")",index+1);
                if (index<0) break;
                map.put(index,")");
            }
            while(true){
                ind = strn.indexOf(")",ind+1);
                if(ind<0) break;
                strn = ""+sb.replace(ind,ind+1," ");
            }
        }
        if(str.contains(":")){
            ind = strn.indexOf(":");
            index = str.indexOf(":");
            map.put(index,":");
            strn = ""+sb.replace(ind,ind+1," ");
            int count = 0;
            String st = null;
            String st_2 = null;
            while(true){
                char[] s = new char[10];
                int k=0;
                for (int j=index-1;str.charAt(j)!=' ';j--){
                    s[k] = str.charAt(j);
                    k++;
                }
                char[] sx = new char[k+1];
                char[] mx = new char[k];
                k=0;
                for (int j=index-1;str.charAt(j)!=' ';j--){
                    sx[k] = str.charAt(j);
                    k++;
                }

                k=0;
                for (int j=index-1;str.charAt(j)!=' ';j--){
                    mx[k] = str.charAt(j);
                    k++;
                }
                sx[k]='?';
                st=new String(sx);
                st_2 = new String(mx);
                map.put(index-st_2.length(),st);
                index = str.indexOf(":",index+1);
                if (index<0) break;
                map.put(index,":");
            }
            strn = ""+sb.replace(ind-st_2.length(),ind,"");
            while(true){
                ind = strn.indexOf(":",ind+1);
                if(ind<0) break;
                strn = ""+sb.replace(ind,ind+1," ");
                strn = ""+sb.replace(ind-st_2.length(),ind,"");
            }
        }
        if(str.contains(">=")){
            ind = strn.indexOf(">=");
            index = str.indexOf(">=");
            map.put(index,">=");
            strn = ""+sb.replace(ind,ind+2," ");
            while(true){
                index = str.indexOf(">=",index+1);
                if (index<0) break;
                map.put(index,">=");
            }
            while(true){
                ind = strn.indexOf(">=",ind+1);
                if(ind<0) break;
                strn = ""+sb.replace(ind,ind+2," ");
            }
        }
        if(str.contains("=")){
            ind = strn.indexOf("=");
            index = str.indexOf("=");
            if(str.charAt(index-1) !='>'&& str.charAt(index-1) !='<' && str.charAt(index-1) !='='){
                map.put(index,"=");
            }
            strn = ""+sb.replace(ind,ind+1," ");
            while(true){
                index = str.indexOf("=",index+1);
                if (index<0) break;
                if(str.charAt(index-1) !='>'&& str.charAt(index-1) !='<' && str.charAt(index-1) !='='){
                    map.put(index,"=");
                }
                //map.put(index,"=");
            }
            while(true){
                ind = strn.indexOf("=",ind+1);
                if(ind<0) break;
                strn = ""+sb.replace(ind,ind+1," ");
            }
        }
        if(str.contains(">")){
            ind = strn.indexOf(">");
            index = str.indexOf(">");
            if(str.charAt(index+1) !='='){
                map.put(index,">");
            }
            strn = ""+sb.replace(ind,ind+1," ");
            while(true){
                index = str.indexOf(">",index+1);
                if (index<0) break;
                if(str.charAt(index+1) !='='){
                    map.put(index,">");
                }
            }
            while(true){
                ind = strn.indexOf(">",ind+1);
                if(ind<0) break;
                strn = ""+sb.replace(ind,ind+1," ");
            }
        }
        if(str.contains("+")){
            ind = strn.indexOf("+");
            index = str.indexOf("+");
            map.put(index,"+");
            strn = ""+sb.replace(ind,ind+1," ");
            while(true){
                index = str.indexOf("+",index+1);
                if (index<0) break;
                map.put(index,"+");
            }
            while(true){
                ind = strn.indexOf("+",ind+1);
                if(ind<0) break;
                strn = ""+sb.replace(ind,ind+1," ");
            }
        }
        if(str.contains("*")){
            ind = strn.indexOf("*");
            index = str.indexOf("*");
            map.put(index,"*");
            strn = ""+sb.replace(ind,ind+1," ");
            while(true){
                index = str.indexOf("*",index+1);
                if (index<0) break;
                map.put(index,"*");
            }
            while(true){
                ind = strn.indexOf("*",ind+1);
                if(ind<0) break;
                strn = ""+sb.replace(ind,ind+1," ");
            }
        }
        strn = shortStr(strn);
        int id = -1;
        for(int j=1;j<strn.length();j++) {
            if(strn.charAt(j-1)==' '){
                String str1 = new String();
                int k = j;
                while(strn.charAt(k)!=' '){
                    str1 = str1.concat(String.valueOf(strn.charAt(k)));
                    k++;
                }
                id = str.indexOf(str1,id+1);
                if(map.get(id)==null){
                    map.put(id,str1);
                }

            }
        }
        PrintStream ps2 = new PrintStream("/Users/uu/Desktop/编译原理/二元式表.txt");
        System.setOut(ps2);
        System.out.println(sout);
        LinkedHashMap<Integer,String> newMap = sortMap(map);
        LinkedHashMap<String,String> finalMap = new LinkedHashMap<String,String>();
        for(Map.Entry<Integer, String> entry : newMap.entrySet()){
            String ss = entry.getValue();
            if(ss.contains("?")){
                ss = ss.replace("?","");
                System.out.println("(L,"+ss+")");
                finalMap.put("L",ss);
            }else{
                if(ss.equals("IF")||ss.equals("ELSE")||ss.equals("GOTO")||ss.equals("THEN")){
                    finalMap.put("K",ss);
                    System.out.println("(K,"+ss+")");
                }else{
                    if(ss.equals("(")||ss.equals(")")||ss.equals(":")){
                        finalMap.put("P",ss);
                        System.out.println("(P,"+ss+")");
                    }else{
                        if(ss.equals("=")||ss.equals("+")||ss.equals(">")||ss.equals(">=")){
                            finalMap.put("O",ss);
                            System.out.println("(O,"+ss+")");
                        }else{
                            if(StringUtils.isNumeric(ss)){
                                finalMap.put("C",ss);
                                System.out.println("(C,"+ss+")");
                            }else{
                                finalMap.put("I",ss);
                                System.out.println("(I,"+ss+")");
                            }
                        }
                    }
                }
            }


        }

    }

    //将map按key值从小到大排序
    public static LinkedHashMap<Integer,String> sortMap(LinkedHashMap<Integer,String> map){
        List<Map.Entry<Integer, String>> infoIds =new ArrayList<Map.Entry<Integer, String>>(map.entrySet());
        //排序
        Collections.sort(infoIds, new Comparator<Map.Entry<Integer, String>>() {
            public int compare(Map.Entry<Integer, String> o1, Map.Entry<Integer, String> o2) {
                Integer p1 = o1.getKey();
                Integer p2 = o2.getKey();;
                return p1-p2;//如果要升序， 改为return Integer.valueOf(p1)-Integer.valueOf(p2);
            }
        });
        //转换成新map输出
        LinkedHashMap<Integer, String> newMap = new LinkedHashMap <Integer, String>();

        for(Map.Entry<Integer,String> entity : infoIds){
            newMap.put(entity.getKey(), entity.getValue());
        }
        return newMap;
    }

    //逆字符串
    public static String fun2(String str){
        StringBuilder sb = new StringBuilder(str);
        return  sb.reverse().toString();
    }

    //删除重复空格
    public static String shortStr(String str){
        StringBuilder sb = new StringBuilder(str);
        while (str.contains("  ")){
            sb = new StringBuilder(str);
            int ind = str.indexOf("  ");
            str = ""+sb.replace(ind,ind+2," ");
        }
        return str;
    }

    public static String fileRead() throws Exception {
        File file = new File("/Users/uu/Desktop/编译原理/实验一/实验1/source.txt");//定义一个file对象，用来初始化FileReader
        FileReader reader = new FileReader(file);//定义一个fileReader对象，用来初始化BufferedReader
        BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
        StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
        String s = "";
        while ((s = bReader.readLine()) != null)
        {
            //逐行读取文件内容，不读取换行符和末尾的空格
            if(s.contains("/*")){
                int i = s.indexOf("*/",s.indexOf("/*"));
                StringBuilder sn = new StringBuilder(s);
                sn.replace(s.indexOf("/*"),s.indexOf("*/")+2,"");
                String x = ""+sn;
                sb.append(x+" ");
            }else {
                sb.append(s+" ");//将读取的字符串添加换行符后累加存放在缓存中
            }
        }
        bReader.close();
        String str = sb.toString();
        return str;
    }


}
