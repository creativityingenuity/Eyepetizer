#kotlin笔记
##补充
###mapTo
eg:

    (0 until mTitles.size).mapTo(mTabEntities) {
        TabEntity(mTitles[it], mIconSelectIds[it], mIconUnSelectIds[it])
    }
	给每个标题赋值（until:半闭区间运算符 ： “a until b”从a到b范围内所有的值，包括a和不包括b。）
将给定的变换函数应用于原始数组的每个元素，并将结果附加到给定目标
###lateinit 和 by lazy
两个都是延迟初始化。lateinit只用于变量var，by lazy只用于常量val

lazy只获取，不赋值，并且可以多次使用，返回的也是第一次调用的结果。

lateinit是可以多次赋值的
##1.基础
1. 每行没有分号
2. 变量 常量

		var a:Int = 1
		val b:Int = 2
		var定义变量 可读可写
		var定义的是个可读的数据，只有get方法

		val 是线程安全的，并且不需要担心 null 的问题，我们自然应该尽可能地使用它
		val修饰变量是只读变量即只能赋值一次，再次赋值时就会编译错误
3. 字符串模板

		var a: String = "a"
		var b: String = "b"
		var c: String = "$a$b" -> String c = a + b
4. 自动类型转换

		Kotlin在定义变量或者常量时给他赋值，可以不指定他的类型，如：

		var a = "aaa" //这里的a是String类型
		val b = 1 //这里的b是Int类型
5. 定义函数

		无返回值
		fun abc(){}
 		fun abc(name:String,age:Int){}

		有返回值
		fun abc(name:String):String{} 返回值的类型在函数末尾指定，并用冒号":"分隔
6. 条件语句
	1. if

			Kotlin中的if语句本身就是表达式，有返回值，类似于Java的三元表达式
			c = a>b?3:4  java
			c = if(a>b) 3 else 4
			var c = if(a>b){
				3
			}else{
				4
			}
	2. when = switch

			var a = 0
			when(a){
				0 ->{}
				1->{}
				else->{}
			}
			注意：when语句在满足条件的分支执行后，会终止when语句执行，所以不用像switch语句那样每一个case都加上break。 如果很多分支需要用相同的方式处理，when语句可以把多个分支条件放在一起，用逗号,分隔，如下所示：
			var a = 0
			when (a) {
			    0 -> {}
			    1,2 -> {//如果a=1或a=2，执行该代码块}
			    else -> {}
			}
			when语句还能像if语句那样作为表达式：
			var a = 1
			var b = when (a) {
			    0 -> 0  //如果a=0，则b=0
			    1 -> 1  //如果a=1，则b=1
			    else -> 2 //如果a=其他，则b=2
			}
	3. for循环

			//java
			for (int i = 0; i < array.size(); i++) {
			    System.out.println(i);
			}
			Kotlin代码
			for (i in array.indices) {
			    println(array[i])
			}
			除了普通的用法之外，Kotlin的for循环还可以这样：
			for ((index, value) in array.withIndex()) {
			    println("the element at $index is $value")
			    //index是索引，value是值
			}
7. 关键字
	1. in

			当我们要判断5是否在1-10里面
			if (5 in 1..10){//1..10代表1-10的意思
			    return true
			}else{
			    return false
			}
	2. is = instanceof

			fun hasPrefix(x: Any) = when(x) {//kotlin中所有类都有一个共同的父类： Any
			    is String -> {//如果x是String类型，执行该代码块}
			    is Int -> {//如果x是Int类型，执行该代码块}
			    else -> false
			}
8. 集合

		Kotlin常用创建集合对象的函数：

			//只读集合，不可修改
			listOf：用于创建List对象
				var list: List<Int> = listOf<Int>()
			setOf：用于创建Set对象
				var set: Set<Int> = setOf<Int>()
			mapOf：用于创建Map对象
				var map: Map<String, Int> = mapOf<String, Int>()

			//可读写集合
			mutableListOf：用于创建MutableListOf对象
				var mutableList: MutableList<Int> = mutableListOf()
				mutableList.add(1)
				mutableList.add(2)
				mutableList.add(3)
				mutableList.remove(1)
				mutableList.get(2)
				mutableList.clear()
			mutableSetOf：用于创建MutableSetOf对象
				var mutableSet: MutableSet<Int> = mutableSetOf()
				mutableSet.add(1)
				mutableSet.add(2)
				mutableSet.add(3)
				mutableSet.remove(1)
				mutableSet.contains(2)
				mutableSet.clear()
			mutableMapOf：用于创建MutableMapOf对象
				var mutableMap: MutableMap<String, Int> = mutableMapOf()
				mutableMap.put("1", 1)
				mutableMap.put("2", 2)
				mutableMap.put("3", 3)
				mutableMap.remove("1")
				mutableMap.get("2")
				mutableMap.clear()
###? !!
"?"加在变量名后，系统在任何情况不会报它的空指针异常。

"!!"加在变量名后，如果对象为null，那么系统一定会报异常！

大多数情况下都会使用?来检测null，轮不到!!出场。!!只会在你需要对某对象进行非空判断，并且需要抛出异常时才会使用到。

	// 这是声明一个变量，问号跟在类名后面
    var room: Room? = Room()

    private fun checkRoom() {
        // 因为加上了问号，所以可以任意的把room变成空
        room = null

        // 因为在调用时加上了问号，所以程序不会抛出异常
        Log.d("TAG", "-->> room name = ${room?.roomName}")
    }
	在声明对象时，把它跟在类名后面，表示这个类允许为null；
	在调用成员时，把它跟在对象后面，表示如果为null程序就会视而不见。
>?:(对象A ?: 对象B)  在程序提示加！！的时候可用

?:表示的意思是，当对象A值为null的时候，那么它就会返回后面的对象B。

	val roomList: ArrayList<Room>? = null
    if (roomList?.size ?: 0 > 0) {    // 这一行添加了?:
        Log.d("TAG", "-->> 房间数不是0")
    }

就目前为止使，用上面的?和?:基本上能避免程序中出现的所有NullPointerException。

##2.类和对象
>普通类

	class Demo(name: String = "yt") { //主构造函数，在类名后。主构造函数不能包含任何代码，初始化的代码放到init代码块 yt默认参数

	    //属性
	    lateinit var name: String
	    var sex : String = "男"
	    //属性可不设置get set 方法，但也可以设置
	    var address : String
	        get() = "北京"
	        set(value){
	            address = value
	        }

	    init {
	        //主构造函数 中的初始化代码
	        //可以有多个init代码块，顺序执行
	        print(name)
	    }

	    //次构造函数 可通过this调用主构造
	    constructor() : this("爱你")

	    constructor(sex: String, age: Int) : this("") {
	        print("$sex $age")
	    }

	    /**
	     * 嵌套类
	     */
	    inner class DemoInner{

	    }
	}


	 //实例化demo 类 去掉new
    var demo = Demo()
    //调用属性 可直接调用
    demo.name = "yt" //print(demo.name)
    demo.sex = "女"


    //调用嵌套类
    var demoInner : Demo.DemoInner = Demo().DemoInner()

	实例2
	class Person constructor(id: Int) {//（构造函数No.0）主构造函数
	    var id = id//主构造函数初始化id
	    var name = ""
	    var age = 0
	    //（构造函数No.1）直接代理主构造函数
	    constructor(name: String, id: Int) : this(id) {
	        this.name = name
	    }
	    //（构造函数No.2）代理了构造函数No.1，间接代理主构造函数
	    constructor(name: String, age: Int, id: Int) : this(name, id) {
	        this.age = age
	    }
	}
>数据类 entity

	data class User(var userName: String, var age: Int) //var

	数据类用关键字data标识，会自动创建下面的方法：
		getter/setter方法；
		equals()/hashCode() 对；
		toString() 格式是 "User(name=Czh, age=22)"；
		componentN() 函数 按声明顺序对应于所有属性；
		copy() 函数。

	创建数据类需要注意的是：
	主构造方法至少要有一个参数，且参数必须标记为val或var
	数据类不能用open、abstract、sealed(封闭类)、inner标识

	copy()函数。在很多情况下，我们需要复制一个对象改变它的一些属性，但其余部分保持不变。 copy() 函数就是为此而生成。举个例子：

		var user = User("Czh2",22)
		var user1 = user.copy(age = 23)
>枚举类

	enum Color {   //java
	    RED, GREEN, BLUE
	}

	Kotlin定义一个枚举类
	enum class Color {
	    RED, GREEN, BLUE
	}

	enum Color { //Java为枚举类指定数值
	    RED(0xFF0000), GREEN(0x00FF00), BLUE(0x0000FF);

	    private Color(int rgb) {
	    }
	}
	Kotlin为枚举类指定数值
	enum class Color(rgb: Int) {
	    RED(0xFF0000), GREEN(0x00FF00), BLUE(0x0000FF)
	}
>继承

	/父类
	open class Food {
	    open fun banana() {}
	}
	//子类
	class Fruits : Food(){
	    override fun banana() {
	        super.banana()
	    }
	}

	Java跟Kotlin都是单继承的，也就是说，都只能有一个父类。不同的是，Kotlin使用冒号:继承，而且Kotlin的class默认不允许继承，如果想让类可以被继承，需要用open关键字来标识该类
>抽象类

abstract可以用于类的修饰、或者类成员方法的修饰，能将对应的class或fun声明成为抽象类或者抽象方法

	abstract class A {
	    abstract fun testAbsFun()
	}
	class B : A() {
	    override fun testAbsFun() {
	        //do sth...
	    }
	}

重写抽象类的方法需在方法体前面加上override关键字声明。
>接口

	Java接口跟Kotlin接口都是用interface关键字声明
		interface A{}
		interface B{}
	Java用implements实现接口
		class Foods implements A, B {}
	Kotlin用冒号:实现接口
		class Food : A, B {}
	如果Kotlin中同时存在继承类和实现接口
		//继承Food类和接口AB
		class Fruits: Food,A, B {}
		一个类可以实现多个接口，而且接口中的属性和方法都是open的，不用另外加open标识。

	接口中的方法体

	Kotlin接口中的方法可以有默认方法体，有默认方法体的方法可以不重写。而Java不支持接口里的方法有方法体。举个例子：
		interface UserImpl {
		    fun getName(): String
		    fun getAge(): Int{
		        return 22
		    }
		}
		//实现接口UserImpl，可以不重写getName()
		class User :UserImpl{
		    override fun getName(): String {
		        return "Czh"
		    }
		}

###object
>1. 对象声明(总是在 object 关键字后跟一个名称。 就像变量声明一样)->单例

**对象声明的初始化过程是线程安全的。
里面的方法和变量都是静态的。**

在Java中，单例的声明可能具有多种方式：如懒汉式、饿汉式、静态内部类、枚举等；
在Kotlin中，单例模式的实现只需要一个 object 关键字即可；

	// Kt文件中的声明方式： object 关键字声明,其内部不允许声明构造方法
	object SingleObject {
		fun test() { //... }
	}

	// 调用方式：类名.方法名()
	class Main {
		fun test() {
			SingleObject.test() //调用方式
		}
	 }
	//eg2
	object MyMachine: Machine() {
		override fun start() { //... }
	}

	abstract class Machine {
		abstract fun start()
		open fun stop() {}//只有被open修饰过的方法才能被继承，否则默认是final类型的，不可被重写；
	}

注意：对象声明不能在局部作用域（即直接嵌套在函数内部），但是它们可以嵌套到其他对象声明或非内部类中。

>3.对象表达式->匿名对象

	//匿名内部类 object是一个对象 实现View.OnClickListener
    button.setOnClickListener(object : View.OnClickListener {
        override fun onClick(v: View?) {
        }
    })
    //创建一个匿名对象
    val abc = object {
        var a = 1
        var b = 2
    }
    Toast.makeText(this, "${abc.a}${abc.b}", Toast.LENGTH_SHORT).show()

请注意，匿名对象可以用作只在本地和私有作用域中声明的类型。如果你使用匿名对象作为公有函数的返回类型或者用作公有属性的类型，那么该函数或属性的实际类型会是匿名对象声明的超类型，如果你没有声明任何超类型，就会是 Any。在匿名对象中添加的成员将无法访问。如下所示：

	class User {
	    // 私有函数，所以其返回类型是匿名对象类型
	    private fun getUserName() = object {
	        val userName = "Czh"
	    }

	    // 公有函数，所以其返回类型是 Any
	    fun getAge() = object {
	        val age = 22
	    }

	    fun get() {
	        getUserName().userName
	        //getAge().age //编译错误
	    }
	}
>4.伴生对象

类似于java中静态方法，伴生对象在类中只能存在一个

	class MyClass {
	    companion object Factory {
	        fun create(): MyClass = MyClass()
	    }
	}

	调用：val instance = MyClass.create()

companion object 中调用不到成员变量
##3.泛型
>泛型类

	Java泛型
	public class Box<T> {
	    public T value;

	    public Food(T t) {
	        value = t;
	    }
	}

	new Box<String>("123");
	new Box<Integer>(1);

	对应的Kotlin泛型
	class Box<T>(t: T) {
	    var value = t
	}
	var box: Box<String> = Box("123")
	var box2: Box<Int> = Box(123)
可以看出Java跟Kotlin定义泛型的方法都是差不多的，不同的是Java中的泛型有通配符，而Kotlin没有。
>泛型约束

泛型约束能够限制泛型参数允许使用的类型，如下所示：

	Kotlin代码
	fun <T : Comparable<T>> sort(list: List<T>) {
	}

	sort(1) //编译错误
	sort(listOf(1)) //编译通过
	上述代码把泛型参数允许使用的类型限制为 List
	Java中也有类似的泛型约束，对应的代码如下：
	public static <T extends Comparable> List<T> sort(List<T> list){
	}
如果没有指定泛型约束，Kotlin的泛型参数默认类型上界是Any，Java的泛型参数默认类型上界是Object
>out和in修饰符

out生产者（大->小）  in消费者（小->大）
any->string         stirng->any

	interface Source<out T> {
	    fun nextT(): T
	}

	fun demo(strs: Source<String>) {
	    val objects: Source<Any> = strs // 这个没问题，因为 T 是一个 out-参数
	    // ……
	}

	interface Comparable<in T> {
	    operator fun compareTo(other: T): Int
	}

	fun demo(x: Comparable<Number>) {
	    x.compareTo(1.0) // 1.0 拥有类型 Double，它是 Number 的子类型
	    // 因此，我们可以将 x 赋给类型为 Comparable <Double> 的变量
	    val y: Comparable<Double> = x // OK！
	}
>泛型函数

	不仅类可以有类型参数。函数也可以有。类型参数要放在函数名称之前：
	fun <T> singletonList(item: T): List<T> {
	    // ……
	}

	//调用
	val l = singletonList<Int>(1)
	singletonList(l)
	复制代码类似于Java的泛型方法：
	public <T> T singletonList(T item) {
	    // ……
	}

	//调用
	singletonList(1);
##4.扩展
在Kotlin中，不需要继承该类或使用像装饰者这样的任何类型的设计模式，通过一种特殊形式的声明，来实现具体实现某一具体功能。**扩展函数是静态解析的，并未对原类增添函数或者属性，对类本身没有影响**。Kotlin 支持 扩展函数 与 扩展属性。
>1.扩展函数

声明一个扩展函数，我们需要用被扩展的类型来作为他的前缀。 下面代码为Kotlin原生集合类 MutableList 添加一个 swap 函数：

	/**
     * 以被扩展的类型  作为前缀
     * 对MutableList进行扩展，为其添加swap函数
     */
    fun MutableList<Int>.swap(index : Int, num : Int){
        val tem = this[index]
        this[index] = this[num]
        this[num] = tem
    }

上面代码用MutableList作为接受者类型，对其进行扩展，为其添加一个 swap 函数，当我们调用 swap 函数时，可以这样：

	val mutableList = mutableListOf(1, 2, 3)
	mutableList.swap(1, 2) //调用扩展函数swap()
	println(mutableList)
	运行代码，得到结果
	[1,3,2]

我们想强调的是**扩展函数是静态分发的，即他们不是根据接收者类型的虚方法。 这意味着调用的扩展函数是由函数调用所在的表达式的类型来决定的， 而不是由表达式运行时求值结果决定的。**例如：

	open class C
	class D: C()
	fun C.foo() = "c"
	fun D.foo() = "d"
	fun printFoo(c: C) {
	    println(c.foo())
	}
	printFoo(D())

	这个例子会输出 "c"，因为调用的扩展函数只取决于参数 c 的声明类型，该类型是 C 类。
* 如果扩展函数的函数名跟内部成员函数的函数名冲突，会优先调用内部成员函数。
* 可以为可空的接收者类型定义扩展

		//扩展函数
		fun Any?.toString(): String {
		    if (this == null) return "null"
		    return toString()
		}

		//调用
		var a = null
		a.toString()
		println(a)
		var b = "not null"
		b.toString()
		println(b)

		结果
			null
			not null
>2.扩展属性

	class User {
	    //必须声明为public（Kotlin默认是public）
	    //否则扩展属性无法访问该变量
	    var mValue = 0
	}

	//扩展属性
	var User.value: Int
	    get() = mValue
	    set(value) {
	        mValue = value
	    }

	//调用扩展属性
	var user = User()
	user.value = 2
	println(user.value)

	结果 2
>3.扩展伴生对象

	class User {
	    companion object {
	    }
	}

	//扩展伴生对象
	fun User.Companion.foo() {
	    println("伴生对象扩展")
	}

	//调用
	User.foo()

	结果： 伴生对象扩展
>4.扩展的作用域

* 在不同的包内进行扩展，用import导入资源

		package com.demo.czh.otherpackage

		class OtherUser {

		}

		fun OtherUser.print() {
		    println("其他包的")
		}
		复制代码在其他包中调用
		package com.demo.czh.activitydemo

		import com.demo.czh.otherpackage.OtherUser
		import com.demo.czh.otherpackage.print

		User().print()
		OtherUser().print()
* 扩展声明为成员

在一个类内部你可以为另一个类声明扩展，如下所示：

	//定义User类，添加一个printUser()函数
	class User {
	    fun printUser(){
	        println("User")
	    }
	}

	//定义User2类，在里面对User类进行扩展
	class User2 {
	    fun printUser2() {
	        println("User2")
	    }

	    //扩展函数
	    fun User.print() {
	        printUser()
	        printUser2()
	    }

	    fun getUser(user: User) {
	        //调用扩展函数
	        user.print()
	    }
	}

	//调用
	User2().getUser(User())

扩展声明所在的类的实例为**分发接收者**，扩展方法调用所在的接收者的实例称为**扩展接收者**，对于分发接收者和扩展接收者的成员名字冲突的情况，扩展接收者优先。如果要引用分发接收者的成员，可以这样写：

	//User类不变
	class User {
	    fun printUser(){
	        println("User")
	    }
	}

	//User2
	class User2 {
	    fun printUser() {
	        println("User2")
	    }

	    fun User.print() {
	        printUser()
	        //表示调用User2的printUser()函数
	        this@User2.printUser()
	    }

	    fun getUser(user: User) {
	        //调用扩展方法
	        user.print()
	    }
	}
##5.内联
###内联函数
将内联函数的函数在编译期间复制到调用处来实现内联

inline 的工作原理就是将内联函数的函数体复制到调用处实现内联。所以如果我们的方法比较大或者调用的比较多的话，那么编译器生成的代码量就会变大。

	inline fun <reified T : Activity> Activity.newIntent() {
	    val intent = Intent(this, T::class.java)
	    startActivity(intent)
	}


reified 的意思是具体化。在内联函数中，可以获取泛型的真实类型。这是因为在编译期间，该泛型参数在代码拷贝中被解析成实参类型，从而避免了类型擦除过程。当需要获取泛型参数的真实类型时，使用reified进行声明，此后可用于is、as、T::class等操作。

###内联扩展函数之let
使用场景：

* 最常用的场景就是使用let函数处理需要针对一个可null的对象统一做判空处理（避免写一些判断null的操作）
* 去定义一个变量在一个特定的作用域范围内

		fun main(args: Array<String>) {
		    val list: MutableList<String> = mutableListOf("A","B","C")
		    val change: Any
		    change = list.let {
				//表示list不为null的条件下，才会去执行let函数体
		        it.add("D")//在let中，用it表示引用对象，并可调用其方法
		        it.add("E")
		        it.size//返回值是语句块的最后一行，若最后一行语句无返回值，则整个let语句块也无返回值
		    }
		    println("list = $list") list = [A, B, C, D, E]
		    println("change = $change") 5
		}

>1.内联函数

使用高阶函数会带来一些运行时的效率损失。每一个函数都是一个对象，并且会捕获一个闭包。 即那些在函数体内会访问到的变量。 内存分配（对于函数对象和类）和虚拟调用会引入运行时间开销。这时可以通过内联函数消除这类的开销。举个例子：

	fun printName(a: String, name: (str: String) -> String): String {
	    var str = "$a${name("Czh")}"
	    return str
	}

	println(printName("Name:", { name -> getName("Czh") }))
上面代码中，printName函数有一个函数类型的参数，通过Lambda表达式向printName函数传入参数值，Kotlin编译器会为Lambda表达式单独创建一个对象，再将Lambda表达式转换为相应的函数并调用。如果这种情况出现比较多的时候，就会很消耗资源。这是可以在函数前使用inline关键字，把Lambda函数内联到调用处。如下所示：

	inline fun printName(a: String, name: (str: String) -> String): String {
	    var str = "$a${name("Czh")}"
	    return str
	}

	println(printName("Name:", { name -> getName("Czh") }))
>2.禁用内联

通过inline关键字，编译器将Lambda函数内联到调用处，消除了运行时消耗。但内联可能导致生成的代码增加，所以需要避免内联比较大的Lambda表达式。如果想禁用一些Lambda函数的内联，可以使用noinline修饰符禁用该Lambda函数的内联，如下所示：

	inline fun printName(name1: (str1: String) -> String
	                     , noinline name2: (str2: String) -> String): String {
	    var str = "${name1("Name:")}${name2("Czh")}"
	    return str
	}
>3.内联属性


inline关键字除了可以使函数内联之外，还能内联没有幕后字段(field)的属性，如下所示：

	val foo: Foo
	    inline get() = Foo()

	var bar: Bar
	    get() = ……
	    inline set(v) { …… }

##6.委托
当一个对象要进行操作时，可以将这个对象委托给另一个对象来进行这个操作。可以看作时替代继承的一种方式

>使用场景

* 有多个类或属性重复使用同一段代码。
* 实现继承的代替方式
* 可以使用标准委托，来实现一些特殊的功能，当然，自己也可以自定义一些
>1.类委托

委托模式是实现继承一个很好的的替代方式，Kotlin支持委托模式，不用为了实现委托模式而编写样板代码。举个例子：

	//定义一个接口 Base
	interface Base {
	    fun print()
	}

	//定义一个 ImplBase 实现接口 Base
	class ImplBase(val i: Int) : Base {
	    override fun print() {
	        println(i)
	    }
	}

	//使用委托模式，将 Base 里的所有属性以及方法委托给 Drived，并不用去重写方法了
	class Drived(b: Base) : Base by b

	//调用 print() 方法
	var b = ImplBase(10)
	Drived(b).print()

	//运行代码，打印结果为 10
从上面代码可以看出，Derived 类通过使用 by 关键字将 Base 接口的 print 方法委托给对象 b ，如果不进行委托的话，则要 override Base 接口的 print 方法。
如果出现委托后仍然 override 的情况，编译器会使用你的 override 实现取代委托对象中的实现，如下所示：

	//委托后仍然 override
	class Drived(b: Base) : Base by b {
	    override fun print() {
	        println("abc")
	    }
	}

	//调用 print() 方法
	var b = ImplBase(10)
	Drived(b).print()

	//运行代码，打印结果为 abc
>2.属性委托

在实际应用中，有很多类的属性都拥有 getter 和 setter 函数，这些函数大部分都是相同的。Kotlin允许委托属性，把所有相同的 getter 和 setter 函数放到同一个委托类中，这样能大大减少冗余代码。举个例子：

	class User1 {
	    var userName: String = ""
	        get() = field
	        set(value) {
	            field = value
	        }
	}
	class User2 {
	    var userName: String = ""
	        get() = field
	        set(value) {
	            field = value
	        }
	}
User1和User2都有相同的 getter 和 setter 函数，把它们放到委托类中，如下：

	//定义一个委托类Delegate
	class Delegate {
	    var userName = ""

	    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
	        println("getValue  类名：$thisRef, 属性名：${property.name}")
	        return userName
	    }

	    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
	        println("setValue  类名：$thisRef, 属性名：${property.name}，值：$value")
	        userName = value
	    }
	}

	//将 userName 委托给 Delegate
	class User1 {
	    var userName: String by Delegate()
	}
	class User2 {
	    var userName: String by Delegate()
	}

	//调用 getter 和 setter 函数
	var user1 = User1()
	user1.userName = "user1"
	println(user1.userName)
	var user2 = User2()
	user2.userName = "user2"
	println(user2.userName)

>3.标准委托

Kotlin标准库中提供了一些有用的委托函数：

*3.1 延迟加载

好处：程序启动更快、效率使用内存

lazy()是接受一个 lambda 表达式作为参数，并返回一个 Lazy <T> 实例的函数，返回的实例作为一个委托，第一次调用 get() 会执行已传递给 lazy() 的 lambda 表达式并记录结果， 之后再调用 get() 返回记录的结果。

	val lazyValue: String by lazy {
	    println("computed!")
	    "Hello"
	}

	//调用两次
	println(lazyValue)
	println(lazyValue)
	结果：
	computed
	Hello
	Hello
**默认情况下，对于 lazy 属性的求值是同步锁的（synchronized）：该值只在一个线程中计算，并且所有线程会看到相同的值。如果初始化委托的同步锁不是必需的，这样多个线程可以同时执行，那么将 LazyThreadSafetyMode.PUBLICATION 作为参数传递给 lazy() 函数。如下所示：**

	val lazyValue: String by lazy(LazyThreadSafetyMode.PUBLICATION ) {
	    "Hello"
	}
而如果你确定初始化将总是发生在单个线程，那么你可以使用 LazyThreadSafetyMode.NONE 模式， 它不会有任何线程安全的保证和相关的开销，如下所示：

	val lazyValue: String by lazy(LazyThreadSafetyMode.NONE) {
	    "Hello"
	}

用在方法上

	//kotlin 封装：
	fun <V : View> Activity.bindView(id: Int): Lazy<V> = lazy {
	    viewFinder(id) as V
	}
* 可观察属性委托

实现可观察属性委托的函数是`Delegates.observable()`，当我们使用该委托函数时，可以观察属性的变化，如下所示：

	var name: String by Delegates.observable("Czh") { property, oldValue, newValue ->
	    println("属性名：$property  旧值：$oldValue  新值：$newValue")
	}

	//修改name的值
	name = "abc"
	name = "hello"
	运行代码，得到结果：
	属性名：property name (kotlin reflection is not available)  旧值：Czh 新值：abc
	属性名：property name (kotlin reflection is not available)  旧值：abc 新值：hello

`Delegates.observable()`接收两个参数，第一个是初始值，第二个是修改时处理程序（handler）。 每当我们给属性赋值时会调用该处理程序，他有三个参数，第一个是被赋值的属性，第二个是旧值，第三个是新值。
如果想拦截属性的赋值操作，并且否决他的赋值操作，可以用vetoable()取代 observable()，传递给vetoable()的修改时处理程序会返回一个boolean类型，如果返回true，允许赋值，返回false则反之。如下所示：

	var name: String by Delegates.vetoable("Czh") { property, oldValue, newValue ->
	    if (newValue.equals("abc")) {
	        println("属性名：$property  旧值：$oldValue  新值：$newValue")
	        true
	    } else {
	        println("不能修改为除了abc以外的值")
	        false
	    }
	}

	//修改name的值
	name = "abc"
	name = "hello"
	结果：
	属性名：property name (kotlin reflection is not available)  旧值：Czh 新值：abc
	不能修改为除了abc以外的值
* Delegates.notNull()

notNull 适用于那些无法在初始化阶段就确定属性值的场合。

有时我们有一个非空的 var ，但我们在构造函数中没有一个合适的值，比如它必须稍后再分配。问题是你不能持有一个未初始化并且是非抽象的属性：

	class Foo {
	    var bar: Bat //错误必须初始化
	}
我们可以用 null 初始化它，但我们不用每次访问时都检查它。
Delegates.notNull()可以解决这个问题

	var name: String by Delegates.notNull()
如果这个属性在第一次写之前读，它就会抛出一个异常，只有分配之后才会正常。

	class User {
	    var name: String by Delegates.notNull()

	    fun init(name: String) {
	        this.name = name
	    }
	}

	  fun main(args: Array<String>) {
	    val user = User()
	    // user.name -> IllegalStateException
	    user.init("Carl")
	    println(user.name)
	}
	//Carl

* Map委托

Map委托是指用Map实例自身作为委托来实现委托属性，通常用于解析 JSON ，如下所示：

	//新建User类，主构函数要求传入一个Map
	class User(val map: Map<String, Any>) {
	    //声明一个 String 委托给 map
	    val name: String by map
	    //因为 Map 为只读，所以只能用 val 声明
	    val age: Int     by map
	}

	var map = mapOf("name" to "Czh", "age" to 22)
	var user = User(map)
	println("${user.name}  ${user.age}")
	//打印结果为  Czh  22
因为Map只有getValue方法而没有setValue方法，所以不能通过User对象设置值，这时可以把User的主构函数改为传入一个MutableMap，并把属性委托给MutableMap，如下所示：

	class User(val map: MutableMap<String, Any>) {
	    //因为MutableMap为读写，可以用var声明
	    var name: String by map
	    var age: Int     by map
	}

	var map = mutableMapOf("name" to "Czh", "age" to 22)
	var user = User(map)
	user.name = "James Harden"
	user.age = 28
	println("${user.name}  ${user.age}")
	//打印结果为  James Harden  28

##7.反射和注解
###反射
反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意一个方法和属性，这种动态获取的信息以及动态调用对象的方法的功能称为java语言的反射机制。

Kotlin中使用反射功能所需的运行时组件作为单独的 JAR 文件（kotlin-reflect.jar）分发。这样做是为了减少不使用反射功能的应用程序所需的运行时库的大小。如果用Android Studio运行Kotlin项目，一般会在创建工程时，自动引入（kotlin-reflect.jar）
>1.类引用

	Java代码
	//方式一
	Class<?> clazz = User.class;
	//方式二
	Class<?> clazz = Class.forName("包名.User");

	Kotlin代码
	//如果User是Kotlin的类
	 var user = User::class
	//如果User是Java的类
	 var user = User::class.java
在Kotlin中，用类名+两个冒号::+class（如果是java类要在后面加上.java）表示获取这个类的对象。

>2.函数引用

Java代码

	public class User {
	    private String userName;

	    public User(String userName) {
	        super();
	        this.userName = userName;
	    }

	    public void printUserName() {
	        System.out.println(userName);
	    }
	}

	//获取User对象
	Class<?> clazz = Class.forName("com.demo.czh.myapplication.User");
	//获取带String参数的public构造函数
	Constructor c=clazz.getDeclaredConstructor(String.class);
	//创建User对象的实例
	User user = (User) c.newInstance("Czh");
	//根据方法名"printUserName"获取 method 对象
	Method method = clazz.getDeclaredMethod("printUserName");
	//通过 method 调用 invoke()方法，调用User里的 printUserName
	method.invoke(user);
	运行代码，得到结果：
	Czh
Kotlin代码

	class User(var userName: String) {
	    fun printUserName() {
	        println(userName)
	    }
	}

	//方式一
	//获取printUserName函数对象
	var p = User::printUserName
	//调用invoke()函数执行printUserName函数
	p.invoke(User("Czh"))

	//方式二
	//利用Java反射机制调用getMethod()方法，并指定方法名字"printUserName"
	var method = User::class.java.getMethod("printUserName")
	//调用invoke()函数
	method.invoke(User("Czh"))

	运行结果：Czh
在Kotlin中，可以用类名+两个冒号::+函数名直接获取这个函数的对象；或者利用Java反射机制调用getMethod()方法来获取函数的对象。
>3.属性引用

Java代码

	public class User {
	    public String userName;
	}

	//获取User对象
	Class<?> clazz = Class.forName("com.demo.czh.myapplication.User");
	//创建User对象的实例
	User user = (User) clazz.newInstance();
	//获取Field对象并指定属性名为"userName"
	Field field = clazz.getField("userName");
	//通过set()方法给userName赋值
	field.set(user, "Czh");
	//通过get()方法获取userName的值
	System.out.println(field.get(user));
	运行代码，得到结果：Czh
Kotlin代码

	class User {
	    var userName: String = "Czh"
	        get() = field
	        set(value) {
	            field = value
	        }
	}

	//方式一
	var user = User()
	//获取属性对象
	var userName = User::userName
	println(userName.get(user))
	//设置属性值
	userName.set(user, "James")
	//获取属性值
	println(userName.get(user))

	//方式二
	//利用Java反射机制获取getUserName方法
	var getName = User::class.java.getMethod("getUserName")
	//利用Java反射机制获取setUserName方法
	var setName = User::class.java.getMethod("setUserName", java.lang.String().javaClass)
	//设置属性值
	setName.invoke(user, "Harden")
	//获取属性值
	println(getName.invoke(user))
	运行代码，得到结果：
	Czh
	James
	Harden
在Kotlin中，可以用类名+两个冒号::+属性名直接获取属性对象；或者通过Java反射机制获取属性的get/set方法来获取或修改属性值。
###2.注解
>1.注解声明

Java声明注解

	public @interface MyAnnotation {
	}
Kotlin声明注解

	annotation class MyAnnotation
注解的附加属性可以通过用元注解标注注解类来指定：

* @Target 指定可以用该注解标注的元素的可能的类型（类、函数、属性、表达式等）；
* @Retention 指定该注解是否存储在编译后的 class 文件中，以及它在运行时能否通过反射可见 （默认都是 true）；
* @Repeatable 允许在单个元素上多次使用相同的该注解；
* @MustBeDocumented 指定该注解是公有 API 的一部分，并且应该包含在生成的 API 文档中显示的类或方法的签名中。

Java添加元注解

	@Target(ElementType.METHOD)//表示可以在方法中使用
	@Retention(RetentionPolicy.RUNTIME)//表示运行时注解
	public @interface MyAnnotation {
	}
Kotlin添加元注解

	@Target(AnnotationTarget.FUNCTION)//表示可以在函数中使用
	@Retention(AnnotationRetention.RUNTIME)//表示运行时注解
	annotation class MyAnnotation
>2.构造函数

注解类可以带有构造函数

Kotlin代码

	annotation class MyAnnotation(val value: Int)

	//使用
	@MyAnnotation(1)
	class Foo {
	}

Java代码

	public @interface MyAnnotation {
	    int value();
	}

	//使用
	@MyAnnotation(1)
	public class Foo {
	}
注解类的构造函数只允许下列参数类型：

* 对应于 Java 原生类型的类型（Int、 Long等）；
* 字符串；
* 类（Foo::class）；
* 枚举；
* 其他注解；
* 上面已列类型的数组。
* Kotlin中只允许用val声明参数
* 当参数类型是其他注解时，该注解类的名字前面不能用@
##9.Kotlin的其他技术
###一、解构声明
解构声明能同时创建多个变量，将对象中的数据解析成相对的变量。举个例子：

	//创建一个数据类User
	data class User(var name: String, var age: Int)

	//获得User的实例
	var user = User("Czh", 22)
	//声明变量 name 和 age
	var (name, age) = user

	println("name:$name  age:$age")
	//输出结果为：name:Czh  age:22
上面代码中用解构声明同时创建两个变量的时候，会被编译成以下代码：

	//指定变量name的值为user第一个参数的值
	var name = user.component1()
	//指定变量name的值为user第二个参数的值
	var age = user.component2()

	println("name:$name  age:$age")
	//输出结果为：name:Czh  age:22

* 解构声明和Map

Map可以保存一组key-value键值对，通过解构声明可以把这些值解构出来。如下所示：

	var map = mutableMapOf<String, Any>()
	map.put("name", "Czh")
	map.put("age", 22)
	for ((key, value) in map) {
	    println("$key：$value")
	}
	结果：
	name:Czh
	age:22
###二、区间
>1.in

假如现在要判断 i 是否在 1-5 内，可以这样写：

	if (i in 1..5) {
	    println("i 在 1-5 内")
	}
上面代码中，1..5指的是 1-5，in指的是在...范围内，如果 i 在范围 1-5 之内，将会执行后面的代码块，输出结果。如果想判断 i 是否不在 1-5 内，可以这样写：

	//!in表示不在...范围内
	if (i !in 1..5) {
	    println("i 不在 1-5 内")
	}
上面两段代码等同于：

	if (i >= 1 && i <= 5) {
	    println("i 在 1-5 内")
	}
	if (i <= 1 && i >= 5) {
	    println("i 不在 1-5 内")
	}
>2.downTo

如果想输出 1-5 ，可以这样写：

	for (i in 1..5) println(i)
	//输出12345
如果倒着来：

	for (i in 5..1) println(i)
	//什么也不输出
这个时候可以用downTo函数倒序输出 5-1

	for (i in 5 downTo 1) println(i)
>3.step

上面的代码顺序输出12345或倒序54321，按顺序+1或者-1，也就是步长为1。如果要修改步长，可以用step函数，如下所示：

	 for (i in 1..5 step 2) println(i)
	//输出135

	//倒序
	for (i in 1 downTo 5 step 2) println(i)
	//输出531
>4.until

上面的代码中，使用的范围都是闭区间，例如1..5的区间是[1,5]，如果要创建一个不包括其结束元素的区间，即区间是[1,5)，可以使用until函数，如下所示：

	for (i in 1 until 5) println(i)
	//输出1234
###三、类型检查与转换
>1.is操作符

在Kotlin中，可以通过is操作符判断一个对象与指定的类型是否一致，还可以使用is操作符的否定形式!is，举个例子：

	var a: Any = "a"
	if (a is String) {
	    println("a是String类型")
	}
	if (a !is Int) {
	    println("a不是Int类型")
	}
	运行代码，输出结果为：
	a是string类型
	a不是Int类型
>2.智能转换

在Kotlin中不必使用显式类型转换操作，因为编译器会跟踪不可变值的is检查以及显式转换，并在需要时自动插入（安全的）转换。举个例子：

	var a: Any = "a"
	if (a is String) {
	    println("a是String类型")
	    println(a.length) // a 自动转换为String类型
	    //输出结果为：1
	}
还可以反向检查，如下所示：

	if (a !is String) return
	print(a.length) // a 自动转换为String类型
在 && 和 || 的右侧也可以智能转换：

	// `&&` 右侧的 a 自动转换为String
	if (a is String && a.length > 0)

	// `||` 右侧的 a 自动转换为String
	if (a is String || a.length > 0)
在when表达式和while循环里也能智能转换：

	when(a){
	    is String -> a.length
	    is Int -> a + 1
	}
需要注意的是，当编译器不能保证变量在检查和使用之间不可改变时，智能转换不能用。智能转换能否适用根据以下规则：

* val 局部变量——总是可以，局部委托属性除外；
* val 属性——如果属性是 private 或 internal，或者该检查在声明属性的同一模块中执行。智能转换不适用于 open 的属性或者具有自定义 getter 的属性；
* var 局部变量——如果变量在检查和使用之间没有修改、没有在会修改它的 lambda 中捕获、并且不是局部委托属性；
* var 属性——决不可能（因为该变量可以随时被其他代码修改）

>3.强制类型转换

在Kotlin中，用操作符as进行强制类型转换，如下所示：

	var any: Any = "abc"
	var str: String = any as String
但强制类型转换是不安全的，如果类型不兼容，会抛出一个异常，如下所示：

	var int: Int = 123
	var str: String = int as String
	//抛出ClassCastException
>4.可空转换操作符

null不能转换为 String，因该类型不是可空的。举个例子：

	var str = null
	var str2 = str as String
	//抛出TypeCastException

解决这个问题可以使用可空转换操作符as?，如下所示：

	var str = null
	var str2 = str as? String
	println(str2) //输出结果为：null
使用安全转换操作符as?可以在转换失败时返回null，避免了抛出异常。
###四、this表达式
为了表示当前的接收者我们使用this表达式。当this在类的成员中，this指的是该类的当前对象;当this在扩展函数或者带接收者的函数字面值中，this表示在点左侧传递的接收者参数。

 如果this没有限定符，它指的是最内层的包含它的作用域。如果要访问来自外部作用域的this（一个类或者扩展函数， 或者带标签的带接收者的函数字面值）我们使用this@label，其中 @label 是一个代指this来源的标签。举个例子：

	class A { // 隐式标签 @A
	    inner class B { // 隐式标签 @B
	        fun Int.foo() { // 隐式标签 @foo
	            val a = this@A // A 的 this
	            val b = this@B // B 的 this

	            val c = this // foo() 的接收者，一个 Int
	            val c1 = this@foo // foo() 的接收者，一个 Int

	            val funLit = lambda@ fun String.() {
	                val d = this // funLit 的接收者
	            }


	            val funLit2 = { s: String ->
	                // foo() 的接收者，因为它包含的 lambda 表达式
	                // 没有任何接收者
	                val d1 = this
	            }
	        }
	    }
	}
###五、相等性
在Kotlin中存在结构相等和引用相等两中相等判断。

>1.结构相等

使用equals()或==判断，如下所示：

	var a = "1"
	var b = "1"
	if (a.equals(b)) {
	    println("a 和 b 结构相等")
	    //输出结果为：a 和 b 结构相等
	}

	var a = 1
	var b = 1
	if (a == b) {
	    println("a 和 b 结构相等")
	    //输出结果为：a 和 b 结构相等
	}
>2.引用相等

引用相等指两个引用指向同一对象，用===判断，如下所示：

	data class User(var name: String, var age: Int)

	var a = User("Czh", 22)
	var b = User("Czh", 22)
	var c = b
	var d = a
	if (c == d) {
	    println("a 和 b 结构相等")
	} else {
	    println("a 和 b 结构不相等")
	}
	if (c === d) {
	    println("a 和 b 引用相等")
	} else {
	    println("a 和 b 引用不相等")
	}
	运行代码，输出结果为：
	a 和 b 结构相等
	a 和 b 引用不相等

###六、操作符重载
Kotlin允许对自己的类型提供预定义的一组操作符的实现，这些操作符具有固定的符号表示 （如 + 或 *）和固定的优先级。为实现这样的操作符，我们为相应的类型（即二元操作符左侧的类型和一元操作符的参数类型）提供了一个固定名字的成员函数或扩展函数。 重载操作符的函数需要用 operator 修饰符标记。

* 重载操作符

+是一个一元操作符，下面来对一元操作符进行重载：

	//用 operator 修饰符标记
	operator fun String.unaryPlus(): String {
	    return this + this
	}

	//调用
	var a = "a"
	println(+a)  //输出结果为：aa
当编译器处理例如表达式 +a 时，它执行以下步骤：

* 确定 a 的类型，令其为 T；
* 为接收者 T 查找一个带有 operator 修饰符的无参函数 unaryPlus（），即成员函数或扩展函数；
* 如果函数不存在或不明确，则导致编译错误；
* 如果函数存在且其返回类型为 R，那就表达式 +a 具有类型 R；

除对一元操作符进行重载外，还可以对其他操作符进行重载，其重载方式和原理大致相同。下面来一一列举：

![](https://i.imgur.com/CemiGkJ.png)
###七、空安全
在Java中，NullPointerException 可能是最常见的异常之一，而Kotlin的类型系统旨在消除来自代码空引用的危险。

>1.可空类型与非空类型

在Kotlin中，只有下列情况可能导致出现NullPointerException：

* 显式调用 throw NullPointerException()；
* 使用了下文描述的 !! 操作符；
* 有些数据在初始化时不一致；
* 外部 Java 代码引发的问题。

在 Kotlin 中，类型系统区分一个引用可以容纳 null （可空引用）还是不能容纳（非空引用）。 例如，int 类型的常规变量不能容纳 null：

如果要允许为空，我们可以声明一个变量为可空字符串，在字符串类型后面加一个问号?，写作 String?，如下所示：

	var b: String? = "b"
	b = null
>2.安全调用操作符

接着上面的代码，如果你调用a的方法或者访问它的属性，不会出现NullPointerException，但如果调用b的方法或者访问它的属性，编译器会报告一个错误，如下所示：

![](https://i.imgur.com/BdyX9r2.png)

这个时候可以使用安全调用操作符，写作?.，在b后面加安全调用操作符，表示如果b不为null则调用b.length，如下所示：

	b?.length

安全调用操作符还能链式调用，例如一个员工 Bob 可能会（或者不会）分配给一个部门， 并且可能有另外一个员工是该部门的负责人，那么获取 Bob 所在部门负责人（如果有的话）的名字，我们写作：

	Bob?.department?.head?.name
	//如果Bob分配给一个部门
	//执行Bob.department.head?获取该部门的负责人
	//如果该部门有一个负责人
	//执行Bob.department.head.name获取该负责人的名字

如果该链式调用中任何一个属性为null，整个表达式都会返回null。
如果要只对非空值执行某个操作，安全调用操作符可以与let一起使用，如下所示：

	val listWithNulls: List<String?> = listOf("A", null, "B")
	for (item in listWithNulls) {
	    item?.let { println(it) }
	}
	运行代码，输出结果为：
	A
	B
* 安全的类型转换

如果对象不是目标类型，那么常规类型转换可能会导致 ClassCastException。 另一个选择是使用安全的类型转换，如果尝试转换不成功则返回null，如下所示：

	val i: Int? = i as? Int

* 可空类型的集合

如果你有一个可空类型元素的集合，并且想要过滤非空元素，你可以使用filterNotNull来实现。如下所示：

	val nullableList: List<Int?> = listOf(1, 2, null, 4)
	val intList: List<Int> = nullableList.filterNotNull()

>3.Elvis 操作符

先看一段代码：

	val i: Int = if (b != null) b.length else -1
	val i = b?.length ?: -1

这两行代码表达的都是“如果b不等于null，i = b.length;如果b等于null,i = -1”。第一行代码用的是if表达式，而第二行代码使用了Elvis操作符，写作?:。Elvis操作符表示如果?:左侧表达式非空，就使用左侧表达式，否则使用右侧表达式。

请注意，因为throw和return在Kotlin中都是表达式，所以它们也可以用在Elvis操作符右侧。如下所示：

	fun foo(node: Node): String? {
	    val parent = node.getParent() ?: return null
	    val name = node.getName() ?: throw IllegalArgumentException("name expected")
	    // ……
	}
>4. !! 操作符

!!操作符将任何值转换为非空类型，若该值为空则抛出异常。如下所示：

	var a = null
	a!!
	//运行代码，抛出KotlinNullPointerException
###八、异常
Kotlin中所有异常类都是Throwable类的子类。每个异常都有消息、堆栈回溯信息和可选的原因。

使用throw表达式可以抛出异常。举个例子：

	throw NullPointerException("NPE")
使用try表达式可以捕获异常。一个try表达式可以有多个catch代码段；finally代码段可以省略。举个例子：

	try {
	    //捕获异常
	} catch (e: NullPointerException) {
	    //异常处理
	} catch (e: ClassNotFoundException) {
	    //异常处理
	} finally {
	    //可选的finally代码段
	}
因为Try是一个表达式，所以它可以有一个返回值。举个例子：

	val a: Int? = try {
	    parseInt(input)
	} catch (e: NumberFormatException) {
	    null
	}
try表达式的返回值是 try块中的最后一个表达式或者是catch块中的最后一个表达式。finally块中的内容不会影响表达式的结果。

###九、类型别名
Kotlin提供类型别名来代替过长的类型名称，这些类型别名不会引入新类型，且等效于相应的底层类型。可以通过使用关键字typealias修改类型别名，如下所示：

	//使用关键字typealias修改类型别名Length
	//相当于 Length 就是一个 (String) -> Int 类型
	typealias Length = (String) -> Int

	//调用
	fun getLength(l: Length) = l("Czh")
	//编译器把 Length 扩展为 (String) -> Int 类型
	val l: Length = { it.length }
	println(getLength(l)) //输出结果为：3

使用类型别名能让那些看起来很长的类型在使用起来变得简洁，如下所示：

	typealias MyType = (String, Int, Any, MutableList<String> ) -> Unit
	//当我们使用的时候
	var myType：MyType
	//而不需要写他原来的类型
	//var myType：(String, Int, Any, MutableList<String> ) -> Unit

##4.函数+Lambda表达式
###函数
	fun method(){}
    //2.带参数
    fun method1(str : String, int : Int){}
    //3.带返回值
    fun method2(str : String, int : Int) : String{ return ""}
    //4.默认参数 当调用方法时没有给参数传值的时候 使用默认值
    fun method3(str: String = "daf", i : Int = 1){}

	//调用该函数，这个时候可以只传一个参数
	method3("abc")
	//运行代码，得到结果为： abc  1

	5.命名参数
	如果有默认值的参数在无默认值的参数之前，要略过有默认值的参数去给无默认值的参数指定值，要使用命名参数来指定值，有点绕我们看代码：
	//有默认值的参数在无默认值的参数之前
	fun foo(i: Int = 1, str: String) {
	    println("$str  $i")
	}
	//foo("hello")  //编译错误
	foo(str = "hello")  //编译通过，要使用参数的命名来指定值
	//运行代码，得到结果为： hello  1

	6.可变数量的参数
	函数的参数可以用 vararg 修饰符标记，表示允许将可变数量的参数传递给函数，如下所示：

	//用 vararg 修饰符标记参数
	fun <T> asList(vararg ts: T): List<T> {
	    val result = ArrayList<T>()
	    for (t in ts) // ts is an Array
	        result.add(t)
	    return result
	}

	val a = arrayOf(1, 2, 3)
	//*a代表把a里所有元素
	val list = asList(-1, 0, *a, 4)
	//运行代码，得到结果为： [-1, 0, 1, 2, 3, 4]


	7.简写
	如果函数体只有一条语句，且有返回值
	fun method() = "返回值"
> kotlin支持函数嵌套，把局部函数看做语句一样

		fun foo() {
		    println("outside")
		    fun inside() {
		        println("inside")
		   }
		   inside()
		}

		//调用foo()函数
		foo()
		运行代码，得到结果
		outside
		inside
>尾递归函数

尾递归函数是一个递归函数，用关键字`tailrec`来修饰，函数必须将其自身调用作为它执行的最后一个操作。当一个函数用tailrec修饰符标记并满足所需的形式时，编译器会优化该递归，留下一个快速而高效的基于循环的版本，无堆栈溢出的风险，举个例子：

	先看一段代码
	fun count(x: Int = 1): Int = if (x == 10) x else count(x - 1)
上面的count()函数是一个死循环，当我们调用count()函数后，会报StackOverflowError。这时可以用tailrec修饰符标记该递归函数，并将其自身调用作为它执行的最后一个操作，如下所示：

	tailrec fun count(x: Int = 1): Int = if (x == 10) x else count(x - 1)
再次运行代码，无堆栈溢出。

>中缀表示法

中缀表示法是调用函数的另一种方法。如果要使用中缀表示法，需要用infix 关键字来修饰函数，且要满足下列条件：

* 它们必须是成员函数或扩展函数；
* 它们必须只有一个参数；
* 其参数不得接受可变数量的参数。

		下面来举个例子：
		//扩展函数
		infix fun String.removeLetter(str: String): String {
		    //this指调用者
		    return this.replace(str, "")
		}

		//调用
		var str = "hello world"
		//不使用中缀表示法
		println(str.removeLetter("h")) //输出ello world
		//使用中缀表示法
		println(str removeLetter "d")  //输出hello worl
		//使用中缀表示法调用str removeLetter "d"等同于调用str.removeLetter("d")

		//还可以连续调用
		println(str.removeLetter("h").removeLetter("d").removeLetter("l")) // 输出 eo wor
		println(str removeLetter "h" removeLetter "d" removeLetter "l") // 输出 eo wor

###Lambda表达式的语法
Lambda表达式的语法如下：

* Lambda 表达式总是括在大括号中；
* 其参数（如果有的话）在 -> 之前声明（参数类型可以省略）；
* 函数体（如果存在的话）在 -> 后面。


		 1. 无参数的情况 ：
	    val/var 变量名 = { 操作的代码 }

	    2. 有参数的情况
	    val/var 变量名 : (参数的类型，参数类型，...) -> 返回值类型 = {参数1，参数2，... -> 操作参数的代码 }

	    可等价于
	    // 此种写法：即表达式的返回值类型会根据操作的代码自推导出来。
	    val/var 变量名 = { 参数1 ： 类型，参数2 : 类型, ... -> 操作参数的代码 }

	    3. lambda表达式作为函数中的参数的时候，这里举一个例子：
	    fun test(a : Int, 参数名 : (参数1 ： 类型，参数2 : 类型, ... ) -> 表达式返回类型){
	        ...
	    }

举个例子：

	//这是一个Lambda表达式的完整语法形式
	val sum = { x: Int, y: Int -> x + y }
	//Lambda表达式在大括号中
	//参数 x 和 y 在 -> 之前声明
	//参数声明放在大括号内，并有参数类型标注
	//函数体 x + y 在 -> 后面

	val i: Int = sum(1, 2)
	println(i) //输出结果为 3
如果Lambda表达式自动推断的返回类型不是Unit，那么在Lambda表达式函数体中，会把最后一条表达式的值当做是返回值。所以上面的常量sum 的返回值是Int类型。如果要指定常量sum的返回值为Int类型，可以这样写：

	val sum: (Int, Int) -> Int = { x, y -> x + y }

	val i: Int = sum(1, 2)
	println(i) //输出结果为 3
当Lambda表达式只有一个参数的时候，那么它将可以省略这个唯一的参数的定义，连同->也可以省略。如下所示：

	//当Lambda表达式只有一个参数的时候
	val getInt: (Int) -> Int = { x -> x + 1 }
	val int = getInt(2)
	println(int)  //输出结果为：3

	//可以省略这个参数的定义
	//并且将隐含地奖这个参数命名为 it
	val sum: (Int) -> Int = { it + 1 }
	val int = sum(2)
	println(int)  //输出结果为：3
	it是在当一个高阶函数中Lambda表达式的参数只有一个的时候可以使用it来使用此参数。it可表示为单个参数的隐式名称
上面说到如果Lambda表达式自动推断的返回类型不是Unit，那么在Lambda表达式函数体中，会把最后一条表达式的值当做是返回值。举个例子：

	var sum: (Int) -> Int = {
	      val i: Int = it + 1
	      val j: Int = i + 3
	      val k: Int = it + j - i
	      i
	      k
	      j
	}
	println(sum(1))
	//输出结果为 5，也就是 j 的值
###高阶函数与Lambda表达式

高阶函数是将函数用作参数或返回值的函数，如下所示：

	fun getName(name: String): String {
	    return name
	}

	fun printName(a: String, name: (str: String) -> String): String {
	    var str = "$a${name("Czh")}"
	    return str
	}

	//调用
	println(printName("Name:", ::getName))
	//运行代码，输出 Name:Czh
上面代码中`name: (str: String) -> String`是一个函数，拥有函数类型`() -> String`，接收一个String参数，当我们执行`var str = "$a${name("Czh")}"`这行代码的时候，相当于执行了`var str = "$a${getName("Czh")}"`，并返回了字符串"Czh"。当我们调用`printName("Name:", ::getName)`时，将函数作为参数传入高阶函数，需要在该函数前加两个冒号::作为标记。

Kotlin提供了Lambda表达式来让我们更方便地传递函数参数值。Lambda表达式总是被大括号括着；如果有参数的话，其参数在 -> 之前声明，参数类型可以省略；如果存在函数体的话，函数体在-> 后面，如下所示：

	println(printName("Name:", { name -> getName("Czh") }))
	//运行代码，输出 Name:Czh
如果函数的最后一个参数是一个函数，并且你传递一个Lambda表达
式作为相应的参数，你可以在圆括号()之外指定它，如下所示：

	println(printName("Name:") { name -> getName("Czh") })
	//运行代码，输出 Name:Czh
###匿名函数
匿名函数与常规函数一样，只是省略了函数名称而已。举个例子

	fun(x: Int, y: Int): Int = x + y
匿名函数函数体是表达式，也可以是代码段，如下所示：

	fun(x: Int, y: Int): Int {
	    return x + y
	}
上面高阶函数的例子中的printName函数的第二个参数也可以传入一个匿名函数，如下所示：

	println(printName("Name:", fun(str: String): String { return "Czh" }))
	//运行代码，输出 Name:Czh








