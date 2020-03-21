Ext.define('Index.deleteDormitorysDisciplinary', {
    extend: 'Ext.window.Window',
    xtype: 'deleteDormitorysDisciplinary',
    layout: 'fit',
    width: 300,
    height: 150,
    title:'批量删除宿舍违纪',
    initComponent: function () {
        var me = this;
        me.callParent();
        me.loadComponent();
    },
    loadComponent:function () {
        var me =this;
        var startTime = Ext.widget('datefield',{
            fieldLabel: '开始时间',
            name: 'startTime',
            maxValue: new Date(),
            allowBlank:false,
            format:'Y-m-d',
            labelWidth:80
        });
        var endTime = Ext.widget('datefield',{
            fieldLabel: '结束时间',
            name: 'endTime',
            maxValue: new Date(),
            allowBlank:false,
            format:'Y-m-d',
            labelWidth:80
        });
        var formPanel = Ext.create('Ext.form.Panel',{
            bodyPadding:'10 10',
            layout:'vbox',
            items:[startTime,endTime],
            url:'/dormitory/deleteDormitorysDisciplinary',
            buttons:[{
                text: '确定删除',
                handler: function() {
                    var form = this.up('form').getForm();
                    var date = form.getValues();
                    if (form.isValid()) {
                        if(date.startTime>date.endTime){
                            Ext.Msg.alert('提示信息','开始时间不能大于结束时间!');
                        }else{
                            form.submit({
                                success: function (form, action) {
                                    Ext.Msg.alert('提示信息', '删除成功!');
                                    me.close();
                                }
                            });
                        }
                    }
                }
            }, {
                text: '取消',
                margin:'0 70 0 5',
                handler: function() {
                    me.close();
                }}]
        });
    me.add(formPanel);
    }
});