# SSM_Hotel

## 系统组织模块

<img src="C:\Users\Fox\AppData\Roaming\Typora\typora-user-images\image-20231201120904347.png" alt="image-20231201120904347" style="zoom:150%;" />

**模块说明：**

员工管理模块：负责员工信息的管理，包括员工信息的增删改查以及给员工分配分配角色权限，其中查找分为按员工姓名查找、按真实姓名查找、按员工性别查找、按员工所属部门查找、按员工入职日期查找。

部门管理模块、角色管理模块、菜单管理模块、房间管理模块、房型管理模块、楼层管理模块负责对应信息的增删改查，其中角色管理模块还负责给角色分配角色权限。

预订管理模块：包括接收到前台发管理的预订请求后进行确认的操作、按预订人姓名查询订单信息、按身份证号查询订单信息等等。

入住管理模块：负责入住信息的管理，包括接受前台发送的退房请求后进行退房确认的操作、按入住人姓名查询订单信息、按身份证号查询订单信息、按手机号码查询订单信息、按房间类型查询订单信息、按入住状态（入住中、已离店）查询订单信息、按入住日期查询订单信息、按离店日期查询订单信息。

## **相关技术和环境配置**：

开发环境：Jdk8；Windows10;MySql8.0;Google浏览器;Tomcat服务器

开发工具：IDEA

相关技术：spring;springmvc;mybatis;jsp;laymini框架

**Spring**

​	Spring就像是整个项目中装配bean的大工厂，在[配置文件](https://baike.baidu.com/item/配置文件/286550?fromModule=lemma_inlink)中可以指定使用特定的参数去调用[实体类](https://baike.baidu.com/item/实体类/9766323?fromModule=lemma_inlink)的[构造方法](https://baike.baidu.com/item/构造方法/10455265?fromModule=lemma_inlink)来实例化对象。也可以称之为项目中的粘合剂。

​	Spring的核心思想是IoC（[控制反转](https://baike.baidu.com/item/控制反转/1158025?fromModule=lemma_inlink)），即不再需要[程序员](https://baike.baidu.com/item/程序员/62748?fromModule=lemma_inlink)去显式地`new`一个对象，而是让Spring框架帮你来完成这一切。

**SpringMVC**

​	SpringMVC在项目中拦截用户请求，它的核心Servlet即[DispatcherServlet](https://baike.baidu.com/item/DispatcherServlet/12740507?fromModule=lemma_inlink)承担中介或是前台这样的职责，将用户请求通过HandlerMapping去匹配Controller，Controller就是具体对应请求所执行的操作。SpringMVC相当于SSH框架中[struts](https://baike.baidu.com/item/struts/485073?fromModule=lemma_inlink)。

**mybatis**

​	mybatis是对[jdbc](https://baike.baidu.com/item/jdbc/485214?fromModule=lemma_inlink)的封装，它让数据库底层操作变的透明。mybatis的操作都是围绕一个sql[SessionFactory](https://baike.baidu.com/item/SessionFactory/6659145?fromModule=lemma_inlink)实例展开的。mybatis通过配置文件关联到各实体类的[Mapper](https://baike.baidu.com/item/Mapper/17330783?fromModule=lemma_inlink)文件，Mapper文件中配置了每个类对数据库所需进行的[sql语句](https://baike.baidu.com/item/sql语句/5714895?fromModule=lemma_inlink)映射。在每次与数据库交互时，通过sqlSessionFactory拿到一个sqlSession，[再执行](https://baike.baidu.com/item/再执行/8533454?fromModule=lemma_inlink)sql命令。

​	页面发送请求给控制器，控制器调用[业务层](https://baike.baidu.com/item/业务层/22287753?fromModule=lemma_inlink)处理逻辑，逻辑层向[持久层](https://baike.baidu.com/item/持久层/3584971?fromModule=lemma_inlink)发送请求，持久层与数据库交互，后将结果返回给业务层，业务层将处理逻辑发送给控制器，控制器再调用视图展现数据。 

**程序执行流程图**

![image-20231201121605301](C:\Users\Fox\AppData\Roaming\Typora\typora-user-images\image-20231201121605301.png)

**SSM框架说明：**

![image-20231201121651204](C:\Users\Fox\AppData\Roaming\Typora\typora-user-images\image-20231201121651204.png)

​	SSM框架spring、spring MVC和mybatis框架的整合，是标准的MVC模式（业务模型、用户界面、控制器），将整个系统划分为4层分别为表现层、controller层、service层和DAO层；通过使用spring MVC负责请求的转发和视图管理，spring实现业务对象管理，mybatis作为数据对象的持久化引擎。视图层jsp页面获取用户请求到控制层并与视图层交互，控制层实现相应的功能调用业务层的方法，再到数据库访问层访问数据库，然后在持久层完成对数据库的操作。

## 数据库设计

​	该系统目前由13张表组成，可分为两大功能模块，一部分负责管理员工及其与之有所联系的属性命名为图1，另一部分负责用户及其与之有所联系的属性命名为图2。

![image-20231201121851183](C:\Users\Fox\AppData\Roaming\Typora\typora-user-images\image-20231201121851183.png)

​																								**图1**

![image-20231201121927571](C:\Users\Fox\AppData\Roaming\Typora\typora-user-images\image-20231201121927571.png)

​																									**图2**



## 相关页面：

后台登录页面：

![image-20231201122114619](C:\Users\Fox\AppData\Roaming\Typora\typora-user-images\image-20231201122114619.png)

后台页面：
![image-20231201122136320](C:\Users\Fox\AppData\Roaming\Typora\typora-user-images\image-20231201122136320.png)

前台页面：
![image-20231201122215478](C:\Users\Fox\AppData\Roaming\Typora\typora-user-images\image-20231201122215478.png)

