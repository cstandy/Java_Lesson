# Java learning

This repo aims to store the source codes and some reflections while practicing Java programming.

## Environment

+ Java JDK 10.0.1
+ Java JRE 10.0.1

## Basic command to compile and run a Jave program

For compiling a Java source code, for example, `HelloWorld.java`:

```
$ javac HelloWorld.java
```

+ Use `javac -d {path} HelloWorld.java` to assign the output directory.
    - ensure output file to be in the package folder
    - enable other classes in the same package to use that class with `import`

And the above command will produce a new file (`HelloWorld.class`). To run this application, enter:

```
$ java HelloWorld
```

+ Notice that there is no `.class` behind the name of application.

## CS: Java (goo.gl/Wsx9Wn)

+ 李信杰老師
+ [FINAL](https://hackmd.io/jlLl9wDDQYajRH6R-yEyDg)

### Final Project: Monopoly

+ monopoly 分支：console 版本的遊戲
+ gui 分支：gui 版本的遊戲

### chap1 基本介紹 與 第一支程式

+ 使用Eclipse開發 (IDE)
+ `../bin/Javac`: compiler
+ `../bin/Java`: Java Virtual Machine (JVM)
+ 創建程式
    -  `File -> New -> Java Project`
    -  `src  -> New -> Package`
    -  `(package) ->New ->Class` (勾選 method->main())

+ JRE (Java runtime machine): 只含 library
+ JDK: 含complier等工具與JRE
+ Android 建立於 Java language (但架構不一樣)

### chap2 基本語法 與 變數型態

+ Object Class (程式碼) VS Object Instance (記憶體中)
+ Element of Class
    - Class Name
    - Attributes (field)
    - Method (C++ function)

+ String 在 Java 中是 Class Object
    - 已經有寫好的 Class
    - 有很多好用 method (講義 P.60~67) 
    - 包含特殊字元同 C++ 需加 `\`

+ 不能把位數較大的值 assign 給小的 (eg. int -> double)
    - 要 casting
    - `int a = (int)1.5;` 會自動無條件捨去

+ 不同變數型態的運算，系統會自動讓結果變成位數較大的變數
+ 特殊字頭
    -  `final` 同C++ `const`
    -  `static`

### chap3 輸入與輸出

+ `System` 也是一個 Class
    - out 是他的一個 Attribute
    - out 下面有可以輸出到螢幕的 Method 
    - `print`、`println`、`printf`
    - `printf("%5.2f" , variable)`
    - `%+數字` 同 `setw` / `%-`數字向左對齊
    - `.2` 表示小數點後 2 位、 `f` 表示 float point 

+ `Scanner` 類似於C++的 `fstream `
    - `Scanner (name) = new Scanner(System.in);` 創建方式
    - 用於抓取鍵盤在 Console 的輸入
    - `String line = (scanner_name).nextLine();` 用法與 C++ `getline()` 類似
    - `int x = (scanner_name).nextInt();`
    - `double x = (scanner_name).nextDouble();`
    - 還有以上兩種用法可以直接抓取 int 或 double

+ Java 的 include 用 `import`
    - `import java.xxx.yyy`
    - `import java.xxx.*` (表示以下全部import) 
    - `*` ：常規表示法中的萬用字元，表示不限長度的任意字元
    - 當內建 Class 底下冒紅線時，滑鼠指向它可以直接選擇第一項錯誤訊息，則會自動輸入 import 在上面 

+ `try...catch...`
    - 與` do...while...` 相似
    - 但專用於catch錯誤訊息使用

```java=
Scanner fileIn = null ; // initializes fileIn to empty
try
{
    fileIn = new Scanner( new FileInputStream("PathToFile"));// 嘗試打開檔案
}
catch (FileNotFoundException e) // Java自己有錯誤訊息可以呼叫
{
    System.out.println("File not found.");
    System.exit(0);
}
... Code continues here
```

### chap4 條件判斷 與 迴圈

+ 跟C++一樣的啦XD孜孜吱吱  滋滋滋滋滋滋滋滋滋滋滋滋(彣彣生病嘞)你才生病ㄇㄉ
+ `if...else... (else if)` 同C++
+ `switch...case...` 同C++
+ ==誤用注意！ `==` 與 `equals()`==

```java=
String a = new String("Java");
String b = new String("Java");

if(a == b)                              //指兩物件相等(記憶體位置相同)
    System.out.println("a==b");         //這裡是 false
if(a.equals(b))                         //指兩字串文字內容相同
    System.out.println("a equals to b");//這裡是 true
```

+ `&` 與 `&&`(`|`、`||`亦同)
    - `A && B`:當A為false則直接跳出，不執行B的判斷式(較有效率)
    - `A & B` :不管A的判斷式為true或false，仍會執行B的判斷式

+ `do...while...`、`while`、`for` 同 C++
+ `break` 的 label 用法

```java=
loop1: for(int i = 0; i <= 10; i++){
        System.out.println("i = " + i);
        for(int j = 0; j <= 10; j++){
                System.out.println("j = " + j);
                break loop1; // 跳出標籤的迴圈外
        }
}
```

+ Random 在 Java 也是一個 Class
    - `Random rnd = new Random();` 創建方式跟 `Scanner` 差不多
    - `int x = rand.nextInt(40);`  表示 rand 產生的下一個數 % 40，即 x = 0~39

### chap5 自定義 Class 呱呱 Quack!

+ 創建與架構同 C++，只是 main 包含在主 Class 中
+ `public static void main(String[] args)` main 是程式進入點（同C++）
+ instance variable VS local variable
    - instance: 直接宣告在 Class 底下、method 外，整個 Class 可用
    - local: 宣告在 method 內，在 method 外不得使用

+ 參數
    - parameter: 宣告時定義
    - argument: 使用變數時

+ `this` pointer
    - 用於 method 中分辨 parameter 與 instance variable
    - ![](https://i.imgur.com/Z67KhqV.png)
    - 上圖 `canfly` 藍字為 instance variable，黑字為 parameter

+ Class Encapsulation 封裝
    - 把變數包裝成 private
    - 增加 get/set function

+ Overloading
    - 同名字函數收不同變數
    - 系統會自己找最適合的函數

+ `StringTokenizer`
    - 定義 Sting 的分割符號
    - `StringTokenizer (name) = new StringTokenizer((stringName), ",");`

+ `static` 適合用於同 ClassName 的共用變數
+ `Math` Class 意同 C++ `cmath.h`
+ Wrapper Class
    - `Byte`, `Short`, `Integer`, `Long`, `Float`, `Double`, and `Character`
    - 可以轉換資料型態，方便又好玩

### chap6

+ Pass by reference
- Java 沒有指標，如果 function 的參數是 class，直接就是pass by reference的作法

+ Java method 註解格式
```java=
/**
  普通廢話寫這裡 _(:3」ㄥ)_
  @param 變數說明
  @return 回傳三小都寫這裡
 */
```

+ Array
    - 是一個物件，要用 new 產生
    - `BaseType[] ArrayName = new BaseType[size];`
    - 長度固定
    - `BaseType[][] 2DArrayName = new BaseType[size][size];` 也可以用二維陣列

+ ArrayList
    - `ArrayList<Type> (name) = new ArrayList<Type>();`
    - 可以使用 `add()` 一直增加成員
    - 也可以使用` remove()` 移除成員，被刪掉的空位會往前遞補
