/**
 * 批量删除毕业生面板
 */
Ext.define('Index.DeleteStudents', {
    extend: 'Ext.window.Window',
    xtype: 'deleteStudents',
    width: 300,
    height: 130,
    layout: 'fit',
    title: '批量删除毕业生',
    initComponent: function () {
        var me = this;
        me.callParent();
        me.loadData();
    },
    loadData:function(){
        var me= this;
        Ext.Ajax.request({
            url: '/student/getAllGrade',
            success: function(response){
                var grades = Ext.decode(response.responseText);
                me.grades = grades.data;
                if(me.grades.length == 0){
                    me.close();
                    Ext.Msg.alert('提示信息','没有相应年级！');
                }else{
                    me.loadComp();
                }
            }
        });
    },
    loadComp:function () {
        var me = this;
        var formPanel = Ext.create('Ext.form.Panel',{
            url: '/student/deleteStudents',
            layout:'hbox',
            bodyPadding:'20 30',
            buttons:[{
                text: '确定删除',
                handler: function() {
                    var form = this.up('form').getForm();
                    if(Object.keys(form.getValues()).length == 0){
                        Ext.Msg.alert('提示信息','请选择年级！');
                        return;
                    }
                    if (form.isValid()) {
                        form.submit({
                            success: function (form, action) {
                                Ext.Msg.alert('提示信息', '删除成功');
                                me.close();
                            }
                        });
                    }
                }
            }, {
                text: '取消',
                margin:'0 70 0 5',
                handler: function() {
                    me.close();
                }}]
         });
        for(var i=0;i<me.grades.length;i++){
            formPanel.add(Ext.widget('radio',{
                boxLabel:me.grades[i]+'级',
                name:'grade',
                allowBlank:false,
                inputValue:me.grades[i]
            }));
        }
        me.add(formPanel);
    }
});