# HSBC-HomeTask-Transaction

## 功能说明
1. 实现了简单的交易管理功能，对Transaction对象进行增、删、改、查等功能
2. 时间紧迫，前端项目仍然是半成品，很多问题未解决，因此未上传至git。
 
## 项目结构
项目结构遵循之前实践的DDD结构.
1. src/main/java：主要的 Java 源代码目录。
2. com.caoyinglong.config 包用于整体配置类
3. com.caoyinglong.exceptions 包用于自定义异常类及异常处理工具类和统一异常处理类
4. com.caoyinglong.filter 包存放过滤器
5. com.caoyinglong.statusenmus 包存放接口返回的状态枚举
6. com.caoyinglong.transaction 为交易业务的包
7. com.caoyinglong.utils 存放工具类

具体的DDD接口体现在com.caoyinglong.transaction包中：
1. application包存放应用层数据，用于业务编排不承担实际业务处理
2. controller包存放api层接口
3. domain层用于存储领域层功能类，包含领域实体entity，使用的枚举enums，领域方法service（其中领域方法分为查询方法和增删改方法），领域对应的基础访问层（infra）
4. dto包用于存放领域对api层暴露的实体对象
5. assembler包存储领域实体与dto互转的静态方法

## 运行环境
- Java 21
- Maven3.9.x
- Docker(可选)
- k8s(可选)

## 实现亮点
1. 根据要求，数据无需进行持久化，因此采用内存数据库Hsqldb进行数据库模拟。（也因此，性能测试时，产生数据较多时，访问速度下降，内存成为瓶颈）
2. 缓存采用caffeine进行缓存，并配合spirng框架的@cacheable等方法，实现无代码入侵的缓存机制。
3. 目前采用的DDD模式，采用充血模型，由于业务较为简单，因此无法体现其优势，实际使用时，业务逻辑等完全封装在domain层中，并可新增applicationOutService防腐层，提供给其他领域调用的同时，保证本领域内逻辑相对独立。
4. 所有返回结果通过Result进行封装，并集成了IStatus接口获取返回状态，Istatus的两个实现类分别对应了ApiStatus类返回以及微服务内部通信的返回Status(未使用到)。统一了所有的返回形式及返回结果
5. 自定义异常类型，BusinessException，通过该异常可将业务校验错误等结果抛出，并有GlobalExceptionHandler进行捕捉统一处理。
6. 使用@Valid标签及@NotBlank等validation.constraints包的验证方法对传入参数进行统一验证，验证异常时通过GlobalExceptionHandler进行捕捉统一处理。防止大量验证工作入侵到domain层。
7. 使用snowflake生成算法为新增数据进行编码，为之后的分布式部署做好基础。

## 接口访问设置
| 接口路径                         | 请求方法 | 请求参数                                                     | 功能描述               |
|----------------------------------|----------|------------------------------------------------------------|--------------------|
| `/transaction/findPage`          | GET      | `pageNo` (int), `pageSize` (int)                             | 查询所有trasaction列表（分页） |
| `/transaction/{id}`              | GET      | `id` (String, path)                                           | 查询指定ID的trasaction        |
| `/transaction`                   | POST     | `accountId` (string, required), `bussinessType` (string, required) ,`amout` (BigDecimal, required)    | 创建新trasaction              |
| `/transaction`                   | PUT      | `id` (string, required),`accountId` (string, required), `bussinessType` (string, required) ,`amout` (BigDecimal, required)   | 更新指定ID的trasaction          |
| `/transaction/{id}`              | DELETE   | `id` (string, path)                                           | 删除指定id的trasaction          |    

## 单元测试
测试用例覆盖controller中的所有接口及applicationService层的缓存设计

## 压力测试
使用Jmeter进行压力测，测试结果详见Jmeter-report文件夹

## docker及k8s
配置文件已给出，使用方法：
1. 确保你已经安装了Docker。
2. 在项目根目录下，运行以下命令构建Docker镜像：
   ```bash
   docker build -t hsbc-hometask-transaction .
3. 运行Docker容器：
   ```bash
   docker run -p 8080:8080 hsbc-hometask-transaction
4. k8s配置文件已给出，由于云服务网络问题未解决，未实际部署完成。

## 下一步计划
1. 完成k8s的部署
2. 前端使用vue + element-ui，实现增删改查等功能
3. 进一步完善单元测试用例，目前仅覆盖controller层的所有api，之后可覆盖application层以及domain的service层。
