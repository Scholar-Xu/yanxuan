var E = window.wangEditor;
// 创建editor
var editor = new E('#editor');
// 自定义属性
// 设置的服务器端的请求路径，用来完成图片的上传
editor.customConfig.uploadImgServer = '/upload';
// 是否显示网络图片的菜单
editor.customConfig.showLinkImg = false;
// filename的属性值为file
editor.customConfig.uploadFileName = "file";
// 页面的初始化
editor.create();