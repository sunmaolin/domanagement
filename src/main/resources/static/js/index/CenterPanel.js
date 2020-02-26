Ext.define('Index.CenterPanel', {
    extend: 'Ext.panel.Panel',
    requires:['Index.StudentsInfo','Index.RandomDutyPanel','Index.DormitoryDisciplinaryPanel','Index.DormitoryMaintainPanel'],
    xtype: 'centerPanel',
    closable:true,
    layout:{
        type:'table',
        columns:2
    },
    did:null,
    autoScroll:true,
    id:'centerPanel'+this.did,
    initComponent: function () {
        var me = this;
        me.callParent();
        me.loadComponent();
    },
    loadComponent:function(){
        var me=this;
        me.add(Ext.create('Index.StudentsInfo',{did:me.did}));
        me.add(Ext.create('Index.DormitoryDisciplinaryPanel',{did:me.did}));
        me.add(Ext.create('Index.RandomDutyPanel',{did:me.did}));
        me.add(Ext.create('Index.DormitoryMaintainPanel',{did:me.did}));
        me.add({
           xtype:'button',
           margin:'5 0 0 5',
           width:436,
           height:165,
           text:'电费余额实时查询',
           handler:function () {
               Ext.Msg.alert('提示信息','待开发');
           }
        });
    }
});