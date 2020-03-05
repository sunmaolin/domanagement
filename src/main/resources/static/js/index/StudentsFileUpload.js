/**
 * 学生批量上传
 */
Ext.define('Index.StudentsFileUpload', {
    extend: 'Ext.window.Window',
    xtype: 'studentsFileUpload',
    width:300,
    height:130,
    layout: 'fit',
    title:'学生批量上传',
    initComponent: function () {
        var me = this;
        me.callParent();
        me.loadData();
        me.loadComp();
    },
    loadData: function () {

    },
    loadComp:function () {
        var me = this;
        var fileUpload = Ext.create('Ext.form.field.File',{
           name:'studentsFile',
           fieldLabel:'学生excel',
           labelWidth:70,
           allowBlank:false,
           buttonText:'选择excel表格'
        });
        var formPanel=Ext.create('Ext.form.Panel',{
            bodyPadding:'10 0 0 10',
            items:[fileUpload],
            buttons:[{
                text:'确定',
                handler:function () {
                    me.upload();
                }
            },{
                text:'取消',
                margin:'0 60 0 10',
                handler:function () {
                    me.close();
                }
            }]
        });
        me.add(formPanel);
    },
    upload:function () {
        var form = this.down('form').getForm();
        if(form.isValid()){
            form.submit({
                url:'/student/addStudents',
                waitMsg:'正在上传',
                success:function (form,action) {
                    Ext.Msg.alert('提示信息','上传成功！');
                },
                failure:function (form,action) {
                    Ext.Msg.alert('提示信息',action.result.message);
                }
            })
        }
    }
});