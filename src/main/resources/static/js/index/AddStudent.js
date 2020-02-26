/**
 * 增加学生窗口
 */
Ext.define('Index.AddStudent', {
    extend: 'Ext.window.Window',
    layout:'fit',
    title:'添加学生',
    width:250,
    height:250,
    did:null,
    initComponent: function () {
        var me = this;
        me.callParent();
        me.loadData();
        me.loadComp();
    },
    loadData:function () {
        //TODO 将专业班级设置为下拉框
    },
    loadComp:function () {
        var me=this;
        var studentForm=Ext.create('Ext.form.Panel',{
            bodyPadding:10,
            url:'/student/addStudent/'+me.did,
            layoout:'anchor',
            defaults:{
                anchor:'100%',
                allowBlank:false,
                labelWidth:30
            },
            defaultType:'textfield',
            items:[{
                fieldLabel:'姓名',
                name:'sname'
            },{
                fieldLabel:'学号',
                name:'sid'
            },{
                fieldLabel:'电话',
                name:'sphone'
            },{
                fieldLabel:'性别',
                xtype:'combo',
                queryMode:'local',
                displayField:'ssex',
                valueField:'ssex',
                store:{
                    xtype:'store',
                    fields:['ssex'],
                    data:[{'ssex':'男'},{'ssex':'女'}]
                },
                name:'ssex'
            },{
                fieldLabel:'专业',
                name:'sprofessional'
            },{
                fieldLabel:'班级',
                name:'sclass'
            }],
            buttons:[{
                text:'确定',
                handler:function () {
                    var form=this.up('form').getForm();
                    if(form.isValid()){
                        form.submit({
                           success:function (form,action) {
                               Ext.Msg.alert('提示信息','添加成功');
                               Ext.getCmp('studentGrid'+me.did).getStore().load();
                           } ,
                            failure:function (form, action) {
                                Ext.Msg.alert('提示信息',action.result.message);
                            }
                        });
                    }
                }
            },{
                text:'取消',
                margin:'0 35 0 5',
                handler:function () {
                    me.close();
                }
            }]
        });
        me.add(studentForm);
    }
});