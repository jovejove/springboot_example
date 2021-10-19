# ioc container

## 配置元数据的3种方式
- 基于xml  <bean/> <beans/> 
- 基于注解
- 基于java   @Configuration、@Bean、@Import和@DependsOn

## 依赖注入的两种方式
- 基于构造器的依赖注射
- 基于Setter的依赖注射

## 使用构造器还是setter注入
根据经验，对强制依赖项使用构造函数， 对可选依赖项使用 setter 方法或配置方法是一个很好的经验法则。

请注意，在 setter 方法上使用 @Required 注释可用于使属性成为必需的依赖项； 但是，最好使用带有参数编程验证的构造函数注入。

Spring 团队通常提倡构造函数注入，因为它可以让您将应用程序组件实现为不可变对象，并确保所需的依赖项不为空。
此外，构造函数注入的组件总是以完全初始化的状态返回给客户端（调用）代码。

大量的构造函数参数是一种糟糕的代码味道，这意味着该类可能有太多的责任，应该重构以更好地解决适当的关注点分离问题。 

Setter 注入应该主要仅用于可以在类中分配合理默认值的可选依赖项。 否则，必须在代码使用依赖项的任何地方执行非空检查。

setter 注入的一个好处是 setter 方法使该类的对象可以在以后重新配置或重新注入。

因此，通过 JMX MBean 进行管理是 setter 注入的一个引人注目的用例。

使用对特定类最有意义的 DI 样式。



## 循环依赖
如果您主要使用构造函数注入，则可能会创建无法解决的循环依赖场景。
例如：A类通过构造函数注入需要B类的实例，B类通过构造函数注入需要A类的实例。
如果您将类 A 和 B 的 bean 配置为相互注入，则 Spring IoC 容器在运行时检测到此循环引用，并抛出 BeanCurrentlyInCreationException。
一种可能的解决方案是编辑一些类的源代码，以便由 setter 而不是构造函数来配置。
或者，避免构造函数注入并仅使用 setter 注入。
也就是说，虽然不推荐，但是可以通过setter注入来配置循环依赖。

与典型情况（没有循环依赖）不同，bean A 和 bean B 之间的循环依赖迫使其中一个 bean 在完全初始化之前注入另一个 bean（经典的鸡和蛋场景）。
## 构造函数实例化
```
    <bean id="personService" class="com.panda.spring.service.PersonService"/>
```
## Instantiation with a Static Factory Method
```
    <!-- 使用实例工厂方法进行实例实例的实例化 the factory bean, which contains a method called createInstance() -->
    <bean id="serviceLocator" class="com.panda.spring.service.DefaultServiceLocator">
        <!-- inject any dependencies required by this locator bean -->
    </bean>
    <!-- the bean to be created via the factory bean -->
    <bean id="clientService" 
          factory-bean="serviceLocator"
          factory-method="createClientServiceInstance"/>

```

## Bean Scopes

