# HibernateDemo3
Hibernate基础，缓存操作，一对多关系映射，多对多关系映射，级联删除等
#一级缓存操作
session提供map容器，用于执行一些指定的操作时，进行相应PO对象缓存。

###save方法

	当OID类型为：代理主键，执行save方法时，将触发insert语句，确定OID的值。直到commit数据才进入数据库。
	当OID类型为：自然主键，执行save方法时，此时不触发insert语句，直到commit才触发insert语句，数据进入数据库。
	如果OID在数据库中已经存在记录将抛异常。

###saveOrUpdate方法
	对象时瞬时态，执行saveOrUpdate，底层执行save方法。如果对象时脱管态，执行saveOrUpdate，底层执行update方法。
    OID 代理主键，OID如果没有值，执行save方法；如果有值（setUid(,,)），执行update方法。
	如果设置oid，但数据库中没有对应记录，将抛异常。
	oid自然主键，先执行select查询来获得主键的值，若OID不存在，则save，OID存在，则update。

###update方法
当执行update时，都会执行update，即使数据没有变更。

可以通过映射文件配置：select-before-update,在更新前先查询，如果数据系统，将不进行update，适用于更新不频繁的应用。

###一级缓存内容的操作
清空缓存

    session.clear();

或者

    session.evict(user);


一级缓存快照（备份）

提交时要确定一级缓存的数据是否进行了修改，如果没有修改，就不进行任何操作，如果修改了，就执行update，将一级缓存的数据同步到数据库。是否修改的依据就是一级缓存的数据与快照的数据是否一致。

默认提交时，一级缓存的数据将进行刷新，执行update语句，使一级缓存的数据同步到数据库。刷新时机是可以修改的：在hibernate中提供了FlushMode进行设置。

刷新时机：1、查询前，2，提交时，3，执行flush进行手动刷新，4，默认进行commit时，可以理解为依据进行了flush了。


#关联关系映射

一对多、多对多、一对一

实体关系

采用ER图（关系实体图），开发时进行表之间的描述。

##一对多

	<!--  一个客户拥有多个订单，一对多  
				Set:set
				List:list
				Map:map
				Array:array
				key:用来确定从表的
				
		-->
		<set name="orderSet" >
				<key column="customer_id"></key>
				<one-to-many  class="cn.tf.domain.Order" />
		
		</set>
		

--

	<!-- 多个订单属于一个客户
			每一个映射文件都可以完整的描述对象之间的关系
			column,确定从表外键
		 -->
		<many-to-one name="customer"   class="cn.tf.domain.Customer"   column="customer_id">
			
		</many-to-one>

一对多：
多方必须维护外键信息，如果一方没有OID值，将触发update语句，
一方默认对外键信息进行维护，一方将放弃对外键的维护
在Customer.hbm.xml中，加入

    inverse="true"


###级联操作

 级联保存或更新   

save-update:A关联瞬时态B，当保存A时，自动将瞬时态的B转换成持久态B

	cascade="save-update"

 级联删除：


    <set name="orderSet"  cascade="delete"   >

删除客户的时候删除订单：
删除后台执行的语句：

Hibernate: 

    select
        customer0_.cid as cid1_0_,
        customer0_.cname as cname1_0_ 
    from
        t_customer customer0_ 
    where
        customer0_.cid=?
Hibernate: 

    select
        orderset0_.customer_id as customer3_1_1_,
        orderset0_.oid as oid1_,
        orderset0_.oid as oid2_0_,
        orderset0_.price as price2_0_,
        orderset0_.customer_id as customer3_2_0_ 
    from
        t_order orderset0_ 
    where
        orderset0_.customer_id=?
Hibernate: 

    update
        t_order 
    set
        customer_id=null 
    where
        customer_id=?
Hibernate: 

    delete 
    from
        t_order 
    where
        oid=?
Hibernate: 

    delete 
    from
        t_customer 
    where
        cid=?


 孤儿删除

    <set name="orderSet"  cascade="delete-orphan"   >


解除关系之后，那个表外键设置null,从表记录就是孤儿，一并进行删除，解除关系时，一并删除订单，但是客户还是存在的。



##多对多

	<!-- 多对多
			确定中间表的表名
			当前表在中间表对应的名称
			确定容器中另一个对象,class来确定另一个对象的类型,column确定的是另一个表对应的外键名称
		 -->
		<set name="studentSet"  table="t_student_course" >
			<key column="course_id"></key>
			<many-to-many class="cn.tf.domain2.Student"   column="student_id"></many-to-many>
		</set>

--

	<set name="courseSet"  table="t_student_course">
			<key column="student_id"></key>
			<many-to-many  class="cn.tf.domain2.Course"  column="course_id"></many-to-many>
		</set>



###操作
两个对象不用同时对中间表进行维护，所以需要在多对多中有一方配置放权。

	inverse="true"


级联保存
直接保存即可。

双向级联删除


    Student.hbm.xml
	 * 		<set name="courseSet" table="m_student_course" cascade="delete">
	 * 	Course.hbm.xml
	 * 		<set name="studentSet" table="m_student_course" cascade="delete" inverse="true">

