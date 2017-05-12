var dateutil = {

    MILLISECONDS_ONE_DAY : 1000 * 60 * 60 * 24,

    /**
     * 获取当天的日期，格式例如 2014-01-01
     */
    today : function() {
        return dateutil.formatDateNormal(new Date());
    },
    
    /**
     * 获取指定日期所在月份的第一天
     * @params date 如果为空则默认为今天
     */
    getFirstDayOfMonth: function(date) {
        var result = new Date(date || dateutil.today());
        result.setDate(1);
        return result;
    },
    
    /**
     * 为指定日期添加指定天数
     * @params count: 天数，可以为负数
     * @params date: 指定日期，不传入该值时默认为今天
     */
    addDays: function(count, date) {
        var result = new Date(date || dateutil.today());
        result.setDate(result.getDate() + count);
        return result;
    },
    
    /**
     * 格式化日期，例如 2014-01-01
     */
    formatDateNormal: function(d) {
        var month = d.getMonth() < 9 ? "0" + (d.getMonth() + 1) : d.getMonth() + 1;
        var date = d.getDate() < 10 ? "0" + d.getDate() : d.getDate();
        return d.getFullYear() + "-" + month + "-" + date;
    },

    /**
     * 校验时间范围不超过指定天数
     * @param maxDays: 允许的最大天数
     * @param startTime: 开始时间，字符串类型，例如 2014-08-01
     * @param endTime: 结束时间，字符串类型，例如 2014-08-01
     * @return 校验通过则返回true，否则返回false
     */
    validateTimeRange : function(maxDays, startTime, endTime) {
        return ((new Date(endTime)).getTime() - (new Date(startTime)).getTime()) <= dateutil.MILLISECONDS_ONE_DAY * (maxDays - 1)
    },

    /**
     * 校验时间范围不超过指定天数
     * @param maxDays: 允许的最大天数
     * @param startTimeEleId: 开始时间元素ID，如果为空则默认为 beginDate
     * @param endTimeEleId: 结束时间元素ID，如果为空则默认为 endDate
     * @return 校验通过则返回true，否则，弹窗提示，然后返回false
     */
    validateTimeRangeWithAlert : function(maxDays, startTimeEleId, endTimeEleId) {
        startEleId = startTimeEleId || "beginDate";
        endEleId = endTimeEleId || "endDate";
        if (!dateutil.validateTimeRange(maxDays, $("#" + startEleId).val(), $("#" + endEleId).val())) {
            alert("时间超出" + maxDays + "天");
            return false;
        }
        return true;
    }
}