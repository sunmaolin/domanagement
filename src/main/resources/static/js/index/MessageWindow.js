Ext.define('Index.MessageWindow', {
    extend: 'Ext.window.Window',
    xtype: 'messageWindow',
    layout: 'fit',
    width: 500,
    height: 400,
    title:'意见留言查看',
    initComponent: function () {
        var me = this;
        me.callParent();
        me.loadData();
        me.loadComponent();
    },
    loadData: function () {
        var me=this;
        me.messageStore=Ext.create('Ext.data.Store',{
            fields:['messageid','opinion','createTime'],
            proxy:{
                url:'/student/findAllMessage',
                type: 'ajax',
                method:'GET',
                reader: {
                    type: 'json',
                    root: 'data'
                }
            },
            autoLoad:true
        });
    },
    loadComponent:function () {
        var me=this;
        me.add(Ext.create('Ext.grid.Panel',{
            scroll:true,
            columns:[
                {header:'建议',dataIndex:'opinion',width:370},
                {header:'留言时间',dataIndex:'createTime'}
            ],
            store:me.messageStore
        }));
    }
});