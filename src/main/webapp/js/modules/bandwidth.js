/**
 * 带宽统计
 */
var bandwidth = {
    
    params: {
        // 保留绘图对象供后续使用
        chartPeak: undefined,
        chartAvg: undefined
    },
    
    /**
     * 初始化
     * @params isAdmin 是否管理员。如果不是管理员，则会自动进行一次查询
     */
    init: function(isAdmin) {
        $(function() {
            bandwidth.initTimeRange();
            bandwidth.initDatePicker();
            $("#qbutton").click(bandwidth.query);
            if (!isAdmin) {
                $("#qbutton").click();
            }
        });
    },
    
    /**
     * 初始化时间范围选择时的联动
     */
    initTimeRange: function() {
        // 选择了时间范围后，级联地设置开始时间、结束时间
        $("#time_range").change(function() {
            $("#beginDate,#endDate").unbind("change"); // 先解绑开始时间、结束时间的change事件，避免事件死循环
            
            switch ($("#time_range").val()) {
            case "today":
                var today = dateutil.today();
                $("#beginDate,#endDate").val(today);
                break;
            case "last_7_days":
                $("#beginDate").val(dateutil.formatDateNormal(dateutil.addDays(-6)));
                $("#endDate").val(dateutil.today());
                break;
            case "this_month":
                $("#beginDate").val(dateutil.formatDateNormal(dateutil.getFirstDayOfMonth()));
                $("#endDate").val(dateutil.today());
                break;
            case "last_month":
                var endDate = dateutil.addDays(-1, dateutil.getFirstDayOfMonth());
                $("#beginDate").val(dateutil.formatDateNormal(dateutil.getFirstDayOfMonth(endDate)));
                $("#endDate").val(dateutil.formatDateNormal(endDate));
                break;
            }
            
            // 设置好开始时间、结束时间后，再绑定change事件
            $("#beginDate,#endDate").change(function() {
                $("#time_range option[value='custom']").attr("selected", "selected");
            });
        });
        
        // 如果手工选择时间，级联地设置时间范围为“自定义”
        $("#beginDate,#endDate").change(function() {
            $("#time_range option[value='custom']").attr("selected", "selected");
        });
    },
    
    /**
     * 初始化日历控件
     */
    initDatePicker: function() {
        $.datepicker.setDefaults($.extend(
                {maxDate:'0'}, 
                $.datepicker.regional['zh-CN']
        ));
        $("#beginDate,#endDate").datepicker();
    },
    
    /**
     * 查询数据
     */
    query: function() {
        if (!bandwidth.validateParams()) {
            return;
        }
        
        // 查询数据，绘图
        $.getJSON(
            "/accesslog/bandwidth/data/", 
            {customer: $('#parent').val(), beginDate: $('#beginDate').val(), endDate: $('#endDate').val()}, 
            bandwidth.handleQueryResult
        );
    },
    
    /**
     * 校验参数
     * @return 校验通过则返回true，否则返回false
     */
    validateParams: function() {
        // 校验客户
        if (!($('#parent').val())) {
            alert("请选择客户");
            return false;
        }

        // 校验时间
        return dateutil.validateTimeRangeWithAlert(366);
    },
    
    /**
     * 处理查询结果
     */
    handleQueryResult: function(data) {
        if (!data.success) {
            $("#chart_peak").hide();
            $("#chart_avg").hide();
            $("#flux").hide();
            $("#info_nodata").show();
        } else {
            $("#info_nodata").hide();
            $("#chart_peak").show();
            $("#chart_avg").show();
            
            if (!bandwidth.params.chartPeak) {
                bandwidth.params.chartPeak = echarts.init(document.getElementById("chart_peak"));
                bandwidth.params.chartAvg = echarts.init(document.getElementById("chart_avg"));
            }
            bandwidth.params.chartPeak.setOption(bandwidth.getChartData(data.bandwidthPeak), true);
            bandwidth.params.chartAvg.setOption(bandwidth.getChartData(data.bandwidthAvg), true);
            
            $("#flux_output").html(numberutil.formatFlux(data.flux.in));
            $("#flux_input").html(numberutil.formatFlux(data.flux.out));
            $("#flux").show();
        }
    },
    
    /**
     * 获取供 echarts 使用的绘图数据
     */
    getChartData: function(chartData) {
        result = $.extend(true, {}, bandwidth.chartTemplate);
        result.title.text = chartData.title;
        result.title.subtext = chartData.subTitle;
        result.xAxis[0].data = chartData.categories;
        result.series[0].data = chartData.bandwidthIn;
        result.series[1].data = chartData.bandwidthOut;
        return result;
    },
    
    /**
     * 供 echarts 使用的绘图数据模板
     */
    chartTemplate: {
        "title" : {
            "text": "", // 标题
            "subtext": "", // 副标题
            "x": "center",
            "subtextStyle": {
                "color": "#666",
                "fontSize": 13
            }
        },
        "grid": {
            "x2": 40,
            "y2": 20
        },
        "tooltip" : { // 悬浮提示框
            "trigger": "axis",
            "showDelay": 5,
            "hideDelay": 20,
            "formatter": chartutil.formatTooltip // 接收的第一个参数为数组，与 series 对应。数组中每个对象有各种属性：seriesName（对应 series[k].name）, name（对应 xAxis.data[i]）, value（对应 series[k].data[i]）
        },
        "legend": { // 图例
            "x": "right",
            "data":["回源带宽","节省带宽"],
            "selected": {"回源带宽": false}
        },
        "xAxis" : [
            {
                "type" : "category",
                "boundaryGap" : false,
                "data": [] // 横坐标轴标签数据
            }
        ],
        "yAxis" : [
            {
                "type" : "value",
                "axisLabel": {
                    "formatter": numberutil.formatBandwidth // 接收的参数即为 series.data 中的一个值
                }
            }
        ],
        "series" : [
            {
                "name":"回源带宽",
                "type":"line",
                "smooth":true,
                "symbol": "none",
                "itemStyle": {
                    "normal": {
                        "areaStyle": {"type": "default"}, // default 表示填充
                        "lineStyle": {"width": 1}
                    }
                },
                "data": [] // 一个系列的数据
            },
            {
                "name":"节省带宽",
                "type":"line",
                "smooth":true,
                "symbol": "none",
                "itemStyle": {
                    "normal": {
                        "areaStyle": {"type": "default"},
                        "lineStyle": {"width": 1}
                    }
                },
                "data": [] // 一个系列的数据
            }
        ]
    }
}