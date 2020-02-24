Ext.define('Index.StudentsInfo', {
    extend: 'Ext.container.Container',
    xtype: 'studentsInfo',
    did:null,
    initComponent: function () {
        var me = this;
        me.callParent();
        me.loadData();
        me.loadComp();
    },
    loadData:function(){
        var me = this;
        me.randomDutyStore=Ext.create('Ext.data.Store',{
            fields:['sname','sid','ssex','sphone','sprofessional','sclass'],
            proxy: {
                type: 'ajax',
                url: '/student/find/'+me.did,
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
    loadComp:function(){
        var me=this;
        me.add(Ext.create('Ext.grid.Panel',{
            title:'个人信息',
            width:600,
            columns:[
                {header:'姓名',dataIndex:'sname'},
                {header:'学号',dataIndex:'sid'},
                {header:'性别',dataIndex:'ssex'},
                {header:'电话',dataIndex:'sphone'},
                {header:'专业',dataIndex:'sprofessional'},
                {header:'班级',dataIndex:'sclass'}
            ],
            store:me.randomDutyStore,
            bbar:[{
                xtype:'button',
                text:'添加学生',
                handler:function () {
                    me.addStudent();
                }
            }]
        }));
    },
    addStudent:function () {
        alert(1);
    }
});