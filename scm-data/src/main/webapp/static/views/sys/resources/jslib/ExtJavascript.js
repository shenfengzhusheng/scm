var xfs = xfs || {};

/**
 * 去字符串空格
 * 
 * @author 刘治
 */
xfs.trim = function(str) {
	return str.replace(/(^\s*)|(\s*$)/g, '');
};
xfs.ltrim = function(str) {
	return str.replace(/(^\s*)/g, '');
};
xfs.rtrim = function(str) {
	return str.replace(/(\s*$)/g, '');
};

/**
 * 判断开始字符是否是XX
 * 
 * @author 刘治
 */
xfs.startWith = function(source, str) {
	var reg = new RegExp("^" + str);
	return reg.test(source);
};
/**
 * 判断结束字符是否是XX
 * 
 * @author 刘治
 */
xfs.endWith = function(source, str) {
	var reg = new RegExp(str + "$");
	return reg.test(source);
};

/**
 * iframe自适应高度
 * 
 * @author 刘治
 * 
 * @param iframe
 */
xfs.autoIframeHeight = function(iframe) {
	iframe.style.height = iframe.contentWindow.document.body.scrollHeight + "px";
};

/**
 * 设置iframe高度
 * 
 * @author 刘治
 * 
 * @param iframe
 */
xfs.setIframeHeight = function(iframe, height) {
	iframe.height = height;
};