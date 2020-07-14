$(function () {

    $("#orderId").validatebox({
        required : true,
        missingMessage : '不超过50个字',
        validType : "length[1,50]",
    })

    //上传图片
    var file_input = document.getElementById('upload_file_input');
    file_input.addEventListener('change', function() {
        if (!this['value'].match(/.jpg|.png|.bmp|.gif/i)) {
            alert('仅支持BMP、JPEG、JPG、PNG图片格式文件！');
            return;
        }
        var fileName = file_input.files[0].name;
        var fileType = file_input.files[0].name.substring(file_input.files[0].name.lastIndexOf("."),file_input.files[0].name.length);
        $("#fileName").val(fileName);
        $("#fileType").val(fileType);
        var reader = new FileReader();
        reader.readAsDataURL(this.files[0]);
        reader.onload = function(e) {
            var html = '<img style="width:120px;height:120px;" src="' + this.result + '">';
            $("#showImg").empty().append(html);
            $("#image").val(this.result);
        }
    })

// 保存
    $("#addBtn").click( function() {
        if ($('#addform').form('enableValidation').form('validate') == false) {
            return false;
        }
        $("#addform").form("submit", {
            url : ctxPath +"/operate/editPayment.htm",
            success : function(res) {
                var data = jQuery.parseJSON(res);
                if(data.success==="1"){
                    $("#backBtn").click();
                    alert("保存成功");
                }else{
                    alert("保存失败");
                }
            },
            error : function() {
                alert("请求失败！");
            }
        });


    })

    //返回
    $("#backBtn").click(function() {
        visitUrl(ctxPath + "/operate/questionnairePage.htm", 1, 1, 1);
    });

    $('#showImg').on('click', 'img', function showItem(e) {
        var _this = $(this);//将当前的pimg元素作为_this传入函数
        imgShow("#ViewQi", "#showdiv", "#imgsrc", _this);
    });

})

function imgShow(ViewQi, showdiv, imgsrc, _this) {
    var src = _this.attr("src");//获取当前点击的pimg元素中的src属性
    $(imgsrc).attr("src", src);//设置#imgsrc元素的src属性
    /*获取当前点击图片的真实大小，并显示弹出层及大图*/
    $("<img/>").attr("src", src).load(function () {
        var windowW = $(window).width();//获取当前窗口宽度
        var windowH = $(window).height();//获取当前窗口高度
        var realWidth = this.width;//获取图片真实宽度
        var realHeight = this.height;//获取图片真实高度
        var imgWidth, imgHeight;
        var scale = 0.5;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放
        if (realHeight > windowH * scale) {//判断图片高度

            imgHeight = windowH * scale;//如大于窗口高度，图片高度进行缩放
            imgWidth = imgHeight / realHeight * realWidth;//等比例缩放宽度
            if (imgWidth > windowW * scale) {//如宽度扔大于窗口宽度
                imgWidth = windowW * scale;//再对宽度进行缩放
            }
        } else if (realWidth > windowW * scale) {//如图片高度合适，判断图片宽度
            imgWidth = windowW * scale;//如大于窗口宽度，图片宽度进行缩放
            imgHeight = imgWidth / realWidth * realHeight;//等比例缩放高度
        } else {//如果图片真实高度和宽度都符合要求，高宽不变
            imgWidth = realWidth * 3;//如果原图较小 可以调整
            imgHeight = realHeight * 3;//如果原图较小 可以调整
        }

        $(imgsrc).css("width", imgWidth);//以最终的宽度对图片缩放
        var leftW = $("#content_left").width();
        var w = (windowW - imgWidth-leftW - 300) / 2;//计算图片与窗口左边距
        var h = (windowH - imgHeight) / 2;//计算图片与窗口上边距
        $(showdiv).css({ "top": h, "left": w });//设置#showdiv的top和left属性
        $(ViewQi).fadeIn("fast");//淡入显示#ViewQi及.pimg
    });
    $(ViewQi).click(function () {
        //再次点击淡出消失弹出层
        $(this).fadeOut("fast");
    });
}

