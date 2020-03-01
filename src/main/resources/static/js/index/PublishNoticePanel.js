/**
 * 通报面板组件
 */
Ext.define('Index.PublishNoticePanel', {
    extend: 'Ext.panel.Panel',
    xtype: 'publishNoticePanel',
    closable: true,
    layout: 'hbox',
    autoScroll: true,
    title:'通报记录',
    initComponent: function () {
        var me = this;
        me.callParent();
        me.loadData();
        me.loadComponent();
    },
    loadData: function () {
        var me=this;
        //通知store
        me.noticeStore=Ext.create('Ext.data.Store',{
            fields:['nid','title','content','publishUser','createUser','createTime','modifyUser','modifyTime'],
            proxy:{
                type: 'ajax',
                url: '/dormitory/findAllNotice',
                method:'GET',
                reader: {
                    type: 'json',
                    root: 'data'
                }
            },
            autoLoad:true
        });
    },
    loadComponent: function () {
        var me = this;
        //标题
        var title=Ext.widget('textfield',{
            fieldLabel:'标题',
            allowBlank:false,
            labelWidth:60,
            width:400,
            height:30,
            name:'title'
        });
        //内容
        var content=Ext.widget('textareafield',{
            fieldLabel:'内容',
            allowBlank:false,
            labelWidth:60,
            width:600,
            height:370,
            name:'content'
        });
        //发布单位/人
        var publishUser=Ext.widget('textfield',{
            fieldLabel:'发布者',
            allowBlank:false,
            labelWidth:60,
            width:250,
            height:30,
            name:'publishUser'
        });
        //发布按钮
        var publishButton=Ext.widget('button',{
            text:'发布',
            margin:'0 20 0 80',
            handler:function () {
                me.publish();
            }
        });
        //重置按钮
        var resetButton=Ext.widget('button',{
            text:'重置内容',
            handler:function () {
                me.reset();
            }
        });
        //预览按钮
        var previewButton=Ext.widget('button',{
           text:'预览内容',
           margin:'0 20 0 20',
           handler:function () {
               me.preview();
           } 
        });
        //修改按钮
        var updateButton=Ext.widget('button',{
            text:'修改已发布内容',
            handler:function () {
                me.update();
            }
        });
        //修改提交按钮
        var updateCommitButton=Ext.widget('button',{
            text:'提交已修改内容',
            id:'updateCommit',
            hidden:true,
            height:30,
            margin:'0 0 0 20',
            handler:function () {
                me.updateCommit();
                Ext.getCmp('updateCommit').setVisible(false);
            }
        });
        //删除按钮
        var deleteButton=Ext.widget('button',{
            text:'删除已发布内容',
            margin:'0 0 0 20',
            handler:function () {
                me.delete();
            }
        });
        //通知表单
        var formPanel=Ext.create('Ext.form.Panel',{
            width:630,
            border:0,
            bodyPadding:10,
            layout:'vbox',
            items:[title,content,{
                layout:'hbox',
                border:0,
                items:[publishUser,updateCommitButton]
            },{
                layout:'hbox',
                defaults:{
                  height:30
                },
                border:0,
                margin:'5 0 0 0',
                items:[publishButton,resetButton,previewButton,updateButton,deleteButton]
            }]
        });
        me.add(formPanel);
        // 已发布内容表格
        var publishGrid=Ext.create('Ext.grid.Panel',{
            scroll:true,
            height:480,
            width:516,
            columns:[
                {header:'标题',dataIndex:'title',width:80},
                {header:'内容',dataIndex:'content',width:125},
                {header:'发布单位',dataIndex:'publishUser'},
                {header:'发布时间',dataIndex:'createTime'},
                {header:'修改时间',dataIndex:'modifyTime'}
            ],
            store:me.noticeStore
        });
        me.add(publishGrid);
    },
    publish:function () {
        var me=this;
        var form=me.down('form').getForm();
        if(form.isValid()) {
            form.submit({
                url: '/dormitory/addPublishNotice',
                success: function (form, action) {
                    Ext.Msg.alert('提示信息', '发布成功！');
                    form.reset();
                    me.noticeStore.load();
                },
                failure: function (form, action) {
                    Ext.Msg.alert('提示信息', action.result.message);
                }
            });
        }
    },
    reset:function () {
        this.down('form').getForm().reset();
    },
    preview:function () {
        Ext.Msg.alert('提示信息','待开发');
    },
    update:function () {
        var me=this;
        var grid=me.down('grid');
        var form=me.down('form').getForm();
        var selected = grid.getSelectionModel().selected.items[0];
        if (!selected){
            Ext.Msg.alert('提示信息','请在表格中选中记录进行更改！');
            return;
        }else {
            form.setValues(selected.data);
            //保存一下点击的数据
            me.commitData=selected.data;
            Ext.getCmp('updateCommit').setVisible(true);

        }
    },
    updateCommit:function () {
        var me=this;
        var form=me.down('form').getForm();
        if(form.isValid()) {
            form.submit({
                url: '/dormitory/updatePublishNotice',
                params:{
                  nid:me.commitData.nid,
                  createTime:me.commitData.createTime,
                  createUser:me.commitData.createUser
                },
                success: function (form, action) {
                    Ext.Msg.alert('提示信息', '修改成功！');
                    form.reset();
                    me.noticeStore.load();
                },
                failure: function (form, action) {
                    Ext.Msg.alert('提示信息', action.result.message);
                }
            });
        }
    },
    delete:function () {
        var me=this;
        var grid=me.down('grid');
        var selected = grid.getSelectionModel().selected.items[0];
        if (!selected){
            Ext.Msg.alert('提示信息','请在表格中选中记录进行删除！');
            return;
        }else {
            Ext.Ajax.request({
                url: '/dormitory/deletePublishNotice/' + selected.data.nid,
                method: 'GET',
                success: function (response) {
                    var resp = Ext.decode(response.responseText);
                    if (resp.state) {
                        Ext.Msg.alert('提示信息', '删除成功！');
                        grid.getStore().load();
                    } else {
                        Ext.Msg.alert('提示信息', resp.message);
                    }
                }
            });
        }
    }
});