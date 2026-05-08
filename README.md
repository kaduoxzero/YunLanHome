# 云岚到家 (Yunlan Daojia) - 互联网分布式 O2O 家政服务平台

## 📖 项目简介

**云岚到家** 是一款高性能、高可用的互联网分布式家政服务平台。项目深度还原了 O2O（Online to Offline）行业从“线上搜索下单”到“线下履约服务”的全链路业务逻辑。

本项目旨在解决 **高并发、海量数据存储、以及复杂业务流程自动化** 等企业级挑战，涵盖了分布式事务、分库分表、异步双写、状态机引擎等核心技术栈。

---

## 🏗️ 核心架构设计

项目采用 **Spring Cloud Alibaba** 微服务架构，实现了全链路异步化与多级缓存体系。

* **网关层**: 基于 Spring Cloud Gateway 实现统一路由、鉴权及 [Sentinel 流量防护](https://nacos.io/)。
* **注册/配置中心**: 采用 [Nacos](https://nacos.io/) 负责服务治理与配置动态热更新。
* **搜索层**: 采用 Elasticsearch 配合 [Canal](https://github.com/alibaba/canal) 监听 Binlog 增量，实现 MySQL 与 ES 的异步数据同步。
* **持久层**: MySQL 配合 **ShardingSphere-JDBC** 实现千万级订单数据的水平分库分表。
* **调度层**: 集成 **XXL-JOB** 处理缓存预热、活动状态自动切换及延迟关单。

---

## 🔥 技术亮点与业务攻坚

### 1. 高并发抢券系统 (秒杀方案)
* **原子控制**: 采用 **Redis + Lua 脚本** 封装“校验-扣减-记账”逻辑，确保在高并发下库存不超卖、用户不重领。
* **异步写盘**: 抢券成功后立即返回，通过 **RabbitMQ** 异步平滑地将数据同步至 MySQL，极大降低请求响应时间（RT）。

### 2. 复杂订单状态机 (逻辑解耦)
* **状态引擎**: 引入 **Spring StateMachine** 管理订单从待支付到完工的 7 种核心状态流转。
* **模式解耦**: 结合 **策略模式 (Strategy Pattern)** 优雅处理不同渠道（微信/余额）和不同触发源（用户/后台/超时）的订单取消逻辑。

### 3. 分布式事务控制 (数据一致性)
* **跨服务核销**: 在下单核销优惠券场景下，集成 **Seata AT 模式**。
* **故障补偿**: 确保订单创建与券状态扣减在分布式环境下处于同一个原子操作，防止数据不一致。

### 4. 核心调度系统 (LBS 派单)
* **地理检索**: 利用 **Redis Geo** 指令实现服务人员位置的实时上报与 5 公里范围内的高性能圈定。
* **分布式锁**: 使用 **Redisson** 锁定订单资源，彻底解决多人抢单场景下的并发冲突。

---

## 🛠️ 技术栈清单

| 类别 | 关键技术 |
| :--- | :--- |
| **核心框架** | Spring Boot 2.7, Spring Cloud Alibaba 2021 |
| **注册中心/配置** | Nacos |
| **分布式事务/限流**| Seata, Sentinel |
| **数据库治理** | MySQL 8.0, ShardingSphere-JDBC, MyBatis-Plus |
| **缓存/分布式锁** | Redis 6.x, Redisson, SpringCache |
| **搜索/同步** | Elasticsearch 7.x, Canal |
| **消息队列** | RabbitMQ |
| **任务调度** | XXL-JOB |

## 🔗 微服务模块代码库 (Module Repositories)

本项目由多个微服务模块组成，各模块分工明确，通过 Git 进行独立维护：

| 模块名称 | 技术栈 | 仓库链接 | 描述 |
| :--- | :--- | :--- | :--- |
| **基础工程** | Java | [jzo2o-foundations](https://github.com/kaduoxzero/jzo2o-foundations) | 项目基础依赖、配置、基础工具类 |
| **通用框架** | Java | [jzo2o-framework](https://github.com/kaduoxzero/jzo2o-framework) | 微服务通用组件、安全框架、自定义 Starter |
| **API定义** | Java | [jzo2o-api](https://github.com/kaduoxzero/jzo2o-api) | 存放 Feign 接口定义、DTO 及 Feign 降级逻辑 |
| **API网关** | Java | [jzo2o-gateway](https://github.com/kaduoxzero/jzo2o-gateway) | 基于 Spring Cloud Gateway 的统一接入入口 |
| **前端工程** | Vue.js | [jzo2o-front](https://github.com/kaduoxzero/jzo2o-front) | 平台管理端、移动端 H5 界面 |
| **客户服务** | Java | [jzo2o-customer](https://github.com/kaduoxzero/jzo2o-customer) | 用户管理、地址簿、身份认证等 |
| **营销服务** | Java | [jzo2o-market](https://github.com/kaduoxzero/jzo2o-market) | 优惠券发放、活动营销、秒杀抢券逻辑 |
| **订单服务** | Java | [jzo2o-order](https://github.com/kaduoxzero/jzo2o-order) | 订单生命周期管理、派单逻辑、状态机实现 |
| **交易服务** | Java | [jzo2o-trade](https://github.com/kaduoxzero/jzo2o-trade) | 支付对接（微信/支付宝）、退款处理、交易流水 |
| **公共服务** | Java | [jzo2o-publics](https://github.com/kaduoxzero/jzo2o-publics) | 文件上传(OSS)、短信发送、基础数据字典 |

---
