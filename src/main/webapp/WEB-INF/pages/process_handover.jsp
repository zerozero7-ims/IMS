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

    <title>孵化器管理系统 - 房屋交接流程</title>

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
                <h3 class="page-header">房屋交接流程</h3>
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
                                <th>房屋交接单</th>
                                <th>交接状态</th>
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
                url:"process_handoveraction",
                data:function(data){
                    var result = {};
                    result.action=data.action;
                    var flows = [];
                    for(var i in data.data){
                        var flow = data.data[i];
                        flow.id=i;
                        flows.push(JSON.stringify(flow));
                    }
                    result.flows = "["+flows.toString()+"]";
                    return result;
                }

            },
            table: "#dataTables-example",
            idSrc:'id',
            fields: [ {
                label: "企业名称:",
                name: "companyname"
            }, {
                label: "房屋交接单:",
                name: "attachment",
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
            ajax:"process_handoverlist",
            order: [[ 1, 'asc' ]],
            columns: [
                {
                    data: null,
                    defaultContent: '',
                    className: 'select-checkbox',
                    orderable: false
                },
                { data: "companyname" },
                { data: "attachment" ,
                  render:function ( fileId, counter ) {
                      // return '<img src="'+editor.file( 'files', fileId ).web_path+'"/>';
                      var result = '';
                      if(fileId!=null && fileId!=undefined && fileId!=''){
                          for(var i=0;i<fileId.length;i++){
                              result+='<a href="'+editor.file( 'files', fileId[i] ).web_path+'" target="_blank">附件'+(i+1)+'</a>&nbsp;&nbsp;';
                          }
                      }
                      return result;
                  }
                },
                { data: "curflow",
                  render:function (data,type,row) {
                        if(row.curflow==0){
                            return '<span class = "label label-info">交接中</span>'
                        }else if(row.curflow==1){
                            return '<span class = "label label-success">已交接</span>'
                        }
                   }
                },
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
                { extend: "create", editor: editor ,text: '<i class="fa fa-plus">&nbsp;&nbsp;添加交接</i>'},
                { extend: "edit",   editor: editor ,text: '<i class="fa fa-edit">&nbsp;&nbsp;修改交接</i>'},
                { extend: "remove", editor: editor ,text: '<i class="fa fa-trash-o">&nbsp;&nbsp;删除交接</i>'},
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
                var companyname = this.field('companyname');
                if ( ! companyname.isMultiValue() ) {
                    if ( ! companyname.val() ) {
                        companyname.error( '合同编号必填' );
                    }

                    if ( companyname.val().length >= 20 ) {
                        companyname.error( '合同编号填写有误' );
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
