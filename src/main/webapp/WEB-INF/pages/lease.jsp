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

    <title>孵化器管理系统 - 对外租赁资源管理</title>

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
                    <h3 class="page-header">对外租赁资源管理</h3>
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
                                        <th>房间号</th>
                                        <th>面积</th>
                                        <th>单价</th>
                                        <th>承租状态</th>
                                        <th>承租企业名称</th>
                                        <th>期限</th>
                                        <th>操作</th>
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
                url:"leaseaction",
                data:function(data){
                    var result = {};
                    result.action=data.action;
                    var leases = [];
                    for(var i in data.data){
                        var lease = data.data[i].lease;
                        lease.id=i;
                        leases.push(JSON.stringify(lease));
                    }
                    result.leases = "["+leases.toString()+"]";
                    return result;
                }

            },
            table: "#dataTables-example",
            idSrc:'lease.id',
            fields: [ {
                label: "房间号:",
                name: "lease.roomnum"
            }, {
                label: "面积:",
                name: "lease.area"
            }, {
                label: "单价:",
                name: "lease.unitprice",
            }, {
                label: "期限:",
                name: "lease.term",
                type:"datetime",
                format:"YYYY-MM-DD"

            }, {
                label: "承租状态:",
                name: "lease.state",
                type:"select",
                options:[
                    {label:"未出租",value:"0"},
                    {label:"已出租",value:"1"}
                ]
            }, {
                label: "楼层:",
                name: "lease.floor",
                type:"select",
                options:[
                    {label:"1楼",value:"1"},{label:"2楼",value:"2"}, {label:"3楼",value:"3"},{label:"4楼",value:"4"},
                    {label:"5楼",value:"5"},{label:"6楼",value:"6"}, {label:"7楼",value:"7"},{label:"8楼",value:"8"}
                ]
            }]
        } );

        // $('#dataTables-example').on( 'click', 'tbody td:not(:first-child)', function (e) {
        //     editor.inline( this );
        // } );

        var table =$('#dataTables-example').DataTable({
            dom: 'Bfrtilp',
            ajax:"leaselist",
            order: [[ 1, 'asc' ]],
            columns: [
                {
                    data: null,
                    defaultContent: '',
                    className: 'select-checkbox',
                    orderable: false
                },
                { data: "lease.roomnum" },
                { data: "lease.area" },
                { data: "lease.unitprice" },
                {
                    data: "lease.state",
                    render:function (data,type,row) {
                        if(row.lease.state==0){
                            return '<span class = "label label-warning">未出租</span>'
                        }else if(row.lease.state==1){
                            return '<span class = "label label-info">已出租</span>'
                        }
                    }
                },
                { data:function(row,type,set){
                    if(row.company == null){
                        return "";
                    }else{
                        return row.company.companyname;
                    }
                    }},
                { data: "lease.term" },
                {
                    data: null,
                    className:"center",
                    orderable:false,
                    defaultContent:'<a href="" class="editor_edit"><i class="fa fa-edit"></i></a> / <a href="" class="editor_remove"><i class="fa fa-trash-o"></i></a>'
                }
            ],

            select: {
                style:    'os',
                selector: 'td:first-child'
            },
            buttons: [
                { extend: "create", editor: editor ,text: '<i class="fa fa-plus">&nbsp;&nbsp;添加租赁资源</i>'},
                { extend: "edit",   editor: editor ,text: '<i class="fa fa-edit">&nbsp;&nbsp;修改租赁资源</i>'},
                { extend: "remove", editor: editor ,text: '<i class="fa fa-trash-o">&nbsp;&nbsp;删除租赁资源</i>'},
                { extend: "excel", text: '<i class="fa fa-level-up">&nbsp;&nbsp;导出列表</i>',
                    exportOptions:{
                        columns:[1,2,3]
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
        //表单验证
        editor.on("preSubmit",function(e,o,action){
            if(action !== 'remove'){
                var roomnum = this.field('lease.roomnum');
                if ( ! roomnum.isMultiValue() ) {
                    if ( ! roomnum.val() ) {
                        roomnum.error( '房间号必填' );
                    }

                    if ( roomnum.val().length >= 20 ) {
                        roomnum.error( '房间号填写有误' );
                    }
                }
                if ( this.inError() ) {
                    return false;
                }
            }
        })


    });

    </script>

</body>

</html>
