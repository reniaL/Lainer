var stringutil = {
    
    /**
     * 截取字符串
     * @param str 目标字符串
     * @param length 最大长度
     * @return 如果str未定义或为空，则返回空字符串<br>
     *         如果str长度不超过length，则返回str<br>
     *         否则，返回str的前length个字符，并追加...
     */
    truncate: function(str, length) {
        if (!str) {
            return "";
        }
        if (str.length <= length) {
            return str;
        }
        return str.substring(0, length) + "...";
    }
}