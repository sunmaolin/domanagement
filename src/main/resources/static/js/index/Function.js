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
            text:'宿舍楼设置',
            handler:function () {
                var newFloorPanel=Ext.create('Ext.form.Panel',{
                    bodyPadding:'20 0 0 20',
                    layout:'vbox',
                    id:'newFloorPanel',
                    items:[{
                        xtype:'fieldcontainer',
                        width:300,
                        height:30,
                        layout:'hbox',
                        items:[{
                            xtype:'radiofield',
                            boxLabel:'添加宿舍楼',
                            name:'addOrDel',
                            inputValue:'addFloor',
                            checked:true
                        },{
                            xtype:'radiofield',
                            name:'addOrDel',
                            inputValue:'addFloors',
                            boxLabel:'批量添加宿舍楼',
                            margin:'0 0 0 30'
                        }]
                    },{
                        xtype:'fieldcontainer',
                        layout:'hbox',
                        width:300,
                        items:[{
                            xtype: 'textfield',
                            margin: '0 4 0 0',
                            labelWidth: 150,
                            fieldLabel: '输入你要添加的宿舍楼名称',
                            labelSeparator: '',//隐藏掉冒号：
                            width: 250,//初始值为250
                            // width: 190//一会设置为190
                            name:'addFloor'

                        },{
                            xtype:'textfield',
                            labelWidth:10,
                            fieldLabel:'一',
                            labelSeparator: '',//隐藏掉冒号：
                            width: 50,
                            hidden: true,
                            hideLabel:true,
                            name:'addFloors'
                        }]
                    },{
                        xtype:'fieldcontainer',
                        width:300,
                        height:30,
                        margin:'20 0 0 0',
                        layout:'hbox',
                        items:[{
                            xtype:'radiofield',
                            boxLabel:'删除宿舍楼',
                            name:'addOrDel',
                            inputValue:'delFloor'
                        },{
                            xtype:'radiofield',
                            name:'addOrDel',
                            inputValue:'delFloors',
                            boxLabel:'批量删除宿舍楼',
                            margin:'0 0 0 30'
                        }]
                    },{
                        xtype:'fieldcontainer',
                        layout:'hbox',
                        width:300,
                        items:[{
                            xtype: 'textfield',
                            margin: '0 4 0 0',
                            labelWidth: 150,
                            fieldLabel: '输入你要删除的宿舍楼名称',
                            labelSeparator: '',//隐藏掉冒号：
                            width: 250,//初始值为250
                            // width: 190//一会设置为190
                            name:'delFloor'

                        },{
                            xtype:'textfield',
                            labelWidth:10,
                            fieldLabel:'一',
                            labelSeparator: '',//隐藏掉冒号：
                            width: 50,
                            hidden: true,
                            hideLabel:true,
                            name:'delFloors'
                        }]
                    },{
                        xtype:'fieldcontainer',
                        width:300,
                        height:30,
                        margin:'20 0 0 0',
                        layout:'hbox',
                        items:[{
                            xtype:'radiofield',
                            boxLabel:'添加宿舍',
                            name:'addOrDel',
                            inputValue:'addDormitory'
                        },{
                            xtype:'radiofield',
                            name:'addOrDel',
                            inputValue:'addDormitorys',
                            boxLabel:'批量添加宿舍',
                            margin:'0 0 0 30'
                        }]
                    },{
                        xtype:'fieldcontainer',
                        layout:'hbox',
                        width:300,
                        items:[{
                            xtype: 'textfield',
                            margin: '0 4 0 0',
                            labelWidth: 150,
                            fieldLabel: '输入你要添加的宿舍名称',
                            labelSeparator: '',//隐藏掉冒号：
                            width: 250,//初始值为250
                            // width: 190//一会设置为190
                            name:'addDormitory'

                        },{
                            xtype:'textfield',
                            labelWidth:10,
                            fieldLabel:'一',
                            labelSeparator: '',//隐藏掉冒号：
                            width: 50,
                            hidden: true,
                            hideLabel:true,
                            name:'addDormitorys'
                        }]
                    },{
                        xtype:'fieldcontainer',
                        width:300,
                        height:30,
                        margin:'20 0 0 0',
                        layout:'hbox',
                        items:[{
                            xtype:'radiofield',
                            boxLabel:'删除宿舍',
                            name:'addOrDel',
                            inputValue:'delDormitory'
                        },{
                            xtype:'radiofield',
                            name:'addOrDel',
                            inputValue:'delDormitorys',
                            boxLabel:'批量删除宿舍',
                            margin:'0 0 0 30'
                        }]
                    },{
                        xtype:'fieldcontainer',
                        layout:'hbox',
                        width:300,
                        items:[{
                            xtype: 'textfield',
                            margin: '0 4 0 0',
                            labelWidth: 150,
                            fieldLabel: '输入你要删除的宿舍名称',
                            labelSeparator: '',//隐藏掉冒号：
                            width: 250,//初始值为250
                            // width: 190//一会设置为190
                            name:'delDormitory'

                        },{
                            xtype:'textfield',
                            labelWidth:10,
                            fieldLabel:'一',
                            labelSeparator: '',//隐藏掉冒号：
                            width: 50,
                            hidden: true,
                            hideLabel:true,
                            name:'delDormitorys'
                        }]
                    }]
                });
                var newFloor=Ext.create('Ext.window.Window',{
                    title:'宿舍楼设置',
                    width:300,
                    height:400,
                    layout:'fit',
                    items:newFloorPanel,
                    bbar:[{
                        xtype: 'button',
                        text: '确定',
                        width: 50,
                        height: 30,
                        margin: '0 30 0 75',
                        handler:function () {
                            newFloorPanel.getForm().getFields().each(function(item){
                                // console.log(item);
                                if(item.checked){
                                    var fname;
                                    var dname;
                                    var valueOne;
                                    var valueTwo;
                                    var url;
                                    var fid;
                                    var go=false;
                                    var sel=item.inputValue;
                                    if(sel=='addFloor') {
                                        fname=newFloorPanel.getForm().findField('addFloor').getValue();
                                        if (fname!=""){
                                            go=true;
                                        }else{
                                            Ext.Msg.alert('提示信息','宿舍楼名不能为空！');
                                            return;
                                        }
                                        url='add_floor';
                                    }else if (sel=='addFloors') {
                                        valueOne=newFloorPanel.getForm().findField('addFloor').getValue();
                                        valueTwo=newFloorPanel.getForm().findField('addFloors').getValue();
                                        url='add_floor';
                                        var go=me.booleanLegal(0,valueOne,valueTwo);
                                    }else if (sel=='delFloor') {
                                        fname=newFloorPanel.getForm().findField('delFloor').getValue();
                                        if (fname!=""){
                                            go=true;
                                        }else{
                                            Ext.Msg.alert('提示信息','宿舍楼名不能为空！');
                                            return;
                                        }
                                        url='del_floor';
                                    }else if (sel == 'delFloors') {
                                        valueOne=newFloorPanel.getForm().findField('delFloor').getValue();
                                        valueTwo=newFloorPanel.getForm().findField('delFloors').getValue();
                                        url='del_floor';
                                        var go=me.booleanLegal(0,valueOne,valueTwo);
                                    }else if (sel == 'addDormitory') {
                                        dname=newFloorPanel.getForm().findField('addDormitory').getValue();
                                        var raw=Ext.getCmp('treep').getSelectionModel().getSelection();
                                        if(raw.length==0){
                                            Ext.Msg.alert('提示信息','请在左边选中你要添加的宿舍楼！');
                                            return;
                                        }else{
                                            fid=raw[0].raw.fid;
                                        }
                                        if (dname!=""){
                                            go=true;
                                        }else{
                                            Ext.Msg.alert('提示信息','宿舍名不能为空！');
                                            return;
                                        }
                                        url='add_dormitory';
                                    }else if (sel == 'addDormitorys') {
                                        valueOne=newFloorPanel.getForm().findField('addDormitory').getValue();
                                        valueTwo=newFloorPanel.getForm().findField('addDormitorys').getValue();
                                        url='add_dormitory';
                                        var raw=Ext.getCmp('treep').getSelectionModel().getSelection();
                                        if(raw.length==0){
                                            Ext.Msg.alert('提示信息','请在左边选中你要添加的宿舍楼！');
                                            return;
                                        }else{
                                            fid=raw[0].raw.fid;
                                        }
                                        var go=me.booleanLegal(1,valueOne,valueTwo);
                                    }else if (sel == 'delDormitory') {
                                        dname=newFloorPanel.getForm().findField('delDormitory').getValue();
                                        var raw=Ext.getCmp('treep').getSelectionModel().getSelection();
                                        if(raw.length==0){
                                            Ext.Msg.alert('提示信息','请在左边选中你要删除宿舍的宿舍楼！');
                                            return;
                                        }else{
                                            fid=raw[0].raw.fid;
                                        }
                                        if (dname!=""){
                                            go=true;
                                        }else{
                                            Ext.Msg.alert('提示信息','宿舍名不能为空！');
                                            return;
                                        }
                                        url='del_dormitory';
                                    }else if (sel == 'delDormitorys') {
                                        valueOne=newFloorPanel.getForm().findField('delDormitory').getValue();
                                        valueTwo=newFloorPanel.getForm().findField('delDormitorys').getValue();
                                        url='del_dormitory';
                                        var raw=Ext.getCmp('treep').getSelectionModel().getSelection();
                                        if(raw.length==0){
                                            Ext.Msg.alert('提示信息','请在左边选中你要删除宿舍的宿舍楼！');
                                            return;
                                        }else{
                                            fid=raw[0].raw.fid;
                                        }
                                        var go=me.booleanLegal(1,valueOne,valueTwo);
                                    }
                                    if(go){
                                        Ext.Ajax.request({
                                            url: '/dormitory/'+url,
                                            method:'POST',
                                            params: {
                                                fname:fname,
                                                dname:dname,
                                                start:valueOne,
                                                end:valueTwo,
                                                fid:fid,
                                            },
                                            success: function(response){
                                                var resp=Ext.decode(response.responseText);
                                                if(resp.state){
                                                    Ext.Msg.alert('提示信息',item.boxLabel.substring(0,2)+'成功！');
                                                    Ext.getCmp('treep').getStore().getProxy().extraParams.flag=0;
                                                    Ext.getCmp('treep').getStore().getProxy().extraParams.fid=0;
                                                    Ext.getCmp('treep').getStore().load();
                                                }else{
                                                    Ext.Msg.alert('提示信息',resp.message);
                                                }
                                            }
                                        });
                                    }
                                }
                            });
                            // console.log(newFloorPanel.getForm().getValues()["addOrDel"]);
                        }
                    },{
                        xtype:'button',
                        text:'取消',
                        width:50,
                        height:30,
                        handler:function () {
                            newFloor.close();
                        }
                    }],
                    listeners:{
                        afterrender:function () {
                            newFloorPanel.getForm().getFields().each(function(item){
                                var sel=item.inputValue;
                                if(sel){
                                    item.on('focus',function() {
                                        if(sel=='addFloor') {
                                            me.changeFieldHidden('addFloor');
                                        }else if (sel=='addFloors') {
                                            me.changeFieldShow('addFloors');
                                        }else if (sel=='delFloor') {
                                            me.changeFieldHidden('delFloor');
                                        }else if (sel == 'delFloors') {
                                            me.changeFieldShow('delFloors');
                                        }else if (sel == 'addDormitory') {
                                            me.changeFieldHidden('addDormitory');
                                        }else if (sel == 'addDormitorys') {
                                            me.changeFieldShow('addDormitorys');
                                        }else if (sel == 'delDormitory') {
                                            me.changeFieldHidden('delDormitory');
                                        }else if (sel == 'delDormitorys') {
                                            me.changeFieldShow('delDormitorys');
                                        }
                                    });
                                }
                            });
                        }
                    }
                });
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
     * 判断批量添加或者宿舍楼/宿舍是否合法
     * flag:0 宿舍楼   1 宿舍
     */
    booleanLegal:function(flag,valueOne,valueTwo){
        if (valueOne=="" || valueTwo==""){
            Ext.Msg.alert('提示信息','起始号码与结束号码不能为空');
            return;
        }
        //正则表达式可判断为空
        var regFloor = new RegExp('^[0-9]{1,2}$');
        var regDormitory=new RegExp('^[0-9]*$');
        if(flag==0 && (!regFloor.test(valueOne) || !regFloor.test(valueTwo))){
            Ext.Msg.alert('提示信息','批量操作请输入纯数字且最长2位数！');
            return;
        }else if(flag==1 && (!regDormitory.test(valueOne) || !regDormitory.test(valueTwo))){
            Ext.Msg.alert('提示信息','批量操作请输入纯数字！');
            return;
        }
        if(parseInt(valueOne)>parseInt(valueTwo)){
            Ext.Msg.alert('提示信息','起始号码不能大于结束号码！');
            return;
        }else {
            return true;
        }
    },
    /**
     *增加宿舍楼设置输入框隐藏
     */
    changeFieldHidden:function(name){
        Ext.getCmp('newFloorPanel').getForm().findField(name+'s').setVisible(false);
        Ext.getCmp('newFloorPanel').getForm().findField(name+'s').setFieldLabel('');
        Ext.getCmp('newFloorPanel').getForm().findField(name).setWidth(250);
    },
    /**
     * 增加宿舍楼设置输入框显示
     * @param name
     */
    changeFieldShow:function(name){
        Ext.getCmp('newFloorPanel').getForm().findField(name).setVisible(true);
        Ext.getCmp('newFloorPanel').getForm().findField(name).setFieldLabel('一');
        Ext.getCmp('newFloorPanel').getForm().findField(name.substring(0,name.length-1)).setWidth(190);
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
     * 判断是都存在操作权限权限
     */
    isControl:function (win) {
        Ext.Ajax.request({
            url: '/users/is_su',
            method:'POST',
            success: function(response){
                var resp = Ext.decode(response.responseText);
                if(!resp.state){
                    Ext.Msg.alert('提示信息',resp.message);
                    return;
                }else{
                    win.show();
                }
            }
        });
    }


});