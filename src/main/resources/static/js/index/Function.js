/**
 * 功能栏设计
 */
Ext.define('Index.Function',{
    extend:'Ext.menu.Menu',
    requires:['Index.AddOrDeleteFloor','Index.DisciplinaryRecordWindow'],
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
            text:'宿舍维修登记',
            handler:function () {
                alert('宿舍维修登记');
            }
        },{
            text:'违纪记录',
            menu:Ext.create('Ext.menu.Menu',{
                plain:true,
                items:[
                    {text:'个人违纪记录',handler:function () {
                            alert(1);
                        }},
                    {text:'宿舍违纪记录',handler:function () {
                            Ext.create('Index.DisciplinaryRecordWindow',{title:'宿舍违纪记录',flag:1,url: '/dormitory/findAllDormitoryDisciplinary'}).show();
                        }},
                    {text:'卫生查询记录',handler:function() {
                            Ext.create('Index.DisciplinaryRecordWindow',{title:'卫生查询记录',flag:2,url: '/student/findAllStudentDisciplinary'}).show();
                        }}
                ]
            })
        },{
            text:'学生批量操作',
            handler:function () {
                Ext.Msg.alert('提示信息','待开发');
            }
        },{
            text:'生成周值日表',
            handler:function () {
               me.isControl('',function(){
                   var wait=Ext.Msg.wait('正在生成值日表...');
                   Ext.Ajax.request({
                       url:'/student/randomDuty',
                       success:function (response) {
                           if(Ext.decode(response.responseText).state){
                               wait.close();
                               Ext.Msg.alert('提示信息','生成值日表成功！请重新刷新页面！');
                           };
                       }
                   });
               });
            }
        },{
            text:'宿舍楼设置',
            handler:function () {
                var newFloor=Ext.create('Index.AddOrDeleteFloor');
                me.isControl(newFloor);
            }
        },{
            text: '管理员设置',
            id:'changeu',
            handler:function () {
                var findUser=Ext.create('Ext.window.Window',{
                    title:'管理员设置',
                    width:350,
                    height:400,
                    draggable:false,//不可移动
                    resizable:false,//不可改变大小
                    modal:true,//模态窗口
                    layout:'fit',
                    items:{
                        xtype:'grid',
                        id:'users',
                        border:false,
                        // bodyStyle: 'background:#87CEFA',
                        plugins:{
                            ptype: 'cellediting',
                            clicksToEdit: 2
                        },
                        store: {
                            xtype:'store',
                            fields:['username', 'password', 'uid'],
                            proxy: {
                                type: 'ajax',
                                url: '/users/find_user',
                                actionMethods:{
                                    create:'POST'
                                },
                                reader: {
                                    type: 'json',
                                    root: 'data'
                                }
                            },
                            autoLoad: true
                        },
                        columns: [
                            { header: 'id',  dataIndex: 'uid',width:30 },
                            { header: '用户名', dataIndex: 'username', flex: 1 },
                            { header: '密码', dataIndex: 'password' ,flex:1 }
                        ],
                        listeners:{
                            itemcontextmenu:function (view,record,item,index,e) {
                                var userMenu=Ext.create('Ext.menu.Menu',{
                                    plain:true,
                                    width:100,
                                    margin: '0 0 10 0',
                                    items:[{
                                        text:'删除管理员',
                                        handler:function () {
                                            var username=record.data.username;
                                            var uid=record.data.uid;
                                            Ext.Msg.confirm('提示信息','确认删除'+username+'?',function (btn) {
                                                if(btn=='yes'){
                                                    Ext.Ajax.request({
                                                        url: '/users/delete_user',
                                                        method:'POST',
                                                        params: {
                                                            username:username,
                                                            uid:uid
                                                        },
                                                        success: function(response){
                                                            var resp=Ext.decode(response.responseText);
                                                            if(resp.state){
                                                                Ext.Msg.alert('提示信息','删除成功！');
                                                                Ext.getCmp('users').getStore().load();
                                                            }else{
                                                                Ext.Msg.alert('提示信息',resp.message);
                                                            }
                                                        }
                                                    });
                                                }
                                            })
                                        }
                                    },{
                                        text:'添加管理员',
                                        handler:function () {
                                            me.addUserShow();
                                        }
                                    }]
                                });
                                e.preventDefault();//阻止浏览器默认行为
                                userMenu.showAt(e.getXY());
                            },
                            containercontextmenu:function (view, e) {
                                var conMenu=Ext.create('Ext.menu.Menu', {
                                    plain: true,
                                    width: 100,
                                    margin: '0 0 10 0',
                                    items:[{
                                        text:'添加管理员',
                                        handler:function () {
                                            me.addUserShow();
                                        }
                                    }]
                                });
                                e.preventDefault();//阻止浏览器默认行为
                                conMenu.showAt(e.getXY());
                            }
                        }
                    },
                    bbar:[{
                        xtype: 'button',
                        text: '添加',
                        width: 50,
                        height: 30,
                        margin: '0 30 0 100',
                        handler:function () {
                            // var a=Ext.getCmp('users').getStore();
                            // a.load();
                            // console.log(a);
                            me.addUserShow();
                        }
                    },{
                        xtype:'button',
                        text:'取消',
                        width:50,
                        height:30,
                        handler:function () {
                            findUser.close();
                        }
                    }]
                });
                me.isControl(findUser);//控制权限
            }
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
                    url: '/users/session_data',
                    method:'POST',
                    params:{
                      flag:'clear'
                    },
                    success: function(){
                        window.location.href="/";
                    }
                });
            }
        }]
        this.add(items);
    },
    /**
     * 增加用户面板展示
     */
    addUserShow:function(){
        var addUser=Ext.create('Ext.window.Window',{
            title:'添加管理员',
            width:300,
            height:300,
            constrain:true,
            // draggable:false,//不可移动
            resizable:false,//不可改变大小
            modal:true,//模态窗口
            items:[{
                xtype:'textfield',
                fieldLabel: '用户名',
                id:'nuser',
                margin:'40 20 0 20',
                labelWidth:55,
                maxLength:18,
                maxLengthText:'用户名最大长度为18！'
            },{
                xtype:'textfield',
                fieldLabel: '密码',
                inputType:'password',
                id:'npass',
                margin:'40 20 0 20',
                labelWidth:55,
                maxLength:18,
                maxLengthText:'密码最大长度为18！'
            }],
            bbar:[{
                xtype: 'button',
                text: '确定',
                width: 50,
                height: 30,
                margin: '0 30 0 80',
                handler:function () {
                    var nuser=Ext.getCmp('nuser').getValue();
                    var npass=Ext.getCmp('npass').getValue();
                    if(nuser.length>18 || npass.length>18){
                        Ext.Msg.alert('提示信息','最大长度18位！');
                        return;
                    }else if (nuser.length==0 || npass.length==0){
                        Ext.Msg.alert('提示信息','用户名或密码为空！');
                        return;
                    }
                    Ext.Ajax.request({
                        url: '/users/add_user',
                        method:'POST',
                        params: {
                            username:nuser,
                            password:npass
                        },
                        success: function(response){
                            var resp=Ext.decode(response.responseText);
                            if(resp.state){
                                Ext.Msg.alert('提示信息','增加成功！');
                                addUser.close();
                                Ext.getCmp('users').getStore().load();
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
                height:30,
                handler:function () {
                    addUser.close();
                }
            }]
        }).show();
    },
    /**
     * 判断是否存在操作权限权限
     */
    isControl:function (win,call) {
        Ext.Ajax.request({
            url: '/users/is_su',
            method:'POST',
            success: function(response){
                var resp = Ext.decode(response.responseText);
                if(!resp.state){
                    Ext.Msg.alert('提示信息',resp.message);
                    return;
                }else{
                    if(win){
                        win.show();
                    }else{
                        call();
                    }
                }
            }
        });
    }


});