## 如何使用
* 引入module
```xml
<dependency>
    <groupId>com.celi</groupId>
    <artifactId>cii-csa-schedule</artifactId>
    <version>${project.version}</version>
</dependency>
```
* 创建表
  *手动创建表
```sql
create table schedule_config
(
    JOB_ID          int auto_increment comment '任务ID' primary key,
    BEAN_NAME       varchar(255) not null comment 'bean名称',
    METHOD_NAME     varchar(255) null comment '方法名,如果为空默认执行execute函数',
    METHOD_PARAMS   varchar(500) null comment '方法实参，键值对格式',
    CORN_EXPRESSION varchar(50)  not null comment 'corn表达式',
    REMARK          varchar(200) null comment '描述',
    STATUS          int          null comment '状态0暂停1正常',
    CREATE_BY       int          null comment '创建人',
    UPDATE_BY       int          null comment '更新人',
    CREATE_DATE     datetime     null comment '创建时间',
    UPDATE_DATE     datetime     null comment '更新时间'
) comment '定时任务配置类';


```
  * 配置JPA自动创建表
```yaml
  jpa:
    hibernate:
      ddl-auto: update
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
        implicit-strategy: org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl
```
