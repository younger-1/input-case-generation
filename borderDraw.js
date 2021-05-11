var map, addMarker;
var geocoder;
var placeSearch;

// function $(Nid) {
//     return document.getElementById(Nid);
// }

$(function () {
    // 加入高的地图
    map = new AMap.Map('mymap', {
        resizeEnable: true/* ,
            zoom:11,
            center: [116.397428, 39.90923] */
    });
    AMap.plugin(['AMap.ToolBar', 'AMap.Scale'],
        function () {
            map.addControl(new AMap.ToolBar());

            map.addControl(new AMap.Scale());
        });
    AMap.service('AMap.Geocoder', function () {//回调函数
        //实例化Geocoder
        geocoder = new AMap.Geocoder({
            city: "全国"//城市，默认：“全国”
        });
        //TODO: 使用geocoder 对象完成相关功能
    });
    // 加载搜索列表
    myMapViewLocation();
    AMap.service(["AMap.PlaceSearch"], function () {
        placeSearch = new AMap.PlaceSearch({ //构造地点查询类
            pageSize: 5,
            pageIndex: 1,
            city: "全国", //城市
            map: map,
            panel: "panel"
        });
    });
    //为地图注册click事件获取鼠标点击出的经纬度坐标
    var clickEventListener = map.on('click', function (e) {
        $("input[name=lon]").val(e.lnglat.lng);
        $("input[name=lat]").val(e.lnglat.lat);
        // 填写地址
        writeAddress([e.lnglat.lng, e.lnglat.lat]);
    });
    //placeSearch.search("北京");
    $("#mymap_search").on("keydown", function (event) {
        if (event = event || window.event) {
            if (event.keyCode == 13) {
                mapsearch();
            }
        }
    });
});
// 地图搜索
function mapsearch() {
    var searchVal = $("#mymap_search").val();
    placeSearch.search(searchVal);
}
// 回显
function myMapViewLocation() {
    //console.log("回显坐标");
    var mlon = $("input[name=lon]").val();
    var mlat = $("input[name=lat]").val();
    var lnglatXY = [mlon, mlat];
    if (mlon && mlat) {
        addMarker(lnglatXY);
    }
}
// 实例化点标记
function addMarker(lnglatXY) {
    //console.log(lnglatXY);
    marker = new AMap.Marker({
        icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
        position: lnglatXY
    });
    marker.setMap(map);
    map.setFitView();// 执行定位
}
// 填写地址
function writeAddress(lnglatXY) {
    geocoder.getAddress(lnglatXY, function (status, result) {
        if (status === 'complete' && result.info === 'OK') {
            geocoder_CallBack(result);
        }
    });
}
// 地址回调
function geocoder_CallBack(data) {
    var address = data.regeocode.formattedAddress; //返回地址描述
    $("input[name=resourceAddress]").val(address);
}