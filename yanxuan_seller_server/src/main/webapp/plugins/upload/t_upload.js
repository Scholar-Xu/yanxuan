(function($) {
        $.Tupload = {
            fileNum: 0,
            uploadFile: [],
            options: null,
            init: function(defaults) {
                this.options = defaults;
                this.fileNum = defaults.fileNum;
                this.createHtml(defaults);
                this.preViewData = defaults.preViewData;// 要展示的内容
                $(".uploading-img li").mouseenter(function() {
                    $(this).find(".uploading-tip").stop().animate({
                        height: '25px'
                    }, 200);
                });
                $(".uploading-img li").mouseleave(function() {
                    $(this).find(".uploading-tip").stop().animate({
                        height: '0'
                    }, 200);
                });

                this.onSuccess = defaults.onSuccess;
                $.Tupload.priView(this.preViewData);
                this.photoImg();
            },
            destroy:function (options) {
                $("#" + options.divId).html("");
            },
            photoImg: function() {
                var photoImgH = $('.uploading-imgBg').height();
                var ImgH = $('.uploading-imgBg img').height();
                if (ImgH > photoImgH) {
                    $('.uploading-imgBg img').addClass('cur');
                } else {
                    $('.uploading-imgBg img').removeClass('cur');
                }
            },
            createHtml: function(defaults) {
                var fileNum = defaults.fileNum
                    , title = defaults.title
                    , divId = defaults.divId
                    , accept = defaults.accept;
                var html = "";
                if (fileNum < 0 && fileNum == null) {
                    fileNum = 5;
                }
                html += '<div class="uploading-img">';
                html += '<p>' + title == '' ? '宝贝图片大小不能超过500kb,为使避免图片上传出现问题，请尽量选择完毕图片后再上传' : title + '</p>';
                html += '<input type="hidden" id="fileNum" value="0">';
                html += '<ul>';
                for (var i = 0; i < fileNum; i++) {
                    html += '<li>';
                    html += '<div class="uploading-imgBg">';
                    html += '<img id="img_src' + i + '" class="upload_image" src="./images/imgadd.png"/>';
                    html += '</div>';
                    html += '<p id="uploadProgress_' + i + '" class="uploadProgress"></p>';
                    html += '<p id="uploadTure_' + i + '" class="uploadTrue"></p>';
                    html += '<div id="uploading-tip' + i + '" class="uploading-tip" style="display: none">';
                    html += '<span class="onLandR" data="left,' + i + '" ><</span>';
                    html += '<span class="onLandR" data="rigth,' + i + '" >></span>';
                    html += '<i class="onDelPic" data="' + i + '">x</i>';
                    html += '</div></li>';
                }
                html += '</ul>';
                html += '<div class="uploading-imgInput">';
                html += '<input readonly="readonly" id="fileText" type="text" class="imgInput-file"/><span id="uploadFile">上传</span>';
                html += '<div class="andArea">';
                html += '<div class="filePicker">选择</div>';
                html += '<input id="fileImage" class="fileImage" name="file" type="file" multiple accept=' + accept + '/>';
                html += '</div>';
                html += '</div>';
                html += '</div>';
                $("#" + divId).html(html);
            },
            imgLoad: function(i, file, isSrc) {
                if(isSrc){
                    while (true) {
                        if ($("#img_src" + i).attr("src") != "./images/imgadd.png") {
                            i++;
                        } else {
                            break;
                        }
                    }
                    arrFile[i] = file;
                    $("#img_src" + i).attr("src", file.name);
                    $("#uploading-tip" + i).show();
                    return true;
                }
                var r = new FileReader();
                r.readAsDataURL(file);
                $(r).on('load',function() {
                    while (true) {
                        if ($("#img_src" + i).attr("src") != "./images/imgadd.png") {
                            i++;
                        } else {
                            break;
                        }
                    }
                    arrFile[i] = file;
                    $("#img_src" + i).attr("src", this.result);
                    $("#uploading-tip" + i).show();
                    $.Tupload.setPhotoImg();
                });
            },
            setPhotoImg: function() {
                var divH = $('.uploading-imgBg').height();
                var img = $('.uploading-imgBg img');
                for (var i = 0; i < img.length; i++) {
                    var H = $('.uploading-imgBg img').eq(i).height();
                    if (H > divH) {} else {
                        $('.uploading-imgBg img:eq(' + i + ')').css('margin-top', (divH - H) / 2 + "px");
                    }
                }
            },
            onLandR: function(flag, i) {
                i = parseInt(i);
                if (flag == 'left') {
                    if (i != 0) {
                        var temp = $("#img_src" + i).attr("src");
                        $("#img_src" + i).attr("src", $("#img_src" + (i - 1)).attr("src"));
                        $("#img_src" + (i - 1)).attr("src", temp);
                        var temp = $("#img_src" + i).attr("value");
                        $("#img_src" + i).attr("value", $("#img_src" + (i - 1)).attr("value"));
                        $("#img_src" + (i - 1)).attr("value", temp);
                        var tempFile = arrFile[i];
                        arrFile[i] = arrFile[i - 1];
                        arrFile[i - 1] = tempFile;
                        var tp1 = $("#uploadTure_" + i).css("display")
                        var tp2 = $("#uploadTure_" + (i - 1)).css("display")
                        if (tp1 == 'none') {
                            $("#uploadTure_" + (i - 1)).hide();
                        } else {
                            $("#uploadTure_" + (i - 1)).show();
                        }
                        if (tp2 == 'none' || tp2 == undefined) {
                            $("#uploadTure_" + i).hide();
                        } else {
                            $("#uploadTure_" + i).show();
                        }
                        var tip1 = $("#uploading-tip" + i).css("display");
                        var tip2 = $("#uploading-tip" + (i - 1)).css("display");
                        if (tip1 == 'none') {
                            $("#uploading-tip" + (i - 1)).hide();
                        } else {
                            $("#uploading-tip" + (i - 1)).show();
                        }
                        if (tip2 == 'none' || tip2 == undefined) {
                            $("#uploading-tip" + i).hide();
                        } else {
                            $("#uploading-tip" + i).show();
                        }
                    }
                } else {
                    if (i != ($.Tupload.fileNum - 1)) {
                        var temp = $("#img_src" + i).attr("src");
                        $("#img_src" + i).attr("src", $("#img_src" + (i + 1)).attr("src"));
                        $("#img_src" + (i + 1)).attr("src", temp);
                        var temp1 = $("#img_src" + i).attr("value");
                        $("#img_src" + i).attr("value", $("#img_src" + (i + 1)).attr("value"));
                        $("#img_src" + (i + 1)).attr("value", temp1);
                        var tempFile = arrFile[i];
                        arrFile[i] = arrFile[i + 1];
                        arrFile[i + 1] = tempFile;
                        var tp1 = $("#uploadTure_" + i).css("display");
                        var tp2 = $("#uploadTure_" + (i + 1)).css("display");
                        if (tp1 == 'none') {
                            $("#uploadTure_" + (i + 1)).hide();
                        } else {
                            $("#uploadTure_" + (i + 1)).show();
                        }
                        if (tp2 == 'none' || tp2 == undefined) {
                            $("#uploadTure_" + i).hide();
                        } else {
                            $("#uploadTure_" + i).show();
                        }
                        var tip1 = $("#uploading-tip" + i).css("display");
                        var tip2 = $("#uploading-tip" + (i + 1)).css("display");
                        if (tip1 == 'none') {
                            $("#uploading-tip" + (i + 1)).hide();
                        } else {
                            $("#uploading-tip" + (i + 1)).show();
                        }
                        if (tip2 == 'none' || tip2 == undefined) {
                            $("#uploading-tip" + i).hide();
                        } else {
                            $("#uploading-tip" + i).show();
                        }
                    }
                }
                $.Tupload.setPhotoImg();
            },
            onDelete: function(i) {
                $.Tupload.options.onDelete(i);
                $("#uploadTure_" + i).hide();
                $("#img_src" + i).attr("value", "");
                $("#img_src" + i).attr('name', "");
                var num = parseInt($("#fileNum").val()) - 1;
                $("#fileNum").val(num);
                $("#fileText").val("选中" + num + "个文件");
                arrFile[i] = '';
                $("#img_src" + i).attr("src", "./images/imgadd.png");
                $("#uploading-tip" + i).hide();
                $.Tupload.setPhotoImg();
            },
            priView : function (preViewData) {
                if(preViewData===undefined){
                    return ;
                }
                if(preViewData.length>0){
                    for(var i=0 ; i<preViewData.length; i++){
                        var imagePath = preViewData[i];
                        var imageFile = new File(["string"],imagePath);
                        $.Tupload.imgLoad(i, imageFile, true);
                    }
                    $("#fileNum").val(preViewData.length);
                    $("#fileText").val("选中" + preViewData.length + "张图片");
                }

            }
        }
    }
)(jQuery);
var arrFile = [];
$(document).on('change','#fileImage', function() {
    var element = $("#fileImage")[0];
    console.log(element.files)
    var num = parseInt($("#fileNum").val()) + parseInt(element.files.length);
    if (num < $.Tupload.fileNum + 1) {
        $("#fileNum").val(num);
        $("#fileText").val("选中" + num + "张图片");
        for (var i = 0; i < num; i++) {
            for (var j = 0; j < num; j++) {
                if ($("#img_src" + j).attr("src") == "./images/imgadd.png") {
                    if (element.files.length - 1 < i) {
                        break;
                    } else {
                        $.Tupload.imgLoad(i, element.files[i]);
                        break;
                    }
                }
            }
        }
    } else {
        alert("只能上传" + $.Tupload.fileNum + "张");
    }
});
$(document).on('click','.onLandR', function() {
    var data = $(this).attr("data").split(",");
    $.Tupload.onLandR(data[0], data[1]);
});
$(document).on('click',".onDelPic", function() {
    $.Tupload.onDelete($(this).attr("data"));
});
$(document).on('click','#uploadFile',function () {
    if($("#fileImage")[0].files.length <=0){
        return false;
    }
    $.ajaxFileUpload({
        fileElementId: 'fileImage',    //需要上传的文件域的ID，即<input type="file">的ID。
        data:arrFile,
        url: $.Tupload.options.url, //后台方法的路径
        type: 'post',   //当要提交自定义参数时，这个参数要设置成post
        dataType: 'json',   //服务器返回的数据类型。可以为xml,script,json,html。如果不填写，jQuery会自动判断。
        secureuri: false,   //是否启用安全提交，默认为false。
        async : true,   //是否是异步
        success: function(data) {   //提交成功后自动执行的处理函数，参数data就是服务器返回的数据。
            $.Tupload.onSuccess(data);
        },
        error: function(data, status, e) {  //提交失败自动执行的处理函数。
            console.error(e);
        }
    });
});
