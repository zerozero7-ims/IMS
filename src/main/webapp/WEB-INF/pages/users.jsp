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

    <title>孵化器管理系统 - 用户管理</title>

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
                    <h3 class="page-header">用户管理</h3>
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
                                        <th>用户名</th>
                                        <th>密码</th>
                                        <th>用户类型</th>
                                        <th>账号状态</th>
                                        <th width="18%">登录时间</th>
                                        <th>注册时间</th>
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
                    title:"删除用户",
                    submit:"删除"
                },
                datetime: {
                    months:   [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月' ],
                    weekdays: [  '日','一', '二', '三', '四', '五', '六' ]
                }
            },
            // ajax: {
            //     create:{
            //       type:'POST',
            //       url:'/updateuser',
            //       data:function(data){
            //           var result={};
            //           for(var i in data.data){
            //               var result = data.data[i];
            //               result.DT_RowId=i;
            //               result.action=data.action;
            //               console.log(result.toString());
            //           }
            //           return result;
            //       },
            //     },
            //     edit:{
            //         type:'POST',
            //         url:'updateuser',
            //         contentType: "application/json;charset=UTF-8",
            //         data:function(data){
            //             var result={};
            //             for(var i in data.data){
            //                 var result = data.data[i];
            //                 result.DT_RowId=i;
            //                 result.action=data.action;
            //                 result.id=i;
            //                 console.log(result.toString());
            //             }
            //             return JSON.stringify(result);
            //         },
            //     },
            //     remove:{
            //         url:'/updateuser',
            //         data:function(data){
            //             var result={};
            //             for(var i in data.data){
            //                 var result = data.data[i];
            //                 result.id=i;
            //                 result.action=data.action;
            //                 console.log(result.toString());
            //             }
            //             return result;
            //         },
            //     },
            // },
            ajax: {
                url:"useraction",
                data:function(data){
                    var result = {};
                    result.action=data.action;
                    var users = [];
                    for(var i in data.data){
                        var user = data.data[i];
                        user.id=i;
                        users.push(JSON.stringify(user));
                    }
                    result.users = "["+users.toString()+"]";
                    return result;
                }

            },
            table: "#dataTables-example",
            idSrc:'id',
            fields: [ {
                label: "用户名:",
                name: "username"
            }, {
                label: "密码:",
                name: "password"
            }, {
                label: "用户类型:",
                name: "usertype",
                type:"select",
                options:[
                    {label:"员工【园区部】",value:"0"},
                    {label:"员工【招商部】",value:"1"},
                    {label:"孵化器【综合管理部】",value:"2"},
                    {label:"管理员【系统维护者】",value:"3"}
                ]
            }, {
                label: "账号状态:",
                name: "userstatus",
                type:"select",
                options:[
                    {label:"正常",value:"1"},
                    {label:"注销",value:"0"}
                ]
            }, {
                label: "备注:",
                name: "remark",
                type:"textarea"
            }
            ]
        } );
        // $('#dataTables-example').on( 'click', 'tbody td:not(:first-child)', function (e) {
        //     editor.bubble( this );
        // } );

        // $('#dataTables-example').on( 'click', 'tbody td:not(:first-child)', function (e) {
        //     editor.inline( this );
        // } );

        var table =$('#dataTables-example').DataTable({
            dom: 'Bfrtilp',
            ajax:"userlist",
            order: [[ 1, 'asc' ]],
            columns: [
                {
                    data: null,
                    defaultContent: '',
                    className: 'select-checkbox',
                    orderable: false
                },
                { data: "username" },
                { data: "password" },
                {
                    data: "usertype" ,
                    render:function (data,type,row) {
                      if(row.usertype==0){
                          return '<span class = "label label-success">员工【园区部】</span>'
                      }else if(row.usertype==1){
                          return '<span class = "label label-success">员工【招商部】</span>'
                      }else if(row.usertype==2){
                          return '<span class = "label label-danger">孵化器【综合管理部】</span>'
                      }else if(row.usertype==3){
                          return '<span class = "label label-default">管理员【系统维护者】</span>'
                      }
                    }
                },
                {
                    data: "userstatus",
                    render:function (data,type,row) {
                        if(row.userstatus==0){
                            return '<span class = "label label-warning">注销</span>'
                        }else if(row.userstatus==1){
                            return '<span class = "label label-info">正常</span>'
                        }
                    }
                },
                { data: "logintime" },
                { data: "regtime" },
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
                { extend: "create", editor: editor ,text: '<i class="fa fa-plus">&nbsp;&nbsp;新建用户</i>'},
                { extend: "edit",   editor: editor ,text: '<i class="fa fa-edit">&nbsp;&nbsp;修改用户</i>'},
                { extend: "remove", editor: editor ,text: '<i class="fa fa-trash-o">&nbsp;&nbsp;删除用户</i>'}
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
                var username = this.field('username');
                if ( ! username.isMultiValue() ) {
                    if ( ! username.val() ) {
                        username.error( '用户名称必填' );
                    }

                    if ( username.val().length >= 20 ) {
                        username.error( '用户名称不超过20个字符' );
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
