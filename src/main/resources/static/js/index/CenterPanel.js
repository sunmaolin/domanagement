Ext.define('Index.CenterPanel', {
    extend: 'Ext.panel.Panel',
    requires:['Index.StudentsInfo','Index.RandomDutyPanel'],
    xtype: 'centerPanel',
    closable:true,
    layout:'vbox',
    did:null,
    initComponent: function () {
        var me = this;
        me.callParent();
        me.loadData();
        me.loadComponent();
    },
    loadComponent:function(){
        var me=this;
        me.add(Ext.create('Index.StudentsInfo',{did:me.did}));
        me.add(Ext.create('Index.RandomDutyPanel',{did:me.did}));
    },
    loadData: function () {

    }
});