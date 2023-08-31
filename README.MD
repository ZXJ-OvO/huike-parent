### Google Kaptcha

#### 引入依赖

```xml
<properties>
    <kaptcha.version>2.3.2</kaptcha.version>
</properties>

<dependency>
    <groupId>com.github.penggle</groupId>
    <artifactId>kaptcha</artifactId>
    <version>${kaptcha.version}</version>
</dependency>
```



#### yaml中配置模式

> yaml中的自定义配置推荐放在application-dev.yml中，前提是你在application.yaml（主配置文件）中启用它，通过这种方式，你可以针对不同的环境创建不同的配置文件，就像git的分支那样灵活

```yaml
# application.yaml
spring:
  profiles:
    active: dev  # 激活 application-dev.yaml 配置
    # springboot 会开启 application-<profile>.yaml 最好遵循这种命名规范
```



```yaml
# application-dev.yaml
huike:
  captchaType: math # 验证码类型 math: 数字计算,  char: 字符验证
```



#### cv验证码文本生成器

```java
// 1. 继承 DefaultTextCreator  从指定长度的字符数组中随机创建文本。
public class KaptchaTextCreator extends DefaultTextCreator {

    // 2. 定义字符串数组，包含数字 0 到 10 的字符串。用于生成验证码中的数字字符。
    private static final String[] CNUMBERS = "0,1,2,3,4,5,6,7,8,9,10".split(",");

    @Override
    public String getText() {
        // 3. 随机生成参与运算的数，定义算式中的三个数
        Integer result = 0; // 值
        Random random = new Random();
        int x = random.nextInt(10); // 数1
        int y = random.nextInt(10); // 数2
        StringBuilder suChinese = new StringBuilder();
        
        // 4. 随机生成 0~2 之间的整数数值，用于确定运算模式
        int randomoperands = (int) Math.round(Math.random() * 2);
        
        // 5. 根据模式取值选择对应的运算模式
        if (randomoperands == 0) {
            // 0 乘法运算
            
            // 计算运算结果
            result = x * y;
            // 拼接得到算式
            suChinese.append(CNUMBERS[x]);
            suChinese.append("*");
            suChinese.append(CNUMBERS[y]);
        } else if (randomoperands == 1) {
            // 1 除法运算
            
            // 如果 x 不等于 0，同时 y 是 x 的倍数 执行除法运算
            if (!(x == 0) && y % x == 0) {
                result = y / x;
                suChinese.append(CNUMBERS[y]);
                suChinese.append("/");
                suChinese.append(CNUMBERS[x]);
            } else {
                // 否则 执行加法运算
                result = x + y;
                suChinese.append(CNUMBERS[x]);
                suChinese.append("+");
                suChinese.append(CNUMBERS[y]);
            }
        } else if (randomoperands == 2) {
            // 2 减法运算
            
            if (x >= y) {
                result = x - y;
                suChinese.append(CNUMBERS[x]);
                suChinese.append("-");
                suChinese.append(CNUMBERS[y]);
            } else {
                result = y - x;
                suChinese.append(CNUMBERS[y]);
                suChinese.append("-");
                suChinese.append(CNUMBERS[x]);
            }
        } else {
            // 否则 使用默认的加法运算（似乎不可能）
            
            result = x + y;
            suChinese.append(CNUMBERS[x]);
            suChinese.append("+");
            suChinese.append(CNUMBERS[y]);
        }
        
        // 6. 拼接最终的算式并转成String返回
        suChinese.append("=?@" + result);
        return suChinese.toString();
    }
}
```



#### 编写验证码controller

```java
@Api(tags = "通用服务")
@RestController
public class CaptchaController {

    // 1. 操作 Google Kaptcha 需要注入对应的Producer，它提供了两个实现，默认的可用于操作常规字符，带Math后缀的可以操作数学运算
    
    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    // 2. 注入使用的验证码类型
    @Value("${huike.captchaType}")
    private String captchaType;


    @Autowired
    private RedisCache redisCache;

    /**
     * 生成验证码
     */
    @ApiOperation("验证码-生成")
    @GetMapping("/captchaImage")
    public AjaxResult getCode(HttpServletResponse response) throws IOException {
        // 定义redis中的key
        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        // 定义表达式、结果、验证码图片
        String capStr = null, code = null;
        BufferedImage image = null;

        // 生成验证码
        if ("math".equals(captchaType)) { //数学运算
            String capText = captchaProducerMath.createText(); //创建数学运算题文本 ----> 8+1=?@10
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr); //生成图片

        } else if ("char".equals(captchaType)) { //常规字符
            capStr = code = captchaProducer.createText(); //生成随机字符
            image = captchaProducer.createImage(capStr); //生成图片
        }

        // 将信息保存到redis
        redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);

        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
            return AjaxResult.error(e.getMessage());
        }

        AjaxResult ajax = AjaxResult.success();
        ajax.put("uuid", uuid);
        ajax.put("img", Base64.encode(os.toByteArray()));
        return ajax;
    }
}
```

![93c50c6246ca1b4f0ed1e5fb8a677983](https://raw.githubusercontent.com/ZXJ-OvO/picgo-img/master/202308302001517.png)

![image-20230830200820958](https://raw.githubusercontent.com/ZXJ-OvO/picgo-img/master/202308302008006.png)

> 请求验证码的逻辑响应了uuid，uuid在login逻辑中以loginDto的属性再次被使用，然后在service中根据uuid进行值校验，至此验证码的流程结束