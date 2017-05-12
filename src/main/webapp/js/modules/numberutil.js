var numberutil = {

    /**
     * 格式化带宽数据
     * @return 例如 12.34 Tbps, 12.34 Gbps, 12.34 Kbps
     */
    formatBandwidth: function(value) {
        var result;
        var scale = 1;
        var unit = "bps";
        if (value >= 1000000000000) {
            scale = 1000000000000;
            unit = "Tbps";
        } else if (value >= 1000000000) {
            scale = 1000000000;
            unit = "Gbps";
        } else if (value >= 1000000) {
            scale = 1000000;
            unit = "Mbps";
        } else if (value >= 1000) {
            scale = 1000;
            unit = "Kbps";
        }
        return (value / scale).toFixed(2) + " " + unit;
    },
    
    /**
     * 格式化流量数据
     * @return 例如 12.34 GB, 12.34 KB, 12.34 Byte
     */
    formatFlux: function(value) {
        var result;
        var scale = 1;
        var unit = "Bytes";
        if (value > 1024 * 1024 * 1024 * 1024) {
            scale = 1024 * 1024 * 1024 * 1024;
            unit = "TB";
        } else if (value > 1024 * 1024 * 1024) {
            scale = 1024 * 1024 * 1024;
            unit = "GB";
        } else if (value > 1024 * 1024) {
            scale = 1024 * 1024;
            unit = "MB";
        } else if (value > 1024) {
            scale = 1024;
            unit = "KB";
        }
        return (value / scale).toFixed(2) + " " + unit;
    }
}