<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/newStyle/cropper.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/newStyle/selector.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/newStyle/style.css" type="text/css"></link>



<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/newScript/cropper.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/newScript/Selector.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/scripts/plugins/editor/icomoon/style.css" type="text/css"></link>


<style>
ul {
	overflow: hidden;
}

.ui_addUser ul li {
	width: 100%;
}

.ui_addUser ul li label {
	width: 110px;
	position: static;
}

input {
	border: 1px solid #bbb;
}

select {
	width: 247px;
}

.skill-cls input {
	width: 13px;
}
.title-block span:nth-child(1) {
	display: inline-block;
    width: 600px;
    border-radius: 4px;
    background-color: grey;
    color: white;
    text-align: center;
    margin-right: 9px;
	
}
.title-block span:nth-child(2) {
	display: inline-block;
    width: 200px;
    border-radius: 4px;
    background-color: grey;
    color: white;
    text-align: center;
	
}
.form-block input:nth-child(1){
    width: 600px;
}
.form-block input:nth-child(2){
    width: 200px;
}
    .form .item {
            overflow: hidden;
            display: inline-block;
                margin-right: 30px;
        }

        .item label {
            float: left;
            width: auto;
    vertical-align: bottom;
    margin-right: 12px;
        }

        .item input {
            float: right;
            width: auto;
    vertical-align: bottom;
        }
</style>
<script type="text/javascript">
  var videoId='${sc.videoId}';
  console.log(videoId=='');
  if(videoId=='' || videoId==null){
         $("#uploadViodeSpan").show();
	     $("#DelViodeSpan").hide();
  }else{
         $("#uploadViodeSpan").hide();
		$("#DelViodeSpan").show();
  }
</script>
<div class="content">
	<div class="ui_addUser" id="serviceProject">
		<form id="addform" enctype="multipart/form-data" autocomplete="off">
			<input type="hidden" name="id" id="id" value="${sc.id}" />
			<div class="policy_added">
				<ul>
					<li>
						<label>*视频名称：</label>
						<span class="sel_span">
							<input placeholder="请输入视频名称" type="text" name="name" id="name" value="${sc.name}"/>
						</span>
					</li>
						<li>
						<label>*内容简介:</label>
						<span class="sel_span">
							<textarea placeholder="请输入内容简介" name="content" id="content" value="${sc.content}" 
									style="margin-left:0px;height:112px;">${sc.content}</textarea>
						</span>
					</li>
				
					<li>
						<label>*所属类别：</label>
						<span class="sel_span">
							<select style="margin-left:0px;" name="typeDictionaryId" id="typeDictionaryId" value="${typeDictionaryId}" >
								<option value="">请选择</option>
								 <c:forEach items="${list}" var="s">
									<option <c:if test='${s.id == sc.typeDictionaryId}'>selected="selected"</c:if> value="${s.id}">${s.content}</option>
								</c:forEach> 
							</select>
						</span>
					</li>
							<li>
						<label id="picfc">*封面图片:</label>
						<span class="sel_span">
							<input id="change_avatar" type="button" value="上传封面" class="city_airport_button_s01" />
							<input type="hidden" name="covermapId" id="covermapId" value="${sc.covermapId}"/>
							&nbsp;&nbsp;&nbsp;&nbsp;<font id="picts" style="color:red;"></font>
						</span>
					</li>
					<li>
						<label>&nbsp;</label>
						<span id="images4" class="sel_span">
						 <img style="width:120px;height:120px;"
							src="${ctxPath}/uploadFile/pictureView.htm?id=${sc.covermapId}" /> 
						</span>
					</li>
					<li style="border-bottom:none">
						<label>*视频标签：</label>
						<span class="sel_span">
							<input id="change_skills" type="button" value="新增" class="city_airport_button_s01" />
						</span>
					</li>
					<li>
						<label>&nbsp</label>
						<span class="sel_span">
							<span id="skillspan2">
							    <div class="form" id="formcheckboxId">
									   <c:forEach items="${sc.videoRelationDictionaryLs}" var="e" varStatus="ess">
								     
								     <div class="item"  ><label for="man">${e.content} </label><input type="hidden" name="essayRelationDictionaryLs[${ess.index}].dictionaryId"  value='${e.dictionaryId}'>
								       <input type="hidden"  value="${e.content}"/>
								     </div>
								   </c:forEach>
								</div>
							</span>
						</span>
					</li>
					<li>
					
			   	<li style="border-bottom: none;">
						<label>视频文件:</label>
							<span class="sel_span" id="uploadViodeSpan" style="background: #118eea;color: #fff;width: 100px;height: 35px;line-height: 35px;text-align: center;border: 14px solid #118eea;border-radius: 4px; position: relative;border-width: 6px 13px 4px 13px">
								上传视频
								<input id="video_avatar" class="city_airport_button_s01" style="position: absolute; opacity: 0;left: -13px;top: -12px;height:43px" />
							</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<span id="videoName"><span id="videoName${sc.videoId}">${videoP.name}</span></span>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<span class="sel_span" id="DelViodeSpan"   style="background: #118eea;color: #fff;width: 100px;height: 35px;line-height: 35px;text-align: center;border: 14px solid #118eea;border-radius: 4px; position: relative;border-width: 6px 13px 4px 13px;display:none">
							删除
							<input id="video_Del" class="city_airport_button_s01" style="position: absolute; opacity: 0;left: -13px;top: -12px;height:43px" />
						</span>
						<input type="hidden" name="videoId" id="videoId" value="${sc.videoId}"/>
					</li>
					
					<li style="border-bottom:0">
						<div style="text-align:center; margin-top:30px;">
							<input id="addBtn" type="button" value="提交" class="city_airport_button_s01" />
							&nbsp;&nbsp;
							<input id="backBtn" type="button" value="返回" style="background-color:#cccccc;color:#ffffff;" class="city_airport_button_s01" />
							&nbsp;&nbsp;
						</div>
					</li>
				</ul>
			</div>
			<div id="serviceMealDiv" style="display:none">
			  
			</div>
		</form>
	</div>
</div>

<div class="black" style="display:none;" id="alter_video">
	<form id="uploadRefundVideoFrom" method="post" enctype="multipart/form-data" autocomplete="off">
		<div class="edit-avatar">
			<div class="edit-head">
				视频上传
				<div class="close-btn"  id="close_video"></div>
			</div>
			<div class="edit-box">
				   选择视频:<input id="file" type="file" name="file" />
			</div>
			<div class="edit-foot">
				<div id="upload" class="sure-btn">确认</div>
			</div>
		</div>
	</form>
</div>



<div class="black" style="display:none;" id="alter_skills">
	<div class="pop-up">
		<div class="header">
			<span class="tit">标签列表；</span>
			<span id="skill-btnClose" class="cancel iconfont"></span>
		</div>
		<div class="content">
			<div class="skill-tab">
				<ul id="skill-tabName">
					<c:forEach items="${listLabel}" var="its" varStatus="ss">
						<li name="skillDiv"  sc="adfadsf${ss.index }" <c:if test="${its.flag}">class="active"</c:if>  onclick="prochls('${its.id}','${its.content}',this)">
					  	${its.content}
						</li>
						
					</c:forEach>
					<input type="hidden" name="typesName" id="typesName">
				</ul>
			</div>
		</div>
		<div class="footer">
			<div id="skillsure-btn" class="sure-btn">确定</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var data='${data}';
</script>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/unique/videoManager/editVideoManager_jquery.js"></script>





