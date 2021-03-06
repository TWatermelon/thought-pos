# Thought-POS (for the final battle)

### travis-ci

[![travis-ci](https://api.travis-ci.org/TWatermelon/thought-pos.svg)](https://travis-ci.org/TWatermelon/thought-pos)

### BUILD

#### Generate IntelliJ IDEA project
`./gradlew cleanIdea idea`

Open the `thought-pos.ipr` using IntelliJ

#### Run clean and build tasks
`./gradlew clean build`

This will run checkstyle, unit test and code coverage check

for more details, go check
[CheckStyle](https://github.com/TWatermelon/thought-pos/blob/master/config/checkstyle/checkstyle.xml)
[TestConverage](https://github.com/TWatermelon/thought-pos/blob/master/gradle.properties)

#### start application
`./gradlew bootRun` or `java -jar build/libs/pos-0.1.0.jar`
#### swagger-ui links
`http://localhost:8081/pos/swagger-ui.html`
##### username: admin
##### password: admin

### 测试运行结果
#### 有95折优惠和买二赠一优惠：
![result](http://bmob-cdn-1019.b0.upaiyun.com/2016/07/19/e8508fc2407a56f88044351b4b43ef62.png)
#### 只有95折优惠：
![result](http://bmob-cdn-1019.b0.upaiyun.com/2016/07/19/4d54cbef4091b65a80ec1eb03e5260e1.png)
#### 只有买二赠一优惠：
![result](http://bmob-cdn-1019.b0.upaiyun.com/2016/07/19/6db26c5e4027ad8f80566089a9b183b2.png)
#### 无优惠：
![result](http://bmob-cdn-1019.b0.upaiyun.com/2016/07/19/795308694031ce0080f517fcc380de6d.png)
#### swagger-ui添加Goods
![addGoods1](http://bmob-cdn-1019.b0.upaiyun.com/2016/07/20/ad9c5ca040531796807c043051adeaee.png)
![addGoods2](http://bmob-cdn-1019.b0.upaiyun.com/2016/07/20/787537f14082aedc80136c13472998ea.png)
##### generateReceipt
![generateReceipt1](http://bmob-cdn-1019.b0.upaiyun.com/2016/07/20/75a29a7a404a49cf805f2063e9066d3a.png)
![generateReceipt1](http://bmob-cdn-1019.b0.upaiyun.com/2016/07/20/7b6e6376402b5b9c80867704111fe28e.png)


### TODO
- 为GoodsRepository实现类增加Promotions的反射set方式
- 异常处理

### Git提交规范：

[卡号][提交作者&Pair作者] - comment here

Note: [提交作者]和comment之间需要有 空格+minus+空格，comment内容不要出现minus。

**Example:** `[M001][lijun&jason] - add the order domain object`


### Java代码规范：

- Java代码必须删去没有引用到的包，Import语句为灰色即是可删去。
- 类文件开始处不要出现 "/* xxx create xxx */" 这样自动生成的注释。
- 还有其他规范Checkstyle也会覆盖到

