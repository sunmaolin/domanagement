/**
 * 宿舍维修记录设计
 */
Ext.define('Index.DormitoryMaintainPanel', {
    extend: 'Ext.container.Container',
    xtype: 'dormitoryMaintainPanel',
    did:null,
    layout:'fit',
    margin:'5 0 0 0',
    initComponent: function () {
        var me = this;
        me.callParent();
        me.loadData();
        me.loadComp();
    },
    loadData:function() {
        var me = this;
        me.maintainStore=Ext.create('Ext.data.Store',{
            fields:['mid','did','content','createUser','createTime','remark','maintainTime','maintainUser'],
            proxy: {
                type: 'ajax',
                url: '/dormitory/findDormitoryMaintain/'+me.did,
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
            title:'宿舍维修记录',
            scroll:true,
            height:165,
            width:700,
            columns:[
                {header:'维修内容',dataIndex:'content',width:150},
                {header:'登记时间',dataIndex:'createTime'},
                {header:'登记人',dataIndex:'createUser'},
                {header:'备注',dataIndex:'remark',width:136},
                {header:'维修人',dataIndex:'maintainUser'},
                {header:'维修时间',dataIndex:'maintainTime'}
            ],
            store:me.maintainStore
        }));
    }
});