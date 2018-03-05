package reflection.text;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import reflection.text.proxy.DynamicSubject;
import reflection.text.proxy.RealSubject;
import reflection.text.proxy.Subject;

/**
 * @author qiaosen 反射机制
 */
public class RefilectionText {
	// 获取类有三种方法
	public void getClassThreeMethods() {
		RefilectionText mRefilectionText = new RefilectionText();
		boolean flag = mRefilectionText.getClassCheck();
		if (flag) {
			System.out.println("所有反射全部成功！");
		} else {
			System.out.println("反射有问题，请检查！");
		}
	}

	// 反射机制获取类有三种方法
	public boolean getClassCheck() {
		try {
			System.out.println("第一种，通过类本身获得对象");
			Class StudentInfoClass = new StudentInfo().getClass();
			System.out.println("第一种方式成功！类名：" + StudentInfoClass.toString() + "\n");

			System.out.println("第二种，通过类名加.class获得对象");
			Class ForClass = reflection.text.StudentInfo.class;
			System.out.println("第二种方式成功！类名：" + ForClass.toString() + "\n");

			System.out.println("第三种，通过类名的字符串获得对象");
			Class ForName = Class.forName("reflection.text.StudentInfo");
			System.out.println("第三种方式成功！类名：" + ForName.toString() + "\n");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 获得类属性
	public static void getFieldNames(String className) {
		try {
			// 获得类名
			Class<?> classType = Class.forName(className);
			// 使用getFields获取属性
			Field[] fields = classType.getFields();
			for (Field f : fields) {
				System.out.println(f);
			}
			System.out.println();
			// 使用getDeclaredFields获取属性
			fields = classType.getDeclaredFields();
			for (Field f : fields) {
				System.out.println(f);
			}
			Class c = Class.forName(className);
			System.out.println("类名：" + c.toString() + "\n");
			// 获得所有属性
			Field[] fds = c.getDeclaredFields();
			System.out.println("类属性个数：" + fds.length + "\n");

			for (int i = 0; i < fds.length; i++) {
				String fn = fds[i].getName();
				Class tc = fds[i].getType();
				String ft = tc.getName();
				System.out.println("该属性的名字为：" + fn + "，该属性的类型为：" + ft);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 使用getConstructors获取构造器
	public static void getConstructors(String className) {
		// 获得类名
		Class<?> classType;
		try {
			classType = Class.forName(className);
			Constructor<?>[] constructors = classType.getConstructors();
			for (Constructor<?> m : constructors) {
				System.out.println(m);
			}

			System.out.println();

			// 使用getDeclaredConstructors获取构造器
			constructors = classType.getDeclaredConstructors();
			for (Constructor<?> m : constructors) {
				System.out.println(m);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 使用反射机制创建新类的方法
	public static void getCreateNewClass(String className) {
		Class<?> classType;
		try {
			classType = Class.forName(className);

			System.out.println("通过反射机制创建无参新类 第一种方法 开始");
			Object inst1 = classType.newInstance();
			System.out.println(inst1);
			System.out.println("通过反射机制创建无参新类 第一种方法 结束");

			System.out.println("通过反射机制创建无参新类 第二种方法 开始");
			Constructor<?> constructor2 = classType.getConstructor();
			Object inst2 = constructor2.newInstance();
			System.out.println(inst2);
			System.out.println("通过反射机制创建无参新类 第二种方法 结束");

			System.out.println("通过反射机制创建有参新类 第三种方法 开始");
			Constructor<?> constructor3 = classType.getDeclaredConstructor(String.class, String.class);
			Object inst3 = constructor3.newInstance("张安", "123");
			System.out.println(inst3);
			System.out.println("通过反射机制创建有参新类 第三种方法 结束");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 通过反射获取类Method对象，调用Field的Invoke方法调用函数。
	public static void getInvoke(String className) {
		Class<?> classType;
		try {
			classType = Class.forName(className);
			Object inst = classType.newInstance();
			Method logMethod = classType.getDeclaredMethod("setName", String.class);
			logMethod.setAccessible(true);
			logMethod.invoke(inst, "张三是李四的哥");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 通过反射获取类Method对象，调用Field的Invoke方法调用函数。
	public static void getFieldType(String className) {
		Class<?> classType;
		try {
			classType = Class.forName(className);
			Object inst = classType.newInstance();
			Field intField = classType.getField("age");
			intField.setInt(inst, 99);
			int value = intField.getInt(inst);
			System.out.println(value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 通过Proxy.newProxyInstance构建代理对象
	public static void getRealSubject() {
		RealSubject realSub = new RealSubject();
		InvocationHandler handler = new DynamicSubject(realSub);
		Class<?> classType = handler.getClass();
		Subject sub = (Subject)Proxy.newProxyInstance(classType.getClassLoader(),
		realSub.getClass().getInterfaces(), handler);
		System.out.println(sub.getClass());  
		sub.Request();  
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 获取类属性
		RefilectionText.getRealSubject();
	}

}
