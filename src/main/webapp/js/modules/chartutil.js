/**
 * 绘图工具
 */
var chartutil = {
    /**
     * 格式化悬浮提示框的内容
     * @params tootips 数组，与 series 对应。数组中每个对象有各种属性：seriesName（对应 series[k].name）, name（对应 xAxis.data[i]）, value（对应 series[k].data[i]）
     * @return 例如 "2015-09-01<br>回源带宽：12.34 Mbps<br>节省带宽：12.34 Mbps"
     */
    formatTooltip: function(tooltips) {
        var arr = [];
        arr.push(tooltips[0].name);
        for (var i=0; i<tooltips.length; i++) {
            arr.push("<br>");
            arr.push(tooltips[i].seriesName);
            arr.push(": ");
            arr.push(numberutil.formatBandwidth(tooltips[i].value));
        }
        return arr.join("");
    }
}