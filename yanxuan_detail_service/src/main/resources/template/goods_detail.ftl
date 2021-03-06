<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>极光严选</title>
	<link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/product.css">
    <link rel="stylesheet" type="text/css" href="css/zoom.css">
	<!-- <script src="js/jquery-1.12.4.js"></script>
	<script src="js/countdown.js"></script>
	<script src="js/carousel.js"></script>
	<script src="js/million.js"></script> -->
</head>
<body ng-app="yanxuan" ng-controller="goodsController">
	<!-- 顶部导航条区域开始 -->
	<div class="top_bar">
		<div class="inner_c">
			<!-- 发货城市开始 -->
			<div class="s_city">
				<span>送货至:</span>
				<div class="cur_city">
					<span>四川</span>
					<div class="s_city_bg">
						<span class="arrow_top"></span>
						<h4>请选择所在的收货地区</h4>
						<ul>
							<li>
								<span>A</span>
								<a href="javascript:void(0);">安徽</a>
								<a href="javascript:void(0);">澳门</a>
							</li>
							<li>
								<span>B</span>
								<a href="javascript:void(0);">北京</a>
							</li>
							<li>
								<span>C</span>
								<a href="javascript:void(0);">重庆</a>
							</li>
							<li>
								<span>F</span>
								<a href="javascript:void(0);">福建</a>
							</li>
							<li>
								<span>G</span>
								<a href="javascript:void(0);">广东</a>
								<a href="javascript:void(0);">广西</a>
								<a href="javascript:void(0);">贵州</a>
								<a href="javascript:void(0);">甘肃</a>
							</li>
							<li>
								<span>H</span>
								<a href="javascript:void(0);">河北</a>
								<a href="javascript:void(0);">河南</a>
								<a href="javascript:void(0);">黑龙江</a>
								<a href="javascript:void(0);">海南</a>
								<a href="javascript:void(0);">湖北</a>
								<a href="javascript:void(0);">湖南</a>
							</li>
							<li>
								<span>J</span>
								<a href="javascript:void(0);">江苏</a>
								<a href="javascript:void(0);">吉林</a>
								<a href="javascript:void(0);">江西</a>
							</li>
							<li>
								<span>L</span>
								<a href="javascript:void(0);">辽宁</a>
							</li>
							<li>
								<span>N</span>
								<a href="javascript:void(0);">内蒙古</a>
								<a href="javascript:void(0);">宁夏</a>
							</li>
							<li>
								<span>Q</span>
								<a href="javascript:void(0);">青海</a>
							</li>
							<li>
								<span>S</span>
								<a href="javascript:void(0);">上海</a>
								<a href="javascript:void(0);">山东</a>
								<a href="javascript:void(0);">山西</a>
								<a href="javascript:void(0);" class="cur">四川</a>
								<a href="javascript:void(0);">陕西</a>
							</li>
							<li>
								<span>T</span>
								<a href="javascript:void(0);">台湾</a>
								<a href="javascript:void(0);">天津</a>
							</li>
							<li>
								<span>X</span>
								<a href="javascript:void(0);">西藏</a>
								<a href="javascript:void(0);">香港</a>
								<a href="javascript:void(0);">新疆</a>
							</li>
							<li>
								<span>Y</span>
								<a href="javascript:void(0);">云南</a>
							</li>
							<li>
								<span>Z</span>
								<a href="javascript:void(0);">浙江</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<!-- 发货城市结束 -->
			<div class="top_nav">
				<div class="fl">
					<a href="#" class="login">登录</a>
					<a href="#" class="regist">注册</a>
				</div>
				<div class="fr">
					<a href="#">我的订单</a>
					<span>|</span>
					<a href="#">收藏夹</a>
					<span>|</span>
					<a href="#">网站导航</a>
					<span>|</span>
					<a href="#">客户服务</a>
					<span>|</span>
					<a href="#" class="app">APP</a>
				</div>
			</div>
		</div>
	</div>
	<!-- 顶部导航条区域结束 -->
	<!-- header、logo、搜索区域 -->
	<div class="header inner_c">
		<!-- logo -->
		<h1 class="logo"><a href="index.html"></a></h1>
		<!-- 搜索框区域 -->
		<div class="search">
			<form class="top">
				<input type="search" placeholder="请输入您感兴趣的商品、品牌" class="search_box">
				<input type="submit" value="搜索" class="btn">
			</form>
			<p class="exm">
				<a href="#">咖啡</a>
				<a href="#" class="iphone">iPhone6s</a>
				<a href="#">新鲜美食</a>
				<a href="#">蛋糕</a>
				<a href="#">日用品</a>
				<a href="#">连衣裙</a>
			</p>
		</div>
		<!-- 购物车 -->
		<div class="cart">
			<span class="name">购物车</span>
			<span class="number">3</span>
		</div>
	</div>
	<!-- header、logo、搜索区域结束 -->
	<!-- 导航部分 -->
	<div class="nav inner_c">
		<!-- 分类部分 -->
		<div class="classify">
			<h2>全部商品分类</h2>
			<ul class="list">
				<li><a href="#">进口食品、生鲜</a></li>
				<li><a href="#">食品、饮料、酒</a></li>
				<li><a href="#">母婴、玩具、童装</a></li>
				<li><a href="#">家居、家庭清洁、纸品</a></li>
				<li><a href="#">美妆、个人护理、洗护</a></li>
				<li><a href="#">女装、内衣、中老年</a></li>
				<li><a href="#">鞋靴、箱包、腕表配饰</a></li>
				<li><a href="#">男装、运动</a></li>
				<li><a href="#">手机、小家电、电脑</a></li>
				<li><a href="#">礼品、充值</a></li>
			</ul>
		</div>
		<!-- 分类部分结束 -->
		<!-- 大分类导航 -->
		<ul class="main_nav">
			<li><a href="#">首页</a></li>
			<li><a href="#">美食</a></li>
			<li><a href="#">生鲜</a></li>
			<li><a href="#">家居</a></li>
			<li><a href="#">女装</a></li>
			<li><a href="#">美妆</a></li>
			<li><a href="#">数码</a></li>
			<li class="well"><span>|</span><a href="#">严选好物</a></li>
			<li class="global"><a href="#">全球选</a></li>
		</ul>
		<!-- 大分类导航结束 -->
    </div>
    <div class="nav_line"></div>
    <!-- 导航部分结束 -->
    <!-- 面包屑导航开始 -->
    <div class="bread_nav inner_c">
        <span>${categoryName}</span>
        <span class="arrow_right"></span>
        <div class="bit_nav">
            <span>${name}</span>
            <a href="javascript:;"></a>
        </div>
    </div>
    <!-- 面包屑导航结束 -->
    <div class="product_cont inner_c">
        <div class="prod_opt">
            <div id="preview" class="prod_check">
				<#assign imageList=picUrl?eval>
                <ul class="prod_show">
                    <span class="jqzoom">
                    	<img src="${imageList?first}" jqimg="${imageList?first}" />
                    </span>
                </ul>
                <ul class="check_show">
					<#list imageList as image>
					    <li>
                            <img src="${image}" bimg="${image}" alt="" onmousemove="preview(this)">
                        </li>
					</#list>
                </ul>
            </div>
            <div class="opt_check">
                <div class="opt_title">
                    <h6>{{sku.label}}</h6>
                    <p>${sellPoint}</p>
                </div>
                <div class="opt_info">
                    <div class="price_info">
						<dl class="price_details">
							<dt>本店价格</dt>
							<dd class="big_font">{{sku.price}}</dd>
							<dt>原价</dt>
							<dd class="details_del">{{sku.price}}</dd>
							<dt>运费</dt>
							<dd>免邮</dd>
							<dt>服务</dt>
							<dd>${sellService}</dd>
						</dl>
					</div>
					<#assign specList=specCheckedList?eval>
					<#list specList as spec>
					    <dl class="model_opt">
                            <dt>${spec.specName}</dt>
                            <dd>
								<#list spec.optionValue as option>
									<a ng-class="{current: isSelected('${spec.specName}', '${option}')}" href="javascript:;" ng-click="selectSpec('${spec.specName}', '${option}')">${option}</a>
								</#list>
                            </dd>
                        </dl>
					</#list>

					<dl class="number_opt">
						<dt>数量</dt>
						<dd class="number">
							<span class="minus" ng-click="setCount(-1)">-</span>
							<input type="number" ng-model="count">
							<span class="plus" ng-click="setCount(1)">+</span>
						</dd>
						<dd class="share">
							<a href="javascirpt:;">分享</a>
						</dd>
						<dd class="collect">
							<a href="javascirpt:;">收藏商品</a>
						</dd>
					</dl>
					<div class="opt_btn">
						<a href="javascript:;">立即购买</a>
						<a class="cart" href="javascript:;" ng-click="addCart()">加入购物车</a>
					</div>
				</div>
            </div>
		</div>
		<!-- 推荐商品 -->
		<div class="cmd_wares">
			<h5 class="cmd_title">推荐搭配</h5>
			<div class="wares">
				<div>
					<img src="images/cmd_01.png" alt="">
					<p>Dior/迪奥 真我香水EDP 克丽丝汀迪奥</p>
					<span>￥589.00</span>
				</div>
				<span class="cmd_add">+</span>
				<div>
					<img src="images/cmd_02.png" alt="">
					<p>Dior 迪奥 迪奥小姐花漾女士淡香水</p>
					<span>￥658.00</span>
				</div>
				<span class="cmd_add">+</span>
				<div>
					<img src="images/cmd_03.png" alt="">
					<p>伊丽莎白雅顿 第五大道女士香水</p>
					<span>￥98.00</span>
				</div>
				<span class="cmd_equal">=</span>
				<div class="cmd_total">
					<span>原价：￥1345.00</span>
					<p>￥1045.00</p>
					<div class="number">
						<span class="minus">-</span>
						<input type="text" value="1">
						<span class="plus">+</span>
					</div>
					<a class="total_btn" href="javascript:;">组合购买</a>
				</div>
			</div>
		</div>
		<!-- 商品详情 -->
		<div class="pdd clearfix">
			<!-- 用户还喜欢 -->
			<div class="user_like">
				<h5>用户还喜欢</h5>
				<ul class="like_res">
					<li>
						<img src="images/like_res_01.png" alt="">
						<span>￥499</span>
						<p>迪奥圣母院系列香氛100ml</p>
					</li>
					<li>
						<img src="images/like_res_02.png" alt="">
						<span>￥499</span>
						<p>迪奥圣母院系列香氛100ml</p>
					</li>
				</ul>
			</div>
			<!-- 商品详情 -->
			<div class="pdd_deta">
				<ul class="deta_nav">
					<li class="current">商品详情</li>
					<li>商品评论</li>
				</ul>
				<ul class="deta_cont">
					<li class="cont_infor">
						<ul class="infor_list">
							<li>
								<span>商品名称：</span>
								<span>迪奥香水</span>
							</li>
							<li>
								<span>商品编号：</span>
								<span>1546211</span>
							</li>
							<li>
								<span>品牌：</span>
								<span>迪奥（Dior）</span>
							</li>
							<li>
								<span>上架时间：</span>
								<span>2015-09-06 09:19:09</span>
							</li>
							<li>
								<span>商品毛重：</span>
								<span>160.00g</span>
							</li>
							<li>
								<span>商品产地：</span>
								<span>法国</span>
							</li>
							<li>
								<span>香调：</span>
								<span>果香</span>
							</li>
							<li>
								<span>香型：</span>
								<span>淡香水/香露EDT</span>
							</li>
							<li>
								<span>容量：</span>
								<span>1ml-5ml</span>
							</li>
							<li>
								<span>类型：</span>
								<span>女士香水，Q版香水，组合套装</span>
							</li>
						</ul>
						<div class="infor_con">
							${detail}
						</div>
					</li>
					<li class="comment">
						<div class="count">
							<div class="count_good">
								<h6>好评度</h6>
								<p>90.0%</p>
								<div class="star">
									<span></span>
									<span></span>
									<span></span>
									<span></span>
									<span></span>
								</div>
							</div>
							<ul class="count_list">
								<li>
									<p>好评（90%）</p>
									<div class="bar">
										<span></span>
									</div>
								</li>
								<li>
									<p>中评（10%）</p>
									<div class="bar">
										<span></span>
									</div>
								</li>
								<li>
									<p>差评（0%）</p>
									<div class="bar">
										<span></span>
									</div>
								</li>
							</ul>
							<ul class="com_classify">
								<li>味道好闻（32）</li>
								<li>味道好闻（32）</li>
								<li>味道好闻（32）</li>
								<li>味道好闻（32）</li>
							</ul>
						</div>
						<ul class="comt_list">
							<li>
								<div class="head_por">
									<img src="images/head_par.png" alt="">
									<p>开心就好</p>
								</div>
								<div class="count_con">
									<div class="star">
										<span></span>
										<span></span>
										<span></span>
										<span></span>
										<span></span>
									</div>
									<p>型号：30ml  颜色：粉色</p>
									<img src="" alt="">
								</div>
								<div class="par_cont">
									<p>味道很好闻，包装也很上档次，送人非常有面子</p>
									<span>2019-02-21 22:32:32</span>
								</div>
							</li>
							<li>
								<div class="head_por">
									<img src="images/head_par.png" alt="">
									<p>开心就好</p>
								</div>
								<div class="count_con">
									<div class="star">
										<span></span>
										<span></span>
										<span></span>
										<span></span>
										<span></span>
									</div>
									<p>型号：30ml  颜色：粉色</p>
									<img src="images/par_01.png" alt="">
								</div>
								<div class="par_cont">
									<p>古驰的香水从来没让我失望过，这款真的很好闻。
										以前用的栀子花的也很棒。</p>
									<span>2019-02-21 22:32:32</span>
								</div>
							</li>
							<li>
								<div class="head_por">
									<img src="images/head_par.png" alt="">
									<p>开心就好</p>
								</div>
								<div class="count_con">
									<div class="star">
										<span></span>
										<span></span>
										<span></span>
										<span></span>
										<span></span>
									</div>
									<p>型号：30ml  颜色：粉色</p>
									<img src="" alt="">
								</div>
								<div class="par_cont">
									<p>味道很好闻，包装也很上档次，送人非常有面子</p>
									<span>2019-02-21 22:32:32</span>
								</div>
							</li>
							<li>
								<div class="head_por">
									<img src="images/head_par.png" alt="">
									<p>开心就好</p>
								</div>
								<div class="count_con">
									<div class="star">
										<span></span>
										<span></span>
										<span></span>
										<span></span>
										<span></span>
									</div>
									<p>型号：30ml  颜色：粉色</p>
									<img src="images/par_01.png" alt="">
								</div>
								<div class="par_cont">
									<p>古驰的香水从来没让我失望过，这款真的很好闻。
										以前用的栀子花的也很棒。</p>
									<span>2019-02-21 22:32:32</span>
								</div>
							</li>
						</ul>
						<div class="page_swit">
							<a href="javascript:;" class="last_page">&lt;上一页</a>
							<a href="javascript:;" class="page_num current">1</a>
							<a href="javascript:;" class="page_num">2</a>
							<a href="javascript:;" class="page_num">3</a>
							<a href="javascript:;" class="page_num">4</a>
							<a href="javascript:;" class="page_num">5</a>
							<span>···</span>
							<a href="javascript:;" class="next_page">下一页&gt;</a>
							<span>共7页</span>
							<span>到第</span>
							<input type="text">
							<span>页</span>
							<button>确定</button>
						</div>
					</li>
				</ul>
			</div>
		</div>
    </div>
	<!-- 底部承诺内容部分 -->
	<div class="promise inner_c">
		<ul>
			<li>
				<h4>正品保障</h4>
				<p>正品行货 放心购买</p>
			</li>
			<li>
				<h4>满38包邮</h4>
				<p>满38包邮 免运费</p>
			</li>
			<li>
				<h4>天天低价</h4>
				<p>天天低价 畅选无忧</p>
			</li>
			<li>
				<h4>准时送达</h4>
				<p>收货时间由你做主</p>
			</li>
		</ul>
	</div>
	<!-- 底部承诺内容部分结束 -->
	<!-- 底部导航部分 -->	
	<div class="bottom_nav">
		<div class="inner_c bottom_nav_c">
			<dl>
				<dt>新手上路</dt>
				<dd>
					<a href="#">售后流程</a>
					<a href="#">购物流程</a>
					<a href="#">订购方式</a>
					<a href="#">隐私声明</a>
					<a href="#">推荐分享说明</a>
				</dd>
			</dl>
			<dl>
				<dt>配送与支付</dt>
				<dd>
					<a href="#">货到付款区域</a>
					<a href="#">配送支付查询</a>
					<a href="#">支付方式说明</a>
				</dd>
			</dl>
			<dl>
				<dt>会员中心</dt>
				<dd>
					<a href="#">资金管理</a>
					<a href="#">我的收藏</a>
					<a href="#">我的订单</a>
				</dd>
			</dl>
			<dl>
				<dt>服务保证</dt>
				<dd>
					<a href="#">退换货原则</a>
					<a href="#">售后服务保证</a>
					<a href="#">产品质量保证</a>
				</dd>
			</dl>
			<dl>
				<dt>联系我们</dt>
				<dd>
					<a href="#">网站故障报告</a>
					<a href="#">购物咨询</a>
					<a href="#">投诉与建议</a>
				</dd>
			</dl>
			<!-- 关于我们 -->
			<div class="aboutus">
				<a href="#" class="sina">新浪微博</a>
				<a href="#" class="qq">腾讯微博</a>
				<p>
					<span>服务热线：</span>
					<span>400-123-5564</span>
				</p>
			</div>
			<div class="focus">
				<img src="images/ewm.png" class="ewm">
				<img src="images/saoyisao.png" class=>
			</div>
		</div>
	</div>
	<!-- 底部导航部分结束 -->
	<!-- 底部其他信息部分 -->
	<div class="footer">
		<div class="inner_c">
			<p>备案/许可证编号：京ICP备12008899号-1-www.jgxy.cc Copyright © 2018-2018 极光严选网 All Rights Reserved. 复制必究 , Technical Support:
			Dgg Group</p>
			<p>
				<img src="images/footer_1.gif"><img src="images/footer_2.gif"><img src="images/footer_3.gif"><img src="images/footer_4.gif"><img src="images/footer_5.gif"><img src="images/footer_6.gif">
			</p>
		</div>
	</div>
	<!-- 底部其他信息部分结束 -->
	<!-- 登录页开始 -->
	<div class="mask log_on">
		<div class="log_box">
			<div class="log_tit">
				<a href="javascript:;">账号登录</a>
				<span>|</span>
				<a href="javascript:;">手机登录</a>
			</div>
			<form action="">
				<input type="text" placeholder="请输入用户名">
				<input type="password" placeholder="请输入密码">
				<div class="assist">
					<span>
						<input type="checkbox">保存登录信息
					</span>
					<a href="#" class="for_pass">忘记密码</a>
				</div>
				<input type="submit" value="登录" class="log_btn">
			</form>
			<span class="ple_reg">没有账号，<a href="#">立即注册</a></span>
			<a href="javascript:;" class="log_close">×</a>
		</div>
	</div>
	<!-- 登录页结束 -->
	<!-- 注册页面开始 -->
	<div class="mask register">
		<div class="reg_box">
			<h4>注册</h4>
			<form action="">
				<input type="text" placeholder="请输入用户名">
				<input type="password" placeholder="请输入密码">
				<input type="password" placeholder="请输入密码">
				<input type="email" placeholder="请输入邮箱">
				<input type="number" placeholder="请输入手机号码">
				<input type="text" placeholder="请输入验证码">
				<div class="assist">
					<input type="checkbox">我已阅读并接受
					<a href="#" class="user_agr">《用户协议》</a>
				</div>
				<input type="submit" value="注册" class="reg_btn">
			</form>
			<span class="ple_reg">已有账号，<a href="#">立即登录</a></span>
			<a href="javascript:;" class="log_close">×</a>
		</div>
	</div>
	<!-- 注册页面结束 -->
</body>
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/plugins/jquery.jqzoom/jquery.jqzoom.js"></script>
<script type="text/javascript" src="js/plugins/jquery.jqzoom/zoom.js"></script>
<script type="text/javascript" src="js/plugins/angular/angular.min.js"></script>
<script type="text/javascript" src="js/custom/app.module.js"></script>
<script type="text/javascript" src="js/custom/goods.detail.controller.js"></script>
<script type="text/javascript" src="js/custom/cart.service.js"></script>
<script type="text/javascript">
	// 存放SKU信息
	// {id:'', spec: {}, price: '', picUrl: [],  label: ''}
	var goodsSkuList = [
	    <#list skuList as sku>
			{
			    id: ${sku.id?c},
				spec: ${sku.specs},
				price: '${sku.price?string.currency}',
				picUrl: ${sku.picUrl},
				label: '${sku.label}'
			}
			<#if sku_has_next>,</#if>
	    </#list>
	];
</script>
</html>