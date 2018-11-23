/*自定义表单字段类型*/
(function($,DataTable){
    if(! DataTable.ext.editorFields){
        DataTable.ext.editorFields={};
    }
    var Editor = DataTable.Editor;
    var _fieldTypes = DataTable.ext.editorFields;
    /*自定义表单字段类型--缴纳租金情况 paylist*/
    _fieldTypes.paylist={
        create:function(conf){
            var that = this;
            conf._enabled = true;
            conf._input = $(
                '<div id = "'+Editor.safeId(conf.id)+'">'+

                '<table class="table table-bordered table-hover table-striped" style="float:left; width:90%">'+
                '<tr height="30px"><th width="15%" >期数</th><th width="18%" >计费开始时间</th><th width="18%">计费结束时间</th><th width="13%" >单价</th><th width="13%" >缴费金额</th><th width="13%" >缴费状态</th><th width="10%" >操作</th></tr>'+
                '<tbody  id="paylist" >'+
                '<tr id="pay0" selfid="0"></tr>'+
                '</tbody>'+
                '</table>'+

                '<button type="button" class="addpaylist" style="float:right;width:10%">增加</button>'+
                '</div>'
            );
            $('button.addpaylist',conf._input).click(function () {
                if(conf._enabled){
                    addList('paylist',null,conf._input);
                }


            });
            return conf._input;
        },
        get:function(conf){

            var paylist = [];
            $("#paylist tr",conf._input).each(function (i,el) {
                var pay={};
                var term = $(this).children("td").eq(0).children("input").val();
                var start = $(this).children("td").eq(1).children("input").val();
                var end = $(this).children("td").eq(2).children("input").val();
                var unitprice = $(this).children("td").eq(3).children("input").val();
                var payment = $(this).children("td").eq(4).children("input").val();
                var paystate = $(this).children("td").eq(5).children("input").val();
                var id = $(this).children("input").val();
                if(id != null && id != undefined && id !=''){ pay.id = id;}
                if(term != null && term != undefined && term !=''){ pay.term = term;}
                if(term != null && term != undefined && term !=''){pay.start = start;}
                if(end != null && end != undefined && end !=''){ pay.end = end;}
                if(unitprice != null && unitprice != undefined && unitprice !=''){ pay.unitprice = unitprice;}
                if(payment != null && payment != undefined && payment !=''){ pay.payment = payment;}
                if(paystate != null && paystate != undefined && paystate !=''){ pay.paystate = paystate;}
                if(!isEmptyObject(pay)){ paylist.push(pay);}
            })
            return paylist;

        },
        set: function ( conf, val ) {
            var sib = $("#pay0",conf._input).siblings();
            sib.remove();
            if(val !=null && val !="" && val != undefined){
                // var obj = JSON.parse(val);
                var obj = val;
                if(obj != null && obj.length>0){
                    var len = obj.length;
                    for (var i=0;i<len;i++){
                        var html = '';
                        html+='<tr id="pay'+(i+1)+'" selfid ="'+(i+1)+'" >';
                        html+='<input name="pay[id]" type="hidden" value="'+(obj[i].id?obj[i].id:'')+'" />';
                        html+='<td align="center" style="padding:2px"><input name="pay[term]" type="text" value="'+(obj[i].term?obj[i].term:'')+'" /></td>';
                        html+='<td align="center" style="padding:2px"><input name="pay[start]" type="text" value="'+(obj[i].start?obj[i].start:'')+'" /></td>';
                        html+='<td align="center" style="padding:2px"><input name="pay[end]" type="text" value="'+(obj[i].end?obj[i].end:'')+'" /></td>';
                        html+='<td align="center" style="padding:2px"><input name="pay[unitprice]" type="text" value="'+(obj[i].unitprice?obj[i].unitprice:'')+'" /></td>';
                        html+='<td align="center" style="padding:2px"><input name="pay[payment]" type="text" value="'+(obj[i].payment?obj[i].payment:'')+'" /></td>';
                        html+='<td align="center" style="padding:2px"><input name="pay[paystate]" type="text" value="'+(obj[i].paystate?obj[i].paystate:'')+'" /></td>';
                        html+='<td align="center" style="padding:2px;line-height:33px;"><a href="javascript:void()" onclick="$(this).parent().parent().remove();">删除</a></td>';
                        html+='</tr>';
                        $('#pay' + i, conf._input).after(html);

                    }

                }



            }
        },
        enable: function ( conf ) {
            conf._enabled = true;
            $(conf._input).removeClass( 'disabled' );
        },

        disable: function ( conf ) {
            conf._enabled = false;
            $(conf._input).addClass( 'disabled' );
        }
    },
        /*自定义日期范围选择-start*/
    _fieldTypes.daterange={
        create:function (conf) {
            var that = this;
            conf._enabled = true;
            conf._input = $(
                '<div id="'+Editor.safeId( conf.id )+'">'+
                '<div class="input-daterange input-group" id="datepicker">'+
                '<input type="text" class="input-sm form-control" name="start" />'+
                '<span class="input-group-addon">to</span>'+
                '<input type="text" class="input-sm form-control" name="end" />'+
                '</div>'+
                '<script>$(document).ready(function() {$("#datepicker").datepicker({format: "yyyy-mm-dd",language: "zh-CN"});});</script>'+
                '</div>'
            );
            return conf._input;


        },
        get:function(conf){
            var daterange = {};
            daterange.start = $("input[name='start']", conf._input).val();
            daterange.end = $("input[name='end']", conf._input).val();
            return JSON.stringify(daterange);
        },
        set:function(conf,val){
            if(val !=null && val !="" && val != undefined){
                var obj = JSON.parse(val);
                $("input[name='start']", conf._input).val(obj.start);
                $("input[name='end']", conf._input).val(obj.end);
            }
        },
        enable: function ( conf ) {
            conf._enabled = true;
            $(conf._input).removeClass( 'disabled' );
        },

        disable: function ( conf ) {
            conf._enabled = false;
            $(conf._input).addClass( 'disabled' );
        }
    }

    /*自定义日期范围选择-end*/


})(jQuery,jQuery.fn.dataTable);


function addList(id,o,_input){
    var trarr = $("#"+id+" tr",_input);
    var trlen = trarr.length;
    var maxid = 0;
    for(var i=0; i<trlen; i++){
        var sid = trarr[i].getAttribute("selfid");
        if((sid*1)>maxid){
            maxid = sid;
        }
    }
    maxid++;
    var html="";
    if (!o){
         o={term:'',start:'',end:'',unitprice:'',payment:'',paystate:''};
    }
    var len = 1;
    if (o!=null && o.length>0) {
        len = o.length;
    }
    var num=0;
    for (var i=0;i<len;i++){
        var html = '';
            html+='<tr id="pay'+(maxid+i)+'" selfid ="'+(maxid+i)+'" >';
            html+='<td align="center" style="padding:2px"><input name="pay[term]" type="text" value="'+(len==1?o.term:(o[i].term?o[i].term:''))+'" /></td>';
            html+='<td align="center" style="padding:2px"><input name="pay[start]" type="text" value="'+(len==1?o.start:(o[i].start?o[i].start:''))+'" /></td>';
            html+='<td align="center" style="padding:2px"><input name="pay[end]" type="text" value="'+(len==1?o.end:(o[i].end?o[i].end:''))+'" /></td>';
            html+='<td align="center" style="padding:2px"><input name="pay[unitprice]" type="text" value="'+(len==1?o.unitprice:(o[i].unitprice?o[i].unitprice:''))+'" /></td>';
            html+='<td align="center" style="padding:2px"><input name="pay[payment]" type="text" value="'+(len==1?o.payment:(o[i].payment?o[i].payment:''))+'" /></td>';
            html+='<td align="center" style="padding:2px"><input name="pay[paystate]" type="text" value="'+(len==1?o.paystate:(o[i].paystate?o[i].paystate:''))+'" /></td>';
            html+='<td align="center" style="padding:2px;line-height:33px;"><a href="javascript:void()" onclick="$(this).parent().parent().remove();">删除</a></td>';
            html+='</tr>';
            $('#pay' + (maxid+i-1), _input).after(html);

        }
}

//判断对象{}为空
function isEmptyObject(obj) {
    for (var key in obj){
        return false;//返回false，不为空对象
    }
    return true;//返回true，为空对象
}
