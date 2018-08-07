/*!
 * Copyright &copy; 2012-2016 <a href="https://github.com/umasoft/umafrmsite">umafrmsite</a> All rights reserved.
 * 
 * 通用公共方法
 * @author umasoft
 * @version 2018-06-07
 */
//打开弹出层
function openCompLayer(par) {
	var index = layer.open({
		type : 2 // Page层类型
		,
		area : [ '600px', '300px' ],
		title : '选择',
		shade : 0.1 // 遮罩透明度
		,
		maxmin : true // 允许全屏最小化
		,
		anim : 1 // 0-6的动画形式，-1不开启
		,
		content : par
	});
	this.layerIndex = index;
}
