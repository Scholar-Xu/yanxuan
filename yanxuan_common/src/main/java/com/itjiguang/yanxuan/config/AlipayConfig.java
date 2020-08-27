package com.itjiguang.yanxuan.config;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016101500688850";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC1F7iTNRZvKeSwOSuve6g58OYTJcgfkYWv3cPonTQxd8CXc7jF7r5epMMW5OrotYNtwYulrPTZzWlhfaJaoQuEntmNYvK0fURwa1IewJd2fzRcCgt4qKG7G2QNyShmdK+9pNHB0/91uBNi/GSwc97AJvMdD9XcnrMUSzU6pGlKfzoPF++WIqa+gN/4exHA6DkTvOy2mxsGPZBVqJZWTSgLWHtKXeveicG1bdwBBxeeaKu8qDiEELXTayWwE48jLu5TR/GLEU7uqPF8rAtcAc/I/q5lKrfPg8G91eYyPobDrrhJvjpIvWXemCRRksg10FUT5eUo+XObMRYenjYH4YQrAgMBAAECggEAX1NHQ6YUUxvYV7MgfdU37pgSZE3lFEkD+FliQNZPnSKObKrrvrU33JYaY7jItwqlTmctXV633Na1/C2oAtD3QS5lEnndjIjMN+kmZy22DRhUtYZMF42c5OM8Pc1/yL7vq4JRMBiqRotJGTgGwKOdw8Q9ATVOvD2Fws78DAxzqFSBFyNxNjFpxghMg1VK+AXovO8/JtYNh+BZdd2rwqA+X5Ufhl8iDvsOGT+UQmVbwuY97bCi0wbKWgYG/eFPwzXqV9dKjDvJL4k5k8pNWAkUBfeR+tcOkxgkxlqPpn+PlFauOEMRnyq9MlL1pPnSCSkIeNZr8l1oLJvRxq8SH7FYMQKBgQDo9CgTeN/ap0vuYg89ff1loE0SrSweUk/zz2Ik28sNPkcf6dQUZlvl5US4xgTGNcKvZkWc1DJGKLmBEfFtfi7i90ukUkMKr0vcJMF/FaWn0rdNlO156tYHa+2La6YS0RXYDG2Bk2vBaN/XE8xOYGLx5H/AiJct0LdBHGRvmX6t3wKBgQDHAh48mejX8DApJE6LdFFjt+LA5RPybt8wwlwZhqYDeLpGJIthynOqLlyYtyzlnQ2xCFClbOwJGzvWyE95hE6PFDNBsZFrxnoeq9DVi2Y0DM+nFajn6mq8CbGxi7myebJmKWN3lRDXjRB5lof0zXbftK7vPGqvCA2XMLuDU88bNQKBgQCiRnqoXdpqgYHWdH6zUlGzeoBWE0YZdTN23q86h9WhyxFWvNdcwpUfx58gq+hlfsu4zaCOiA7harsWmz44DN9ygdwygo9rIQPSPdSAx76W8qV4KNdox1sC3n8iUQHmN7pkvvuFeQFmQL01IPiAXIpwgMExiLTvWGZft/yK9T0WtwKBgCYOtKOYN1wcVcl4ZQDsRKhSQS3sYQ0qwMn9UUmcY2EklFLXqEQSSF5SMWkPFqnuh/DEC1AnbVgsehwQAIrXj4JzNX4prAq1USNObkh4ORpZHNjyHnQDc1f0bqPqeZ+1ADXjUl9LBYaDNpRaAjQBmusbDZ04q5o/awHv0txBicjFAoGAZhz47NLB+g1ZiNi1XuYqgjac0LbtfL2auKC97yRouLtAVmuoP7tzfBhRn3AAy1eE8oX+2AniTckTluFXuQmqAHN1xcLhyABI7vQljr6fLX+vDmUhlpuUocEEEH7oJNEc5FhZfbblHjMd2Ts+j6Cj0r7/c29Glo0xlifEDHJIzAw=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAr5glWL5ZMv0Hli9Kn/IqLJR3LkWyIlqdovF4SysqQLs4tF0huUc9kiFOOkSIjtMRyUYJF/HJISJmMrG8KSnEp11A8lqxDQzoP00SuIp2eKR300WkqN10ORET+AP0YXeQZeVj5pei4KVQTk/9+HEnmBkii8oYG9YManzxbJK9nD/2DhCvLwTY5Eg5HPBw/df8d73N7bfwgHK9HULG/3rPU8y3OV0rQeEYYKknlGqA4VSSqbyXTbrd0oqOlluN1mzMgnzXd9EFTb8GjapljOws4jkCiOhlTl7wzXAFUGee7+v3br7XEbwkAc9gy6LXaNcL3w+P7wRPeKQ58nUUCqJyewIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:9906/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:9906/alipay_return";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

}
