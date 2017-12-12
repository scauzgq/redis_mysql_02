# redis_mysql_02
## 1.测试Redis作为Mysql缓存<br>
#### 用业务层实现：拦截mysql进行的查询操作，没有数据再读取mysql层，并写入数据到nosql;<br>
#### 同步策略：拦截mysql进行的更新操作，待mysql更新完成，使相应的缓存数据失效.<br>
## 2.测试MyBatis的一级缓存<br>
