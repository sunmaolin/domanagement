/**
 * 增加导员窗口
 */
Ext.define('Index.AddGuide', {
    extend: 'Ext.window.Window',
    xtype:'addGuide',
    layout:'fit',
    title:'添加导员',
    width:250,
    height:240,
    initComponent: function () {
        var me = this;
        me.callParent();
        me.loadData();
        me.loadComp();
    },
    loadData:function () {

    },
    loadComp:function () {
        var me=this;
        var guideForm=Ext.create('Ext.form.Panel',{
            bodyPadding:10,
            url:'/guide/addGuide',
            layoout:'anchor',
            defaults:{
                anchor:'100%',
                allowBlank:false,
                labelWidth:30
            },
            defaultType:'textfield',
            items:[{
                fieldLabel:'姓名',
                name:'gname'
            },{
                fieldLabel:'电话',
                name:'gphone'
            },{
                fieldLabel:'性别',
                xtype:'combo',
                queryMode:'local',
                displayField:'gsex',
                valueField:'gsex',
                store:{
                    xtype:'store',
                    fields:['gsex'],
                    data:[{'gsex':'男'},{'gsex':'女'}]
                },
                name:'gsex'
            },{
                fieldLabel:'专业',
                name:'sprofessional',
                emptyText:'填写所属学院'
            },{
                fieldLabel:'班级',
                name:'sclass',
                emptyText:'填写所属年级'
            }],
            buttons:[{
                text:'确定',
                handler:function () {
                    var form=this.up('form').getForm();
                    if(form.isValid()){
                        form.submit({
                           success:function (form,action) {
                               Ext.Msg.alert('提示信息','添加成功');
                               //清空文本框
                               form.reset();
                               Ext.getCmp('guideGrid').getStore().load();
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
        me.add(guideForm);
    }
});