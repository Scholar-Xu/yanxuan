// Step show event
$("#smartwizard").on("showStep", function(e, anchorObject, stepNumber, stepDirection, stepPosition) {
   //alert("You are on step "+stepNumber+" now");
   if(stepPosition === 'first'){
       $("#prev-btn").addClass('disabled');
   }else if(stepPosition === 'final'){
      $("#next-btn").addClass('disabled');
      $("#btnFinish").removeClass('d-none');
   }else{
       $("#prev-btn").removeClass('disabled');
       $("#next-btn").removeClass('disabled');
   }
});

// 初始化结束按钮
var btnFinish = $('<button id="btnFinish" ng-click="save()"></button>').text('保存').addClass('btn btn-info d-none');
// 设置结束按钮的点击事件
/*btnFinish.on('click', function(){

});*/

// 初始化取消按钮
var btnCancel = $('<button></button>').text('取消').addClass('btn btn-danger');
// 设置取消按钮的点击事件
btnCancel.on('click', function(){
  // 重置当前步骤条
  $('#smartwizard').smartWizard("reset"); 
});

// wizard初始化参数
var options = {
  selected: 0,
  showStepURLhash: false,
  anchorSettings: {
    anchorClickable: false
  },
  toolbarSettings: {
    toolbarExtraButtons: [
      btnFinish, 
      btnCancel
    ]
  }
}
// Smart Wizard 1
$('#smartwizard').smartWizard(options);