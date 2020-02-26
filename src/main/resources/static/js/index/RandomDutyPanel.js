/**
 * 宿舍值日表设计
 */
Ext.define('Index.RandomDutyPanel', {
    extend: 'Ext.container.Container',
    xtype: 'randomDutyPanel',
    did:null,
    margin:'5 0 0 0',
    layout:'fit',
    initComponent: function () {
        var me = this;
        me.callParent();
        me.loadData();
        me.loadComp();
    },
    loadData:function(){
        var me = this;
        me.randomDutyStore=Ext.create('Ext.data.Store',{
            fields:['did','monday','tuesday','wednesday','thursday','friday','saturday','sunday'],
            proxy: {
                type: 'ajax',
                url: '/student/findRandomDuty/'+me.did,
                actionMethods:{
                    create:'GET'
                },
                reader: {
                    type: 'json',
                    root: 'data'
                }
            },
            autoLoad: true
        });
    },
    loadComp:function () {
        var me=this;
        me.add(Ext.create('Ext.grid.Panel',{
            title:'周值日表',
            width:700,
            height:72,
            columns:[
                {header:'周一',dataIndex:'monday'},
                {header:'周二',dataIndex:'tuesday'},
                {header:'周三',dataIndex:'wednesday'},
                {header:'周四',dataIndex:'thursday'},
                {header:'周五',dataIndex:'friday'},
                {header:'周六',dataIndex:'saturday'},
                {header:'周日',dataIndex:'sunday'}
            ],
            store:me.randomDutyStore
        }));
    }
});