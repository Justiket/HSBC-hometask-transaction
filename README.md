#HSBC-HomeTask-Transaction

##功能说明
1.实现了简单的交易管理功能，对Transaction对象进行增、删、改、查等功能
2.时间紧迫，前端项目只进行了搭建，未做开发。

##项目结构
项目结构遵循
src/main/java：主要的 Java 源代码目录。
com.example.transactionmanagement.entity：包含交易实体类 Transaction。
com.example.transactionmanagement.service：包含交易服务类 TransactionService。
com.example.transactionmanagement.controller：包含交易控制器类 TransactionController。
src/test/java：测试代码目录，包含单元测试类 TransactionServiceTest。
Dockerfile：用于容器化部署的 Docker 配置文件。
依赖库
Spring Web：用于构建 RESTful 风格的 Web 服务。
Spring Cache：实现缓存机制，提高数据访问性能。
Spring Test：用于编写和运行单元测试。
API 接口
创建交易
URL：POST /api/transactions
参数：
amount：交易金额，必须大于零。
description：交易描述。
type：交易类型。
响应：返回创建的交易记录，状态码 201。
删除交易
URL：DELETE /api/transactions/{id}
参数：
id：要删除的交易记录的 ID。
响应：删除成功返回状态码 204，未找到记录返回状态码 404。
修改交易
URL：PUT /api/transactions/{id}
参数：
id：要修改的交易记录的 ID。
amount（可选）：新的交易金额。
description（可选）：新的交易描述。
type（可选）：新的交易类型。
响应：返回修改后的交易记录，状态码 200，未找到记录返回状态码 404。
列出所有交易
URL：GET /api/transactions
参数：
page（可选，默认值 0）：页码。
size（可选，默认值 10）：每页的记录数。
响应：返回交易记录列表，状态码 200。
