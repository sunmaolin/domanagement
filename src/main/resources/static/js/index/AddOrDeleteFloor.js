/**
 * 宿舍楼设置
 */
Ext.define('Index.AddOrDeleteFloor', {
    extend: 'Ext.window.Window',
    xtype: 'AddOrDeleteFloor',
    id: 'AddOrDeleteFloor',
    title:'宿舍楼设置',
    width:300,
    height:400,
    layout:'fit',
    initComponent: function () {
        this.callParent();
        this.loadComp();
        this.loadData();
    },
    loadData: function () {

    },
    loadComp: function () {
        var me=this;
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
        me.add(newFloorPanel);
    },
    bbar:[{
        xtype: 'button',
        text: '确定',
        width: 50,
        height: 30,
        margin: '0 30 0 75',
        handler:function () {
            Ext.getCmp('newFloorPanel').getForm().getFields().each(function(item){
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
                        fname=Ext.getCmp('newFloorPanel').getForm().findField('addFloor').getValue();
                        if (fname!=""){
                            go=true;
                        }else{
                            Ext.Msg.alert('提示信息','宿舍楼名不能为空！');
                            return;
                        }
                        url='add_floor';
                    }else if (sel=='addFloors') {
                        valueOne=Ext.getCmp('newFloorPanel').getForm().findField('addFloor').getValue();
                        valueTwo=Ext.getCmp('newFloorPanel').getForm().findField('addFloors').getValue();
                        url='add_floor';
                        var go=Ext.getCmp('AddOrDeleteFloor').booleanLegal(0,valueOne,valueTwo);
                    }else if (sel=='delFloor') {
                        fname=Ext.getCmp('newFloorPanel').getForm().findField('delFloor').getValue();
                        if (fname!=""){
                            go=true;
                        }else{
                            Ext.Msg.alert('提示信息','宿舍楼名不能为空！');
                            return;
                        }
                        url='del_floor';
                    }else if (sel == 'delFloors') {
                        valueOne=Ext.getCmp('newFloorPanel').getForm().findField('delFloor').getValue();
                        valueTwo=Ext.getCmp('newFloorPanel').getForm().findField('delFloors').getValue();
                        url='del_floor';
                        var go=Ext.getCmp('AddOrDeleteFloor').booleanLegal(0,valueOne,valueTwo);
                    }else if (sel == 'addDormitory') {
                        dname=Ext.getCmp('newFloorPanel').getForm().findField('addDormitory').getValue();
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
                        valueOne=Ext.getCmp('newFloorPanel').getForm().findField('addDormitory').getValue();
                        valueTwo=Ext.getCmp('newFloorPanel').getForm().findField('addDormitorys').getValue();
                        url='add_dormitory';
                        var raw=Ext.getCmp('treep').getSelectionModel().getSelection();
                        if(raw.length==0){
                            Ext.Msg.alert('提示信息','请在左边选中你要添加的宿舍楼！');
                            return;
                        }else{
                            fid=raw[0].raw.fid;
                        }
                        var go=Ext.getCmp('AddOrDeleteFloor').booleanLegal(1,valueOne,valueTwo);
                    }else if (sel == 'delDormitory') {
                        dname=Ext.getCmp('newFloorPanel').getForm().findField('delDormitory').getValue();
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
                        valueOne=Ext.getCmp('newFloorPanel').getForm().findField('delDormitory').getValue();
                        valueTwo=Ext.getCmp('newFloorPanel').getForm().findField('delDormitorys').getValue();
                        url='del_dormitory';
                        var raw=Ext.getCmp('treep').getSelectionModel().getSelection();
                        if(raw.length==0){
                            Ext.Msg.alert('提示信息','请在左边选中你要删除宿舍的宿舍楼！');
                            return;
                        }else{
                            fid=raw[0].raw.fid;
                        }
                        var go=Ext.getCmp('AddOrDeleteFloor').booleanLegal(1,valueOne,valueTwo);
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
            Ext.getCmp('AddOrDeleteFloor').close();
        }
    }],
    listeners:{
        afterrender:function () {
            Ext.getCmp('newFloorPanel').getForm().getFields().each(function(item){
                var sel=item.inputValue;
                if(sel){
                    item.on('focus',function() {
                        if(sel=='addFloor') {
                            Ext.getCmp('AddOrDeleteFloor').changeFieldHidden('addFloor');
                        }else if (sel=='addFloors') {
                            Ext.getCmp('AddOrDeleteFloor').changeFieldShow('addFloors');
                        }else if (sel=='delFloor') {
                            Ext.getCmp('AddOrDeleteFloor').changeFieldHidden('delFloor');
                        }else if (sel == 'delFloors') {
                            Ext.getCmp('AddOrDeleteFloor').changeFieldShow('delFloors');
                        }else if (sel == 'addDormitory') {
                            Ext.getCmp('AddOrDeleteFloor').changeFieldHidden('addDormitory');
                        }else if (sel == 'addDormitorys') {
                            Ext.getCmp('AddOrDeleteFloor').changeFieldShow('addDormitorys');
                        }else if (sel == 'delDormitory') {
                            Ext.getCmp('AddOrDeleteFloor').changeFieldHidden('delDormitory');
                        }else if (sel == 'delDormitorys') {
                            Ext.getCmp('AddOrDeleteFloor').changeFieldShow('delDormitorys');
                        }
                    });
                }
            });
        }
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
    }
});