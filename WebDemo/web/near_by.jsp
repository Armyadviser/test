<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
    <style type="text/css">
        body, html {
            width: 100%;
            height: 100%;
            margin: 0;
            font-family: "微软雅黑", serif;
        }

        #all-map {
            height: 500px;
            width: 100%;
        }

        #r-result {
            width: 100%;
        }
    </style>
    <script type="text/javascript"
            src="http://api.map.baidu.com/api?v=2.0&ak=CddCNg0P3cCtp4o5U1c7o7y9U1ui4jiG"></script>
    <%--<script type="text/javascript" src="js/baidu.map.v2.0.js"></script>--%>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <title>添加/删除覆盖物</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/GetPointsWithSleep">
    <label>
        <input type="text" name="index">
        <input type="submit">
    </label>
</form>
<div id="all-map"></div>
<div id="r-result">
    <input type="button" onclick="add_overlay();" value="添加覆盖物"/>
    <input type="button" onclick="remove_overlay();" value="删除覆盖物"/>
</div>
</body>
</html>
<script type="text/javascript">
    // 百度地图API功能
    var map = new BMap.Map("all-map");
    //添加地图类型控件
    map.addControl(new BMap.MapTypeControl({
        mapTypes:[
            BMAP_NORMAL_MAP,
            BMAP_HYBRID_MAP
        ]}));
    map.setCurrentCity("沈阳");          // 设置地图显示的城市 此项是必须设置的
    map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放

    var point = new BMap.Point(123.342106524344,41.8221360293588);
    map.centerAndZoom(point, 11);

    var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
    map.addControl(top_left_control);

    function add_overlay() {
        $.ajax({
            url:'GetSpecifiedPoints',
            type:'POST',
            async: false,
            data:{},
            dataType:'json',
            success:function(data) {
                addPoints(data);
            }
        });
    }

    //清除覆盖物
    function remove_overlay() {
        map.clearOverlays();
    }

    function addPoints(data) {
        if (data.length === 0) {
            remove_overlay();
            return;
        }
        $.each(data, function(i, item) {
            var marker = new BMap.Marker(new BMap.Point(item.lon, item.lat));
            map.addOverlay(marker);
            var label = new BMap.Label(item.id,{offset:new BMap.Size(20,10)});
            marker.setLabel(label);
        });
    }
</script>
