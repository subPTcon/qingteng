# 青藤校园用户服务数据模型+RESTful API文档

---

## 1.数据模型

### User

| 字段名称   | 类型            | 说明                                                  |
| ---------- | --------------- | ----------------------------------------------------- |
| id         | `Long`          | 用户ID,自增长                                         |
| username   | `String`        | 用户名，不超过20个字符（英文字母+数字+_），不能为null |
| tel        | `String`        | 用户电话，电话格式限制，不能为null                    |
| college    | `String`        | 用户学校，不超过20个字符，不能为null                  |
| avatarUrl  | `String`        | 用户头像存储地址，不能为null                          |
| createTime | `LocalDateTime` | 用户创建时间，不能为null                              |
| updateTime | `LocalDateTime` | 用户更新时间，不能为null                              |
| gender     | `Character`     | 用户性别，一个中文字符（“男”，“女”），不能为null      |
| weChatId   | `String`        | 用户微信ID                                            |
| nickname   | `String`        | 用户昵称，不超过20个字符                              |
| password   | `String`        | 用户密码，不能为null                                  |

### UserCreateDto

| 字段名称 | 类型        | 说明                                             |
| -------- | ----------- | ------------------------------------------------ |
| username | `String`    | 用户名                                           |
| tel      | `String`    | 用户手机号，电话格式限制，不能为null             |
| college  | `String`    | 用户学校，不超过20个字符，不能为null             |
| gender   | `Character` | 用户性别，一个中文字符（“男”，“女”），不能为null |
| password | `String`    | 用户密码                                         |

### UserCreateVo

| 字段名称 | 类型        | 说明       |
| -------- | ----------- | ---------- |
| username | `String`    | 用户名     |
| tel      | `String`    | 用户手机号 |
| college  | `String`    | 用户学校   |
| gender   | `Character` | 用户性别   |

### UserPasswordLoginDto

| 字段名称 | 类型     | 说明                   |
| -------- | -------- | ---------------------- |
| tel      | `String` | 用户手机号，手机号格式 |
| password | `String` | 用户密码               |

### UserLoginVo

| 字段名称 | 类型     | 说明                            |
| -------- | -------- | ------------------------------- |
| userId   | `Long`   | 用户ID                          |
| username | `String` | 用户名                          |
| token    | `String` | 根据用户ID生成的token，用于验证 |

### UserProfileVo

| 字段名称         | 类型   | 说明        |
| ---------------- | ------ | ----------- |
| username         | String | 用户名      |
| college          | String | 学校名      |
| tel              | String | 手机号      |
| postCount        | Long   | 发帖数      |
| postGetLikeCount | Long   | 发帖获赞数  |
| avatarUrl        | String | 用户头像URL |

---

## 2. SQL建表脚本

### User

```SQL
create table if not exists user (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username varchar(20) not null,
  tel varchar(20) not null unique,
  college varchar(20) not null,
  profile_photo varchar(225) not null,
  create_time DateTime not null,
  update_time DateTime not null,
  gender char(1) not null,
  wechat_id varchar(100),
  nickname varchar(20)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';
```

---

## 3. RESUful API

基础路径：`/api/user/`

### 3.1 创建用户

接口描述：创建一个新的用户信息

请求路径：`/api/user/create`

HTTP方法：POST

请求头：`Content-Type: application/json`

请求参数:

| 参数名   | 类型     | 说明                                   |
| -------- | -------- | -------------------------------------- |
| username | `String` | 用户名，不超过20个字符，不能为空       |
| tel      | `String` | 用户手机号，手机号格式，不能为空       |
| college  | `String` | 用户学校名称，不能为空                 |
| gender   | `String` | 用户性别，一个字符（男，女），不能为空 |
| password | `String` | 用户密码，不能为空                     |

响应状态码：

- 201 创建成功
- 400 Bad Request请求参数错误

成功响应示例：

```json
{
  "code": 201,
  "message": "用户创建成功",
  "data": null
}
```

失败响应示例:

```json
{
  "code": 400,
  "message": "参数验证失败",
  "data": null,
  "errors": [
    {
      "field": "username"
      "code": 4001,
      "message": "用户名不能为空"
    },
    {
      "field": "tel",
      "code": 4002,
      "message": "用户手机号不能为空"
    },
    {
      "field": "college",
      "code": 4003,
      "message": "用户学校名称不能为空"
    },
    {
      "field": "gender",
      "code": 4004,
      "message": "用户性别不能为空"
    },
    {
      "field": "password",
      "code": 4005,
      "message": "用户密码不能为空"
    }
  ]
}
```

### 3.2 用户登录（使用手机号+密码）

接口描述：用户使用手机号+密码进行登录

请求路径：`/api/user/loginByPassword`

HTTP方法：POST

请求头：`Content-Type: application/json`

请求参数：

| 字段名称 | 类型     | 说明       |
| -------- | -------- | ---------- |
| tel      | `String` | 用户手机号 |
| password | `String` | 用户密码   |

成功响应示例：

```json
{
  "code": 202,
  "message": "用户登录成功",
  "data": {
    "userId": "用户ID",
    "username": "用户名",
    "token": "生成的token"
  }
}
```

失败响应示例：

```json
{
  "code": 4021,
  "message": "用户不存在，请先注册",
  "data": null
}
```

```json
{
  "code": 4022,
  "message": "手机号或密码错误",
  "data": null
}
```

### 3.3 用户使用手机号获取验证码

接口描述：用户使用手机号获取验证码

请求路径：`/api/user/getVerificationCode/{tel}`

HTTP方法：GET

成功响应示例：

```json
{
  "code": 203,
  "message": "获取验证码成功",
  "data": null
}
```

失败响应示例：

```json
{
  "code": 500,
  "message": "服务器错误",
  "data": null
}
```

### 3.4 使用ID获取个人主页信息

接口描述：用户发送ID + token获取个人主页信息

请求路径：`/api/user/profile/{id}`

HTTP方法：GET

请求头：`Authorization: Bearer <access_token>`

成功响应示例：

```json
{
  "code": 204,
  "message": "获取个人主页信息成功",
  "data": {
    "username": "用户名",
    "college": "用户所属学院",
    "tel": "用户手机号",
    "postCount": 1(用户发帖数),
    "postGetLikeCount": 10(用户帖子获赞总数),
    "avatarUrl": "用户头像URL"
  }
}
```

错误响应示例：

```json
{
  "code": 4011,
  "message": "请求头中缺少token",
  "data": null
}
```

```json
{
  "code": 4012,
  "message": "token无效或已过期",
  "data": null
}
```

