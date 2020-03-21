/**
 * 学生信息设计
 */
Ext.define('Index.StudentsInfo', {
    extend: 'Ext.container.Container',
    xtype: 'studentsInfo',
    requires:['Index.AddStudent'],
    did:null,
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
            id:'studentGrid'+me.did,
            width:705,
            height:246,
            columns:[
                {header:'姓名',dataIndex:'sname'},
                {header:'学号',dataIndex:'sid'},
                {header:'性别',dataIndex:'ssex'},
                {header:'电话',dataIndex:'sphone'},
                {header:'专业',dataIndex:'sprofessional'},
                {header:'班级',dataIndex:'sclass'},
                {header:'个人违纪情况',renderer:function () {return '<a href="javascript:void(0)">查看</a>'}}
            ],
            store:me.randomDutyStore,
            listeners:{
                cellclick:function (view,td,index,record) {
                    if(index==6){
                        me.personalDisciplinary(record.data.sid,record.data.sname);
                    }
                }
            },
            bbar:[{
                xtype:'button',
                text:'添加学生',
                handler:function () {
                    me.addStudent();
                }
            },{
                xtype:'button',
                text:'删除学生',
                handler:function () {
                    var selected=this.up('grid').getSelectionModel().selected.items[0];
                    me.deleteStudent(selected);
                }
            }]
        }));
    },
    personalDisciplinary:function(sid,sname){
      Ext.create('Ext.window.Window',{
          title:sname+'违纪情况一览',
          width:415,
          height:200,
          layout:'fit',
          items:[{
            xtype:'grid',
            columns:[
                  {header:'违纪内容',dataIndex:'content'},
                  {header:'违纪时间',dataIndex:'createTime'},
                  {header:'记录人',dataIndex:'createUser'},
                  {header:'图片',renderer:function (value,record,item) {
                      if (item.data.image) {
                          return '<a href="'+window.document.location.href+item.data.image+'" target="_blank">查看违纪图片</a>';
                      // return '<a href="javascript:void(0)" onclick="javascript:{window.open(\'localhost:8080/images/'+item.data.image+'\');}">查看违纪图片</a>';
                      }else{
                          return '<a>无违纪图片</a>';
                      }
                    }
                  }
              ],
            store:{
                fields:['pid','fid','sid','content','createUser','createTime','image'],
                proxy: {
                    type: 'ajax',
                    url: '/student/findPersonalDisciplinary/'+sid,
                    actionMethods:{
                        create:'GET'
                    },
                    reader: {
                        type: 'json',
                        root: 'data'
                    }
                },
                autoLoad: true
            }
          }]
      }).show();
    },
    addStudent:function () {
        Ext.getCmp('fun').isControl(Ext.create('Index.AddStudent',{did:this.did}));
    },
    deleteStudent:function (selected) {
        // console.log(this.down('grid').getSelectionModel().selected.items[0].data);
        var grid=this.down('grid');
        Ext.getCmp('fun').isControl('',function () {
            if (!selected){
                Ext.Msg.alert('提示信息','请在表格中选中学生进行删除！');
                return;
            }else {
                Ext.Ajax.request({
                    url:'/student/deleteStudent/'+selected.data.sid,
                    method:'GET',
                    success:function (response) {
                        var resp=Ext.decode(response.responseText);
                        if(resp.state){
                            Ext.Msg.alert('提示信息','删除成功！');
                            grid.getStore().load();
                        }else{
                            Ext.Msg.alert('提示信息',resp.message);
                        }
                    }
                });
            }
        });
    }
});