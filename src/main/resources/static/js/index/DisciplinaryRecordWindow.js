Ext.define('Index.DisciplinaryRecordWindow', {
    extend: 'Ext.window.Window',
    xtype: 'disciplinaryRecordWindow',
    layout: 'vbox',
    flag:null,
    url:null,
    width:610,
    height:600,
    initComponent: function () {
        var me = this;
        me.callParent();
        me.loadData();
        me.loadComponent();
    },
    loadData:function(){
        var me = this;
        //加载宿舍楼store
        me.floorStore=new Ext.create('Ext.data.Store',{
           fields:['fid','fname'],
           proxy:{
               type: 'ajax',
               url: '/dormitory/findAllFloor',
               method:'GET',
               reader: {
                   type: 'json',
                   root: 'data'
               }
           }
        });
        //加载宿舍store
        me.dormitoryStore=new Ext.create('Ext.data.Store',{
            fields:['did','fid','dname'],
            proxy:{
                type: 'ajax',
                url: '/dormitory/findDormitoryByFid',
                method:'POST',
                extraParams:{
                  fid:null
                },
                reader: {
                    type: 'json',
                    root: 'data'
                }
            }
        });
        if (me.flag == 0) {
            //学生store
            me.studentStore=new Ext.create('Ext.data.Store',{
                fields:['sid','sname'],
                proxy:{
                    type:'ajax',
                    url:'/student/find/'+null,
                    method:'GET',
                    reader: {
                        type: 'json',
                        root: 'data'
                    }
                }
            });
        }
        //违纪记录store
        me.disciplinaryStore=Ext.create('Ext.data.Store',{
            fields:['pid','fname','dname','sname','sid','content','createUser','createTime','image'],
            proxy: {
                type: 'ajax',
                //给俩个地址的是用另一种方法解决问题
                url:me.url,
                method:'GET',
                reader: {
                    type: 'json',
                    root: 'data'
                }
            },
            autoLoad: true
        });
    },
    loadComponent: function () {
        var me=this;
        //宿舍楼下拉框
        var floorComb=Ext.create('Ext.form.ComboBox',{
           fieldLabel:'宿舍楼',
           labelWidth:50,
           width:160,
           editable:false,
           store:me.floorStore,
           displayField:'fname',
           valueField:'fid',
           emptyText:'请选择宿舍楼',
           name:'fid',
           listeners:{
               change:function (field,newValue) {
                   //实现二级联动
                   me.dormitoryStore.getProxy().extraParams={fid:newValue};
                   dormitoryComb.clearValue();
                   me.dormitoryStore.load();
               }
           }
        });
        //宿舍下拉框
        var dormitoryComb=Ext.create('Ext.form.ComboBox',{
            fieldLabel:'宿舍',
            labelWidth:40,
            width:150,
            margin:'0 0 0 20',
            editable:false,
            store:me.dormitoryStore,
            displayField:'dname',
            valueField:'did',
            emptyText:'请选择宿舍',
            name:'did',
            listeners:{
                change:function (field,newValue) {
                    //实现二级联动,如果是个人违纪面板才执行
                    if (me.flag == 0) {
                        me.studentStore.getProxy().url="/student/find/"+newValue;
                        studentComb.clearValue();
                        me.studentStore.load();
                    }
                }
            }
        });
        //学生下拉框
        var studentComb=Ext.create('Ext.form.ComboBox',{
            fieldLabel:'学生',
            labelWidth:40,
            allowBlank:false,
            width:150,
            margin:'0 0 0 20',
            editable:false,
            store:me.studentStore,
            displayField:'sname',
            valueField:'sid',
            emptyText:'请选择学生',
            name:'sid'
        });
        //违纪内容
        var disciplinaryContent=Ext.widget('textareafield',{
           fieldLabel:'违纪内容',
           allowBlank:false,
           labelWidth:60,
           width:310,
           height:100,
           margin:me.flag==0?'10 0 0 80':'10 0 0 0',
           name:'content'
        });
        //文件上传
        var fileUpLoad=Ext.widget('filefield',{
            name:'photo',
            fieldLabel:'违纪照片',
            labelWidth:60,
            width:250,
            margin:me.flag==0?'10 0 0 80':'10 0 0 0',
            buttonText:'选择图片'
        });
        //确定按钮
        var ensureButton=Ext.create('Ext.button.Button',{
            text:'确定',
            margin:me.flag==0?'0 30 0 180':'0 30 0 100',
            handler:function () {
                me.addRecord();
            }
        });
        //删除按钮
        var deleteButton=Ext.create('Ext.button.Button',{
            text:'删除',
            handler:function () {
                me.deleteRecord();
            }
        });
        //表单
        var formPanel=Ext.create('Ext.form.Panel',{
            layout:'vbox',
            bodyPadding:me.flag==0?'10 0 0 40':'10 0 0 120',
            width:605,
            height:250,
            items:[{
                border:0,
                layout:'hbox',
                items:[floorComb,dormitoryComb,me.flag==0?studentComb:null]
            },disciplinaryContent,fileUpLoad,{
                border:0,
                layout:'hbox',
                margin:'30 0 0 0',
                defaults:{
                    width:50,
                    height:30
                },
                items:[ensureButton,deleteButton]
            }]
        });

        me.add([formPanel]);

        if(me.flag==1){
            me.add(Ext.create('Ext.grid.Panel',{
                scroll:true,
                height:318,
                width:600,
                columns:[
                    {header:'宿舍楼',dataIndex:'fname',width:80},
                    {header:'宿舍',dataIndex:'dname',width:80},
                    {header:'违纪内容',dataIndex:'content',width:120},
                    {header:'违纪时间',dataIndex:'createTime'},
                    {header:'记录人',dataIndex:'createUser'},
                    {header:'图片',renderer:function (value,record,item) {
                            if (item.data.image){
                                return '<a href="'+window.document.location.href+item.data.image+'" target="_blank">查看违纪图片</a>';
                            } else {
                                return '<a>无违纪图片</a>';
                            }
                        }
                    }
                ],
                store:me.disciplinaryStore
            }));
        }else if(me.flag==2){
            me.add(Ext.create('Ext.grid.Panel',{
                scroll:true,
                height:318,
                width:600,
                columns:[
                    {header:'宿舍楼',dataIndex:'fname',width:70},
                    {header:'宿舍',dataIndex:'dname',width:70},
                    {header:'值日生',dataIndex:'sname',width:70},
                    {header:'违纪内容',dataIndex:'content',width:115},
                    {header:'违纪时间',dataIndex:'createTime'},
                    {header:'记录人',dataIndex:'createUser',width:60},
                    {header:'图片',renderer:function (value,record,item) {
                            if (item.data.image){
                                return '<a href="'+window.document.location.href+item.data.image+'" target="_blank">查看违纪图片</a>';
                            } else {
                                return '<a>无违纪图片</a>';
                            }
                        }
                    }
                ],
                store:me.disciplinaryStore
            }));
        }else if (me.flag == 0) {
            me.add(Ext.create('Ext.grid.Panel',{
                scroll:true,
                height:318,
                width:600,
                columns:[
                    {header:'宿舍楼',dataIndex:'fname',width:70},
                    {header:'宿舍',dataIndex:'dname',width:70},
                    {header:'违纪人',dataIndex:'sname',width:70},
                    {header:'违纪内容',dataIndex:'content',width:115},
                    {header:'违纪时间',dataIndex:'createTime'},
                    {header:'记录人',dataIndex:'createUser',width:60},
                    {header:'图片',renderer:function (value,record,item) {
                            if (item.data.image){
                                return '<a href="'+window.document.location.href+item.data.image+'" target="_blank">查看违纪图片</a>';
                            } else {
                                return '<a>无违纪图片</a>';
                            }
                        }
                    }
                ],
                store:me.disciplinaryStore
            }));
        }
    },
    addRecord:function () {
        var me = this;
        var form = me.down('form').getForm();
        if(form.isValid()){
            form.submit({
                url:(me.flag==1?'/dormitory':'/student')+'/submitDisciplinaryRecord',
                params:{
                    flag:me.flag
                },
                success:function (form, action) {
                    Ext.Msg.alert('提示信息','添加成功！');
                    form.reset();
                    me.disciplinaryStore.load();
                },
                failure:function (form, action) {
                    Ext.Msg.alert('提示信息',action.result.message);
                }
            });
        }
    },
    deleteRecord:function () {
        // console.log(this.down('grid').getSelectionModel().selected.items[0].data);
        var grid=this.down('grid');
        var selected = grid.getSelectionModel().selected.items[0];
        if (!selected){
            Ext.Msg.alert('提示信息','请在表格中选中记录进行删除！');
            return;
        }else {
            Ext.Ajax.request({
                //删除宿舍违纪记录也使用此链接
                url:'/student/deleteDisciplinaryRecord/'+selected.data.pid,
                method:'GET',
                success:function (response) {
                    var resp=Ext.decode(response.responseText);
                    if(resp.state){
                        Ext.Msg.alert('提示信息','删除成功！');
                        grid.getStore().load();
                    }else{
                        Ext.Msg.alert('提示信息',resp.message);
                    }
                }
            });
        }
    }
});