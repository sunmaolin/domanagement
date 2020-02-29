/**
 * 宿舍违纪信息设计
 */
Ext.define('Index.DormitoryDisciplinaryPanel', {
    extend: 'Ext.container.Container',
    xtype: 'dormitoryDisciplinaryPanel',
    did:null,
    layout:'fit',
    margin:'0 0 0 5',
    rowspan:2,
    initComponent: function () {
        var me = this;
        me.callParent();
        me.loadData();
        me.loadComp();
    },
    loadData:function(){
        var me = this;
        me.disciplinaryStore=Ext.create('Ext.data.Store',{
            fields:['pid','did','content','createUser','createTime','image'],
            proxy: {
                type: 'ajax',
                url: '/dormitory/findDormitoryDisciplinary/'+me.did,
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
            title:'宿舍违纪情况',
            scroll:true,
            height:323,
            width:436,
            columns:[
                {header:'违纪内容',dataIndex:'content',width:117},
                {header:'违纪时间',dataIndex:'createTime'},
                {header:'记录人',dataIndex:'createUser'},
                {header:'图片',renderer:function (value,record,item) {
                    if (item.data.image){
                        return '<a href="'+window.document.location.href+item.data.image+'" target="_blank">查看违纪图片</a>';
                    } else {
                        return '<a>无违纪图片</a>';
                    }
                  }
                }
            ],
            store:me.disciplinaryStore
        }));
    }
});