/**
 * 功能栏设计
 */
Ext.define('Index.Function',{
    extend:'Ext.menu.Menu',
    xtype:'fun',
    id:'fun',
    width:180,
    plain:true,
    margin: '0 0 10 0',
    floating: false,
    initComponent:function () {
        var me=this;
        this.callParent();
        this.loadComp();
        this.loadData();
    },
    loadData:function () {

    },
    loadComp:function(){
        var me=this;
        var items=[{
            text: '管理员设置',
            id:'changeu',
            menu: Ext.create('Ext.menu.Menu',{
                width:100,
                plain:true,
                items:[{
                    text: '查看管理员',
                    handler:function () {
                        me.isControl();
                    }
                },{
                    text: '添加管理员',
                    handler:function () {
                        me.isControl();
                    }
                },{
                    text: '删除管理员',
                    handler:function () {
                        me.isControl();
                    }
                }]
            })
        },{
            text: '修改密码',
            id:'changep',
            listeners:{
                'click':function (item) {
                    var changePwd=Ext.create('Ext.window.Window',{
                        title:'修改密码',
                        width:300,
                        height:300,
                        draggable:false,//不可移动
                        resizable:false,//不可改变大小
                        modal:true,//模态窗口
                        items:[{
                            xtype:'textfield',
                            id:'oldpwd',
                            fieldLabel: '当前密码',
                            inputType:'password',
                            margin:'40 20 0 20',
                            labelWidth:55,
                            maxLength:18,
                            maxLengthText:'密码最大长度为18！'
                        },{
                            xtype:'textfield',
                            id:'newpwd',
                            fieldLabel: '修改密码',
                            inputType:'password',
                            margin:'20 20 0 20',
                            labelWidth:55,
                            maxLength:18,
                            maxLengthText:'密码最大长度为18！'
                        },{
                            xtype:'textfield',
                            id:'anewpwd',
                            fieldLabel: '确认密码',
                            inputType:'password',
                            margin:'20 20 0 20',
                            labelWidth:55,
                            maxLength:18,
                            maxLengthText:'密码最大长度为18！'
                        }],
                        bbar:[{
                            xtype:'button',
                            text:'确定',
                            width:50,
                            height:40,
                            margin:'0 30 0 80',
                            handler:function () {
                                var oldpwd=Ext.getCmp('oldpwd').getValue();
                                var newpwd=Ext.getCmp('newpwd').getValue();
                                var anewpwd=Ext.getCmp('anewpwd').getValue();
                                if(oldpwd.length>18 || newpwd.length>18 || anewpwd.length>18){
                                    Ext.Msg.alert('提示信息','密码最大长度18位！');
                                    return;
                                }else if (oldpwd.length==0 || newpwd.length==0 || anewpwd.length==0){
                                    Ext.Msg.alert('提示信息','密码为空！');
                                    return;
                                }else if(newpwd!==anewpwd){
                                    Ext.Msg.alert('提示信息','修改密码与确认密码不同！');
                                    return;
                                }
                                Ext.Ajax.request({
                                    url: '/users/change_password',
                                    method:'POST',
                                    params: {
                                        oldPassword:oldpwd,
                                        newPassword:newpwd
                                    },
                                    success: function(response){
                                        var resp=Ext.decode(response.responseText);
                                        if(resp.state){
                                            Ext.Msg.alert('提示信息','修改成功！',function () {
                                                window.location.href="/";
                                            });
                                        }else{
                                            Ext.Msg.alert('提示信息',resp.message);
                                        }
                                    }
                                });
                            }
                        },{
                            xtype:'button',
                            text:'取消',
                            width:50,
                            height:40,
                            handler:function () {
                                changePwd.close();
                            }
                        }]
                    }).show();
                }
            }
        },{
            text:'退出登陆',
            handler:function () {
                Ext.Ajax.request({
                    url: '/users/clear_session',
                    method:'POST',
                    success: function(){
                        window.location.href="/";
                    }
                });
            }
        }]
        this.add(items);
    },
    /**
     * 判断是都存在操作权限权限
     */
    isControl:function () {
        Ext.Ajax.request({
            url: '/users/is_su',
            method:'POST',
            success: function(response){
                var resp = Ext.decode(response.responseText);
                if(!resp.state){
                    Ext.Msg.alert('提示信息',resp.message);
                }
            }
        });
    }


});