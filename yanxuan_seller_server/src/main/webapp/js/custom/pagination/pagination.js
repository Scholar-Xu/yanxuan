// 创建分页模块
var pagination = angular.module("pagination",[]);
// 创建分页指令
pagination.directive("pagination", function () {
    return {
        restrict:"EA", // 设置识别方式,默认值为EA： E标签，A属性
        templateUrl : "/js/custom/pagination/template.html" , // 模板的路径，或者使用 template设置模板的内容
        replace: false, // 是否替换页面中的内容
        scope: false ,// 作用域，默认值为false；可选值：false(与所在模块共享作用域)，true(继承所在模块的作用域，并创建新的作用域), Object(不使用所在模块的作用域)
        link : function (scope, element, attr) {
            scope.changeFlag = false;
            // 计算页码值
            /**
             * 如果记录数很多，比如1000，每页显示20条记录，那么分页码的值是50，在页面上展示很长，不够美观
             * 解决设置显示页码的个数为10
             *  1. 如果总页码小于10，则全显示
             *  2. 如果总页码大于10，则只显示当前页码最为中间值，显示前后总共10个页码值（采取前4后5的策略），例如：当前页码为8，显示4-13
             *  3. 如果总页码-当前页码 < 5, 则在前面补齐，凑出10个页码值
             */
            scope.getPages = function () {
                // 计算最大页码值 总记录数/每页记录数 向上取整
                scope.pages = Math.ceil(scope.pageOption.total/scope.pageOption.pageSize);
                // 页码数组
                scope.pagesArray = [];

                // 设置页码的起始值和终止值
                var pageBegin = 1;
                var pageEnd = 10;
                if(scope.pages <= 10){
                    pageEnd = scope.pages;
                }else {
                    // 按照前4后5的策略计算
                    pageBegin = scope.pageOption.currentPage - 4;
                    pageEnd = scope.pageOption.currentPage + 5;

                    // 如果开始值<1
                    if(pageBegin <1){
                        pageBegin = 1;
                        pageEnd = pageBegin + 9;
                    }
                    // 如果结束之 > 总页码值
                    if(pageEnd > scope.pages){
                        pageEnd = scope.pages;
                        pageBegin = scope.pages - 9;
                    }
                }

                // 把页码值存入到
                /*for(var pageNum=1; pageNum<=scope.pages; pageNum++){
                    scope.pagesArray.push(pageNum);
                }*/

                for(var pageNum = pageBegin ; pageNum <= pageEnd; pageNum++){
                    scope.pagesArray.push(pageNum)
                }

                // 如果页码开始不是1，则在前面添加 ...
                if(pageBegin>1){
                    scope.pagesArray.unshift("...")
                }
                // 如果结束页码不是总页码数，则在后面添加...
                if(pageEnd<scope.pages){
                    scope.pagesArray.push("...")
                }

                scope.changeFlag = false;
            };

            // 前一页
            scope.prePage = function(){
                if(scope.pageOption.currentPage > 1 ){
                    scope.pageOption.currentPage -= 1;
                    scope.changeFlag = true;
                }
            };

            // 后一页
            scope.nextPage = function(){
                if(scope.pageOption.currentPage < scope.pages){
                    scope.pageOption.currentPage += 1;
                    scope.changeFlag = true;
                }
            };

            // 切换到选中页
            scope.changePage = function (newPageNum){
                // 排除点击"..."
                if(angular.isNumber(newPageNum)){
                    scope.pageOption.currentPage = newPageNum;
                    scope.changeFlag = true;
                }
            };

            // 设置监听器，进行页码值计算
            // 第一个参数：监听的数据
            // 第二个参数：监听的数据发生变化的回调函数
            scope.$watch(
                function () {
                    return scope.pageOption.total +"_"+scope.pageOption.currentPage+"_"+scope.pageOption.pageSize;
                },
                function (newVal, oldVal, scope) {
                    // 新的每页记录数
                    var newPageSize = newVal.toString().split("_")[2];
                    var oldPageSize = oldVal.toString().split("_")[2];
                    // 如果页码/每页显示记录数发生变化，则进行查询
                    if(newPageSize !== oldPageSize|| scope.changeFlag){
                        scope.pageOption.onChange();
                    }
                    scope.getPages();
                })
        }
    }
});