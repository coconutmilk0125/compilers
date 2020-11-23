import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class exp2 {

    public static LinkedList<Prior> prior_list = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        new Init().init(prior_list);
        int i,j = 0;
        int count=1;
        new Init().beforeOutput();
        if(fileRead()==null){
            System.out.println("请先将实验1文件夹中的预处理.txt文件复制到实验2文件夹中!");
        }
        else{
            String str = fileRead();
            System.out.println(str);
            char[] s=str.toCharArray();
            for(i=0;s[i]!='#';i++){
                if(s[i]=='=' && s[i-1]!='>'){
                    for(j=i;j<str.length();j++){
                        if(s[j]==' ') break;
                    }
                    String sub = str.substring(i+1,j);
                    System.out.println("算术表达式"+count+"为："+sub);
                    count++;
                    String str_input = changeIntoInput(sub);
                    System.out.println("转换为输入串: "+changeIntoInput(sub));
                    System.out.println("步骤号 符号栈 优先关系 当前分析符 剩余输入串 动作");
                    analyse(str_input,count);

                }
            }

        }

    }

    public static void analyse(String str,Integer num) {

        int count = 1;
        List<Integer> record = new LinkedList<>();
        String str2 = str;
        Stack in = new Stack(); //剩余输入串
        Stack out = new Stack();//符号栈
        out.push('#');
        char[] s = str.toCharArray();
        for (int i = str.length() - 1; i >= 0; i--) {
            in.push(s[i]);
        }
        int j = 0;
        char temp;
        System.out.println(count + "       #       <             " + toString_zx(in) + "      移近");
        temp = (char) in.pop();
        count++;
        while (!toString(out).equals("#N#"))
        {
            if (toString(out).contains("i") == true) {
                out.pop();
                out.push('N');
                System.out.println(count + "       " + toString(out) + "       >      " + temp + "       " + toString_zx(in) + "          归约");
                record.add(count);
                count++;
                //>

            } else {
                if (toString(out).contains("(N)") == true) {
                    out.pop();
                    out.pop();
                    out.pop();
                    out.push('N');
                    //       temp = (char) in.pop();
                    System.out.println(count + "       " + toString(out) + "       >        " + temp + "     " + toString_zx(in) + "         归约");
                    record.add(count);
                    //      System.out.println("当前我在：(N)");
                    count++;
                    //=

                } else {
                    if (toString(out).contains("N+N")) {
                        out.pop();
                        out.pop();
                        out.pop();
                        out.push('N');
                        //             temp = (char) in.pop();
                        System.out.println(count + "       " + toString(out) + "       >       " + temp + "      " + toString_zx(in) + "          归约");
                        record.add(count);
                        //    System.out.println("当前我在：N+N");
                        count++;
                        //>
                    } else {
                        if (toString(out).contains("N*N")) {
                            out.pop();
                            out.pop();
                            out.pop();
                            out.push('N');
                            //               temp = (char) in.pop();
                            System.out.println(count + "       " + toString(out) + "       >       " + temp + "      " + toString_zx(in) + "           归约");
                            record.add(count);
                            //          System.out.println("当前我在：N*N");
                            count++;

                        } else {
                            out.push(temp);
                            if (toString(out).equals("#N#")) {
                                System.out.println(count + "       " + toString(out) + "       =      " + temp + "       " + toString_zx(in) + "            移近");
                                System.out.println("算术表达式"+num+"的规约产生式步骤号为："+record);
                            } else {
                                if (toString(out).contains("(N)")) {
                                    System.out.println(count + "       " + toString(out) + "       =      " + temp + "       " + toString_zx(in) + "        移近");
                                } else {
                                    System.out.println(count + "       " + toString(out) + "       <      " + temp + "       " + toString_zx(in) + "        移近");
                                }

                                temp = (char) in.pop();
                            }

                            count++;
                        }

                    }
                }
            }

        }
    }

    public static String toString(Stack stack){
        Stack s = new Stack();
        s = (Stack)stack.clone();
        String str = "";
        while(!s.empty()){
            str = str + String.valueOf((char)s.pop());
        }
        StringBuffer buffer = new StringBuffer(str);
        return buffer.reverse()+"";
    }

    public static String toString_zx(Stack stack){
        Stack s = new Stack();
        s = (Stack)stack.clone();
        String str = "";
        while(!s.empty()){
            str = str + String.valueOf((char)s.pop());
        }
        return str;
    }


    public static Boolean containNum(String sub){
        if(sub.contains("1")||sub.contains("2")||sub.contains("3")||sub.contains("4")||sub.contains("5")||sub.contains("6")||sub.contains("7")||sub.contains("8")||sub.contains("9")||sub.contains("10")){
            return true;
        }
        return false;
    }

    public static String fileRead() throws Exception {
        File file = new File("/Users/uu/Desktop/编译原理/预处理.txt");//定义一个file对象，用来初始化FileReader
        FileReader reader = new FileReader(file);//定义一个fileReader对象，用来初始化BufferedReader
        BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
        StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
        String s = "";
        while ((s = bReader.readLine()) != null)
        {
            sb.append(s+" ");//将读取的字符串添加换行符后累加存放在缓存中
        }
        bReader.close();
        String str = sb.toString();
        return str;
    }

    public static String changeIntoInput(String str){
        String str2 = str;
        String regex = "\\d*";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        while (m.find()) {
            String sk = m.group();
            String replacement = "i";
            if(containNum(sk)) {
                str2 = str2.replace(sk,replacement);
            }
        }
        return str2+"#";
    }

    public static char findChar(char a,char b){
        for(Prior p : prior_list){
            if(p.getBehind().equals(String.valueOf(a)) && p.getFront().equals(String.valueOf(b))){
                return p.getRelation().toCharArray()[0];
            }
        }
        return ' ';
    }

}
