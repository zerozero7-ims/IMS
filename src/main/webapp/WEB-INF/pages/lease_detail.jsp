<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>孵化器管理系统 - 租赁详情</title>

    <!-- Bootstrap Core CSS -->
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="assets/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- DataTables CSS -->
    <link href="assets/vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">
    <link href="assets/vendor/datatables-plugins/buttons.dataTables.min.css" rel="stylesheet">
    <link href="assets/vendor/datatables-plugins/editor.dataTables.min.css" rel="stylesheet">
    <link href="assets/vendor/datatables-plugins/select.dataTables.min.css" rel="stylesheet">



    <!-- Custom CSS -->
    <link href="assets/dist/css/sb-admin-2.css" rel="stylesheet">
    <link href="assets/dist/css/ims.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="assets/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div id="wrapper">

        <jsp:include page="/inc/nav" flush="true"/>
        <div id="page-wrapper" style="height:100%">
            <div class="row">
                <div class="col-lg-12">
                    <h3 class="page-header">租赁详情</h3>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default panel-ims">
                        <!-- /.panel-heading -->
                        <div class="panel-body panel-body-ims">
                            <div style="height:30px;">
                                <div class="legend-col">
                                    <div class="legend-col-left" style="border:7px solid #d9534f;"></div>
                                    <p>1个月内到期</p>
                                </div>
                                <div class="legend-col">
                                    <div class="legend-col-left" style="border:7px solid #f0ad4e;"></div>
                                    <p>1年内到期</p>
                                </div>
                                <div class="legend-col">
                                    <div class="legend-col-left" style="border:7px solid #5cb85c;"></div>
                                    <p>2年内到期</p>
                                </div>
                                <div class="legend-col">
                                    <div class="legend-col-left" style="border:7px solid #5bc0de;"></div>
                                    <p>3年内到期</p>
                                </div>
                                <div class="legend-col">
                                    <div class="legend-col-left" style="border:7px solid #7a92a3;"></div>
                                    <p>3年后到期</p>
                                </div>
                                <div class="legend-col">
                                    <div class="legend-col-left" style="border:7px solid #337ab7;"></div>
                                    <p>空置房间</p>
                                </div>
                                <div class="legend-col">
                                    <div class="legend-col-left" style="border:7px solid #999; box-shadow: 0px 0px 2px #000 inset;"></div>
                                    <p>园区自用</p>
                                </div>
                                <div class="legend-col">
                                    <div class="legend-col-left" style="width:14px; height:14px;border:4px double #ddd;"></div>
                                    <p>有偿配套</p>
                                </div>

                            </div>

                            <jsp:useBean id="now" class="java.util.Date"/>
                            <c:forEach items="${floorlist}" var="floor" varStatus="status">
                                <div class="floor">
                                    <div class="floor-num">
                                        <h4>${floor.floor}<sub>F</sub></h4>
                                        <label>${floor.totalarea}m&sup2; </label>
                                    </div>
                                    <div class="floor-room">
                                        <c:forEach items="${floor.combuildlist}" var="combuild" varStatus="status">
                                            <div class="floor-col" style="width:${100*combuild.building.area/floor.totalarea}%; background-color:

                                                <c:if test='${combuild.building.state==1}'>
                                                <fmt:parseDate var="term" value="${combuild.building.term}"/>
                                                    <c:set var="interval" value="${term.time - now.time}"/>
                                                    <fmt:formatNumber var="days" value="${interval/1000/60/60/24}" pattern="#0"/>
                                                    <c:if test='${days<30}'>#d9534f;</c:if>
                                                    <c:if test='${days>30 && days <= 365}'>#f0ad4e;</c:if>
                                                    <c:if test='${days>365 && days <= 730}'>#5cb85c;</c:if>
                                                    <c:if test='${days>730 && days <= 1095}'>#5bc0de;</c:if>
                                                    <c:if test='${days>1095}'>#7a92a3;</c:if>
                                                    <c:if test='${combuild.building.buildingtype==2}'>
                                                        border:3px double #ddd;
                                                    </c:if>
                                                </c:if>
                                                <c:if test='${combuild.building.state==0}'>
                                                    <c:if test='${combuild.building.buildingtype==0}'>
                                                         #337ab7;
                                                    </c:if>
                                                    <c:if test='${combuild.building.buildingtype==1}'>
                                                        #999; box-shadow: 0px 0px 8px #000 inset;
                                                    </c:if>
                                                    <c:if test='${combuild.building.buildingtype==2}'>
                                                        #337ab7; border:3px double #ddd;
                                                    </c:if>

                                                </c:if>" data-toggle="tooltip" title="<c:if test='${combuild.building.state==1}'>承租者：${combuild.company.companyname}&#10;房间号：${combuild.building.roomnum}室<c:if test='${combuild.building.buildingtype==2}'>【有偿配套<c:if test='${combuild.building.usetype==0}'>（公寓）</c:if><c:if test='${combuild.building.usetype==1}'>（会议室）</c:if><c:if test='${combuild.building.usetype==2}'>（储藏室）</c:if>】</c:if>&#10;面积：${combuild.building.area}m&sup2;&#10;期限：${combuild.building.term}</c:if><c:if test='${combuild.building.state==0}'>房间号：${combuild.building.roomnum}&#10;面积：${combuild.building.area}m&sup2;&#10;<c:if test='${combuild.building.buildingtype!=1}'>预租单价：&yen;${combuild.building.unitprice}元/m&sup2;&bull;天&#10;</c:if>楼宇类型：<c:if test='${combuild.building.buildingtype==0}'>对外租赁资源</c:if><c:if test='${combuild.building.buildingtype==1}'>园区自用&#10;用途：${combuild.building.purpose}</c:if><c:if test='${combuild.building.buildingtype==2}'>有偿配套<c:if test='${combuild.building.usetype==0}'>【公寓】</c:if><c:if test='${combuild.building.usetype==1}'>【会议室】</c:if><c:if test='${combuild.building.usetype==2}'>【储藏室】</c:if></c:if></c:if>">


                                                <label>
                                                    <c:if test='${combuild.building.state==1}'>${combuild.company.companyname}</c:if>
                                                    <c:if test='${combuild.building.state==0}'>${combuild.building.roomnum}</c:if>

                                                </label>
                                                <p>
                                                    <c:if test='${combuild.building.state==1}'>
                                                        ${combuild.building.area}m&sup2;/${combuild.building.roomnum}室<br />${combuild.building.term}到期
                                                    </c:if>
                                                    <c:if test='${combuild.building.state==0}'>

                                                        <c:if test='${combuild.building.buildingtype==1}' var='flag'>
                                                            ${combuild.building.area}m&sup2;<br />${combuild.building.purpose}
                                                        </c:if>
                                                        <c:if test='${!flag}'>
                                                            ${combuild.building.area}m&sup2;<br />&yen;${combuild.building.unitprice}元/m&sup2;&bull;天
                                                        </c:if>

                                                    </c:if>
                                                </p>
                                            </div>

                                        </c:forEach>

                                    </div>
                                </div>


                            </c:forEach>





                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>

        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="assets/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="assets/vendor/metisMenu/metisMenu.min.js"></script>

    <script src="assets/vendor/date/moment.min.js"></script>

    <!-- DataTables JavaScript -->
    <script src="assets/vendor/datatables/js/jquery.dataTables.min.js"></script>
    <script src="assets/vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
    <script src="assets/vendor/datatables-plugins/dataTables.buttons.min.js"></script>
    <script src="assets/vendor/datatables-plugins/dataTables.editor.min.js"></script>
    <script src="assets/vendor/datatables-plugins/dataTables.select.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="assets/dist/js/sb-admin-2.js"></script>

    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script type="text/javascript">


    </script>


</body>

</html>
