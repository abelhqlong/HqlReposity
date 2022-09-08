## 一.自定义注解
### 元注解
#### Target：描述了注解修饰的对象范围，取值在java.lang.annotation.ElementType定义，常用包括：
+ ElementType.METHOD：用于描述方法
+ ElementType.PACKAGE ：用于描述包
+ ElementType.PARAMETER ：用于描述方法变量
+ ElementType.TYPE：用于描述类、接口或enum类型
+ ElementType.LOCAL_VARIABLE：用于描述局部变量
+ ElementType.FIELD：用于描述字段
+ ElementType.CONSTRUCTOR：用于描述构造方法

#### Retention：表述注解保留时间的长短，取值在java.lang.annotation.RetentionPolicy中，取值：
+ RetentionPolicy.SOURCE：在源文件中有效，编译过程中会被忽略
+ RetentionPolicy.CLASS：随源文件一起编译在class文件中，运行时忽略
+ RetentionPolicy.RUNTIME：在运行时有效,可以通过发反射读取 
#### 只有定义为RetentionPolicy.RUNTIME时，我们才能通过注解反射获取注解
@Inherited 允许子类继承，可以不加

@Documented 注解应该被 javadoc工具记录，可以不加

## 二.将请求对象,和返回数据转换为xml格式输出为日志
### 1.将请求对象转换为xml,添加三个注解，然后调用JAXBContext方法
+ @XmlAccessorType(XmlAccessType.FIELD)
+ @XmlRootElement(name = "user")
+ @XmlType(propOrder = { "id", "name" })
### 2.将返回数据转换为xml格式
~~~~
package com.gremlin.utils;

/**
* 该类提供格式化JSON字符串的方法。
* 该类的方法formatJson将JSON字符串格式化，方便查看JSON数据。
* </p><p>使用算法如下：
* </p><p>对输入字符串，追个字符的遍历
* </p><p>1、获取当前字符。
* </p><p>2、如果当前字符是前方括号、前花括号做如下处理：
* </p><p>（1）如果前面还有字符，并且字符为“：”，打印：换行和缩进字符字符串。
* </p><p>（2）打印：当前字符。
* </p><p>（3）前方括号、前花括号，的后面必须换行。打印：换行。
* </p><p>（4）每出现一次前方括号、前花括号；缩进次数增加一次。打印：新行缩进。
* </p><p>（5）进行下一次循环。
* </p><p>3、如果当前字符是后方括号、后花括号做如下处理：
* </p><p>（1）后方括号、后花括号，的前面必须换行。打印：换行。
* </p><p>（2）每出现一次后方括号、后花括号；缩进次数减少一次。打印：缩进。
* </p><p>（3）打印：当前字符。
* </p><p>（4）如果当前字符后面还有字符，并且字符不为“，”，打印：换行。
* </p><p>（5）继续下一次循环。
* </p><p>4、如果当前字符是逗号。逗号后面换行，并缩进，不改变缩进次数。
* </p><p>5、打印：当前字符。
*
*/
public class JsonFormatTool {

    /**
     * 返回格式化JSON字符串。
     * @param json 未格式化的JSON字符串。
     * @return 格式化的JSON字符串。
     */
    public static String formatJson(String json) {
        StringBuffer result = new StringBuffer();

        result.append('\n');
        int length = json.length();
        int number = 0;
        char key = 0;
        //遍历输入字符串。
        for (int i = 0; i < length; i++) {
            //1、获取当前字符。
            key = json.charAt(i);
            //2、如果当前字符是前方括号、前花括号做如下处理：
            if((key == '[') || (key == '{') ) {
                //（1）如果前面还有字符，并且字符为“：”，打印：换行和缩进字符字符串。
                if((i - 1 > 0) && (json.charAt(i - 1) == ':')) {
                    result.append('\n');
                    result.append(indent(number));
                }
                //（2）打印：当前字符。
                result.append(key);
                //（3）前方括号、前花括号，的后面必须换行。打印：换行。
                result.append('\n');
                //（4）每出现一次前方括号、前花括号；缩进次数增加一次。打印：新行缩进。
                number++;
                result.append(indent(number));
                //（5）进行下一次循环。
                continue;
            }
 
            //3、如果当前字符是后方括号、后花括号做如下处理：
            if((key == ']') || (key == '}') ) {
                //（1）后方括号、后花括号，的前面必须换行。打印：换行。
                result.append('\n');
                //（2）每出现一次后方括号、后花括号；缩进次数减少一次。打印：缩进。
                number--;
                result.append(indent(number));
                //（3）打印：当前字符。
                result.append(key);
                //（4）如果当前字符后面还有字符，并且字符不为“，”，打印：换行。
                if(((i + 1) < length) && (json.charAt(i + 1) != ',')) {
                    result.append('\n');
                }
                //（5）继续下一次循环。
                continue;
            }
 
            //4、如果当前字符是逗号。逗号后面换行，并缩进，不改变缩进次数。
            if((key == ',')) {
                result.append(key);
                result.append('\n');
                result.append(indent(number));
                continue;
            }
            //5、打印：当前字符。
            result.append(key);
        }
        return result.toString();
    }
 
    /**
     * 返回指定次数的缩进字符串。每一次缩进三个空格，即SPACE。
     * @param number 缩进次数。
     * @return 指定缩进次数的字符串。
     */
    private static String indent(int number) {
        StringBuffer result = new StringBuffer();
        for(int i = 0; i < number; i++) {
            result.append("   ");
        }
        return result.toString();
    }
}