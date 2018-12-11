<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

    <title>孵化器管理系统 - 基地企业管理</title>

    <!-- Bootstrap Core CSS -->
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="assets/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- DataTables CSS -->
    <link href="assets/vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">
    <link href="assets/vendor/datatables-plugins/buttons.dataTables.min.css" rel="stylesheet">
    <link href="assets/vendor/datatables-plugins/editor.dataTables.min.css" rel="stylesheet">
    <link href="assets/vendor/datatables-plugins/select.dataTables.min.css" rel="stylesheet">


    <!-- DataTables Responsive CSS -->
    <link href="assets/vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="assets/dist/css/sb-admin-2.css" rel="stylesheet">
    <link href="assets/dist/css/ims.css" rel="stylesheet">
    <link href="assets/vendor/date/bootstrap-datepicker.min.css" rel="stylesheet">

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
                    <h3 class="page-header">基地企业管理</h3>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default panel-ims">
                        <!-- /.panel-heading -->
                        <div class="panel-body panel-body-ims">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th></th>
                                        <th>企业名称</th>
                                        <th>统一社会信用代码</th>
                                        <th>项目名称</th>
                                        <th>企业法人</th>
                                        <th>联系人</th>
                                        <th>联系电话</th>
                                        <th>注册时间</th>
                                        <th>注册地址</th>
                                        <th>注册资金</th>
                                        <th>国税纳税地点</th>
                                        <th>地税纳税地点</th>
                                        <th>办公人数</th>
                                        <th>电子邮箱</th>
                                        <th>是否毕业</th>
                                        <th>是否人才企业</th>
                                        <th>是否纳入软件统计平台</th>
                                        <th>本年度营收</th>
                                        <th>上年度营收</th>
                                        <th>本年度税收</th>
                                        <th>上年度税收</th>
                                        <th>入驻时间</th>
                                        <th>孵化时限</th>
                                        <th>退出时间</th>
                                        <th>承租面积</th>
                                        <th>租金单价</th>
                                        <th>房间号</th>
                                        <th>缴费方式</th>
                                        <th>租金金额</th>
                                        <th>缴纳租金情况</th>
                                        <th>企业资质申请情况</th>
                                        <th>企业知识产权申请情况</th>
                                        <th>补充资料</th>
                                    </tr>
                                </thead>

                            </table>

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
    <script src="assets/vendor/date/bootstrap-datepicker.min.js"></script>
    <script src="assets/vendor/date/bootstrap-datepicker.zh-CN.min.js"></script>
    <!-- DataTables JavaScript -->
    <script src="assets/vendor/datatables/js/jquery.dataTables.min.js"></script>
    <script src="assets/vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
    <script src="assets/vendor/datatables-plugins/dataTables.buttons.min.js"></script>
    <script src="assets/vendor/datatables-plugins/dataTables.editor.min.js"></script>
    <script src="assets/vendor/datatables-plugins/dataTables.select.min.js"></script>
    <script src="assets/vendor/datatables-plugins/jszip.min.js"></script>
    <script src="assets/vendor/datatables-plugins/buttons.html5.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="assets/dist/js/sb-admin-2.js"></script>
    <script src="assets/dist/js/ims.js"></script>

    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
    $(document).ready(function() {


        var editor = new $.fn.dataTable.Editor( {

            i18n:{
                edit:{
                    title:"修改记录",
                    submit:"修改"
                },
                remove:{
                    title:"删除企业",
                    submit:"删除"
                },
                datetime: {
                    months:   [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月' ],
                    weekdays: [  '日','一', '二', '三', '四', '五', '六' ]
                }
            },
            ajax: {
                url:"companyaction",
                data:function(data){
                    var result = {};
                    result.action=data.action;
                    var companys = [];
                    for(var i in data.data){
                        var company = data.data[i];
                        company.id=i;
                        companys.push(JSON.stringify(company));
                    }
                    result.companys = "["+companys.toString()+"]";
                    return result;
                }

            },
            table: "#dataTables-example",
            idSrc:'id',
            fields: [{label: "企业名称:", name: "companyname"},
                {label: "统一社会信用代码:", name: "orgcode"},
                {label: "项目名称:", name: "projectname"},
                {label: "企业法人:", name: "legalpersonname"},
                {label: "联系人:", name: "contactname"},
                {label: "联系电话:", name: "contactphone"},
                {label: "电子邮箱:", name: "email"},
                {label: "办公人数:", name: "officenum"},
                {label: "注册时间:", name: "regdate",type: "datetime"},
                {label: "注册地址:", name: "regaddr"},
                {label: "注册资金:", name: "regcapital"},
                {label: "本年度营收:", name: "currevenue"},
                {label: "上年度营收:", name: "lastrevenue"},
                {label: "本年度税收:", name: "curtax"},
                {label: "上年度税收:", name: "lasttax"},
                {label: "国税纳税地点:", name: "nationaltax"},
                {label: "地税纳税地点:", name: "localtax"},
                {label: "入驻时间:", name: "admissiontime",def:function(){return new Date();},type: "datetime"},
                {label: "孵化时限:", name: "hatchtime",type: "daterange"},
                {label: "退出时间:", name: "exittime",def:function(){return new Date();},type: "datetime"},
                {label: "承租面积:", name: "rentarea"},
                {label: "租金单价:", name: "unitprice"},
                {label: "房间号:", name: "roomnum"},
                {label: "租金金额:", name: "totalrent"},
                {label: "缴费方式:", name: "paytype",type:"radio",
                    options:[
                        {label:"半年付",value:"0"},
                        {label:"季付",value:"1"}
                    ],
                    def:0
                },
                {label: "是否毕业:", name: "isgraduation",type:"radio",
                    options:[
                        {label:"否",value:"0"},
                        {label:"是",value:"1"}
                    ],
                    def:0
                },
                {label: "是否人才企业:", name: "istalent",type:"radio",
                    options:[
                        {label:"否",value:"0"},
                        {label:"是",value:"1"}
                    ],
                    def:0
                },
                {label: "是否纳入软件统计平台:", name: "iscount",type:"radio",
                    options:[
                        {label:"否",value:"0"},
                        {label:"是",value:"1"}
                    ],
                    def:1
                },
                {label: "缴纳租金情况:", name: "paystatus",type:"paylist"},
                {label: "企业资质申请情况:", name: "qualifications",type:"textarea"},
                {label: "企业知识产权申请情况:", name: "intellectual",type:"textarea"},
                {
                    label: "补充资料:",
                    name: "additional[].id",
                    type:"uploadMany",
                    display: function ( fileId, counter ) {
                        // return '<img src="'+editor.file( 'files', fileId ).web_path+'"/>';
                        return '<a href="'+editor.file( 'files', fileId ).web_path+'" target="_blank">'+editor.file( 'files', fileId ).filename+'</a>';
                    },
                    noFileText: 'No files'
                }]
        } );

        // $('#dataTables-example').on( 'click', 'tbody td:not(:first-child)', function (e) {
        //     editor.inline( this );
        // } );

        var table =$('#dataTables-example').DataTable({
            dom: 'Bfrtilp',
            ajax:"companylist",
            order: [[ 1, 'asc' ]],
            columns: [
                {
                    data: null,
                    defaultContent: '',
                    className: 'select-checkbox',
                    orderable: false
                },
                { data: "companyname" },
                { data: "orgcode" },
                { data: "projectname"  , "visible":false},
                { data: "legalpersonname"  , "visible":false},
                { data: "contactname" },
                { data: "contactphone" },
                { data: "regdate" , "visible":false},
                { data: "regaddr" , "visible":false},
                { data: "regcapital" , "visible":false},
                { data: "nationaltax" , "visible":false},
                { data: "localtax" , "visible":false},
                { data: "officenum" , "visible":false},
                { data: "email" , "visible":false},
                { data: "isgraduation" , "visible":false},
                { data: "istalent" , "visible":false},
                { data: "iscount" , "visible":false},
                { data: "currevenue" , "visible":false},
                { data: "lastrevenue" , "visible":false},
                { data: "curtax" , "visible":false},
                { data: "lasttax" , "visible":false},
                { data: "admissiontime"},
                { data: "hatchtime" , "visible":false},
                { data: "exittime", "visible":false },
                { data: "rentarea", "visible":false },
                { data: "unitprice", "visible":false },
                { data: "roomnum" },
                { data: "paytype", "visible":false },
                { data: "totalrent" , "visible":false},
                { data: "paystatus", "visible":false },
                { data: "qualifications" , "visible":false},
                { data: "intellectual" , "visible":false},
                { data: "additional" , "visible":false}
            ],

            select: {
                style:    'os',
                selector: 'td:first-child'
            },
            buttons: [
                { extend: "create", editor: editor ,text: '<i class="fa fa-plus">&nbsp;&nbsp;添加企业</i>'},
                { extend: "edit",   editor: editor ,text: '<i class="fa fa-edit">&nbsp;&nbsp;修改企业</i>'},
                { extend: "remove", editor: editor ,text: '<i class="fa fa-trash-o">&nbsp;&nbsp;删除企业</i>'},
                { extend: "excel", text: '<i class="fa fa-level-up">&nbsp;&nbsp;导出列表</i>',
                    exportOptions:{
                        columns:[1,2,3,4,5,6,8]
                    }

                }
            ],

        });

        // Edit record
        $('#dataTables-example').on('click', 'a.editor_edit', function (e) {
            e.preventDefault();
            editor.edit( $(this).closest('tr'), {
                title: 'Edit entry',
                buttons: '更新'
            } );
        } );

        // Delete a record
        $('#dataTables-example').on('click', 'a.editor_remove', function (e) {
            e.preventDefault();
            editor.remove( $(this).closest('tr'), {
                title: 'Delete',
                message: 'Are you sure you wish to delete 1 row?',
                buttons: 'Delete'
            } );
        } );

        // Delete a record
        $('#dataTables-example').on('click', 'a.editor_show', function (e) {
            // e.preventDefault();
            // editor.show( $(this).closest('tr'), {
            //     // title: 'Delete',
            //     // buttons: 'Delete'
            // } );
            editor.modal("show");
        } );




    });

    </script>

</body>

</html>
