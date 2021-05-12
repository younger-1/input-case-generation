### 简易行政区图-省区

<https://lbs.amap.com/demo/javascript-api/example/district/district-pro>



### 中国行政边界

- <https://lbs.amap.com/api/webservice/guide/api/district>

```jsonc
// <https://restapi.amap.com/v3/config/district?keywords=中国&subdistrict=0&extensions=all&key=4d1af9309e20a6f520e8bb81c7bc17f4>
{
    "status": "1",
    "info": "OK",
    "infocode": "10000",
    "count": "1",
    "suggestion": {
        "keywords": [],
        "cities": []
    },
    "districts": [
        {
            "citycode": [],
            "adcode": "100000",
            "name": "中华人民共和国",
            "polyline": "120.823872,40.530257;120.822751,40.530688;120.821408,40.532171;120...",
            "center": "116.3683244,39.915085",
            "level": "country",
            "districts": []
        }
    ]
}
```



### java调用高德地图获取经纬度

> <https://blog.csdn.net/csdnhyms/article/details/109457488>

```jsonc
// https://restapi.amap.com/v3/geocode/geo?address=长沙&key=4d1af9309e20a6f520e8bb81c7bc17f4
{
    "status": "1",
    "info": "OK",
    "infocode": "10000",
    "count": "1",
    "geocodes": [
        {
            "formatted_address": "湖南省长沙市",
            "country": "中国",
            "province": "湖南省",
            "citycode": "0731",
            "city": "长沙市",
            "district": [],
            "township": [],
            "neighborhood": {
                "name": [],
                "type": []
            },
            "building": {
                "name": [],
                "type": []
            },
            "adcode": "430100",
            "street": [],
            "number": [],
            "location": "112.938814,28.228209",
            "level": "市"
        }
    ]
}
```



### 学习｜判断一个点是否在三角形内 - 知乎

> <https://zhuanlan.zhihu.com/p/106253152>

```
// 叉乘（^表示叉乘符号）：
// t1 = PA^PB,
// t2 = PB^PC,
// t3 = PC^PA,
// 如果t1，t2，t3同号（同正或同负），那么P在三角形内部，否则在外部。
```



### JS实现获取坐标和回显点标记

> <https://blog.csdn.net/justdoit_potato/article/details/73189164>
> <https://blog.csdn.net/zk_1325572803/article/details/88794554>



### fastjson

> <https://github.com/alibaba/fastjson>
> <https://www.w3cschool.cn/fastjson/fastjson-stream.html>
> <https://blog.csdn.net/liupeifeng3514/article/details/79179767>
> <https://zhuanlan.zhihu.com/p/36865573>
