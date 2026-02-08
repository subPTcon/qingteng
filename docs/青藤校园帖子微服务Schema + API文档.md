# 青藤校园帖子微服务Schema + API文档

---

## 1. 数据模型

### Post

| 字段名称       | 类型            | 说明                                                         |
| -------------- | --------------- | ------------------------------------------------------------ |
| content        | `String`        | 帖子内容，限制500字                                          |
| type           | `String`        | 帖子分类                                                     |
| anonymous      | `Integer`       | 发帖人是否匿名，0 - 不匿名，1 - 匿名                         |
| posterId       | `Long`          | 发帖人Id                                                     |
| id             | `Long`          | 帖子ID，主键                                                 |
| createTime     | `LocalDateTime` | 帖子创建时间                                                 |
| updateTime     | `LocalDateTime` | 帖子更新时间                                                 |
| isComment      | `Integer`       | 帖子是否为其他帖子的评论，0 - 不是，1 - 是                   |
| likeCount      | `Long`          | 帖子被点赞数量                                               |
| posterUsername | `String`        | 发帖人用户名                                                 |
| mainPostId     | `Long`          | 如果本帖子是其他帖子的评论，那么该字段的值就是主贴的ID，如果不是其他帖子的评论，本字段的值默认为-1 |

### PostCreateDto

| 字段名称       | 类型   | 说明                         |
| -------------- | ------ | ---------------------------- |
| posterId       | Long   | 发帖人id                     |
| posterUsername | String | 发帖人用户名                 |
| anonymous      | int    | 是否匿名,0-不匿名，1-匿名    |
| content        | String | 帖子内容                     |
| type           | String | 帖子类型                     |
| isComment      | int    | 帖子是否为评论，0-不是，1-是 |

### PostListVo

| 字段名称        | 类型     | 说明          |
| --------------- | -------- | ------------- |
| posterUsername  | `String` | 发帖人用户名  |
| posterAvatarUrl | `String` | 发帖人头像URL |
| time            | `String` | 发帖时间      |
| type            | `String` | 帖子类型      |
| likeCount       | `Long`   | 帖子点赞数    |
| commentCount    | `Long`   | 帖子评论数    |

---

## 2.SQL脚本

### 2.1创建post表

```sql
create table if not exists post (
	id bigint primary key auto_increment,
  content varchar(500) not null,
  type int not null,
  anonymous int not null,
  poster_id bigint not null,
  create_time DateTime not null,
  update_time DateTime not null,
  is_comment int not null,
  like_count bigint not null,
  poster_username varchar(20) not null,
  main_post_id bigint not null default -1
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='创建post帖子表';
```

---

## 3.RESTful API

基础路径：`/api/post/`

### 3.1 创建帖子

接口说明：用户发布帖子（或评论）

请求路径：`/api/post/create`

HTTP方法：POST

请求头：`Content-Type: application/json` `Authorization: Bearer <token>`

请求参数：

| 参数名         | 类型      | 说明           |
| -------------- | --------- | -------------- |
| posterId       | `Long`    | 发帖人ID       |
| posterUsername | `String`  | 发帖人用户名   |
| isAnonymous    | `Integer` | 发帖人是否匿名 |
| content        | `String`  | 帖子内容       |
| type           | `String`  | 帖子类型       |
| isComment      | `Integer` | 帖子是否为评论 |

响应状态码：

- 2011 帖子创建成功
- 4031 帖子创建失败

成功响应示例：

```json
{
  "code": 2011,
  "message": "帖子创建成功",
  "data": null
}
```

失败响应示例：

```json
{
  "code": 4031,
  "message": "帖子创建失败",
  "data": null
}
```

### 3.2 获取所有帖子（按发布时间）

接口说明：按照发布时间获取所有帖子（只有帖子，没有评论）

请求路径：`/api/post/listByCreateTime`

HTTP方法：GET

请求头：`Authorization: Bearer <token>`

响应状态码：

- 2012 按时间获取帖子成功
- 4032 按时间获取帖子失败

成功响应示例：

```json
{
  "code": 2012,
  "message": "按时间获取帖子成功",
  "data": null
}
```

失败响应示例：

```json
{
  "code": 4032,
  "message": "按时间获取帖子失败",
  "data": null
}
```





