Ext.define('Index.MaintainRecordWindow', {
    extend: 'Ext.window.Window',
    xtype: 'maintainRecordWindow',
    layout: 'vbox',
    width: 720,
    height: 600,
    initComponent: function () {
        var me = this;
        me.callParent();
        me.loadData();
        me.loadComponent();
    },
    loadData: function () {
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
        //加载学生store
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
        //维修记录store
        me.maintainStore=Ext.create('Ext.data.Store',{
            fields:['mid','fname','dname','did','content','createUser','createTime','remark','maintainTime','maintainUser'],
            proxy: {
                type: 'ajax',
                url:"/dormitory/findAllMaintain",
                method:'GET',
                reader: {
                    type: 'json',
                    root: 'data'
                }
            },
            autoLoad: true
        });
    },
    loadComponent:function () {
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
                    me.studentStore.getProxy().url="/student/find/"+newValue;
                    studentComb.clearValue();
                    me.studentStore.load();
                }
            }
        });
        //学生下拉框
        var studentComb=Ext.create('Ext.form.ComboBox',{
            fieldLabel:'登记学生',
            labelWidth:60,
            allowBlank:false,
            width:160,
            margin:'0 0 0 20',
            editable:false,
            store:me.studentStore,
            displayField:'sname',
            valueField:'sname',
            emptyText:'请选择学生',
            name:'createUser'
        });
        //维修内容
        var maintainContent=Ext.widget('textareafield',{
            fieldLabel:'维修内容',
            allowBlank:false,
            labelWidth:60,
            width:310,
            height:100,
            margin:'20 0 0 80',
            name:'content'
        });
        //确定按钮
        var ensureButton=Ext.create('Ext.button.Button',{
            text:'确定',
            margin:'0 30 0 150',
            handler:function () {
                me.addRecord();
            }
        });
        //删除按钮
        var deleteButton=Ext.create('Ext.button.Button',{
            text:'删除',
            margin:'0 30 0 0',
            handler:function () {
                me.deleteRecord();
            }
        });
        //保存按钮
        var saveButton=Ext.create('Ext.button.Button',{
           text:'保存表格修改',
           width:100,
           handler:function () {
               me.saveRecord();
           }
        });
        //表单
        var formPanel=Ext.create('Ext.form.Panel',{
            layout:'vbox',
            bodyPadding:'20 0 0 90',
            width:710,
            height:250,
            items:[{
                border:0,
                layout:'hbox',
                items:[floorComb,dormitoryComb,studentComb]
            },maintainContent,{
                border:0,
                layout:'hbox',
                margin:'20 0 0 0',
                defaults:{
                    width:50,
                    height:30
                },
                items:[ensureButton,deleteButton,saveButton]
            }]
        });

        me.add([formPanel]);

        me.add(Ext.create('Ext.grid.Panel',{
            scroll:true,
            height:318,
            width:710,
            columns:[
                {header:'宿舍楼',dataIndex:'fname',width:70},
                {header:'宿舍',dataIndex:'dname',width:70},
                {header:'登记人',dataIndex:'createUser',width:70},
                {header:'维修内容',dataIndex:'content',width:115},
                {header:'登记时间',dataIndex:'createTime'},
                {header:'备注',dataIndex:'remark',editor:'textfield'},
                {header:'维修人',dataIndex:'maintainUser',width:70,editor:'textfield'},
                {header:'维修时间',dataIndex:'maintainTime'}
            ],
            store:me.maintainStore,
            plugins:[{
                ptype:'cellediting',
                clicksToEdit:1
            }]
        }));
    },
    addRecord:function () {
        var me = this;
        var form = me.down('form').getForm();
        if(form.isValid()){
            form.submit({
                url:'/dormitory/addMaintainRecord',
                success:function (form, action) {
                    Ext.Msg.alert('提示信息','添加成功！');
                    form.reset();
                    me.maintainStore.load();
                },
                failure:function (form, action) {
                    Ext.Msg.alert('提示信息',action.result.message);
                }
            });
        }
    },
    deleteRecord:function () {
        var grid=this.down('grid');
        var selected = grid.getSelectionModel().selected.items[0];
        if (!selected){
            Ext.Msg.alert('提示信息','请在表格中选中记录进行删除！');
            return;
        }else {
            Ext.Ajax.request({
                url:'/dormitory/deleteMaintainRecord/'+selected.data.mid,
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
    },
    saveRecord:function () {
        var me=this;
        //获取修改过的数据是一个数组
        var updateData=me.maintainStore.getUpdatedRecords();
        var maintains=new Array();
        //遍历放到数组中
        Ext.Array.each(updateData,function (item) {
            maintains.push(item.getData());
        });

        Ext.Ajax.request({
            url: '/dormitory/saveMaintainRecord',
            method:'POST',
            jsonData: JSON.stringify(maintains),
            // headers:{
            //     'Content-Type' : 'application/json;charset=utf-8'
            // },
            success: function(response){
                var resp=Ext.decode(response.responseText);
                if(resp.state){
                   Ext.Msg.alert('提示信息','保存成功');
                   me.maintainStore.load();
                }else{
                    Ext.Msg.alert('提示信息',resp.message);
                }
            }
        });

    }
});